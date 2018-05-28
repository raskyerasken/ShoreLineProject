/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLLFacade;

import BE.UpdateLog;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ander
 */
public interface IBLLManagerUpdateLog 
{
     public void setUpdateLog(UpdateLog updateLog) throws SQLException;
    
     public List<UpdateLog> getAllUpdateLogsToList();
}
