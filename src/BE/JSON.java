/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jacob
 */
public class JSON 
{

    private final IntegerProperty assetSerialNumber = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final IntegerProperty externalWorkOrderId = new SimpleIntegerProperty();
    private final StringProperty systemStatus = new SimpleStringProperty();
    private final StringProperty siteName = new SimpleStringProperty();
    private final StringProperty userStatus = new SimpleStringProperty();
    private Date createdOn;
    private final StringProperty createdBy = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty priority = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String value) {
        priority.set(value);
    }

    public StringProperty priorityProperty() {
        return priority;
    }
    

    private Planning planning;

    /**
     * Get the value of planning
     *
     * @return the value of planning
     */
    public Planning getPlanning() 
    {
        return planning;
    }

    /**
     * Set the value of planning
     *
     * @param planning new value of planning
     */
    public void setPlanning(Planning planning) 
    {
        this.planning = planning;
    }

    public String getName() 
    {
        return name.get();
    }

    public void setName(String value) 
    {
        name.set(value);
    }

    public StringProperty nameProperty() 
    {
        return name;
    }

    public String getCreatedBy() 
    {
        return createdBy.get();
    }

    public void setCreatedBy(String value) 
    {
        createdBy.set(value);
    }

    public StringProperty createdByProperty() 
    {
        return createdBy;
    }

    
    /**
     * Get the value of createdOn
     *
     * @return the value of createdOn
     */
    public Date getCreatedOn() 
    {
        return createdOn;
    }

    /**
     * Set the value of createdOn
     *
     * @param createdOn new value of createdOn
     */
    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }

    public String getUserStatus() 
    {
        return userStatus.get();
    }

    public void setUserStatus(String value) 
    {
        userStatus.set(value);
    }

    public StringProperty userStatusProperty() 
    {
        return userStatus;
    }

    public String getSiteName() 
    {
        return siteName.get();
    }

    public void setSiteName(String value) 
    {
        siteName.set(value);
    }

    public StringProperty siteNameProperty() 
    {
        return siteName;
    }

    public String getSystemStatus() 
    {
        return systemStatus.get();
    }

    public void setSystemStatus(String value) 
    {
        systemStatus.set(value);
    }

    public StringProperty systemStatusProperty() 
    {
        return systemStatus;
    }

    public int getExternalWorkOrderId() 
    {
        return externalWorkOrderId.get();
    }

    public void setExternalWorkOrderId(int value) 
    {
        externalWorkOrderId.set(value);
    }

    public IntegerProperty externalWorkOrderIdProperty()
    {
        return externalWorkOrderId;
    }

    public String getType() 
    {
        return type.get();
    }

    public void setType(String value) 
    {
        type.set(value);
    }

    public StringProperty typeProperty() 
    {
        return type;
    }

    public int getAssetSerialNumber() 
    {
        return assetSerialNumber.get();
    }

    public void setAssetSerialNumber(int value) 
    {
        assetSerialNumber.set(value);
    }

    public IntegerProperty assetSerialNumberProperty() 
    {
        return assetSerialNumber;
    }
 
}
