package GUI;


import javafx.event.Event;
import javafx.event.EventHandler;
<<<<<<< HEAD
=======
import javafx.event.EventType;
>>>>>>> d43076ef2b35c32041fafcd89c7e8249a40b1317
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
<<<<<<< HEAD
 
        public TextFieldTreeCellImpl() {
            MenuItem addMenuItem = new MenuItem("Add Employee");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                  TreeItem newEmployee = 
                        new TreeItem<String>("New Employee");
                            getTreeItem().getChildren().add(newEmployee);}
            });
=======
        private TreeItem addTree = new TreeItem();
 
        public TextFieldTreeCellImpl() {
            MenuItem addMenuItem = new MenuItem("add rule");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler()
                    {
                        public void handle(Event t) 
                    {
                        TreeItem newRule = 
                                new TreeItem<String>("New Class");
                                    getTreeItem().getChildren().add(newRule);
                    }
            });
            
            TreeItem addTreeItem = new TreeItem("Add Class");
            addTree.getChildren().add(addTreeItem);
            addMenuItem.setOnAction(new EventHandler()
                    {
                public void handle(Event t) {
                    TreeItem newClass = 
                            new TreeItem<String>("New Class");
                            getTreeItem().getChildren().add(addTree);
                }
            });
            
            
            
            
            
>>>>>>> d43076ef2b35c32041fafcd89c7e8249a40b1317
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
                }
                
                if (!getTreeItem().isLeaf()&&getTreeItem().getParent()!=null)
                {
                    setContextMenu(addMenu);
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