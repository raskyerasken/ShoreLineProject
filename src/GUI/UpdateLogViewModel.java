/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class UpdateLogViewModel 
{
    private final BLL.BLLManagerUpdateLog bllManagerUL 
            = new BLLManagerUpdateLog();
    private final ObservableList<UpdateLog> updateLogToList 
            = FXCollections.observableArrayList();
    
    UpdateLog ul = new UpdateLog();
    
    ObservableList<UpdateLog> getAllLogUpdates() throws SQLException
    {
        genreToMovies();
        return updateLogToList;
    }
    
    void genreToMovies() throws SQLException
    { 
        updateLogToList.clear();
        for (UpdateLog updateLog : bllManagerUL.getAllUpdateLogsToList()) 
        {
            updateLogToList.add(updateLog);
        }
    }
}
