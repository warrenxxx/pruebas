/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.clases.CUsuario;
import app.clases.CArticulo;
import java.io.InputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import app.login.LoginController;
import app.principal.PrincipalController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WARREN
 */
public class Main extends Application{
    /**
     * @param args the command line arguments
     */
    public static conecion CONNE;

    public static CUsuario USUARIO;
    
    public Stage primariStage;
    public HConeccion hcon;
    public Node root;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {        
        this.primariStage=primaryStage;
        CONNE=new conecion();
        hcon=new HConeccion(null);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene((Parent) gotoLogin()));
        primaryStage.show();
        hcon.start();
        
        ArrayList r=new ArrayList();

            r=new CArticulo().lista();


       CUsuario x=new CUsuario();
       x.setNivel("0");
       x.setTipo("0");
       x.setUser("a");
       x.setPassword("a");
     //  x.insert();
  //      x.insert();

//        aux();
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        close();
    }

   
    
    private void change(Node n){
        this.primariStage.setScene(new Scene((Parent) n));
    }
    
    public void change_principla(CUsuario x){
        change(gotoPrincipal(x));
        this.primariStage.setMaximized(true);
    }
    
    private Node gotoLogin() {        
            try {
                LoginController login = (LoginController) replaceSceneContent("login/login.fxml");
                login.setApp(this);
                login.iniciar_hilo_coneccion();
            } catch (Exception ex) {
                System.out.println("try controlado por warren");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex); 
            }            
            return root;
    }
    
    private Node gotoPrincipal(CUsuario x) {        
            if(x==null)return null;
             try {
                PrincipalController nod = (PrincipalController) replaceSceneContent("principal/principal.fxml");
                nod.setApp(this);
                nod.iniciar_hilo_coneccion();
                nod.set_User(x);
            } catch (Exception ex) {
                System.out.println("try controlado por warren");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex); 
            }
            return  root;
    }
    
    public void close(){
        primariStage.close();
        hcon.cancel();
        System.exit(0);
    }
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();        
        InputStream in =Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        Node page;
        try {
            page =  loader.load(in);
        } finally {
            in.close();
        }
        root=page;
        return (Initializable) loader.getController();
    }
}
