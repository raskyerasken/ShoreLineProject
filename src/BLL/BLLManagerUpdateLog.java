/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLLFacade.IBLLManagerUpdateLog;
import BE.UpdateLog;
import DAL.ConnectionPool.ConnectionPool;
import DAL.ConnectionPool.DalException;
import DAL.ConnectionPool.PooledUserloginDaoController;
import DAL.ConnectionPool.PoolledUpdateLog;
import DAL.DataBaseUpdateLog;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ander
 */
public class BLLManagerUpdateLog implements IBLLManagerUpdateLog
{
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 73e3942... Revert "sad"

    private static BLLManagerUpdateLog INSTANCE;
    DataBaseUpdateLog ul = new DataBaseUpdateLog();

<<<<<<< HEAD
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
     */
    public synchronized static BLLManagerUpdateLog getInstance()
=======
    PoolledUpdateLog pmdcUL;
        private static BLLManagerUpdateLog INSTANCE;
        
        public BLLManagerUpdateLog() throws DalException { 
         pmdcUL= new PoolledUpdateLog(new ConnectionPool());
    }
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
    */
     public synchronized static BLLManagerUpdateLog getInstance() throws DalException
>>>>>>> 87cfc633d5c2481c12f8a920afecb9583dbe9234
=======
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
     */
    public synchronized static BLLManagerUpdateLog getInstance()
>>>>>>> parent of 73e3942... Revert "sad"
    {
        if (INSTANCE == null)
        {
            INSTANCE = new BLLManagerUpdateLog();
        }
        return INSTANCE;
    }

    public void setUpdateLog(UpdateLog updateLog) throws SQLException
    {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 73e3942... Revert "sad"

        ul.setUpdateLog(updateLog);
=======
       
        try {
            pmdcUL.setUpdateLog(updateLog);
        } catch (DalException ex) {
            Logger.getLogger(BLLManagerUpdateLog.class.getName()).log(Level.SEVERE, null, ex);
        }
>>>>>>> 87cfc633d5c2481c12f8a920afecb9583dbe9234
    }

    public List<UpdateLog> getAllUpdateLogsToList()
    {
        try {
            return pmdcUL.getUpdateLog();
        } catch (DalException ex) {
            Logger.getLogger(BLLManagerUpdateLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
