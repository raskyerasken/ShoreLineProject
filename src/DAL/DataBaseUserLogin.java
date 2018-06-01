/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UpdateLog;
import BE.UserLogin;
import GUI.AlertWindow;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ander
 */
public class DataBaseUserLogin
{

    public void setPassword(Connection con, UserLogin userLogin) throws SQLException
    {
        String sql
                = "SELECT * FROM UserLogin"
                + "(Username, Password)"
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    }

    public boolean getAccess(Connection con, UserLogin userLogin) throws SQLException
    {
        UserLogin ul = new UserLogin();

        String query
                = "SELECT * FROM UserLogin "
                + "WHERE Username LIKE ?";

        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, userLogin.getUserName());

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            ul.setUserName(rs.getString("Username"));
            ul.setPassword(rs.getString("Password"));
        }

        return userLogin.getPassword().equals(ul.getPassword());
    }

    public boolean getAdminAccess(Connection con, UserLogin userLogin) throws SQLException
    {
        UserLogin ul = new UserLogin();

        String query
                = "SELECT * FROM UserLogin "
                + "WHERE Username LIKE ?";

        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, userLogin.getUserName());

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            ul.setAccessLevel(rs.getBoolean("Accesslevel"));
        }

        return ul.isAccessLevel();
    }

    public boolean usernameAvaible(Connection con, String Username) throws SQLException
    {
        UserLogin ul = new UserLogin();

        String query
                = "SELECT * FROM UserLogin "
                + "WHERE Username LIKE ?";

        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, Username);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            return false;
        }

        return true;
    }

    public void createNewUser(Connection con, UserLogin newUser) throws SQLServerException, SQLException
    {
        String sql
                = "INSERT INTO UserLogin "
                + "(Username, Password, Email, Accesslevel) "
                + "VALUES(?,?,?,?)";

        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, newUser.getUserName());
        pstmt.setString(2, newUser.getPassword());
        pstmt.setString(3, newUser.getEmail());
        pstmt.setBoolean(4, newUser.isAccessLevel());

        int affected = pstmt.executeUpdate();
        if (affected < 1)
        {
            throw new SQLException("User not added");
        }
    }

    public List<UserLogin> getUserInformation(Connection con) throws SQLException
    {
        List<UserLogin> AllUserLog
                = new ArrayList<>();
        PreparedStatement pstmt
                = con.prepareStatement("SELECT * FROM UserLogin");

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            UserLogin ul = new UserLogin();
            ul.setUserName(rs.getString("Username"));
            ul.setEmail(rs.getString("Email"));
            ul.setAccessLevel(rs.getBoolean("Accesslevel"));
            AllUserLog.add(ul);
        }
        return AllUserLog;
    }
}
