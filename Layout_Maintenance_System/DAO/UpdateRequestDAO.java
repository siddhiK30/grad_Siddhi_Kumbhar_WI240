package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entity.UpdateRequest;
import Utils.DBConnection;

public interface UpdateRequestDAO {
     void approveRequest(int requestId) throws Exception;
     void rejectRequest(int requestId) throws Exception;
     
     void showRequestAll() throws Exception ;



    
} 