package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.SiteStatus;
import Utility.MaintenanceUtil;
import Utils.DBConnection;

public class MaintenanceDAOImpl implements MaintenanceDAO {

  @Override
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

   @Override
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

   @Override
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
@Override
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

   

    
}
