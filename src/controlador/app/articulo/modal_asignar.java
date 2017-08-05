/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.articulo;

import app.clases.CMedida;
import app.herramientas.tabla.table;
import app.herramientas.textfilter.TextFieldDouble;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    ArrayList<CMedida> res = null;

    public ArrayList<CMedida> display(ArrayList<CMedida> c) {
        try {
            Dialog<ArrayList<CMedida>> dialog = new Dialog<>();
            dialog.setTitle("Unidad y propiedades para Ariticulo");
            dialog.setHeaderText("Designe el Nombre de la unidad y la cantidad");
            dialog.setWidth(900);
            ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            ObservableList l = FXCollections.observableArrayList();
            for (CMedida m : c) {
                l.add(new TMedidda(m));
            }
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TMedidda daux=new TMedidda(new CMedida());
            table a = new table(daux,l);

            TMedidda t = new TMedidda(new CMedida());

            a.addEditingCellCrud("Codigo", "codigo",daux.codigoProperty());
            a.addComboBoxEditingCellCrud(new CMedida().getNombres(), "Medida", "medida", true,daux.medidaProperty());
            a.addEditingCellDoubleCrud("Medida Cantidad", "cantidad",daux.cantidadProperty());
            a.addEditingCellDoubleCrud("Costo", "costo",daux.costoProperty());
            a.addEditingCellDoubleCrud("Precio Dia", "pdia",daux.pdiaProperty());
            a.addEditingCellDoubleCrud("Precio Noche", "pnoche",daux.pnocheProperty());
            a.addEditingCellDoubleCrud("Stock", "stock",daux.stockProperty());
            grid.add(a.crud("Medidas").getValue(), 0, 0);

            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(false);

// Do some validation (using the Java 8 lambda syntax).
            dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//        Platform.runLater(() -> nombre.requestFocus());
// Convert the result to a username-password-pair when the login button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    res = new ArrayList<CMedida>();
                    for (Object x : a.getItems()) {
                        TMedidda med = (TMedidda) x;
                        CMedida d = med.toElement();
                        res.add(d);
                    }
                    return res;
                }
                return null;
            });

            Optional<ArrayList<CMedida>> result = dialog.showAndWait();

            result.ifPresent(medida -> {
                res = medida;
            });

            return res;
        } catch (IOException ex) {
            Logger.getLogger(modal_asignar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
