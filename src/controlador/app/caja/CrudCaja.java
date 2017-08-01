/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.caja;

import static app.Main.CONNE;

import app.clases.CCaja;
import app.clases.CMedida;
import app.clases.CMoneda;
import app.clases.CMovimiento;
import app.clases.CSession;
import app.clases.CUsuario;
import app.conecion;
import app.herramientas.tabla.table;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.util.Pair;
import javax.swing.table.TableColumn;
import org.bson.types.ObjectId;

/**
 *
 * @author warren
 */
public class CrudCaja {

    public Node Inodo, Enodo;
    public Initializable Icontroler, Econtroller;
    public CCaja caja;
    public final SimpleDoubleProperty totali,totale;
    public DoubleProperty gettotali(){
        return totali;
    }
    public DoubleProperty gettotale(){
        return totale;
    }
    
    public CrudCaja(CSession s) {
        caja = new CCaja(s);
        caja.listarTcaja();

        ini_egresos(s);
        ini_ingresos(s);
        totali=new SimpleDoubleProperty(0);
        totale=new SimpleDoubleProperty(0);
    }

    public void ini_ingresos(CSession session) {
        ITCaj D;
        CCaja x;
        D = new ITCaj(new CCaja(session));
        CUsuario user = new CUsuario();
        x = new CCaja(null, session);
        table table;
        table = new table(D, caja.itcaja);
        CCaja ventas=new CCaja();
        ventas.setDescripcion("TOTAL VENTAS");
        ventas.setIngreso();
        ventas.setMonedas(new CMoneda("Soles", 1, new CMovimiento(session).sumTotalVentas()));
        table.add_Item(new ITCaj(ventas));
        table.addEditingCellCrud("Descripcion", "descripcion", D.descripcionProperty());
        table.addButtonColumnCrud("Cantidad", "monedas", D.monedasProperty(), new Callable() {
            @Override
            public Object call() throws Exception {
                CMoneda m = new modal_moneda().display();
                return m;
            }
        });
        table.addRemoveButtonCrud();

        ((javafx.scene.control.TableColumn) table.getColumns().get(0)).prefWidthProperty().bind(
                table.widthProperty()
                        .subtract(((javafx.scene.control.TableColumn) table.getColumns().get(1)).widthProperty())
                        .subtract(((javafx.scene.control.TableColumn) table.getColumns().get(2)).widthProperty())
                        .subtract(2) // a border stroke?
        );
        table.getItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
              double k=new CCaja().sumarIngresosCaja(table.getItems());
              totali.set(k);
            }
        });
        try {
            Pair<Initializable, Node> xx = table.crud("Tabla de Ingresos");
            this.Inodo = xx.getValue();
            this.Icontroler = xx.getKey();
        } catch (IOException ex) {
            Logger.getLogger(CrudCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ini_egresos(CSession session) {
        ETCaj D;
        CCaja x;
        D = new ETCaj(new CCaja(session));
        CUsuario user = new CUsuario();
        x = new CCaja(null, session);
        table table;
        table = new table(D, caja.etcaja);
        table.addEditingCellCrud("Descripcion", "descripcion", D.descripcionProperty());
        table.addButtonColumnCrud("Cantidad", "monedas", D.monedasProperty(), new Callable() {
            @Override
            public Object call() throws Exception {
                CMoneda m = new modal_moneda().display();
                return m;
            }
        });
        table.addRemoveButtonCrud();        
        ((javafx.scene.control.TableColumn) table.getColumns().get(0)).prefWidthProperty().bind(
                table.widthProperty()
                        .subtract(((javafx.scene.control.TableColumn) table.getColumns().get(1)).widthProperty())
                        .subtract(((javafx.scene.control.TableColumn) table.getColumns().get(2)).widthProperty())
                        .subtract(2) // a border stroke?
        );
        table.getItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
              double k=new CCaja().sumarEgresosCaja(table.getItems());
              totale.set(k);
            }
        });
        try {
            Pair<Initializable, Node> xx = table.crud("Tabla de Egresos");
            this.Enodo = xx.getValue();
            this.Econtroller = xx.getKey();
        } catch (IOException ex) {
            Logger.getLogger(CrudCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Node get_node() {
        return this.Inodo;
    }

}
