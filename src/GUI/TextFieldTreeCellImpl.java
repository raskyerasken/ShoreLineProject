package GUI;


import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

  final class TextFieldTreeCellImpl extends TreeCell<String> {
 
        private TextField textField;
 
        public TextFieldTreeCellImpl() {
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
<<<<<<< HEAD
        
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
=======
    }
>>>>>>> 7ab353bcbc62f3fe60e1d7659e8fd36ee8b625fa
