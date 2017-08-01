/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.caja;
import app.clases.CCaja;
import app.clases.CMoneda;
import app.herramientas.tabla.crud;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */
public class ETCaj implements crud{
    
    private final SimpleObjectProperty monedas;
    private final SimpleStringProperty descripcion;

    private CCaja a;

    public ETCaj(CCaja x) {
        this.a = x;
        this.monedas = new SimpleObjectProperty(a.getMonedas());
        this.descripcion = new SimpleStringProperty(a.getDescripcion());
    }


    public ObjectProperty monedasProperty() {
        return monedas;
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }


    public CCaja getA() {
        return a;
    }
 
    public CCaja toElement() {
        CCaja b=new CCaja(a.padre);
        b.setMonedas((CMoneda) monedas.get());
        b.setDescripcion(descripcion.get());
        b.setEgreso();
        if(a.getId()!=null) 
            b.setId(a.getId());
        if(a.getPosicion()!=-1) 
            b.setPosicion(a.getPosicion());
        return b;
    }

    public void toProperty(CCaja x) {
        monedas.setValue(x.getMonedas());
        descripcion.setValue(x.getDescripcion());
    }
    @Override
    public boolean update(){
        CCaja x=toElement();        
        x.update();
        if(x.getError()!=null){
            toProperty(a);
            return false;
        }
        return true;
    }
    @Override
    public boolean remove(){
        if(!a.remove()){
            toProperty(a);
            return false;
        }
        return true;
    }

    @Override
    public boolean insert() {
        CCaja x=toElement();        
        x.insert();
        if(x.getError()!=null){
            toProperty(a);
            return false;
        }
        this.a=x;
        return true;
    }

    @Override
    public String toString() {
        return "TCaja{" + "tipo="  + ", monedas=" + monedas.getValue() + ", descripcion=" + descripcion.get() ;
    }    

    @Override
    public Object clone(){
        CCaja x=toElement();
        ETCaj y=new ETCaj(x);
        return y;
    }
    @Override
    public void clear(){
        monedas.setValue("");
        descripcion.setValue("");
        a=new CCaja(a.padre);
    }
    @Override
    public boolean search(String s){
        return true;
    }
}
