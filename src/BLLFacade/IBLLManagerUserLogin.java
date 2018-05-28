/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLLFacade;

import BE.UserLogin;
import DAL.ConnectionPool.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ander
 */
public interface IBLLManagerUserLogin 
{
  public List<UserLogin> getUserDataToTableView() throws DalException, SQLException;
  
  
   public void getPassword(UserLogin userLogin) throws SQLServerException, SQLException, DalException ;
   
   public boolean getAccess(UserLogin userLogin) throws SQLException, DalException ;
   
    public boolean getAdminAccess(UserLogin userLogin) throws SQLException, DalException ;
    
        public boolean usernameAvaible(String Username) throws SQLException, DalException ;
        
         public void createNewUser(UserLogin newUser) throws SQLException, SQLServerException, DalException ;
}
