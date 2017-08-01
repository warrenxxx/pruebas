/*
 * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.principal;

import app.articulo.*;
import app.clases.CSession;
import app.herramientas.textfilter.TextFieldDouble;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ALIM
 */
public class modal_VerificarSession {

    CSession res=null;


    public CSession display(ArrayList<CSession> sessiones) {
        Dialog<CSession> dialog = new Dialog<>();        
        dialog.setTitle("Seciones Abiertas");
        dialog.setHeaderText("Escoger la Seccion en la que se encuentra");

        dialog.setGraphic(new ImageView (Main.class.getResource("/app/login.png").toString()));

        ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        ListView l=new ListView();
        l.prefWidthProperty().bind(grid.widthProperty());
        for(CSession s:sessiones){
            l.getItems().add(s);
        }
        
        
        TextField  opcion= new TextField(); 
        l.setOnMouseClicked(e->{
            if(l.getSelectionModel().getSelectedItem()!=null){
                opcion.setText(l.getSelectionModel().getSelectedItem().toString());
                res= (CSession) l.getSelectionModel().getSelectedItem();
            }
        });
        opcion.setEditable(false);
        
        opcion.setPromptText("Cantidad");
        l.prefHeightProperty().bind(Bindings.size(l.getItems()).multiply(22));

        grid.add(l, 1, 0);
        grid.add(opcion, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        opcion.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

// Do some validation (using the Java 8 lambda syntax).

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter((param) -> {
            if (param == loginButtonType) {
                return res;
            }
            return null; //To change body of generated lambdas, choose Tools | Templates.
        });
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return res;
            }
            return null;
        });

        Optional<CSession> result = dialog.showAndWait();

        result.ifPresent(medida -> {
        });

        return res;
    }
}
