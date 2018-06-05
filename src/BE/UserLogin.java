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
    private final StringProperty email = new SimpleStringProperty();
    private final BooleanProperty accessLevel = new SimpleBooleanProperty();
    private final BooleanProperty adminAccess = new SimpleBooleanProperty();

    public boolean isAccessLevel()
    {
        return accessLevel.get();
    }

    public void setAccessLevel(boolean value)
    {
        accessLevel.set(value);
    }

    public BooleanProperty accessLevelProperty()
    {
        return accessLevel;
    }

    public String getEmail()
    {
        return email.get();
    }

    public void setEmail(String value)
    {
        email.set(value);
    }

    public StringProperty emailProperty()
    {
        return email;
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
