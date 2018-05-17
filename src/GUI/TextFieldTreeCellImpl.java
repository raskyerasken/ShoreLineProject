package GUI;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

 final class TextFieldTreeCellImpl extends TreeCell<String> {
 
        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();
        public TextFieldTreeCellImpl() {
            MenuItem addMenuItem = new MenuItem("add rule");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler()
                    {
                        public void handle(Event t) 
                    {
                        TreeItem newRule = 
<<<<<<< HEAD
                                new TreeItem<String>("New rule");
=======
                                new TreeItem<String>("New Rule");
>>>>>>> 6f8e86c1b33eec2e13ccafde9d9c05fe870f0dd7
                                    getTreeItem().getChildren().add(newRule);
                    }
            });
//            
            MenuItem mumu = new MenuItem("add class");
            addMenu.getItems().add(mumu);
            mumu.setOnAction(new EventHandler()
                    {
                public void handle(Event t) 
                    {
                    TreeItem newClass = 
                            new TreeItem<String>("New Class");
                    TreeItem newRule = 
                            new TreeItem<String>("New Rule");
                    newClass.getChildren().add(newRule);
                            getTreeItem().getChildren().add(newClass);
                }
            });
            
            
        }
 
        @Override
        public void startEdit() {
            super.startEdit();
 
            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
             super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (
                        !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                    ){
                        
                        setContextMenu(addMenu);
                    }
//                    
                }
            }
        
        }
        
        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
 
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });  
            
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }