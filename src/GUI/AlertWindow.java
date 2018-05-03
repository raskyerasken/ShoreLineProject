/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Alert;

/**
 *
 * @author jacob
 */
public class AlertWindow {
/*
    Create alert window with title header and message
    */
    public AlertWindow(String title, String header,String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
   
    }
    
}
