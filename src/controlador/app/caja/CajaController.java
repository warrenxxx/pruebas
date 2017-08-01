/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.caja;

import app.clases.CSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author warren
 */
public class CajaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
            TableView t1,t2;

    public CSession x;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
          
    public void setSeccion(CSession x){
        this.x=x;
        CrudCaja c=new CrudCaja(x);

    }

}
