/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import java.io.File;
import java.util.List;
import javafx.application.Platform;
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
    
    private ObservableList<File> allFiles
            = FXCollections.observableArrayList();
    private TreeItem treeFiles;
    
    void setFiles(ObservableList<File> filesAcceptet) {
        
        allFiles.addAll(filesAcceptet);
        
    }
    
    ObservableList<File> getFiles() {
        return allFiles;
    }
    
    void clearFiles() {
        allFiles.clear();
    }
    
    void setTreeFiles(TreeItem<String> newFilesAdded) {
        treeFiles=newFilesAdded;
    }
    
    TreeItem getTreeFiles() {
        return treeFiles;
    }
    
    File getNextFile() {
        if (allFiles.isEmpty()) {
            return null;
        } else {
            
            File file = allFiles.get(0);
//            Platform.runLater(() -> {
//                allFiles.remove(file);
//            });
            
            return file;
        }
    }
    
    void removeFile(File file) {
        allFiles.remove(file);
    }

    void addFile(File file) {
    allFiles.add(file);
    }

    void AddAllFiles(ObservableList<File> filesAccepted) {
    allFiles.addAll(filesAccepted);
    }
    
}
