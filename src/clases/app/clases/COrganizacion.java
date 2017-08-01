package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class COrganizacion extends C_Principal{
    // <editor-fold desc="DATOS DE LA BD">    
    private final String nombre="nombre";
    private final String descripcion="descripcion";//cajas o bolsas paquetes
    private final String suma="suma";
    private final String productos="pro";
    // </editor-fold>
    
    // <editor-fold desc="Variables">
//</editor-fold>  
    
    // <editor-fold desc="Constructor">        
    public COrganizacion(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public COrganizacion(HashMap map){       
        this.datos=map;
        n=this.datos.size();
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setNombre(String x){
        this.datos.put(nombre, x);
    }
    public void setDescripcion(String x){
        this.datos.put(descripcion, x);
    }

    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public String getNombre(){
        String aux= (String) this.datos.get(nombre);        
        return aux==null?"":aux;
    }    
    public String getDescripcion(){
        String aux= (String) this.datos.get(descripcion);        
        return aux==null?"":aux;
    }
    public int getsuma(){
        if(this.datos.get(suma)==null)return -9999999;
        return  (int) this.datos.get(suma);                
    }
    public ArrayList getProductos(){
        if(this.datos.get(productos)==null)return new ArrayList();
        ArrayList<HashMap>aux=(ArrayList<HashMap>) this.datos.get(productos);
        ArrayList<CArticulo> res=new ArrayList<>();
        for(HashMap h:aux){
            CArticulo x=new CArticulo();
            x.set_datos(h);
            res.add(x);
        }
        return res;
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
