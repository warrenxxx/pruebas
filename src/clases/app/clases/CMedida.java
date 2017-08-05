package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class CMedida extends C_Principal{
    // <editor-fold desc="DATOS DE LA BD">    
    private final String nombre = "n";
    private final String cantidad = "c";
    
    public final String codigo = "codigo_barras";    
    public final String costo = "costo";   
    public final String precio_dia = "precio_dia";
    public final String precio_noche = "precio_noche";    
    public final String stock = "stock";

    public final String organizacion = "organizacion";
    // </editor-fold>
    
    // <editor-fold desc="Variables">
    //</editor-fold>  
    
    // <editor-fold desc="Constructor">        
    public CMedida(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public CMedida(HashMap map){       
        this.datos=map;
        n=this.datos.size();
    }       
    public CMedida(String pnombre,double pcantidad){
        this.datos=new HashMap();

        this.setNombre(pnombre);
        this.setCantidad(pcantidad);
        n=this.datos.size();
    }
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setNombre(String x) {
        this.datos.put(nombre, x);
    }
    public void setCantidad(Double x) {
        this.datos.put(cantidad, x);
    }
    public void setCodigo(String x) {
        this.datos.put(codigo, x);
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
    public void setOrganizacion(String x) {
        this.datos.put(organizacion, x);
    }
     //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public String getNombre() {
        return this.datos.get(nombre) == null ? "" : (String) this.datos.get(nombre);
    }
    public Double getCantidad() {
        return this.datos.get(cantidad) == null ? 0 : (Double) this.datos.get(cantidad);
    }    
        public String getCodigo() {
        return this.datos.get(codigo) == null ? "" : (String) this.datos.get(codigo);
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
        public String getOrganizacion(){
        return this.datos.get(organizacion) == null ? "" : (String) this.datos.get(organizacion);

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
        return  getCantidad()+ " "+ getNombre();
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public ObservableList getNombres(){
        ObservableList res=FXCollections.observableArrayList();;
        res.addAll("Unidad","Litros","Milimetros","Gramos","Kilos");
        return  res;
    }
    public String getNameSearch(){
        return this.codigo+this.nombre+this.cantidad+this.organizacion;
    }
    //</editor-fold>
}
