/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private final StringProperty Email = new SimpleStringProperty();
    private final BooleanProperty AccessLevel = new SimpleBooleanProperty();

    public boolean isAccessLevel() 
    {
        return AccessLevel.get();
    }

    public void setAccessLevel(boolean value) 
    {
        AccessLevel.set(value);
    }

    public BooleanProperty AccessLevelProperty() 
    {
        return AccessLevel;
    }
    
    public String getEmail() 
    {
        return Email.get();
    }

    public void setEmail(String value) 
    {
        Email.set(value);
    }

    public StringProperty EmailProperty() 
    {
        return Email;
    }
    
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
    
    public String getUserName() 
    {
        return userName.get();
    }

    public void setUserName(String value) 
    {
        userName.set(value);
    }

    public StringProperty userNameProperty() 
    {
        return userName;
    }
    
}
