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
public class ConnectionManagerSLProject extends AbstractConnectionManagerSLProject
{
    public ConnectionManagerSLProject() 
    {
        ds.setDatabaseName("CS2017B_13_Shoreline");
        ds.setUser("CS2017B_13_java");
        ds.setPassword("javajava");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
}
