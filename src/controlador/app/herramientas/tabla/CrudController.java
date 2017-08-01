/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author warren
 */
public class CrudController implements Initializable {
    @FXML
    GridPane pane;
    @FXML
    Label titulo;
    @FXML
    HBox hbox;
    @FXML
    TextField filtro;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void setTitle(String a){
        this.titulo.setText(a);        
    }
    public GridPane getPane(){
        return pane;
    }
    public HBox getHbox(){
        return this.hbox;
    }
    public TextField filtro(){
        return filtro;
    }
}
