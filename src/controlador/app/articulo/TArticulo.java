/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.articulo;

import app.clases.CArticulo;
import app.clases.CMedida;
import app.herramientas.tabla.crud;
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
public class TArticulo implements crud{
    
    private final SimpleStringProperty codigo;
    private final SimpleObjectProperty categoria;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;

    private final SimpleObjectProperty medida;

    private final SimpleDoubleProperty costo;
    private final SimpleDoubleProperty pdia;
    private final SimpleDoubleProperty pnoche;
    private final SimpleDoubleProperty stock;

    private final SimpleObjectProperty organizacion;

    private CArticulo a;

    public TArticulo(CArticulo x) {
        this.a = x;
        this.codigo = new SimpleStringProperty(a.getCodigo());
        this.categoria = new SimpleObjectProperty(a.getCategoria());
        this.nombre = new SimpleStringProperty(a.getNombre());
        this.descripcion = new SimpleStringProperty(a.getDescripcion());
        this.medida = new SimpleObjectProperty(a.getMedida());
        this.stock = new SimpleDoubleProperty(a.getStock());
        this.costo = new SimpleDoubleProperty(a.getCosto());
        this.pdia = new SimpleDoubleProperty(a.getPrecio_dia());
        this.pnoche = new SimpleDoubleProperty(a.getPrecio_noche());
        this.organizacion = new SimpleObjectProperty(a.getOrganizacion());

    }

    public StringProperty codigoProperty() {
        return codigo;
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

    public DoubleProperty costoProperty() {
        return costo;
    }

    public DoubleProperty pdiaProperty() {
        return pdia;
    }

    public SimpleDoubleProperty pnocheProperty() {
        return pnoche;
    }

    public DoubleProperty stockProperty() {
        return stock;
    }

    public ObjectProperty organizacionProperty() {
        return organizacion;
    }

    public CArticulo getA() {
        return a;
    }

    public CArticulo toElement() {
        CArticulo b = new CArticulo();
        if(a.getId()!=null)
            b.setId(a.getId());
        b.setCategoria(categoria.getValue()==null?"":categoria.getValue().toString());
        b.setCodigo(codigo.getValue());
        b.setCosto(costo.getValue());
        b.setDescricion(descripcion.getValue());
        b.setMedida((CMedida) medida.getValue());
        b.setNombre(nombre.getValue());
        b.setOrganizacion(organizacion.getValue().toString());
        b.setPrecio_dia(pdia.getValue());
        b.setPrecio_noche(pnoche.getValue());
        b.setStock(stock.getValue());
        return b;
    }

    public void toProperty(CArticulo x) {
        codigo.setValue(x.getCodigo());
        categoria.setValue(x.getCategoria());
        nombre.setValue(x.getNombre());
        descripcion.setValue(x.getDescripcion());
        medida.setValue(x.getMedida());
        stock.setValue(x.getStock());
        costo.setValue(x.getCosto());
        pdia.setValue(x.getPrecio_dia());
        pnoche.setValue(x.getPrecio_noche());
        organizacion.setValue(x.getOrganizacion());
    }
    @Override
    public boolean update(){
        CArticulo x=toElement();        
        x.update();
        if(x.getError()!=null){
            toProperty(a);
            return false;
        }
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

    @Override
    public String toString() {
        return "TArticulo{" + "codigo=" + codigo.getValue() + ", categoria=" + categoria.getValue() + ", nombre=" + nombre.get() + ", descripcion=" + descripcion.get() + ", medida=" + medida.getValue() + ", costo=" + costo.getValue() + ", pdia=" + pdia + ", pnoche=" + pnoche + ", stock=" + stock + ", organizacion=" + organizacion + ", a=" + a + '}';
    }    

    @Override
    public Object clone(){
        CArticulo x=toElement();
        TArticulo y=new TArticulo(x);
        return y;
    }
    @Override
    public void clear(){
        codigo.setValue("");
        categoria.setValue("");
        nombre.setValue("");
        descripcion.setValue("");
        organizacion.setValue("");
        medida.setValue(null);
        stock.setValue(0);
        costo.setValue(0);
        pdia.setValue(0);
        pnoche.setValue(0);
        a=new CArticulo();
    }
    @Override
    public boolean search(String s){
        if(s==null||s.isEmpty())return true;
        String sm=s.toUpperCase();
        String h=codigo.getValue()+categoria.getValue()+nombre.getValue()+descripcion.getValue()+organizacion.getValue();
        if(h.toUpperCase().indexOf(sm)!=-1)return true;        
        return false;
    }
}
