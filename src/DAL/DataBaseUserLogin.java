/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UserLogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ander
 */
public class DataBaseUserLogin 
{
    private ConnectionManagerSLProject cm = new ConnectionManagerSLProject();
    
    public void setPassword(UserLogin userLogin) throws SQLException
    {
        try(Connection con = cm.getConnection())
        {
            String sql
                    = "SELECT * FROM UserLogin"
                    + "(Username, Password)"
                    + "VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getAccess(UserLogin userLogin) throws SQLException
    {
        UserLogin ul = new UserLogin();
        try(Connection con = cm.getConnection())
        {
            String query
                    = "SELECT * FROM UserLogin "
                    + "WHERE Username LIKE ?";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userLogin.getUserName());
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                ul.setUserName(rs.getString("Username"));
                ul.setPassword(rs.getString("Password"));
            }
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userLogin.getPassword().equals(ul.getPassword());
    }

    public boolean usernameAvaible(String Username) throws SQLException {
   UserLogin ul = new UserLogin();
        try(Connection con = cm.getConnection())
        {
            String query
                    = "SELECT * FROM UserLogin "
                    + "WHERE Username LIKE ?";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Username);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                return false;
            }
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void createNewUser(UserLogin newUser) throws SQLServerException, SQLException {
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO UserLogin "
                    + "(Username, Password, Email, Accesslevel) "
                    + "VALUES(?,?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newUser.getUserName());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setBoolean(4,  newUser.isAccessLevel());
           
            int affected = pstmt.executeUpdate();
            if (affected<1)
            {
                throw new SQLException("User not added");
            }
            
    }
}
}
