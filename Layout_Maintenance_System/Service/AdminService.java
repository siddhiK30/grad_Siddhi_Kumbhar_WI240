package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.MaintenanceDAOImpl;
import DAO.SiteDAOImpl;
import DAO.UpdateRequestDAOImpl;
import Entity.Site;
import Entity.SiteStatus;
import Entity.SiteType;

public class AdminService {
    private SiteDAOImpl site = new SiteDAOImpl();
    private MaintenanceDAOImpl main = new MaintenanceDAOImpl();
    private UpdateRequestDAOImpl req = new UpdateRequestDAOImpl();

        
   
    public void addOwnerWithSitePreference(int ownerId,String ownerName,SiteType siteType) throws Exception{
        site.addOwnerWithSitePreference(ownerId, ownerName, siteType);
    }
  
    public void editSite(int siteId, String status, int len, int wid) throws Exception{
        site.editSite(siteId, status, len, wid);
    }
   
    public void removeSite(int siteId) throws Exception{
        site.removeSite(siteId);
    }
      public void generateMaintenance() throws Exception{
        main.generateMaintenance();
      }
    public void collectMaintenance(int siteId) throws Exception{
        main.collectMaintenance(siteId);
    }
    public void viewAllPendingMaintenance() throws Exception{
        main.viewAllPendingMaintenance();
    }
    public void viewPendingBySite(int siteId) throws Exception{
        main.viewPendingBySite(siteId);
    }
     public void approveRequest(int requestId) throws Exception{
        req.approveRequest(requestId);
     }
     public void rejectRequest(int requestId) throws Exception{
        req.rejectRequest(requestId);
     }
    
     void showRequestAll() throws Exception{
        req.showRequestAll();
     }
     public void editOwner(int id, String name) throws Exception {
       site.editOwner(id, name);
     }
     public void removeOwner(int id) throws Exception {
        site.removeOwner(id);
     }
     public void addSite(Site site2) throws Exception {
       site.addSite(site2);
     }



}

