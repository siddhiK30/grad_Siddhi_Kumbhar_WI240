package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.Site;
import Entity.SiteType;
import Entity.SiteStatus;
import Utils.DBConnection;

public interface  SiteDAO {
    
    void addSite(Site site) throws Exception;
    void addOwnerWithSitePreference(int ownerId,String ownerName,SiteType siteType) throws Exception;
  
    void editSite(int siteId, String status, int len, int wid) throws Exception;
   
    void removeSite(int siteId) throws Exception ;
     public void editOwner(int id, String name) throws Exception;
       
     
     public void removeOwner(int id) throws Exception;
        
     



   
    
}
