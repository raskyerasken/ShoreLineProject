/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.JSONCustommize;
import GUI.AlertWindow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class DataBaseCustom
{

    private ConnectionManagerSLProject cm = new ConnectionManagerSLProject();

    public void add(JSONCustommize custom) throws SQLException
    {

        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO JSONCustomTable "
                    + "(type, externalWorkOrderId, systemStatus, userStatus, name, priority, latestFinishDate, earlistStartDate, latestStartDate, estimatedTime, nameTable ) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, custom.getType());
            pstmt.setString(2, custom.getExternalWorkOrderId());
            pstmt.setString(3, custom.getSystemStatus());
            pstmt.setString(4, custom.getUserStatus());
            pstmt.setString(5, custom.getName());
            pstmt.setString(6, custom.getPriority());
            pstmt.setString(7, custom.getLatestFinishDate());
            pstmt.setString(8, custom.getEarlistStartDate());
            pstmt.setString(9, custom.getLatestStartDate());
            pstmt.setString(10, custom.getEstimatedTime());
            pstmt.setString(11, custom.getNameTable());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("User not added");
            }

        }
    }

    public ObservableList<JSONCustommize> getAllCustom()
    {
        ObservableList<JSONCustommize> customList
                = FXCollections.observableArrayList();

        try (Connection con = cm.getConnection())
        {
            PreparedStatement pstmt
                    = con.prepareStatement("SELECT * FROM JSONCustomTable");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                JSONCustommize custom = new JSONCustommize();

                custom.setType(rs.getString("type"));
                custom.setExternalWorkOrderId(rs.getString("externalWorkOrderId"));
                custom.setSystemStatus(rs.getString("systemStatus"));
                custom.setUserStatus(rs.getString("userStatus"));
                custom.setName(rs.getString("name"));
                custom.setPriority(rs.getString("priority"));
                custom.setLatestFinishDate(rs.getString("latestFinishDate"));
                custom.setEarlistStartDate(rs.getString("earlistStartDate"));
                custom.setLatestStartDate(rs.getString("latestStartDate"));
                custom.setEstimatedTime(rs.getString("estimatedTime"));
                custom.setNameTable(rs.getString("nameTable"));

                customList.add(custom);
            }
        }
        catch (SQLException ex)
        {
            AlertWindow alert = new AlertWindow("Data base connectiong error", null, "Check you connection to the database");
        }
        return (ObservableList) customList;
    }

}
