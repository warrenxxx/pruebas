/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

/**
 *
 * @author warren
 */
public class EditingCellDouble extends TableCell<Object, Object> {

    private TextField textField;
    private String validar = "([+-][0-9]|[0-9])[0-9]*([.][0-9]*)?";

    EditingCellDouble(boolean entero) {
        if(entero)
                    this.validar="([+-][0-9]|[0-9])[0-9]*";

    }

    @Override
    public void startEdit() {
        if (!isEmpty() && isEditable()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.textProperty().addListener((l, o, n) -> {
                Pattern pat = Pattern.compile(validar);
                if (n.compareTo("") != 0) {
                    Matcher mat = pat.matcher(n);
                    if (!mat.matches()) {
                        textField.setText(o);
                    }
                }
            });
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
                if (textField != null) {
                    textField.setText(getString());
               }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnAction((e) -> commitEdit(textField.getText()));
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                commitEdit(textField.getText());
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : String.valueOf(getItem());
    }

    @Override
    public void commitEdit(Object newValue) {
        double d = Double.parseDouble(String.valueOf(newValue));
        super.commitEdit(d); //To change body of generated methods, choose Tools | Templates.        
        crud x = (crud) getTableRow().getItem();
        x.update();
    }

}
