/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author ander
 */
class AbstractConnectionManagerSLProject implements IConnectionManager
{

    protected SQLServerDataSource ds = new SQLServerDataSource();

    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}
