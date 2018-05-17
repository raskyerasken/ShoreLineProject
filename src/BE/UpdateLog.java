/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class UpdateLog 
{

    private final StringProperty Username = new SimpleStringProperty();
    private final StringProperty Adjustment = new SimpleStringProperty();
    private final BooleanProperty Error = new SimpleBooleanProperty();
    Date Datelog;
    private final IntegerProperty id = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public boolean isError() 
    {
        return Error.get();
    }

    public void setError(boolean value) 
    {
        Error.set(value);
    }

    public BooleanProperty ErrorProperty() 
    {
        return Error;
    }
    
    /**
     * Get the value of Datelog
     *
     * @return the value of Datelog
     */
    public Date getDatelog() 
    {
        return Datelog;
    }
    
    public void setDatelog(Date Datelog)
    {
        this.Datelog = Datelog;
    }
    
    public String getAdjustment() 
    {
        return Adjustment.get();
    }

    public void setAdjustment(String value) 
    {
        Adjustment.set(value);
    }

    public StringProperty AdjustmentProperty() 
    {
        return Adjustment;
    }
    
    
    public String getUsername() 
    {
        return Username.get();
    }

    public void setUsername(String value)
    {
        Username.set(value);
    }

    public StringProperty UsernameProperty() 
    {
        return Username;
    }
    
}
