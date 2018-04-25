/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.JSON;

/**
 *
 * @author jacob
 */
public interface IConvertingToJSON {
    /*
   "type": "SAP import field -> 'Order Type'"
    */
    public JSON convertedToJSON();
  
}
