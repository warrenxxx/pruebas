package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Iterator;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CMovimientoDetalle extends C_PrincipalArray{
    // <editor-fold desc="DATOS DE LA BD">    
    private final String cantidad="cantidad";
    private final String precio="precio";
    private final String descuento="descuento";
    private final String id_articulo="id_articulo";
    
    //rec
    private final String articulo="articulo";
        
    // </editor-fold>     
    
    // <editor-fold desc="Variables">    
    public CArticulo articulox;
    public CMovimiento padre;
//</editor-fold>
    
    // <editor-fold desc="Constructor">    
    
    public CMovimientoDetalle(){       
        this.datos=new HashMap();
        this.datos.put("_id", new ObjectId());
    }       
    public CMovimientoDetalle(HashMap map){       
        this.datos=map;
        this.datos.put("_id", new ObjectId());
    }       
    public CMovimientoDetalle(CMovimiento x){       
        this.padre=x;
        this.datos=new HashMap();
        this.datos.put("_id", new ObjectId());
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setPrecio(Double x){
        this.datos.put(precio, x);
    }
    public void setCantidad(Double x){
        this.datos.put(cantidad, x);
    }
    public void setDescuento(Double x){
        this.datos.put(descuento, x);
    }
    public void setArticulo(String x){
        this.datos.put(id_articulo, new ObjectId(x));
    }
    public void setArticulo(CArticulo x){
        this.datos.put(id_articulo, x.getId());
    }
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public Double getCantidad(){
        Object aux=this.datos.get(cantidad);        
        return (aux!=null)?((Double)aux):0;
    }
    public Double getPrecio(){
        Object aux=this.datos.get(precio);        
        return (aux!=null)?((Double)aux):0;
    }
    public Double getDescuento(){
        Object aux=this.datos.get(descuento);        
        return (aux!=null)?((Double)aux):0;
    }
    public CArticulo getArticulo(){
        return new CArticulo((HashMap)this.datos.get(articulo));
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
        return "CMovdetalle{" +aux+'}';
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public Double getTotal(){
        return getCantidad()*getPrecio()-getDescuento();
    }
    public Double getSubtotal(){
        return getCantidad()*getPrecio();
    }
    public String getCantidad_s(){
        return  this.datos.get(cantidad).toString();        
    }
    public String getPrecio_s(){
        return  this.datos.get(precio).toString();
    }
    public String getDescuento_s(){
        return  this.datos.get(descuento).toString();        
    }

    //</editor-fold>
}
