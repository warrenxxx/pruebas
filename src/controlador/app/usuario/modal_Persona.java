/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.usuario;


import app.Main;
import app.clases.CPersona;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ALIM
 */
public class modal_Persona {
    CPersona p;
    public CPersona display() throws IOException {
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("Elegir Productos Asignados");
        
        String url = "usuario/FXML.fxml";
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
        FXMLController crudcon = (FXMLController) loader.getController();
        
        Button ac=crudcon.ac();
        ac.setOnAction(e->{
            p=crudcon.aceptar();
            wind.close();
        });
        Button cs=crudcon.can();
        cs.setOnAction(e->{
            p=null;
            wind.close();
        });

        Scene SC = new Scene((Parent) page);
        wind.setScene(SC);
        wind.showAndWait();
        return p;
    }

}
