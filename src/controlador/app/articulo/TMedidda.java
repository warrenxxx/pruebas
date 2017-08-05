/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.articulo;

import app.clases.CArticulo;
import app.clases.CMedida;
import app.herramientas.tabla.crud;
import java.util.ArrayList;
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
public class TMedidda implements crud{
    
    private final SimpleStringProperty codigo;
    private final SimpleObjectProperty medida;
    private final SimpleDoubleProperty cantidad;
    private final SimpleDoubleProperty pdia;
    private final SimpleDoubleProperty pnoche;
    private final SimpleDoubleProperty costo;
    private final SimpleDoubleProperty stock;
    private final SimpleStringProperty organizacion;
        
    private CMedida a;

    public TMedidda(CMedida x) {

        this.a=x;
        this.codigo = new SimpleStringProperty(x.getCodigo());
        this.medida = new SimpleObjectProperty(x.getNombre());
        this.cantidad = new SimpleDoubleProperty(x.getCantidad());
        this.pdia = new SimpleDoubleProperty(x.getPrecio_dia());
        this.pnoche = new SimpleDoubleProperty(x.getPrecio_noche());
        this.costo = new SimpleDoubleProperty(x.getCosto());
        this.stock = new SimpleDoubleProperty(x.getStock());
        this.organizacion = new SimpleStringProperty(x.getOrganizacion());
    }
  
    public StringProperty codigoProperty() {
        return codigo;
    }

    public ObjectProperty medidaProperty() {
        return medida;
    }
    public DoubleProperty cantidadProperty(){
        return cantidad;
    }
    public DoubleProperty pdiaProperty(){
        return pdia;
    }
    public DoubleProperty pnocheProperty(){
        return pnoche;
    }
    public DoubleProperty costoProperty(){
        return costo;
    }
    public DoubleProperty stockProperty(){
        return stock;
    }
    public StringProperty organizacionProperty(){
        return organizacion;
    }

    public CMedida toElement() {
        CMedida b = new CMedida();
        if(a.getId()!=null)
            b.setId(a.getId());
        b.setCantidad(cantidad.get());
        b.setCodigo(codigo.get());
        b.setCosto(costo.get());
        b.setNombre(medida.get().toString());
        b.setOrganizacion(organizacion.get());
        b.setPrecio_dia(pdia.get());
        b.setPrecio_noche(pnoche.get());
        b.setStock(stock.get());
        return b;
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
    public Object clone(){
        CMedida x=toElement();
        System.out.println(x);
        TMedidda y=new TMedidda(x);
        System.out.println(y);
        return y;
    }

    @Override
    public String toString() {
        return this.cantidad.get()+this.costo.get()+this.codigo.get();
    }
    
}
