/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import BLLFacade.IBLLManagerUpdateLog;
import DAL.ConnectionPool.DalException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class UpdateLogViewModel 
{
    private static volatile UpdateLogViewModel instance;

    public UpdateLogViewModel() throws DalException {
    bllManagerUL = BLLManagerUpdateLog.getInstance();
    
   }
 /*
   only create one model
     */
    public static UpdateLogViewModel getInstance() throws DalException {
        if (instance == null) {
            synchronized (UpdateLogViewModel.class) {
                if (instance == null) {
                    instance = new UpdateLogViewModel();
                }
            }
        }
        return instance;
    }
    private final IBLLManagerUpdateLog bllManagerUL ;
    private final ObservableList<UpdateLog> updateLogToList 
            = FXCollections.observableArrayList();
    
    public void logListUpdate() 
    { 
        updateLogToList.setAll(bllManagerUL.getAllUpdateLogsToList());
    }

    public ObservableList<UpdateLog> getUpdateLogToList() 
    {
        
        return updateLogToList;
    }
    
}
