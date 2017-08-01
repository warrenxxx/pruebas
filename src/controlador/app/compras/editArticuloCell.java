/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.compras;

import app.clases.CArticulo;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author warren
 */
public class editArticuloCell extends TableCell<Object, Object> {

    private Button button;
    public editArticuloCell() {  
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
        button.setOnAction((e) -> {
                CArticulo art=((TArticuloCompra)this.getTableRow().getItem()).getA();
                    Object h=new modal_Articulo().display(art);
                    String text=((TArticuloCompra)this.getTableRow().getItem()).nombreProperty().get();
                if(h!=null){
                    CArticulo ART=(CArticulo) h;
                    ((TArticuloCompra)(this.getTableRow().getItem())).setArticulo(ART);            
                    this.setGraphic(null);               
                    ((TArticuloCompra)(this.getTableRow().getItem())).precioProperty().set(ART.getPrecio());            
                    commitEdit(art.getCategoria()+" "+art.getNombre()+" "+art.getDescripcion()+" "+art.getMedida());                        
                }else {
                    this.setGraphic(null);
                    this.setText(text);
                }
        });
    }
    public Object action(){
            return null;
    }

    private Object getcontrol() {
        return getItem() == null ? "": getItem();
    }
    public Button getButton (){
        return this.button;
    }    
}
