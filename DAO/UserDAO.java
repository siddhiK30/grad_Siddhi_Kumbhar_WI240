package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.DBConnection;

public class UserDAO {

   
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
}
