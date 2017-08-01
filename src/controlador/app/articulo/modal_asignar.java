/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.articulo;

import app.clases.CMedida;
import app.herramientas.textfilter.TextFieldDouble;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ALIM
 */
public class modal_asignar {

    CMedida res=null;

    public CMedida display() {
        Dialog<CMedida> dialog = new Dialog<>();        
        dialog.setTitle("Unidad para Ariticulo");
        dialog.setHeaderText("Designe el Nombre de la unidad y la cantidad");

        dialog.setGraphic(new ImageView (Main.class.getResource("/app/login.png").toString()));

        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        
        ComboBox nombre = new ComboBox(new CMedida().getNombres());        
        nombre.setPromptText("Nombre");
        nombre.setEditable(true);

        

        TextFieldDouble cantidad  = new TextFieldDouble();
        cantidad.setPromptText("Cantidad");

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nombre, 1, 0);
        grid.add(new Label("Cantidad:"), 0, 1);
        grid.add(cantidad, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(false);

// Do some validation (using the Java 8 lambda syntax).

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> nombre.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                res=new CMedida(nombre.getSelectionModel().getSelectedItem().toString(),Double.parseDouble( cantidad.getText()));
                return res;
            }
            return null;
        });

        Optional<CMedida> result = dialog.showAndWait();

        result.ifPresent(medida -> {
            res=medida;
            
        });

        return res;
    }
}
