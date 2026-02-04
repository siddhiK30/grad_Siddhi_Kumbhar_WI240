package DAO;

public interface UserDAO {
    
      void viewMySite(int ownerId) throws Exception ;
      void viewMaintenance(int ownerId) throws Exception ;
      void payMaintenance(int siteId) throws Exception ;
      void payMaintenance(int siteId, int amount) throws Exception;
      void requestSiteUpdate(int siteId, String status) throws Exception ;
      boolean ownerExists(int id) throws Exception;
}
