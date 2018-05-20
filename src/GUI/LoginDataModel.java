/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author ander
 */
<<<<<<< HEAD
public class LoginDataModel 
{
    String userNameSavedToString;
    Boolean userAccessLevel;
    
    public void addUserLoginData(String user)
    {
        userNameSavedToString = user;
    }

    public String getUserLogin() 
    {
        return userNameSavedToString;
    }
    
    public void addAccessLoginData(Boolean user)
    {
        userAccessLevel = user;
        System.out.println("What is the user "+user);
    }
    
    public Boolean getUserAccessLevel()
    {
        return userAccessLevel;
    }
=======
public class LoginDataModel {

    private final ObservableList<String> loginData
            = FXCollections.observableArrayList();
    BLLManagerUpdateLog bllManager = new BLLManagerUpdateLog();
    String somethingToAdd;

    void addLoginData(String user) {
        somethingToAdd = user;

    }

    List getLoginData() {
        return loginData;
    }

    public String getUserLogin() {
        return somethingToAdd;
    }

    ObservableList<String> setUserLogin(String userLogin) throws SQLException {

        return loginData;
    }

>>>>>>> 78d5d2fdc5145ad05933cac639b6e9b933aa9097
}
