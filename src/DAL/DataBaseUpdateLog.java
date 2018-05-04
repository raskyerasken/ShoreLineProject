/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UpdateLog;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ander
 */
public class DataBaseUpdateLog 
{
    private ConnectionManagerSLProject cm = new ConnectionManagerSLProject();
    
    public void setUpdateLog(UpdateLog updateLog) throws SQLException 
    {
        try (Connection con = cm.getConnection()) 
        {
            String sql
                    = "INSERT INTO UpdateLog"
                    + "(Username, UploadDate, Adjustment)"
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, updateLog.getUsername());
            pstmt.setTimestamp(2, (java.sql.Timestamp)updateLog.getDatelog());
            pstmt.setString(3, updateLog.getAdjustment());
            
            int affected = pstmt.executeUpdate();
            if (affected < 1) 
            {
                throw new SQLException("Username, adjustments and date not added");
            }
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<UpdateLog> getUpdateLog() 
    {
        
            List<UpdateLog> AllupdateLog 
                = new ArrayList<>();
        
        try (Connection con = cm.getConnection()) 
        {
            PreparedStatement pstmt 
                    = con.prepareStatement("SELECT * FROM UpdateLog");
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                UpdateLog ul = new UpdateLog();
                ul.setUsername(rs.getString("Username"));
                ul.setDatelog(rs.getTimestamp("UploadDate"));
                AllupdateLog.add(ul);
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseUpdateLog.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return AllupdateLog;
    }
    
    
}
