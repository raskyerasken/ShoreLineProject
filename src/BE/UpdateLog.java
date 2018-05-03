/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;
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
    Date Datelog;

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
