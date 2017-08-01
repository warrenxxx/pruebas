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
public class NumerableCell extends TableCell<Object, Object> {

    public NumerableCell() {  
    }
    
    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
                setText(String.valueOf(getIndex()+1));
        }
    }

    
}
