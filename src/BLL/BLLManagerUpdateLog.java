/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.UpdateLog;
import DAL.DataBaseUpdateLog;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ander
 */
public class BLLManagerUpdateLog
{
    DataBaseUpdateLog ul = new DataBaseUpdateLog();
    
//    public void getUpdateLog(UpdateLog updateLog) throws SQLException
//    {
//        ul.getUpdateLog(updateLog);
//    }
    
    public void setUpdateLog(UpdateLog updateLog) throws SQLException
    {
       
        ul.setUpdateLog(updateLog);
    }
    
    public List<UpdateLog> getAllUpdateLogsToList()
    {
        return ul.getUpdateLog();
    }
}
