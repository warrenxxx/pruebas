package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author WARREN
 */
public class CModoPago extends C_Principal{
    // <editor-fold desc="DATOS DE LA BD">    
    private final String nombre="nombre";
    private final String suma="suma";
    // </editor-fold>
    
    // <editor-fold desc="Variables">
//</editor-fold>  
    
    // <editor-fold desc="Constructor">        
    public CModoPago(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public CModoPago(HashMap map){       
        this.datos=map;
        n=this.datos.size();
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setNombre(String x){
        this.datos.put(nombre, x);
    }
    public void setSuma(Double x){
        this.datos.put(suma, x);
    }
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public String getNombre(){
        String aux= (String) this.datos.get(nombre);        
        return aux==null?"":aux;
    }    
    public int getsuma(){
        if(this.datos.get(suma)==null)return -9999999;
        return  (int) this.datos.get(suma);                
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
        return "CCategoria{" +aux+'}';
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    //</editor-fold>
}
