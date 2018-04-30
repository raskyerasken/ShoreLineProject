/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.JSON;
import BE.Planning;

/**
 *
 * @author jacob
 */
public class XLSX implements IConvertingToJSON{

    private JSON JSON;
    
 java.util.Date utilDate = new java.util.Date();
    java.sql.Date toDayDate = new java.sql.Date(utilDate.getTime());
    private Planning planning;
    @Override
    public JSON convertedToJSON() {
        JSON.setAssetSerialNumber(0);
        JSON.setCreatedBy("SAP");
        JSON.setCreatedOn(toDayDate);
        JSON.setExternalWorkOrderId(/*SAP import field -> 'Order'*/0);
        JSON.setName("\"SAP import field -> 'Opr. short text' if empty then  'Description2'\"");
        JSON.setPlanning(planning);
        planning.setEarliestStartDate(/*"Datetime Object"*/null);
        planning.setEstimatedTime(/*"Datetime Object"*/0);
        planning.setLatestFinishDate(/*"Datetime Object"*/null);
        planning.setLatestStartDate(/*"Datetime Object"*/null);
        JSON.setSiteName("");
        JSON.setSystemStatus(": SAP import field -> 'System status'");
        JSON.setType("SAP import field -> 'Order Type'");
        JSON.setUserStatus("SAP import field -> 'User status'");
        
        
    return JSON;
    }

    
}
