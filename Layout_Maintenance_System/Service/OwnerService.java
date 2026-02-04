package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DAO.UserDAOImpl;
import Utils.DBConnection;

public class OwnerService {

    private UserDAOImpl user = new UserDAOImpl();
    public void viewMySite(int ownerId) throws Exception {
       user.viewMySite(ownerId);
    }

    public void viewMaintenance(int ownerId) throws Exception {
        user.viewMaintenance(ownerId);
    }

    public void payMaintenance(int siteId) throws Exception {
       user.payMaintenance(siteId);
    }

    public void payMaintenance(int siteId, int amount) throws Exception {
       user.payMaintenance(siteId);
    }

    public void requestSiteUpdate(int siteId, String status) throws Exception {
       user.requestSiteUpdate(siteId, status);
}

public boolean ownerExists(int ownerId) throws Exception {

      return user.ownerExists(ownerId);
    }
}
