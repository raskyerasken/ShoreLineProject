/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.ConnectionPool;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Taking from MechaChatApp
 */
public class ConnectionPool extends ObjectPool<Connection>
{

    private Connection con;
    private DBConnector dbConnector;

    public ConnectionPool() throws DalException
    {
        super();
        try
        {
            dbConnector = new DBConnector();
        } catch (IOException ex)
        {
            throw new DalException("Error creating connection pool to database", ex);
        }

    }

    @Override
    public void expire(Connection o) 
    {
        try
        {
            o.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean validate(Connection o)
    {
        try
        {
            return !o.isClosed();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    protected Connection create() 
    {
        try
        {
            return dbConnector.getConnection();
        } catch (SQLServerException ex)
        {
            try {
                throw new DalException(ex.getMessage(), ex);
            } catch (DalException ex1) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }

}
