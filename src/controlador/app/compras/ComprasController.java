/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.compras;

import app.clases.CArticulo;
import app.clases.CMovimiento;
import app.clases.CPersona;
import app.clases.CSession;
import app.herramientas.FormularioEmergente.ComboForm;
import app.herramientas.tabla.table;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author warren
 */
public class ComprasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    GridPane ventana;
    @FXML
    ComboBox modoPago;
    @FXML
    Label total;
    @FXML
    AnchorPane normal,editable;
    
    CSession session;
    ComboForm cliente;

    ObservableList<TArticuloSearch> articulos = FXCollections.observableArrayList();
    ObservableList<TArticuloSearch> articulos_filtro = FXCollections.observableArrayList();
    ObservableList<TArticuloCompra> ventas = FXCollections.observableArrayList();
    table t;
    TextField filtro;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenar_tablas();
        tabla_busqueda();
        tabla_ventas();
        tableVentasEspecificar();
        ini_combos();
        ini_buton_nuevo();
        Platform.runLater(()->filtro.requestFocus());
    }
    
    public void setSession(CSession session) {
        this.session = session;
    }

    public void ini_combos() {
        ArrayList al = new ArrayList();
        al.add("DNI");
        al.add("NOMBRES");
        al.add("DIRECCION");

        cliente = new ComboForm(al);
        cliente.setPromptText("Cliente con Datos");
        ventana.add(cliente, 0, 1, 1, 1);
        modoPago.setItems(FXCollections.observableArrayList(
                "efectivo", "visa"
        ));
        modoPago.getSelectionModel().select(0);
    }

    public void llenar_tablas() {
        ArrayList<CArticulo> l = new CArticulo().lista();
        l.forEach((x) -> {
            articulos.add(new TArticuloSearch(x));
        });                  
        articulos_filtro.addAll(articulos);
    }

    public void tabla_busqueda() {
        filtro = new TextField();
        filtro.textProperty().addListener((l, o, n) -> {
            articulos_filtro.clear();
            for (TArticuloSearch x : articulos) {
                if (x.search(n)) {
                    articulos_filtro.add(x);
                }
            }
        });

        filtro.setPromptText("Buscar");
        TableView<TArticuloSearch> lista=new TableView(articulos_filtro);
        TableColumn t1=new TableColumn("Nombre");
        t1.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        TableColumn t2=new TableColumn("Stock");
        t2.setCellValueFactory(new PropertyValueFactory("stock"));
        
        TableColumn t3=new TableColumn("Costo");
        t3.setCellValueFactory(new PropertyValueFactory("precio"));
        lista.getColumns().addAll(t1,t2,t3);
        
        lista.setOnMouseClicked((MouseEvent e) -> {
            if(lista.getSelectionModel().getSelectedItem()!=null)
                agregar_articulo(lista.getSelectionModel().getSelectedItem().getArticulo());
            filtro.requestFocus();
        });
        filtro.setOnAction((e) -> {
            if(lista.getItems().size()!=0){
                agregar_articulo(lista.getItems().get(0).getArticulo());
            }
            filtro.setText("");
            filtro.requestFocus();
        });
        ventana.add(filtro, 4, 2, 3, 1);
        ventana.add(lista, 4, 3, 3, 2);
    }

    public void tabla_ventas() {
        t = new table(null, ventas);
        t.setPrefWidth(800);
        t.addNumerableTable();
        t.addEditingCellDouble("Cantidad", "cantidad");
        t.addEditingCell("Codigo", "codigo");
//        t.addEditingCell("Nombre", "nombre");
/*
        TableColumn tnombre=new TableColumn("Nombre");
        tnombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                = (TableColumn<Object, Object> param) -> new editArticuloCell();

        tnombre.setCellFactory(cellFactory);
        t.getColumns().add(tnombre);
  */      t.addEditingCellDouble("Precio", "precio");
        t.addEditingCellDouble("Total", "total");
        t.addRemoveButto();
        ventana.add(t, 0, 2, 4, 4);
    }

    public void tableVentasEspecificar() {
        ((TableColumn) t.getColumns().get(2)).setPrefWidth(120);
        ((TableColumn) t.getColumns().get(3)).prefWidthProperty().bind(
                        t.widthProperty()
                        .subtract(((TableColumn) t.getColumns().get(0)).widthProperty())
                        .subtract(((TableColumn) t.getColumns().get(1)).widthProperty())
                        .subtract(((TableColumn) t.getColumns().get(2)).widthProperty())
                        .subtract(((TableColumn) t.getColumns().get(4)).widthProperty())
                        .subtract(((TableColumn) t.getColumns().get(5)).widthProperty())
                        .subtract(((TableColumn) t.getColumns().get(6)).widthProperty())
                        .subtract(2) // a border stroke?
        );
        ((TableColumn) t.getColumns().get(5)).setEditable(false);
        t.setOnMouseClicked(e->{
        ((TableColumn) t.getColumns().get(2)).setPrefWidth(115);        
        });
       
    }

    public void registrar(Event e) {
        CMovimiento m = new CMovimiento(this.session);
        m.setHora(new Date().getTime());
        m.setModoPago(modoPago.getSelectionModel().getSelectedItem().toString());
        ArrayList l = new ArrayList();
        for (Object ta : ventas) {
            TArticuloCompra t = (TArticuloCompra) ta;            
            l.add(t.getMovimeinto());
        }
        m.setMovimientoDetalle(l);

        ArrayList l2 = cliente.getlist();
        if (l2 != null) {
            CPersona p = new CPersona();
            p.setnroDocumento((String) l2.get(0));
            p.setNombre((String) l2.get(1));
            p.setDireccion((String) l2.get(2));
            m.setPersona_atendida(p);
        }
        m.setTipoCompra();
        m.insert();
        filtro.requestFocus();
        actArticulos();
        clear();
    }
    public void actArticulos(){
        articulos.clear();
        articulos_filtro.clear();
         ArrayList<CArticulo> l = new CArticulo().lista();
        l.forEach((x) -> {
            articulos.add(new TArticuloSearch(x));
        });                  
        articulos_filtro.addAll(articulos);
    }
    public void clear(){
        t.clear_items();
        this.filtro.setText("");
        this.modoPago.getSelectionModel().select(0);
        cliente.clear();    
    }    
    public void agregar_articulo(CArticulo x){
        for(TArticuloCompra a:ventas){
            if(a.getA().getMedida().getCodigo()==x.getMedida().getCodigo()){
                a.cantidadProperty().set(a.cantidadProperty().get()+1);
                actualizarVentas();
                return;
            }
        }

        t.add_Item(new TArticuloCompra(x));
        actualizarVentas();
    }
    public void actualizarVentas(){
        double k=0;
        for(TArticuloCompra a:ventas){
            k+=a.totalProperty().getValue();
        }
        total.setText("S/."+k);
    }
    public void ini_buton_nuevo(){
        Button b=new Button("Nuevo");
        b.setPrefWidth(Double.MAX_VALUE);
        b.setPrefHeight(Double.MAX_VALUE);
        ventana.add(b, 4, 5,3,1);
        b.setOnAction(e->{
            new modal_newArticulo().display();
            actArticulos();
        });
    }
}
