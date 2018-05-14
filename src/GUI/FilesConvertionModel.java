/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import java.io.File;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author jacob
 */
public class FilesConvertionModel {
 private final ObservableList<File> allFiles 
            = FXCollections.observableArrayList();
 private final ObservableList<TreeItem> treeFiles
                = FXCollections.observableArrayList();
 
    void setFiles(ObservableList<File> filesAcceptet) {
       
    allFiles.addAll(filesAcceptet);
       
        
    }
    ObservableList<File> getFiles()
    {
    return allFiles;
    }

    void clearFiles() {
   allFiles.clear();
    }

    void setTreeFiles(TreeItem<String> newFilesAdded)
    {
        treeFiles.add(newFilesAdded);
    }
    
    ObservableList<TreeItem> getTreeFiles()
    {
        return treeFiles;
    }
    
    
}
