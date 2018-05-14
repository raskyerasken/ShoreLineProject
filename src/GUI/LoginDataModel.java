/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLLManagerUpdateLog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class LoginDataModel 
{

    private final ObservableList<String> loginData
            = FXCollections.observableArrayList();
    BLLManagerUpdateLog bllManager = new BLLManagerUpdateLog();
    String somethingToAdd;
    
    void addLoginData(String lala)
    {
        somethingToAdd = lala;
       
        System.out.println("addLoginData: " + lala);
    }
    
    List getLoginData()
    {
        return loginData;
    }

    public String getUserLogin() 
    {
        return somethingToAdd;
    }

    ObservableList<String> setUserLogin(String userLogin) throws SQLException 
    {
        //loginData.setAll(bllManager.getAllUpdateLogsToList());
        return loginData;
    }


}
