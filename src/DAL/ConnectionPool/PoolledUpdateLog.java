/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.ConnectionPool;

import BE.UpdateLog;
import BE.UserLogin;
import DAL.DataBaseUpdateLog;
import DAL.DataBaseUserLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jacob
 */
public class PoolledUpdateLog {
     private final ConnectionPool conPool;
    private final DataBaseUpdateLog dbul;

    /*
    Taking from MechaChatApp
    */
    public PoolledUpdateLog(ConnectionPool conPool)
    {
        this.conPool = conPool;
        dbul = new DataBaseUpdateLog();
    }

    
     public void setUpdateLog(UpdateLog updateLog) throws SQLException, DalException 
    {
        try
        {
            Connection con = conPool.checkOut();
            dbul.setUpdateLog(updateLog);
            conPool.checkIn(con);
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage(), ex);
        }
    }
      

    public List<UpdateLog> getUpdateLog() throws DalException {
    Connection con = conPool.checkOut();
        List<UpdateLog> ul = dbul.getUpdateLog();
        conPool.checkIn(con);
        return ul;
    }


     
}
