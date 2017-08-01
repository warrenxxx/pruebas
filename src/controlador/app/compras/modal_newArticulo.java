/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.compras;


import app.articulo.CrudArticulo;
import app.clases.CArticulo;
import app.clases.CMedida;
import app.herramientas.textfilter.TextFieldDouble;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ALIM
 */
public class modal_newArticulo {

    public void display()  {
        Stage wind = new Stage();
//        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("Elegir Productos Asignados");
 
        
        GridPane grid=new GridPane();
        AnchorPane pane=new AnchorPane();
        CrudArticulo c=new CrudArticulo();

        
        Scene SC = new Scene((Parent) c.get_node());
        wind.setScene(SC);
        wind.setWidth(950);
        wind.showAndWait();
    }

}
