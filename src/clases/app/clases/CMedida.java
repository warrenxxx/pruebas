package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author WARREN
 */
public class CMedida extends C_Principal{
    // <editor-fold desc="DATOS DE LA BD">    
    private final String nombre = "n";
    private final String cantidad = "c";
    
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
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public String getNombre() {
        return this.datos.get(nombre) == null ? "" : (String) this.datos.get(nombre);
    }
    public Double getCantidad() {
        return this.datos.get(cantidad) == null ? 0 : (Double) this.datos.get(cantidad);
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
    //</editor-fold>
}
