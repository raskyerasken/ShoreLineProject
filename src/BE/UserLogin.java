/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ander
 */
public class UserLogin 
{

    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public String getPassword() 
    {
        return password.get();
    }

    public void setPassword(String value) 
    {
        password.set(value);
    }

    public StringProperty passwordProperty() 
    {
        return password;
    }
    
    private String getUserName() 
    {
        return userName.get();
    }

    private void setUserName(String value) 
    {
        userName.set(value);
    }

    private StringProperty userNameProperty() 
    {
        return userName;
    }
    
}
