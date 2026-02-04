package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.Site;
import Entity.SiteStatus;
import Entity.SiteType;
import Utility.MaintenanceUtil;
import Utils.DBConnection;

public class SiteDAOImpl implements SiteDAO {

   
@Override
public void addOwnerWithSitePreference(int ownerId, String ownerName, SiteType siteType) throws Exception {

    try (Connection con = DBConnection.getConnection()) {
        con.setAutoCommit(false);

        try {
            // Insert owner
            try (PreparedStatement psOwner = con.prepareStatement(
                    "INSERT INTO users(user_id, name, role) VALUES (?, ?, 'OWNER')")) {
                psOwner.setInt(1, ownerId);
                psOwner.setString(2, ownerName);
                psOwner.executeUpdate();
            }

            int siteId;

            // Find free site
            try (PreparedStatement psSite = con.prepareStatement(
                    "SELECT site_id FROM sites WHERE owner_id IS NULL FOR UPDATE LIMIT 1");
                 ResultSet rs = psSite.executeQuery()) {

                if (!rs.next()) {
                    System.out.println("No free site available");
                    con.rollback();
                    return;
                }
                siteId = rs.getInt("site_id");
            }

            // Assign site
           try (PreparedStatement psAssign = con.prepareStatement(
        "UPDATE sites SET owner_id = ?, site_type = ?, status = 'OCCUPIED' WHERE site_id = ?")) {

    psAssign.setInt(1, ownerId);
    psAssign.setString(2, siteType.name()); 
    psAssign.setInt(3, siteId);           

    psAssign.executeUpdate();
}

            int length, width;
            SiteStatus status;

            // Get site details
            try (PreparedStatement psDim = con.prepareStatement(
                    "SELECT length, width, status FROM sites WHERE site_id = ?")) {

                psDim.setInt(1, siteId);

                try (ResultSet rs = psDim.executeQuery()) {
                    rs.next();
                    length = rs.getInt("length");
                    width = rs.getInt("width");
                    status = SiteStatus.valueOf(rs.getString("status"));
                }
            }

            int amount = MaintenanceUtil.calculate(length, width, status);

            // Insert maintenance
            try (PreparedStatement psMaint = con.prepareStatement(
                    "INSERT INTO maintenance(site_id, owner_id, amount, paid) VALUES (?, ?, ?, false)")) {
                psMaint.setInt(1, siteId);
                psMaint.setInt(2, ownerId);
                psMaint.setInt(3, amount);
                psMaint.executeUpdate();
            }

            con.commit();
            System.out.println("Owner added & site assigned: " + siteId);

        } catch (Exception e) {
            con.rollback();
            throw e;
        }
    }
}
@Override
public void addSite(Site site) throws Exception {

    String sql = "INSERT INTO sites(site_type, length, width, status, owner_id) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, site.getSiteType().name());
        ps.setInt(2, site.getLength());
        ps.setInt(3, site.getWidth());
        ps.setString(4, site.getStatus().name());

        if (site.getOwnerId() == null)
            ps.setNull(5, java.sql.Types.INTEGER);
        else
            ps.setInt(5, site.getOwnerId());

        ps.executeUpdate();
        System.out.println("Site added successfully");
    }
}


  @Override
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
                     


   
    
}

