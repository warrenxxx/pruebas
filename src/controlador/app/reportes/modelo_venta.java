/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.reportes;

import app.clases.CMovimiento;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 *
 * @author warren
 */
public class modelo_venta extends GridPane{
    CMovimiento m;
    int k=0;
    public modelo_venta(CMovimiento m) {
        this.m=m;
        ini_stilo();
    }
    public void ini_hora(){
        SimpleDateFormat s=new SimpleDateFormat("h : m", new Locale("es_ES"));
        Label l=new Label("Hora             : "+s.format(new Date(m.getHora())));
        l.prefWidthProperty().bind(this.widthProperty());
        l.setId("labelVentas");
        add(l,0,k++);
    }
    public void ini_persona_atendida(){
        Label l=new Label("Cliente          : "+m.getPersona_atendida().toString());        
        l.prefWidthProperty().bind(this.widthProperty());
        l.setId("labelVentas");
        add(l,0,k++);
    }
    
    public void ini_tipo_pago(){
        Label l=new Label("Modo de Pago     : "+m.getMogoPago().toString());        
        l.prefWidthProperty().bind(this.widthProperty());
        l.setId("labelVentas");
        add(l,0,k++);        

    }
    public void ini_ventas_detalle_total(ObservableList datos){
        TableView t=new TableView();
        TableColumn t0=new TableColumn("#");
        TableColumn t1=new TableColumn("Cantidad");
                    t1.setCellValueFactory(new PropertyValueFactory("cantidad"));
        TableColumn t2=new TableColumn("Codigo");
                    t2.setCellValueFactory(new PropertyValueFactory("codigo"));
        TableColumn t3=new TableColumn("Nombre");
                    t3.setCellValueFactory(new PropertyValueFactory("nombre"));
        TableColumn t4=new TableColumn("Precio Unitario");
                    t4.setCellValueFactory(new PropertyValueFactory("precio"));
        TableColumn t5=new TableColumn("Sub Total");
                    t5.setCellValueFactory(new PropertyValueFactory("subtotal"));
        TableColumn t6=new TableColumn("Descuento");
                    t6.setCellValueFactory(new PropertyValueFactory("descuento"));
        TableColumn t7=new TableColumn("Total");
                    t7.setCellValueFactory(new PropertyValueFactory("total"));
        t.setItems(datos);

        t.getColumns().addAll(t0,t1,t2,t3,t4,t5,t6,t7);
        t.prefWidthProperty().bind(this.widthProperty());
        t.setFixedCellSize(25);
        t.prefHeightProperty().bind(t.fixedCellSizeProperty().multiply(Bindings.size(t.getItems()).add(1.01)));
        t.minHeightProperty().bind(t.prefHeightProperty());
        t.maxHeightProperty().bind(t.prefHeightProperty());
        
        add(t,0,k++);            
    }
    public void ini_stilo(){
        this.setId("gridVentas");
    }
}
