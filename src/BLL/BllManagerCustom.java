/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.JSONCustommize;
import DAL.DataBaseCustom;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class BllManagerCustom {
    DataBaseCustom Dalcustom = new DataBaseCustom();
    public void add(JSONCustommize custom) throws SQLException {
        Dalcustom.add(custom);
    }

    public ObservableList getAllCustom() {
    return Dalcustom.getAllCustom();
    }
    
}
