/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.compras;

import app.clases.CArticulo;
import app.clases.CMedida;
import app.clases.CMovimientoDetalle;
import app.herramientas.tabla.crud;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */

public class TArticuloCompra implements crud{
    
    private final SimpleDoubleProperty cantidad;    
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre; 
    private final SimpleDoubleProperty precio;
    private final SimpleDoubleProperty descuento;
    private final SimpleDoubleProperty total;

    private CArticulo a;
    private CMedida med;
    
    public TArticuloCompra(CArticulo x) {
        this.a = x;
        this.med=x.getMedida();
        String k=x.getCategoria()+" "+x.getNombre()+" "+a.getDescripcion()+" "+a.getMedida();
        this.codigo = new SimpleStringProperty(med.getCodigo());
        this.nombre = new SimpleStringProperty(k);
        this.precio=new SimpleDoubleProperty(med.getCosto());
        this.descuento=new SimpleDoubleProperty(0);
        this.total=new SimpleDoubleProperty(med.getPrecio_dia());
        this.cantidad=new SimpleDoubleProperty(1);
        this.total.bind(cantidadProperty().multiply(precio).subtract(descuento));
    }
    
    public StringProperty codigoProperty() {
        return codigo;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }
    
    public DoubleProperty precioProperty() {
        return precio;
    }
    public DoubleProperty descuentoProperty() {
        return descuento;
    }
    public DoubleProperty totalProperty() {
        return total;
    }
    public DoubleProperty cantidadProperty() {
        return cantidad;
    }
    
    public void setArticulo(CArticulo x){
        this.a=x;
        this.med=x.getMedida();
        this.codigo.set(med.getCodigo());        
        String k=x.getCategoria()+" "+x.getNombre()+" "+a.getDescripcion()+" "+a.getMedida();
        this.nombre.set(k);
    }
    public CArticulo getA() {
        return a;
    }
    public CMovimientoDetalle getMovimeinto(){
        CMovimientoDetalle m=new CMovimientoDetalle();
        m.setArticulo(a);
        m.setCantidad(cantidad.getValue());
        m.setDescuento(descuento.getValue());
        m.setPrecio(precio.getValue());
        return m;
    }

    @Override
    public boolean insert() {
        return true;
    }

    @Override
    public boolean remove() {
        return true;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean search(String s) {
        return true;
    }

    @Override
    public void clear() {
    }
    @Override
    public Object clone() {
        return "";
    }

}
