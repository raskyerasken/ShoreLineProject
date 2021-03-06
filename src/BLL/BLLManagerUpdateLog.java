/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLLFacade.IBLLManagerUpdateLog;
import BE.UpdateLog;
import DAL.DataBaseUpdateLog;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ander
 */
public class BLLManagerUpdateLog implements IBLLManagerUpdateLog
{

    private static BLLManagerUpdateLog INSTANCE;
    DataBaseUpdateLog ul = new DataBaseUpdateLog();
    
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
    */
     public synchronized static BLLManagerUpdateLog getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new BLLManagerUpdateLog();
        }
        return INSTANCE;
    }
    public void setUpdateLog(UpdateLog updateLog) throws SQLException
    {
        ul.setUpdateLog(updateLog);
    }
    
    public List<UpdateLog> getAllUpdateLogsToList()
    {
        return ul.getUpdateLog();
    }
    
}
