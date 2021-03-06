/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ventas;


import app.clases.CArticulo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */
public class TArticuloSearch{
    
  
    private final SimpleStringProperty nombre;
    private final SimpleDoubleProperty stock;
    private final SimpleDoubleProperty precio;
    
    private CArticulo a;

    public TArticuloSearch(CArticulo x) {
        this.a = x;        
        String aux=x.getNombre()+" "+x.getDescripcion()+" "+x.getMedida();
        this.nombre = new SimpleStringProperty(aux);       
        this.precio=new SimpleDoubleProperty(x.getMedida().getPrecio());
        this.stock=new SimpleDoubleProperty(x.getMedida().getStock());
    }

    public StringProperty nombreProperty() {
        return nombre;
    }
    public DoubleProperty precioProperty(){
        return precio;
    }
    public DoubleProperty stockProperty(){
        return stock;
    }

    public CArticulo getArticulo() {
        return a;
    }

    public boolean search(String s){
        if(s==null||s.isEmpty())return true;
        String sm=s.toUpperCase();
        String h=nombre.get();
        if(h.toUpperCase().indexOf(sm)!=-1)return true;        
        return false;
    }

    @Override
    public String toString() {
        return nombre.getValue();
    }
    
}
