/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.principal;

import app.Main;
import app.articulo.CrudArticulo;
import app.caja.CrudCaja;
import app.clases.CArticulo;
import app.clases.CSession;
import app.clases.CUsuario;
import app.compras.ComprasController;
import app.home.HomeController;
import app.reportes.SeccionesController;
import app.usuario.CrudUsuario;
import app.ventas.VentasController;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class PrincipalController implements Initializable {

    private Main application;
    private ArrayList<CArticulo> articulos;
    /**
     * Initializes the controller class.
     */

    @FXML
    Label desconectado;

    @FXML
    BorderPane ventana_principal;
    @FXML
    Button btnInicio,btnVentas,btnEntrada,btnUsuarios,btnProductos,btnSecciones,btnStock,btnMovimintos;

    private CUsuario USER;
    private CSession SESSION;
    

    Pair<Node,Initializable> home;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void actializar_articulos() {
        articulos = new CArticulo().lista();
    }

    public void setApp(Main application) {
        this.application = application;
    }

    public void set_User(CUsuario x) {
        this.USER = x;
        System.out.println(USER);
        btnEntrada.setDisable(true);
        btnProductos.setDisable(true);
        btnUsuarios.setDisable(true);

        if (this.USER.getTipo().compareTo("0") == 0) {
            btnEntrada.setDisable(false);
            btnProductos.setDisable(false);
            btnUsuarios.setDisable(false);

        }
        verificar_sessiones(x);
        cargar_panes();
    }   

    public void cargar_panes(){
        try {

            home=cargar("home/home.fxml");

            GridPane h1=(GridPane) home.getKey();
            HomeController h2=(HomeController) home.getValue();
            h2.setUser(USER);
            this.ventana_principal.setCenter(h1);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    public void verificar_sessiones(CUsuario x) {
        ArrayList sessiones = x.getSeccionAbiertas();
        if (sessiones.size() > 1) {
            CSession aux = new modal_VerificarSession().display(sessiones);
            if (aux == null) {
                application.primariStage.close();
            }
            SESSION=aux;
        }else
        if (sessiones.size() == 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Session ya abierta ");
            alert.setContentText("Ya ahy una cession avierta y los datos se registraran en esta.\n si quiere iniciar una seccion nueva cierre la caja y la seccion");
            alert.showAndWait();
            SESSION=(CSession) sessiones.get(0);
        }else{
            SESSION=new CSession(this.USER);
            SESSION.setEstado(true);
            SESSION.setFecha_inicio(new Date().getTime());
            SESSION.insert();
        }     
    }

    public void iniciar_hilo_coneccion() {
        this.application.hcon.set_node(desconectado);
        this.desconectado.prefWidthProperty().bind(ventana_principal.widthProperty());
    }
    // <editor-fold desc="Metodos del Mouse">       

    public void press(MouseEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
    }
    int x = 0, y = 0;

    public void drag(MouseEvent event) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        this.application.primariStage.setX(point.x - x);
        this.application.primariStage.setY(point.y - y);
    }
    //</editor-fold>

    public BorderPane borderPane;

    public void m_productos(ActionEvent ev) throws IOException {
        CrudArticulo c = new CrudArticulo();
        ventana_principal.setCenter(c.get_node());

    }

    public void m_categoria(ActionEvent ev) throws IOException {
        CrudUsuario c = new CrudUsuario();
        ventana_principal.setCenter(c.get_node());
    }

    public void ventas(ActionEvent ev) throws IOException {
        String url = "ventas/ventas.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(url);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(url));
        Node page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        VentasController crudcon = (VentasController) loader.getController();
        crudcon.setSession(SESSION);
        ventana_principal.setCenter(page);
    }

    public void usuarios(ActionEvent ev) throws IOException {
//        new rutas().tousuarios();
    }

    public void compras(ActionEvent ev) throws IOException {
        String url = "compras/compras.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(url);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(url));
        Node page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        ComprasController crudcon = (ComprasController) loader.getController();
        crudcon.setSession(SESSION);
        ventana_principal.setCenter(page);
    }

    public void desempacar(ActionEvent ev) {
        //      new vista_empaquetado().display() ;
//        borderPane.setCenter(root);
    }
   public void caja(ActionEvent ev) {
       GridPane gp=new GridPane();
       CrudCaja c=new CrudCaja(SESSION);
       gp.add( c.Inodo, 0,0);
       gp.add( c.Enodo, 1,0);
       double in=c.caja.sumarIngresosCaja(c.caja.itcaja);
       double eg=c.caja.sumarEgresosCaja(c.caja.etcaja);
       Label ti=new Label();
       Label te=new Label();       
       Label tt=new Label();
       ti.setId("labeles");
       te.setId("labeles");
       tt.setId("labeles");
       tt.setPrefWidth(Double.MAX_VALUE);
       tt.setAlignment(Pos.CENTER);
       c.gettotali().set(in);
       c.gettotale().set(eg);
       
       gp.add(ti,0,1);
       gp.add(te,1,1);
       gp.add(tt,0,2,2,1);
        
       GridPane.setFillWidth(tt, true);
       GridPane.setFillHeight(tt, true);
       
                    
       ti.textProperty().bind(new SimpleStringProperty("Total Ingreso es S/.").concat( c.gettotali().asString()));
       te.textProperty().bind(new SimpleStringProperty("Total Egreso es S/.").concat( c.gettotale().asString()));
       tt.textProperty().bind(new SimpleStringProperty("Sobrante o Faltante es S/.").concat( (c.gettotali().subtract(c.gettotale())).asString()));
       ventana_principal.setCenter(gp);       
    }

    public void ventas_detallado(ActionEvent ev) throws IOException {
        //    new rep_venta_detallado().display() ;

    }

    public void ventas_total(ActionEvent ev) throws IOException {
        //  new rep_venta_total().display() ;

    }

    public void stock_por_categoria(ActionEvent ev) throws IOException {
        //new stokc_por_categoria().display() ;

    }

    public void stock_por_organizacion(ActionEvent ev) throws IOException {
        //new stokc_por_Organizacion().display() ;

    }

    public void secciones(ActionEvent ev) throws IOException {
        String url = "reportes/secciones.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(url);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(url));
        Node page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        SeccionesController crudcon = (SeccionesController) loader.getController();
        crudcon.setSession(SESSION);
        ventana_principal.setCenter(page);
    }
    public Pair<Node,Initializable> cargar(String url) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(url);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(url));
        Node page=null;
        try {
            page = loader.load(in);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            in.close();
        }
        Initializable crudcon = loader.getController();
        return new Pair(page,crudcon);
    }
}
