/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author kasper
 */
public class TextFieldTreeCellImpl extends TreeCell<String>{
    private TextField textField;
    private ContextMenu addMenu = new ContextMenu();
    
    MainWindowController mwc = new MainWindowController();
    
    public TextFieldTreeCellImpl()
    {
        MenuItem addMenuItem = new MenuItem("Add Class");
        addMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction(new EventHandler() 
                {
                    public void handle(Event t) 
                {
                    TreeItem newClass =
                            new TreeItem<String>("new Class");
                                getTreeItem().getChildren().add(newClass);
                }
                });
    }
    
    @Override
    public void startEdit()
    {
        super.startEdit();
        
        if (textField == null)
        {
            createTextField();
        }
        
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }
    
    public void cancelEdit()
    {
        super.cancelEdit();
        setText((String) getItem());
        setGraphic(getTreeItem().getGraphic());
    }
    
   @Override
   public void updateItem(String item, boolean empty)
   {
       super.updateItem(item, empty);
       
       if (empty) 
       {
           setText(null);
           setGraphic(null);
       }
       
       else 
       {
          if (isEditing()) 
          {
              if (textField != null)
              {
                  textField.setText(getString());
              }
             setText(getString());
             setGraphic(getTreeItem().getGraphic());
             if ( !getTreeItem().isLeaf()&&getTreeItem().getParent()!=null)
             {
                 setContextMenu(addMenu);
             }
          }   
       }
   }
   
   private void createTextField()
   {
       textField = new TextField(getString());
       textField.setOnKeyReleased(new EventHandler<KeyEvent>()
       {
           @Override
           public void handle(KeyEvent t) 
           {
               if (t.getCode() == KeyCode.ENTER)
               {
                   commitEdit(textField.getText());
               }
               
               else if (t.getCode() == KeyCode.ESCAPE)
               {
                   cancelEdit();
               }
           }
       });
       
      
   }
   
   private String getString() 
       {
           return getItem() == null ? "" : getItem().toString();
       }
}
