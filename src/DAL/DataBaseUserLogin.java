/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UserLogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public void getPassword(UserLogin userLogin) throws SQLException
    {
        try(Connection con = cm.getConnection())
        {
            String sql
                    = "SELECT * FROM UserLogin"
                    + "(Username, Password)"
                    + "VALUES (?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}