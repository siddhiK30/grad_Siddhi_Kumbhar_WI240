

package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.Maintenance;
import Utils.DBConnection;

public interface MaintenanceDAO {
    void generateMaintenance() throws Exception;
    void collectMaintenance(int siteId) throws Exception;
    void viewAllPendingMaintenance() throws Exception;
    void viewPendingBySite(int siteId) throws Exception;
  
 
}
