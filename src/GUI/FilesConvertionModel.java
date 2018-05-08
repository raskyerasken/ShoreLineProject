/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class FilesConvertionModel {
 private final ObservableList<File> allFiles 
            = FXCollections.observableArrayList();
    void setFiles(ObservableList<File> filesAcceptet) {
        
    allFiles.addAll(filesAcceptet);
       
        
    }
    ObservableList<File> getFiles()
    {
    return allFiles;
    }
    
    
}
