/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;


import BE.JSONCustommize;
import BE.UpdateLog;
import BLL.BllManagerCustom;
import java.io.File;
import java.sql.SQLException;
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
    
    private static volatile FilesConvertionModel instance;

    public FilesConvertionModel() {}
 /*
   only create one model
     */
    public static FilesConvertionModel getInstance() {
        if (instance == null) {
            synchronized (FilesConvertionModel.class) {
                if (instance == null) {
                    instance = new FilesConvertionModel();
                }
            }
        }
        return instance;
    }
    JSONCustommize customClass;
 BllManagerCustom bllCustom = new BllManagerCustom();
    public ObservableList<File> allFiles
            = FXCollections.observableArrayList();
       public ObservableList<JSONCustommize> customJSON
            = FXCollections.observableArrayList();
    public ObservableList<TreeItem> treeFiles = FXCollections.observableArrayList();

    void setFiles(ObservableList<File> filesAcceptet) {

        allFiles.addAll(filesAcceptet);

    }
    public void setCustomClass(JSONCustommize customClass)
    {
    this.customClass= customClass;
    }
       public JSONCustommize getCustomClass()
    {
    return customClass;
    }

    public ObservableList<File> getFiles() 
    {
        return allFiles;
    }

    public void clearFiles() {
        allFiles.clear();
    }

    public void setTreeFiles(TreeItem<String> newFilesAdded) {
        treeFiles.add(newFilesAdded);
    }

    public ObservableList<TreeItem> getTreeFiles() {
        return treeFiles;
    }

    public File getNextFile() 
    {
        if (allFiles.isEmpty()) 
        {
            return null;
        } 
        else 
        {
            File file = allFiles.get(0);
            return file;
        }
    }

    public void removeFile(File file) 
    {
        allFiles.remove(file);
    }

    public void addFile(File file) 
    {
        allFiles.add(file);
    }
    public void AddAllFiles(ObservableList<File> filesAccepted) 
    {
        allFiles.addAll(filesAccepted);
    }

    public void saveCustom(JSONCustommize custom) throws SQLException {
    customJSON.add(custom);
    bllCustom.add(custom);
    
    }
    public ObservableList<JSONCustommize> getCustom()
    {
        customJSON.clear();
        customJSON.addAll(bllCustom.getAllCustom());
        return customJSON;
    
    }

}
