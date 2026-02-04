package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.SiteStatus;
import Entity.SiteType;
import Utility.MaintenanceUtil;
import Utils.DBConnection;

public class AdminService {

 public void addSite(
        SiteType type,
        int length,
        int width,
        SiteStatus status,
        int ownerId
) throws Exception {

    String sql =
        "INSERT INTO sites(site_type, length, width, status, owner_id) " +
        "VALUES (?, ?, ?, ?, ?)";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, type.name());
        ps.setInt(2, length);
        ps.setInt(3, width);
        ps.setString(4, status.name());

        if (ownerId <= 0)
            ps.setNull(5, java.sql.Types.INTEGER);
        else
            ps.setInt(5, ownerId);

        ps.executeUpdate();
        System.out.println("Site added successfully");

        viewAllSites(con);
    }
}

  
public void addOwnerWithSitePreference(
        int ownerId,
        String ownerName,
        SiteType siteType
)
 throws Exception {
       try (Connection con = DBConnection.getConnection()) {
    con.setAutoCommit(false);

    try {
       
        try (PreparedStatement psOwner = con.prepareStatement(
            "INSERT INTO users(user_id, name, role) VALUES (?, ?, 'OWNER')")) {
            psOwner.setInt(1, ownerId);
            psOwner.setString(2, ownerName);
            psOwner.executeUpdate();
        }

       
        int siteId;
       try (PreparedStatement psSite = con.prepareStatement(
    "SELECT site_id FROM sites WHERE owner_id IS NULL FOR UPDATE LIMIT 1"
)) {
    try (ResultSet rs = psSite.executeQuery()) {
        if (!rs.next()) {
            System.out.println("No free site available");
            con.rollback();
            return;
        }
        siteId = rs.getInt("site_id");
    }
}


        try (PreparedStatement psAssign = con.prepareStatement(
            "UPDATE sites SET owner_id = ?, status = 'OCCUPIED' WHERE site_id = ?")) {
            psAssign.setInt(1, ownerId);
            psAssign.setInt(2, siteId);
            psAssign.executeUpdate();
        }

        int length, width;
        SiteStatus status;
        try (PreparedStatement psDim = con.prepareStatement(
            "SELECT length, width, status FROM sites WHERE site_id = ?")) {

            psDim.setInt(1, siteId);

            try (ResultSet rs = psDim.executeQuery()) {
                rs.next();
                length = rs.getInt("length");
                width  = rs.getInt("width");
                status = SiteStatus.valueOf(rs.getString("status"));
            }
        }

        int amount = MaintenanceUtil.calculate(length, width, status);

        try (PreparedStatement psMaint = con.prepareStatement(
            "INSERT INTO maintenance(site_id, owner_id, amount, paid) VALUES (?, ?, ?, false)")) {
            psMaint.setInt(1, siteId);
            psMaint.setInt(2, ownerId);
            psMaint.setInt(3, amount);
            psMaint.executeUpdate();
        }

        con.commit();
        System.out.println("Owner added & site assigned :" + siteId);

    } catch (Exception e) {
        con.rollback();
        throw e;
    }
}

    }

   
    public void editOwner(int id, String name) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE users SET name = ? WHERE user_id = ? AND role = 'OWNER'")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Owner updated");
            } else {
                System.out.println("No owner found with id " + id);
            }
        }
    }

 
    public void removeOwner(int id) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                try (PreparedStatement psMaint = con.prepareStatement(
                        "DELETE FROM maintenance WHERE owner_id = ?")) {
                    psMaint.setInt(1, id);
                    psMaint.executeUpdate();
                }

              
                try (PreparedStatement psSites = con.prepareStatement(
                        "UPDATE sites SET owner_id = NULL, status = 'FREE' WHERE owner_id = ?")) {
                    psSites.setInt(1, id);
                    psSites.executeUpdate();
                }

                try (PreparedStatement psOwner = con.prepareStatement(
                        "DELETE FROM users WHERE user_id = ? AND role = 'OWNER'")) {
                    psOwner.setInt(1, id);
                    psOwner.executeUpdate();
                }

                con.commit();
                System.out.println("Owner removed successfully");
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

  
    public void editSite(int siteId, String status, int len, int wid) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE sites SET status = ?, length = ?, width = ? WHERE site_id = ?")) {
            ps.setString(1, status);
            ps.setInt(2, len);
            ps.setInt(3, wid);
            ps.setInt(4, siteId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Site updated");
            } else {
                System.out.println("No site found with id " + siteId);
            }

            // Show updated site
            try (PreparedStatement view = con.prepareStatement("SELECT site_id, site_type, status FROM sites WHERE site_id = ?")) {
                view.setInt(1, siteId);
                try (ResultSet rs = view.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("site_id") + " " +
                                        rs.getString("site_type") + " " +
                                        rs.getString("status")
                        );
                    }
                }
            }
        }
    }

  
    public void removeSite(int siteId) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            try (PreparedStatement ps2 = con.prepareStatement(
                    "DELETE FROM maintenance WHERE site_id = ?")) {
                ps2.setInt(1, siteId);
                ps2.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM sites WHERE site_id = ?")) {
                ps.setInt(1, siteId);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Site removed");
                } else {
                    System.out.println("No site found with id " + siteId);
                }
            }

            try (Connection con2 = DBConnection.getConnection()) {
                viewAllSites(con2);
            }
        }
    }

  
    public void generateMaintenance() throws Exception {
        String selectSql = "SELECT site_id, length, width, status, owner_id FROM sites WHERE owner_id IS NOT NULL";
        String insertSql = "INSERT INTO maintenance(site_id, owner_id, amount, paid) VALUES (?, ?, ?, false)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement psSelect = con.prepareStatement(selectSql);
             PreparedStatement psInsert = con.prepareStatement(insertSql);
             ResultSet rs = psSelect.executeQuery()) {

            con.setAutoCommit(false);

            try {
                while (rs.next()) {
                    int siteId = rs.getInt("site_id");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
SiteStatus status = SiteStatus.valueOf(rs.getString("status"));
                    int ownerId = rs.getInt("owner_id");


int amount = MaintenanceUtil.calculate(length, width, status);
                    psInsert.setInt(1, siteId);
                    psInsert.setInt(2, ownerId);
                    psInsert.setInt(3, amount);
                    psInsert.executeUpdate();
                }

                con.commit();
                System.out.println("Maintenance generated");
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

   
    public void collectMaintenance(int siteId) throws Exception {
        String updateSql = "UPDATE maintenance SET paid = true WHERE site_id = ?";
        String viewSql = "SELECT site_id, amount, paid FROM maintenance WHERE site_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql)) {
            ps.setInt(1, siteId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Maintenance collected");
            } else {
                System.out.println("No maintenance record found for site " + siteId);
            }

            try (PreparedStatement view = con.prepareStatement(viewSql)) {
                view.setInt(1, siteId);
                try (ResultSet rs = view.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(
                                "Site: " + rs.getInt(1) +
                                        " Amount: " + rs.getInt(2) +
                                        " Paid: " + rs.getBoolean(3)
                        );
                    }
                }
            }
        }
    }

   
    public void viewAllPendingMaintenance() throws Exception {
        String sql = "SELECT site_id, amount FROM maintenance WHERE paid = false";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Site " + rs.getInt(1) + " Pending: " + rs.getInt(2));
            }
            if (!found) {
                System.out.println("No pending maintenance");
            }
        }
    }

    public void viewPendingBySite(int siteId) throws Exception {
        String sql = "SELECT amount FROM maintenance WHERE site_id = ? AND paid = false";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, siteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Pending Amount: " + rs.getInt(1));
                } else {
                    System.out.println("No pending amount");
                }
            }
        }
    }

   
    public void approveRequest(int requestId) throws Exception {
        updateRequestStatus(requestId, "APPROVED");
    }

    public void rejectRequest(int requestId) throws Exception {
        updateRequestStatus(requestId, "REJECTED");
    }

    private void updateRequestStatus(int requestId, String status) throws Exception {
        String updateSql = "UPDATE owner_site_update_requests SET approval_status = ? WHERE request_id = ?";
        String selectSql = "SELECT request_id, site_id, approval_status FROM owner_site_update_requests WHERE request_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql)) {
            ps.setString(1, status);
            ps.setInt(2, requestId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Request " + status.toLowerCase());
            } else {
                System.out.println("No request found with id " + requestId);
            }

            try (PreparedStatement show = con.prepareStatement(selectSql)) {
                show.setInt(1, requestId);
                try (ResultSet rs = show.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(
                                "Request ID: " + rs.getInt("request_id") +
                                        " | Site ID: " + rs.getInt("site_id") +
                                        " | Status: " + rs.getString("approval_status")
                        );
                    }
                }
            }
        }
    }

 
    private void viewAllSites(Connection con) throws Exception {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT site_id, site_type, length, width, status, owner_id FROM sites");
             ResultSet rs = ps.executeQuery()) {

            System.out.println("---- SITES ----");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("site_id") + " | " +
                                rs.getString("site_type") + " | " +
                                rs.getInt("length") + "x" + rs.getInt("width") + " | " +
                                rs.getString("status") + " | Owner: " + rs.getObject("owner_id")
                );
            }
        }
    }

   public void showRequestAll() throws Exception {

    String sql = "SELECT request_id, site_id, new_status, approval_status " +
                 "FROM owner_site_update_requests";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        boolean found = false;

        System.out.println("---- UPDATE REQUESTS ----");

        while (rs.next()) {
            found = true;
            System.out.println(
                "Request ID: " + rs.getInt("request_id") +
                " | Site ID: " + rs.getInt("site_id") +
                " | New Status: " + rs.getString("new_status") +
                " | Approval: " + rs.getString("approval_status")
            );
        }

        if (!found) {
            System.out.println("No update requests found");
        }
    }
}

}