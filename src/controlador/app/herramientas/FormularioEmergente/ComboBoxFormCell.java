/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.FormularioEmergente;

import app.herramientas.tabla.*;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author warren
 */
public class ComboBoxFormCell extends ListCell<comboPropertys> {

    public ComboBoxFormCell() {
    }

    @Override
    public void updateItem(comboPropertys item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            HBox hb;
            TextField t=new TextField("");
            Label l= new Label();;
            hb = new HBox(l,t);

            String x = item.nombreProperty().getValue();
            String y = item.valorProperty().getValue();

            item.valorProperty().bindBidirectional(t.textProperty());
            l.setText(x);
            t.setPromptText(y);
            setGraphic(hb);
        }
    }
}
