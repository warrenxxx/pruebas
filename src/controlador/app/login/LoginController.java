/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.login;

import java.awt.MouseInfo;
import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import app.Main;
import app.clases.CUsuario;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    TextField user;
    @FXML
    PasswordField password;
    @FXML
    Button login,cancel;
    @FXML
    Label error;
    @FXML
    VBox ventana;
    @FXML
    ProgressIndicator progress;    
    @FXML
    ImageView con_estado;
    
    private Main application;    
    private ServiceUser service=new ServiceUser();

        
     // <editor-fold desc="Override Inicio">    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ini_service();   
    }
    public void iniciar_hilo_coneccion(){
        this.application.hcon.set_node(con_estado);
    }
    //</editor-fold>
     // <editor-fold desc="Iniciar servicio">    
   
    public void ini_service(){
        progress.progressProperty().bind(service.progressProperty());
        progress.visibleProperty().bind(service.runningProperty());
        service.setOnSucceeded(e->{
            CUsuario x=service.getValue();
            if(x.getError()!=null){
                error.setText(x.getError());error.setVisible(true);
            }
            else {     
                this.application.change_principla(x);
            }
            login.setDisable(false);
        });
        
        service.setOnCancelled(e->{
            System.out.println("cancel");
        });
        service.setOnFailed(e->{
            System.out.println("errr");
        });
    }
    //</editor-fold>
    
     // <editor-fold desc="Metodos auxiliares">    
   
    public void setApp(Main application){
        this.application = application;

    }
            
    public void entrar(ActionEvent event) throws InterruptedException {
        service.user=user.getText();
        service.pass=password.getText();
        service.restart();
        login.setDisable(true);
    }
    public void cerrar(ActionEvent event) {
        application.close();
    }
    
    //</editor-fold>
    
     // <editor-fold desc="Metodos del Mouse">    
   
    public void press(MouseEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
    }
    int x=0,y=0;
    public void drag(MouseEvent event) {        
        Point point = MouseInfo.getPointerInfo().getLocation();        
        this.application.primariStage.setX(point.x-x);
        this.application.primariStage.setY(point.y-y);
    }
    
    //</editor-fold>
}
