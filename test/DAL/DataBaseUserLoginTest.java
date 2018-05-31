/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UserLogin;
import DAL.ConnectionPool.ConnectionPool;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacob
 */
public class DataBaseUserLoginTest {

    /**
     * Test of getAccess method, of class DataBaseUserLogin.
     */
    @Test
    public void testGetAccess() throws Exception {
        System.out.println("getAccess");
        ConnectionManagerSLProject cm = new ConnectionManagerSLProject();
        Connection con = cm.getConnection();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName("Jacob");
        userLogin.setPassword("Jacob");
        DataBaseUserLogin instance = new DataBaseUserLogin();
        boolean expResult = true;
        boolean result = instance.getAccess(con, userLogin);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAccessFalse() throws Exception {
        System.out.println("getAccessFalse");
        ConnectionPool conPool = new ConnectionPool();
        Connection con = conPool.checkOut();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName("Jacob");
        userLogin.setPassword("jacob");
        DataBaseUserLogin instance = new DataBaseUserLogin();
        boolean expResult = false;
        boolean result = instance.getAccess(con, userLogin);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdminAccess method, of class DataBaseUserLogin.
     */
    @Test
    public void testGetAdminAccess() throws Exception {
        System.out.println("getAdminAccess");
        ConnectionPool conPool = new ConnectionPool();
        Connection con = conPool.checkOut();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName("Jacob");
        DataBaseUserLogin instance = new DataBaseUserLogin();
        boolean expResult = true;
        boolean result = instance.getAdminAccess(con, userLogin);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAdminAccessFalse() throws Exception {
        System.out.println("getAdminAccess");
        ConnectionPool conPool = new ConnectionPool();
        Connection con = conPool.checkOut();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName("kris");
        DataBaseUserLogin instance = new DataBaseUserLogin();
        boolean expResult = true;
        boolean result = instance.getAdminAccess(con, userLogin);
        assertEquals(expResult, result);
    }

}
