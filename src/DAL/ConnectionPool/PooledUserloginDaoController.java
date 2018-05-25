/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.ConnectionPool;

import BE.UserLogin;
import DAL.DataBaseUserLogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author pgn
 */
public class PooledUserloginDaoController
{

    private final ConnectionPool conPool;
    private final DataBaseUserLogin dbul;

    public PooledUserloginDaoController(ConnectionPool conPool)
    {
        this.conPool = conPool;
        dbul = new DataBaseUserLogin();
    }

    
     public void setPassword(UserLogin userLogin) throws SQLException, DalException 
    {
        try
        {
            Connection con = conPool.checkOut();
            dbul.setPassword(con,userLogin);
            conPool.checkIn(con);
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage(), ex);
        }
    }

  public boolean getAccess(UserLogin userLogin) throws SQLException, DalException
    {
        try
        {
            Connection con = conPool.checkOut();
         boolean acces=   dbul.getAccess(con,userLogin);
            conPool.checkIn(con);
            return acces;
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage(), ex);
        }
    }

   public boolean getAdminAccess (UserLogin userLogin) throws SQLException, DalException
    {
        try
        {
            Connection con = conPool.checkOut();
          boolean admin= dbul.getAdminAccess(con,userLogin);
            conPool.checkIn(con);
            return admin;
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage(), ex);
        }
    }
    public boolean usernameAvaible (String userLogin) throws SQLException, DalException
    {
        try
        {
            Connection con = conPool.checkOut();
            boolean nameAvaible= dbul.usernameAvaible(con,userLogin);
            conPool.checkIn(con);
            return nameAvaible;
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage(), ex);
        }
    }
     public void createNewUser(UserLogin newUser) throws SQLServerException, SQLException, DalException 
    {
        try
        {
            Connection con = conPool.checkOut();
            dbul.createNewUser((Connection) conPool,newUser);
            conPool.checkIn(con);
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage(), ex);
        }
    }
        public List<UserLogin> getUserInformation() throws DalException, SQLException 
    {
        Connection con = conPool.checkOut();
        List<UserLogin> users=dbul.getUserInformation(con);
        conPool.checkIn(con);
        return users;
    }

}
