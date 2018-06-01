/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLLFacade.IBLLManagerUserLogin;
import BE.UserLogin;
import DAL.ConnectionPool.ConnectionPool;
import DAL.ConnectionPool.DalException;
import DAL.ConnectionPool.PooledUserloginDaoController;
import DAL.DataBaseUpdateLog;
import DAL.DataBaseUserLogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ander
 */
public class BLLManagerUserLogin implements IBLLManagerUserLogin
{

    PooledUserloginDaoController pmdcUL;
    private static BLLManagerUserLogin INSTANCE;

    ;
    public BLLManagerUserLogin() throws DalException
    {
        pmdcUL = new PooledUserloginDaoController(new ConnectionPool());
    }

    public synchronized static BLLManagerUserLogin getInstance() throws DalException
    {
        if (INSTANCE == null)
        {
            INSTANCE = new BLLManagerUserLogin();
        }
        return INSTANCE;
    }

    public List<UserLogin> getUserDataToTableView() throws DalException, SQLException
    {
        return pmdcUL.getUserInformation();
    }

    public void getPassword(UserLogin userLogin) throws SQLServerException, SQLException, DalException
    {
        pmdcUL.setPassword(userLogin);
    }

    public boolean getAccess(UserLogin userLogin) throws SQLException, DalException
    {
        return pmdcUL.getAccess(userLogin);
    }

    public boolean getAdminAccess(UserLogin userLogin) throws SQLException, DalException
    {
        return pmdcUL.getAdminAccess(userLogin);
    }

    public boolean usernameAvaible(String Username) throws SQLException, DalException
    {
        return pmdcUL.usernameAvaible(Username);
    }

    public void createNewUser(UserLogin newUser) throws SQLException, SQLServerException, DalException
    {
        pmdcUL.createNewUser(newUser);
    }
}
