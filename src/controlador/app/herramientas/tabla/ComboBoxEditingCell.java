/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;

/**
 *
 * @author warren
 */
public class ComboBoxEditingCell extends TableCell<Object, Object> {

    private ComboBox comboBox;
    ObservableList l;
    boolean editable;
    public ComboBoxEditingCell(ObservableList l,boolean editable) {  
        this.editable=editable;
        this.l=l;
        this.setEditable(true);        
    }

    @Override
    public void startEdit() {
        if (!isEmpty()&&isEditable()) {
            super.startEdit();
            createComboBox();
            setText(null);
            setGraphic(comboBox);
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
                if (comboBox != null) {
                    comboBox.setValue(getItem());
                }
                setText(String.valueOf(getItem()));
                setGraphic(comboBox);
            } else {
                setText(String.valueOf(getItem()));
                setGraphic(null);
            }
        }
    }

    private void createComboBox() {
        comboBox = new ComboBox<>(l);
        comboBox.setEditable(editable);
        //comboBoxConverter(comboBox);

        comboBox.valueProperty().set(getcontrol());
        comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        comboBox.setOnAction((e) -> {
            commitEdit(comboBox.getSelectionModel().getSelectedItem());
        });
    }

    private void comboBoxConverter(ComboBox comboBox) {
        // Define rendering of the list of values in ComboBox drop down. 
        comboBox.setCellFactory((c) -> {
            return new ListCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf( getItem()));
                    }
                }
            };
        });
    }

    private Object getcontrol() {
        return getItem() == null ? "": getItem();
    }

    @Override
    public void commitEdit(Object newValue) {
        super.commitEdit(newValue); //To change body of generated methods, choose Tools | Templates.
                crud x= (crud) getTableRow().getItem();
        x.update();
    }
    
}
