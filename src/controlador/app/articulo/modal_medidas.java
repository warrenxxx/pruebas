/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.articulo;

import app.compras.*;
import app.articulo.CrudArticulo;
import app.clases.CArticulo;
import app.clases.CMedida;
import app.herramientas.tabla.table;
import app.herramientas.textfilter.TextFieldDouble;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ALIM
 */
public class modal_medidas {

    public ArrayList<CMedida> display(ArrayList<CMedida> c) {
        Stage wind = new Stage();
//        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("Medidas");
        ObservableList l = FXCollections.observableArrayList();
        for (CMedida m : c) {
            l.add(new TMedidda(m));
        }
        TMedidda daux = new TMedidda(new CMedida());
        table a = new table(daux, l);

        TMedidda t = new TMedidda(new CMedida());

        a.addEditingCellCrud("Codigo", "codigo", daux.codigoProperty());
        a.addComboBoxEditingCellCrud(new CMedida().getNombres(), "Medida", "medida", true, daux.medidaProperty());
        a.addEditingCellDoubleCrud("Medida Cantidad", "cantidad", daux.cantidadProperty());
        a.addEditingCellDoubleCrud("Costo", "costo", daux.costoProperty());
        a.addEditingCellDoubleCrud("Precio Dia", "pdia", daux.pdiaProperty());
        a.addEditingCellDoubleCrud("Precio Noche", "pnoche", daux.pnocheProperty());
        a.addEditingCellDoubleCrud("Stock", "stock", daux.stockProperty());

        Scene SC = null;
        try {
            SC = new Scene((Parent) a.crud("Medidas").getValue());
        } catch (IOException ex) {
            Logger.getLogger(modal_medidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        wind.setScene(SC);
//        wind.setWidth(950);
        wind.showAndWait();
        ArrayList res = new ArrayList<CMedida>();
        for (Object x : a.getItems()) {
            TMedidda med = (TMedidda) x;
            CMedida d = med.toElement();
            res.add(d);
        }
        return res;
    }

}
