/*
 * I don't care
 */
package app.caja;

import app.articulo.*;
import static app.Main.CONNE;
import app.clases.CArticulo;
import app.clases.CMedida;
import app.clases.CSession;
import app.clases.CUsuario;
import app.conecion;
import app.herramientas.tabla.table;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

/**
 *
 * @author Hasan Kara <hasan.kara@fhnw.ch>
 */
public class Main extends Application {

    private table table;
//    final HBox hb = new HBox();

    @Override
    public void start(Stage stage) {
        CONNE=new conecion();

        CUsuario user=new CUsuario();
         user.buscar("596fb6e6074df613bc5d71ff");
        CSession sc=new CSession(user);
        sc.setPosicion(0);

         CrudCaja crd=new  CrudCaja(sc) ;       
        Scene scene = new Scene(new Group());
        stage.setWidth(550);
        stage.setHeight(550);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));     
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(crd.get_node());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }










}