/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.usuario;

import app.clases.CPersona;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author warren
 */
public class FXMLController implements Initializable {

    @FXML ComboBox tipodoc,sexo;
    @FXML TextField ndoc,nombres,apellidos,email,direccion;
    @FXML DatePicker nacimiento;
    @FXML Button aceptar,cancelar;
    CPersona p;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipodoc.getItems().addAll("DNI","RUC","VISA","OTROS");
        tipodoc.getSelectionModel().select(0);
        sexo.getItems().addAll("Hombre","Mujer");
        sexo.getSelectionModel().select(0);
        nacimiento.setValue(LocalDate.now());
        p=new CPersona();
    }    
    public CPersona aceptar(){    
        p.setApellidos(apellidos.getText());
        p.setDireccion(direccion.getText());
        p.setEmail(email.getText());
        p.setFecha_nacimiento(Date.from(nacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        p.setNombre(nombres.getText());
        p.setSexo(sexo.getSelectionModel().getSelectedIndex());
        p.setnroDocumento( ndoc.getText());
        p.setTipoDocumento(tipodoc.getSelectionModel().getSelectedIndex());        
        return p;
    }    
    public Button ac(){
        return aceptar;        
    }
    public Button can(){
        return cancelar;
    }
}
