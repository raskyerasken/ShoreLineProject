/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author jacob
 */
public class JSONCustommize {

    private String siteName;

    private String assertSerialNumber;

    private String type;

    private String externalWorkOrderId;

    private String systemStatus;

    private String userStatus;

    private String createdOn;

    private String createdBy;

    private String nameTable;

    public String getNameTable() {
        return nameTable;
    }

    @Override
    public String toString() {
        return  nameTable ;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    private String name;

    private String priority;

    private String status;
    
    private String latestFinishDate;
    private String earlistStartDate;
    private String latestStartDate;
    private String estimatedTime;

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }


    public String getLatestStartDate() {
        return latestStartDate;
    }

    public void setLatestStartDate(String latestStartDate) {
        this.latestStartDate = latestStartDate;
    }


    public String getEarlistStartDate() {
        return earlistStartDate;
    }

    public void setEarlistStartDate(String earlistStartDate) {
        this.earlistStartDate = earlistStartDate;
    }


    public String getLatestFinishDate() {
        return latestFinishDate;
    }

    public void setLatestFinishDate(String latestFinishDate) {
        this.latestFinishDate = latestFinishDate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the value of priority
     *
     * @return the value of priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Set the value of priority
     *
     * @param priority new value of priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of createdBy
     *
     * @return the value of createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value of createdBy
     *
     * @param createdBy new value of createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String getExternalWorkOrderId() {
        return externalWorkOrderId;
    }

    public void setExternalWorkOrderId(String externalWorkOrderId) {
        this.externalWorkOrderId = externalWorkOrderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the value of assertSerialNumber
     *
     * @return the value of assertSerialNumber
     */
    public String getAssertSerialNumber() {
        return assertSerialNumber;
    }

    /**
     * Set the value of assertSerialNumber
     *
     * @param assertSerialNumber new value of assertSerialNumber
     */
    public void setAssertSerialNumber(String assertSerialNumber) {
        this.assertSerialNumber = assertSerialNumber;
    }

    /**
     * Get the value of siteName
     *
     * @return the value of siteName
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * Set the value of siteName
     *
     * @param siteName new value of siteName
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}
