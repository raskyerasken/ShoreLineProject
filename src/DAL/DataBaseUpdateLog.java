/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UpdateLog;
import GUI.AlertWindow;
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
             AlertWindow  alert = new AlertWindow("Data base connectiong error", null, "Check you connection to the database");
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
            System.out.println("Thread sleep");
            int MAX_PRIORITY = Thread.MAX_PRIORITY;
            while (rs.next()) 
            {
                UpdateLog ul = new UpdateLog();
                ul.setUsername(rs.getString("Username"));
                ul.setDatelog(rs.getTimestamp("UploadDate"));
                ul.setAdjustment(rs.getString("Adjustment"));
                AllupdateLog.add(ul);
            }
        } 
        catch (SQLException ex) 
        {
          AlertWindow  alert = new AlertWindow("Data base connectiong error", null, "Check you connection to the database");
        }
        return AllupdateLog;
    }
    
    
}
