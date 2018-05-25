/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import GUI.Models.UpdateLogViewModel;
import BE.UpdateLog;
import BE.UserLogin;
import static GUI.LogViewController.lines;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class LogViewController implements Initializable 
{

    UserLogin ul = new UserLogin();
    LoginDataModel modelData;
    UpdateLogViewModel model = new UpdateLogViewModel();
    public static final ObservableList lines
            = FXCollections.observableArrayList();
    int fileLinesNumber = 2;
    @FXML
    private JFXTextField searchTxt;
    boolean search = false;
    FilteredList<String> searchData
            = new FilteredList<>(lines, p -> true);
    private FilesConvertionModel fcModel;
    @FXML
    private TableView<UpdateLog> LogView;
    @FXML
    private TableColumn<UpdateLog, String> userNameTable;
    @FXML
    private TableColumn<UpdateLog, Date> timeTable;
    @FXML
    private TableColumn<UpdateLog, String> adjustment;
    @FXML
    private TableColumn<UpdateLog, Boolean> error;
    @FXML
    private AnchorPane exportWindow;
    @FXML
    private Button logMenuSelect;
    @FXML
    private Button adminButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        adminButton.setVisible(false);
        logMenuSelect.setDisable(true);
        addColumsToTableView();   //get all the colums
        LogView.setItems(model.getUpdateLogToList());
        Thread t = new Thread(()
                -> 
        {
            model.logListUpdate();
            searchLogView();
            errorColor();
        });
        t.start();
    }
    
    private void errorColor()
    {
        LogView.setRowFactory(row -> new TableRow<UpdateLog>()
        {
            @Override
            public void updateItem(UpdateLog item, boolean empty)
            {
                super.updateItem(item, empty);

                if (item == null || empty) 
                {
                    setStyle("");
                } 
                else 
                {
                    //Now 'item' has all the info of the user in this row
                    if (item.isError() == true) 
                    {System.out.println("(item.isError() == true)");
                        //We apply now the changes in all the cells of the row
                        for(int i=0; i<getChildren().size();i++)
                        {System.out.println("forloop");
                            ((Labeled) getChildren().get(i)).setTextFill(Color.RED);
                        }                        
                    } 
                    else 
                    {
                        if(getTableView().getSelectionModel().getSelectedItems().contains(item))
                        {
                            for(int i=0; i<getChildren().size();i++)
                            {
                                ((Labeled) getChildren().get(i)).setTextFill(Color.WHITE);;
                            }
                        }
                        else
                        {
                            for(int i=0; i<getChildren().size();i++)
                            {
                                ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);;
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void addColumsToTableView()
    {
        userNameTable.setCellValueFactory(new PropertyValueFactory("Username"));
        timeTable.setCellValueFactory(new PropertyValueFactory("Datelog"));
        adjustment.setCellValueFactory(new PropertyValueFactory("Adjustment"));
        error.setCellValueFactory(new PropertyValueFactory("Error"));
    }

    private void searchLogView() 
    {
        FilteredList<UpdateLog> filteredData;
        filteredData = new FilteredList<>(model.getUpdateLogToList(), p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue)
                -> 
        {
            filteredData.setPredicate(updateLog
                    -> 
            {
                if (newValue == null || newValue.isEmpty()) 
                {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (updateLog.getUsername().toLowerCase().contains(lowerCaseFilter)) 
                {
                    return true;
                } else if (updateLog.getDatelog().toString().contains(lowerCaseFilter)) 
                {
                    return true; // Filter matches last name.
                }

                return false;

            });

        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<UpdateLog> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(LogView.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        LogView.setItems(sortedData);
    }

    @FXML
    private void importMenuSelect(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try 
        {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            exportWindow.getChildren().setAll(root);
        } 
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show ImoportView");
        }
    }

    @FXML
    private void exportMenuSelect(ActionEvent event) throws SQLException 
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
        Parent root;
        try 
        {
            root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            exportWindow.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void customDataMenuSelect(ActionEvent event) throws FileNotFoundException, ParseException, SQLException 
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
        Parent root;
        try 
        {
            root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            exportWindow.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show CustomData");
        }
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) throws SQLException 
    {
        try 
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddUserView.fxml"));
            Parent root = fxLoader.load();
            AddUserViewController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            controller.setmodel(fcModel);
            controller.modelData(modelData);
            exportWindow.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
    }

    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) throws SQLException 
    {
        this.fcModel = fcModel;
        this.modelData = modelData; 
        isUserAdmin();
    }
    
    private void isUserAdmin() throws SQLException
    {
        System.out.println(modelData.getUserAccessLevel());
        if (modelData.getUserAccessLevel() == true) 
        {
            adminButton.setVisible(true);
        }
    }

}
