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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 73e3942... Revert "sad"
=======
>>>>>>> parent of 78bfc66... Revert "Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject"
=======
>>>>>>> parent of 47ef2f3... Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject
=======
>>>>>>> parent of 47ef2f3... Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject

    private static BLLManagerUpdateLog INSTANCE;
    DataBaseUpdateLog ul = new DataBaseUpdateLog();

<<<<<<< HEAD
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
     */
    public synchronized static BLLManagerUpdateLog getInstance()
<<<<<<< HEAD
<<<<<<< HEAD
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
<<<<<<< HEAD
=======
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
     */
    public synchronized static BLLManagerUpdateLog getInstance()
>>>>>>> parent of 73e3942... Revert "sad"
=======
>>>>>>> parent of 78bfc66... Revert "Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject"
=======
>>>>>>> parent of 47ef2f3... Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject
=======
        private static BLLManagerUpdateLog INSTANCE;
    DataBaseUpdateLog ul = new DataBaseUpdateLog();
    /*
    So their only can be created one, so allway you classe.getInstance,
    instead on new class
    */
     public synchronized static BLLManagerUpdateLog getInstance()
>>>>>>> parent of 72296a0... sad
=======
>>>>>>> parent of 47ef2f3... Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 73e3942... Revert "sad"
=======
>>>>>>> parent of 78bfc66... Revert "Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject"
=======
>>>>>>> parent of 47ef2f3... Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject

=======
       
>>>>>>> parent of 72296a0... sad
=======

>>>>>>> parent of 47ef2f3... Merge branch 'master' of https://github.com/raskyerasken/ShoreLineProject
        ul.setUpdateLog(updateLog);
    }
    
    public List<UpdateLog> getAllUpdateLogsToList()
    {
        return ul.getUpdateLog();
    }
    
}
