package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.DArticulo;
import app.articulo.TArticulo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CArticulo extends C_Principal{
    // <editor-fold desc="DATOS DE LA BD">    
    public final String codigo = "codigo_barras";
    public final String categoria = "categoria";
    public final String nombre = "nombre";
    public final String descripcion = "descripcion";
    public final String medida="medida";
    
    public final String costo = "costo";   
    public final String precio_dia = "precio_dia";
    public final String precio_noche = "precio_noche";    
    public final String stock = "stock";

    public final String organizacion = "organizacion";
    public final String articulo_descendiente = "articulo_desendiente";

    // </editor-fold>
    
    // <editor-fold desc="Variables">
    private CArticulo articulox;
//</editor-fold>  
    
    // <editor-fold desc="Constructor">        
    public CArticulo(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public CArticulo(String cod,String cart,double cost){       
        this.datos=new HashMap();
        n=this.datos.size();
        this.setCosto(cost);
        this.setCodigo(cod);
        this.setCategoria(cart);
    }       
    public CArticulo(HashMap map){       
        this.datos=map;

    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setCodigo(String x) {
        this.datos.put(codigo, x);
    }
    public void setNombre(String x) {
        this.datos.put(nombre, x);
    }
    public void setDescricion(String x) {
        this.datos.put(descripcion, x);
    }
    public void setCosto(double x) {
        this.datos.put(costo, x);
    }
    public void setPrecio_dia(double x) {
        this.datos.put(precio_dia, x);
    }
    public void setPrecio_noche(double x) {
        this.datos.put(precio_noche, x);
    }
    public void setStock(double x) {
        this.datos.put(stock, x);
    }
    public void setCategoria(String x) {
        this.datos.put(categoria, x);
    }
    public void setOrganizacion(String x) {
        this.datos.put(organizacion, x);
    }
    public void setArticuloDesendiente(CArticulo x) {
        this.datos.put(articulo_descendiente, x.get_datos());
    }
    public void setMedida(CMedida x) {
        if(x!=null)
        this.datos.put(medida, x.get_datos());
    }

    //</editor-fold>

    
    // <editor-fold desc="Metodos Get">    
    public String getCodigo() {
        return this.datos.get(codigo) == null ? "" : (String) this.datos.get(codigo);
    }
    public String getNombre() {
        return this.datos.get(nombre) == null ? "" : (String) this.datos.get(nombre);
    }
    public String getDescripcion() {
        return this.datos.get(descripcion) == null ? "" : (String) this.datos.get(descripcion);
    }
    public double getCosto() {
       return (double) (this.datos.get(costo) == null ? 0 : Double.parseDouble(String.valueOf(this.datos.get(costo))));
    }
    public double getPrecio_dia() {
       return (double) (this.datos.get(precio_dia) == null ? 0 : Double.parseDouble(String.valueOf(this.datos.get(precio_dia))));
    }
    public double getPrecio_noche() {
       return (double) (this.datos.get(precio_noche) == null ? 0 : Double.parseDouble(String.valueOf(this.datos.get(precio_noche))));
    }
    public double getStock() {
       return (double) (this.datos.get(stock) == null ? 0 : Double.parseDouble(String.valueOf(this.datos.get(stock))));
    }
    public CArticulo getArticuloDesendiente(){
        if(this.datos.get(articulo_descendiente)==null)return null;
        return new CArticulo((HashMap)this.datos.get(articulo_descendiente));
    }
    public String getCategoria(){
         return this.datos.get(categoria) == null ? "" : String.valueOf( this.datos.get(categoria));
    }
    public String getOrganizacion(){
        return this.datos.get(organizacion) == null ? "" : (String) this.datos.get(organizacion);

    }
    public CMedida getMedida(){
        if(this.datos.get(medida)==null)return null;
        return new CMedida((HashMap)this.datos.get(medida));
    }
    public double getPrecio(){
        Date h=new Date();
        if(h.getHours()<7||h.getHours()>23){
            return getPrecio_noche();
        }
        return getPrecio_dia();
    }
//    </editor-fold>

    // <editor-fold desc="Overrride">    
    
    @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CArticulo{" +aux+'}';
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public String getCosto_s() {
        return  (this.datos.get(costo) == null ? "0" : this.datos.get(costo).toString());
    }
    public String getPrecio_dia_s() {
        return this.datos.get(precio_dia).toString();
    }
    public String getPrecio_noche_s() {
        return  this.datos.get(precio_noche).toString();
    }
    public String getStock_s() {
        String aux = this.datos.get(stock).toString();
        return aux;
    }
    
    public void buscar(String x){
        set_datos( new DArticulo().buscar(new ObjectId(x)));
    }
    public void insert(){
        set_datos(new DArticulo().insert(get_datos()));
    }
    public void update(){
        set_datos(new DArticulo().update(getId(), datos));
    }
    public boolean delete(){
        return new DArticulo().delete(getId());
    }
    public ArrayList lista(){
        ArrayList<HashMap> aux=new DArticulo().listar();
        ArrayList<CArticulo> res=new ArrayList<>();

        for(HashMap x:aux)
            res.add(new CArticulo(x));
       return res;
    }
    public ObservableList listaObservable(){
        ArrayList<HashMap> aux=new DArticulo().listar();
        ObservableList res=FXCollections.observableArrayList();   

        for(HashMap x:aux)
            res.add(new CArticulo(x));
       return res;
    }
    public ObservableList listaObservableTarcitulo(){
        ArrayList<HashMap> aux=new DArticulo().listar();
        ObservableList res=FXCollections.observableArrayList();   

        for(HashMap x:aux)
            res.add(new TArticulo( new CArticulo(x)));
       return res;
    }
    public ArrayList getAllCategorias(){
        return new DArticulo().getAllCategorias();
    }
    public ObservableList getAllCategoriasObserbable(){
        ObservableList l=FXCollections.observableArrayList();   
        
        ArrayList r= new DArticulo().getAllCategorias();
        for(Object o:r)
            l.add(o);            
        return l;
    }
    public ObservableList getAllOrganizacionObserbable(){
        ObservableList l=FXCollections.observableArrayList();           
        ArrayList r= new DArticulo().getAllOrganizaciones();
        for(Object o:r)
            l.add(o);            
        return l;
    }
    
    //</editor-fold>
}
