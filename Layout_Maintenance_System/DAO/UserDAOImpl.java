package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.DBConnection;

public class UserDAOImpl implements UserDAO {

   
    public boolean ownerExists(int ownerId) throws Exception {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT 1 FROM users WHERE user_id = ? AND role = 'OWNER'"
        );
        ps.setInt(1, ownerId);

        ResultSet rs = ps.executeQuery();

        boolean exists = rs.next(); 

        con.close();
        return exists;
    }
    public void viewMySite(int ownerId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT site_id, site_type, status FROM sites WHERE owner_id=?"
        );
        ps.setInt(1, ownerId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(
                rs.getInt("site_id") + " | " +
                rs.getString("site_type") + " | " +
                rs.getString("status")
            );
        }
        con.close();
    }

    public void viewMaintenance(int ownerId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT site_id, amount, paid FROM maintenance WHERE owner_id=?"
        );
        ps.setInt(1, ownerId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(
                "Site: " + rs.getInt("site_id") +
                " | Amount: " + rs.getInt("amount") +
                " | Paid: " + rs.getBoolean("paid")
            );
        }
        con.close();
    }

    public void payMaintenance(int siteId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "UPDATE maintenance SET paid=true WHERE site_id=?"
        );
        ps.setInt(1, siteId);
        ps.executeUpdate();

        System.out.println("Maintenance fully paid");
        con.close();
    }

    public void payMaintenance(int siteId, int amount) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "UPDATE maintenance SET amount = amount - ? WHERE site_id=? AND paid=false"
        );
        ps.setInt(1, amount);
        ps.setInt(2, siteId);

        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Partial payment successful");
        else
            System.out.println("Invalid payment");

        con.close();
    }

    public void requestSiteUpdate(int siteId, String status) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO owner_site_update_requests(site_id, new_status) VALUES (?, ?)"
        );
        ps.setInt(1, siteId);
        ps.setString(2, status);
        ps.executeUpdate();

        System.out.println("Update request sent to admin");
        con.close();
    }
    
}
