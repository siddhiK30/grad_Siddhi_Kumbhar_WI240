package DAO;
import Utils.InputUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.DBConnection;

public class UpdateRequestDAOImpl implements UpdateRequestDAO {

    @Override
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

    

    private void updateRequestStatus(int requestId, String status) throws Exception {

        String updateSql =
                "UPDATE owner_site_update_requests SET approval_status = ? WHERE request_id = ?";
        String selectSql =
                "SELECT request_id, site_id, approval_status FROM owner_site_update_requests WHERE request_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql)) {

            ps.setString(1, status);
            ps.setInt(2, requestId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Request " + status.toLowerCase());
            } else {
                System.out.println("No request found with ID " + requestId);
                return;
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

    @Override
    public void approveRequest(int requestId) throws Exception {
        updateRequestStatus(requestId, "APPROVED");
    }

    @Override
    public void rejectRequest(int requestId) throws Exception {
        updateRequestStatus(requestId, "REJECTED");
    }
}
