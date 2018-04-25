/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author jacob
 */
public class Planning {
    
    private Date latestFinishDate;
    private Date earliestStartDate;
    private Date latestStartDate;
    private final IntegerProperty estimatedTime = new SimpleIntegerProperty();
    
    public int getEstimatedTime() 
    {
        return estimatedTime.get();
    }

    public void setEstimatedTime(int value) 
    {
        estimatedTime.set(value);
    }

    public IntegerProperty estimatedTimeProperty() 
    {
        return estimatedTime;
    }

    /**
     * Get the value of latestStartDate
     *
     * @return the value of latestStartDate
     */
    public Date getLatestStartDate() 
    {
        return latestStartDate;
    }

    /**
     * Set the value of latestStartDate
     *
     * @param latestStartDate new value of latestStartDate
     */
    public void setLatestStartDate(Date latestStartDate) 
    {
        this.latestStartDate = latestStartDate;
    }

    /**
     * Get the value of earliestStartDate
     *
     * @return the value of earliestStartDate
     */
    public Date getEarliestStartDate() 
    {
        return earliestStartDate;
    }

    /**
     * Set the value of earliestStartDate
     *
     * @param earliestStartDate new value of earliestStartDate
     */
    public void setEarliestStartDate(Date earliestStartDate) 
    {
        this.earliestStartDate = earliestStartDate;
    }

    /**
     * Get the value of latestFinishDate
     *
     * @return the value of latestFinishDate
     */
    public Date getLatestFinishDate() 
    {
        return latestFinishDate;
    }

    /**
     * Set the value of latestFinishDate
     *
     * @param latestFinishDate new value of latestFinishDate
     */
    public void setLatestFinishDate(Date latestFinishDate) 
    {
        this.latestFinishDate = latestFinishDate;
    }

}
