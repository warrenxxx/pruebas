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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */
public class TArticulo implements crud{
    
    private final SimpleObjectProperty categoria;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;

    private final SimpleObjectProperty<ArrayList< CMedida>> medida;

    private CArticulo a;

    public TArticulo(CArticulo x) {
        this.a = x;
        this.categoria = new SimpleObjectProperty(a.getCategoria());
        this.nombre = new SimpleStringProperty(a.getNombre());
        this.descripcion = new SimpleStringProperty(a.getDescripcion());
        this.medida = new SimpleObjectProperty(a.getMedidas());
    }


    public ObjectProperty categoriaProperty() {
        return categoria;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public ObjectProperty medidaProperty() {
        return medida;
    }



    public CArticulo getA() {
        return a;
    }

    public CArticulo toElement() {
        CArticulo b = new CArticulo();
        if(a.getId()!=null)
            b.setId(a.getId());
        b.setCategoria(categoria.getValue()==null?"":categoria.getValue().toString());
        b.setDescricion(descripcion.getValue());
        b.setNombre(nombre.getValue());
        b.setMedidas(medida.getValue());
        return b;
    }

    public void toProperty(CArticulo x) {
        categoria.setValue(x.getCategoria());
        nombre.setValue(x.getNombre());
        descripcion.setValue(x.getDescripcion());
        medida.setValue(x.getMedidas());
    }
    @Override
    public boolean update(){
        CArticulo x=toElement();        
        x.update();
        System.out.println("llego2");
        if(x.getError()!=null){
            toProperty(a);
            System.out.println("no updateo");
            System.out.println(x.getError());
            return false;            
        }
        System.out.println(x);
        
        return true;
    }
    @Override
    public boolean remove(){
        if(!a.delete()){
            toProperty(a);
            return false;
        }
        return true;
    }

    @Override
    public boolean insert() {
        CArticulo x=toElement();        

        x.insert();
        if(x.getError()!=null){
            toProperty(a);
            return false;
        }
        this.a=x;
        return true;
    }
/*
    @Override
    public String toString() {
        return "TArticulo{" + "codigo=" + codigo.getValue() + ", categoria=" + categoria.getValue() + ", nombre=" + nombre.get() + ", descripcion=" + descripcion.get() + ", medida=" + medida.getValue() + ", costo=" + costo.getValue() + ", pdia=" + pdia + ", pnoche=" + pnoche + ", stock=" + stock + ", organizacion=" + organizacion + ", a=" + a + '}';
    }    
*/
    @Override
    public Object clone(){
        CArticulo x=toElement();
        TArticulo y=new TArticulo(x);
        return y;
    }
    @Override
    public void clear(){
        categoria.setValue("");
        nombre.setValue("");
        descripcion.setValue("");
        medida.setValue(null);
         a=new CArticulo();
    }
    @Override
    public boolean search(String s){
        if(s==null||s.isEmpty())return true;
        String sm=s.toUpperCase();
        String h=categoria.getValue()+nombre.getValue()+descripcion.getValue();
        for (CMedida m:this.medida.get()){
            h+=m.getNameSearch();
        }
        if(h.toUpperCase().indexOf(sm)!=-1)return true;        
        return false;
    }
}
