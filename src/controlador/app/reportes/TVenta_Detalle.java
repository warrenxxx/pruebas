/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.reportes;

import app.clases.CArticulo;
import app.clases.CMovimientoDetalle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */
public class TVenta_Detalle {
    private final SimpleDoubleProperty cantidad;
    private final SimpleDoubleProperty precio;
    private final SimpleDoubleProperty descuento;
    private final SimpleObjectProperty articulo;
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre;
    private final SimpleDoubleProperty subtotal;
    private final SimpleDoubleProperty total;
    
    private CMovimientoDetalle x;
    private CArticulo y; 
    public TVenta_Detalle(CMovimientoDetalle x) {
        this.x=x;
        this.y=x.getArticulo();
        this.cantidad = new SimpleDoubleProperty(x.getCantidad());
        this.precio = new SimpleDoubleProperty(x.getPrecio());
        this.descuento = new SimpleDoubleProperty(x.getDescuento());
        this.articulo = new SimpleObjectProperty(x.getArticulo());
        this.codigo=new SimpleStringProperty(y.getMedida().getCodigo());
        this.nombre=new SimpleStringProperty(y.getCategoria()+" "+y.getNombre()+" "+y.getDescripcion()+" "+y.getMedida());
        this.subtotal=new SimpleDoubleProperty();
        this.subtotal.bind(precio.multiply(cantidad).multiply(-1));
        this.total=new SimpleDoubleProperty();
        this.total.bind(subtotal.subtract(descuento));
    }
    
    public DoubleProperty cantidadProperty(){
        return this.cantidad;
    }
    public DoubleProperty precioProperty(){
        return this.precio;
    }
    public DoubleProperty descuentoProperty(){
        return this.descuento;
    }
    public ObjectProperty articuloProperty(){
        return this.articulo;
    }
    public StringProperty codigoProperty(){
        return this.codigo;
    }
    public StringProperty nombreProperty(){
        return this.nombre;
    }
    public DoubleProperty subTotalProperty(){
        return this.subtotal;
    }
    public DoubleProperty totalProperty(){
        return this.total;
    }
    
}
