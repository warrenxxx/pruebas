/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author warren
 */
public abstract class ButtonEditingCell extends TableCell<Object, Object> {

    private Button button;
    public ButtonEditingCell() {  
        this.setEditable(true);
    }
    
    @Override
    public void startEdit() {
        if (!isEmpty()&&isEditable()) {
            super.startEdit();
            createButton();
            setText(null);
            setGraphic(button);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(String.valueOf(getItem()));
        setGraphic(null);
    }

    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (button != null) {
                    button.setText(String.valueOf(getItem()));
                }
                setText(String.valueOf(getItem()));
                setGraphic(button);
            } else {
                setText(String.valueOf(getItem()));
                setGraphic(null);
            }
        }
    }

    private void createButton() {
        button = new Button("Editar");
        //buttonConverter(button);
        //button.valueProperty().set(getcontrol());
        //button.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        
        button.setOnAction((e) -> {
            commitEdit(action(this.getTableRow().getItem()));
        });
    }
    public abstract Object action(Object h);

    private Object getcontrol() {
        return getItem() == null ? "": getItem();
    }
    public Button getButton (){
        return this.button;
    }

    @Override
    public void commitEdit(Object newValue) {
        if(newValue==null)return;
        super.commitEdit(newValue); //To change body of generated methods, choose Tools | Templates.
        crud x= (crud) getTableRow().getItem();

        x.update();
    }
    
}
