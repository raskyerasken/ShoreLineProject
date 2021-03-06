/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import BE.UpdateLog;
import BE.UserLogin;
import BLL.BLLManagerUserLogin;
import DAL.ConnectionPool.DalException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class MainWindowController implements Initializable
{

    UpdateLog updateLog = new UpdateLog();
    BLL.BLLManagerUpdateLog up ;
    Parent root;
    UserLogin ul = new UserLogin();
    BLLManagerUserLogin bllManagerUL;
    LoginDataModel modelData;
    LoginViewController loginID;
    boolean acceptFile = false;
    
    String[] acceptedFiles =
    {
        ".xlsx", ".csv"
    };
    List<File> files;
    @FXML
    private ListView<File> taskField;

    public String selectedDocument = "C:\\Users\\jacob\\Desktop\\Import_data.xlsx";
    @FXML
    private AnchorPane importWindow;
    @FXML
    private Button importbtn;
    
    private final ObservableList<File> filesAccepted
            = FXCollections.observableArrayList();
    
    private final ObservableList<File> filesNotAccepted
            = FXCollections.observableArrayList();
    
    private FilesConvertionModel fcModel;    
    public ObservableList<File> fileNames
            = FXCollections.observableArrayList();
    
    @FXML
    private Button adminButton;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        up.getInstance();
        try
        {
            bllManagerUL = BLLManagerUserLogin.getInstance();
        }
        catch (DalException ex)
        {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // for screen size
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        importWindow.setPrefSize(width, height - 65);
        importbtn.setDisable(true);
        adminButton.setVisible(false);
    }
 /*
    import files to the list view
     */
    @FXML
    private void importData(ActionEvent event) throws SQLException
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        // fileChooser.setInitialDirectory(new File("...")); only for windows
        files = fileChooser.showOpenMultipleDialog(new Stage());

        CompletableFuture.runAsync(() //starts the threadding 
                ->
        {
            filesAccepted.clear();
            for (File file : files)
            {
                acceptFile = false;
                for (String acceptedFile : acceptedFiles)
                {
                    if (file.getAbsolutePath().endsWith(acceptedFile))
                    {
                        filesAccepted.add(file);
                        acceptFile = true;
                    }

                }
                if (!acceptFile)
                {
                    filesNotAccepted.add(file);
                }
            }
            Platform.runLater(()
                    ->
            {
                fcModel.AddAllFiles(filesAccepted);
                for (File acceptedFile : filesNotAccepted)
                {
                    AlertWindow alertWindow
                            = new AlertWindow("File is not support yet", null, "This file " + "" + acceptedFile.getName() + "" + " cannot be added");
                }
                filesNotAccepted.clear();
            });
         
        });

    }


    @FXML //Loads exportMenu scene
    private void exportMenuSelect(Event event) throws SQLException
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
            Parent root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);

            Scene scene = new Scene(root);
            importWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
    }

    @FXML //Loads CustomData scene
    private void customDataMenuSelect(Event event) throws FileNotFoundException, ParseException, SQLException
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
            Parent root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
            importWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
    }

    @FXML //Loads the log view
    private void logMenuSelect(Event event) throws SQLException, IOException
    {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LogView.fxml"));
            Parent root = fxLoader.load();
            LogViewController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);

            importWindow.getChildren().setAll(root);
        
      
    }


    @FXML //loads the admin view
    private void adminMenuSelect(ActionEvent event) throws SQLException, IOException
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddUserView.fxml"));
        Parent root = fxLoader.load();
        AddUserViewController controller = fxLoader.getController();
        controller.setmodel(fcModel, modelData);
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setAlwaysOnTop(true);
        newStage.setTitle("Admin Window");
        newStage.setScene(scene);
        newStage.show();
    }

    void modelData(LoginDataModel modelData) throws SQLException
    {
        this.modelData = modelData;
        ul.setUserName(modelData.getUserLogin());
        ul.setAccessLevel(modelData.getUserAccessLevel());
    }

    void setmodel(FilesConvertionModel fcModel) throws SQLException
    {
        this.fcModel = fcModel;
        taskField.getItems().clear();
        taskField.setItems(fcModel.getFiles());
        isUserAdmin();
    }

    public String getTextNames()
    {
        fileNames = (ObservableList<File>) fcModel;
        return fileNames.toString();
    }

    private void isUserAdmin() throws SQLException
    {
        if (modelData.getUserAccessLevel() == true)
        {
            adminButton.setVisible(true);
        }
    }

    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) throws SQLException
    {
        this.fcModel = fcModel;
        taskField.getItems().clear();
        taskField.setItems(fcModel.getFiles());
        this.modelData = modelData;
        isUserAdmin();

        ul.setUserName(modelData.getUserLogin());
    }

    @FXML //logs the user out and returns you to the login screen
    private void logOut(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) adminButton.getScene().getWindow();
        stage.close();
        
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent root = fxLoader.load();
        LoginViewController controller = fxLoader.getController();
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setAlwaysOnTop(true);
        newStage.setTitle("Login Window");
        newStage.setScene(scene);
        newStage.show();
    }
}
