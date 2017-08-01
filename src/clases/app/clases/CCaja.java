package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.DCaja;
import app.DSession;
import app.caja.ETCaj;
import app.caja.ITCaj;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WARREN
 */
public class CCaja extends C_PrincipalArray{
    // <editor-fold desc="DATOS DE LA BD">    
    public final String monedas="monedas";
    public final String descripcion="descripcion";
    public final String tipo="tipo";
           
    // </editor-fold>     
    
    // <editor-fold desc="Variables">        
    public CSession padre;
//</editor-fold>
    
    // <editor-fold desc="Constructor">    
    
    public CCaja(CSession padre){       
        this.datos=new HashMap();
        this.padre=padre;
    }
    public CCaja(HashMap x,CSession padre){       
        this.datos=x;
        this.padre=padre;
    }       

    public CCaja() {
    }
    
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setMonedas(CMoneda moneda){
        this.datos.put(this.monedas, moneda.get_datos());
    }
    public void setDescripcion(String descripcion){
        this.datos.put(this.descripcion, descripcion);
    }
    public void setTipo(String tipo){
        this.datos.put(this.tipo, tipo);    
    }
    public void setIngreso(){
        this.datos.put(this.tipo, "ingreso");    
    }
    public void setEgreso(){
        this.datos.put(this.tipo, "egreso");    
    }
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public CMoneda getMonedas(){
        if(this.datos.get(monedas)==null)return null;
        return new CMoneda((HashMap) this.datos.get(monedas));
    }
    public String getDescripcion(){
        if(this.datos.get(descripcion)==null)return "";
        return (String) this.datos.get(descripcion);
    }
    public String getTipo(){
        if(this.datos.get(tipo)==null)return "";
        return (String) this.datos.get(tipo);
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
        return "Cseccion{" +aux+'}';
    }    
//        @Override
  //  public String toString() {
    //    return "Seccion de dia " +getFechaInicio();
    //}    

    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public void insert(){
        set_datos(new DCaja(padre).insert(datos));
    }
    public void update(){
        set_datos(new DCaja(padre).update(datos));
    }
    public boolean remove(){
        return (new DCaja(padre).delete(this.getPosicion()));
    }
    public ArrayList listar(){
        return new DCaja(padre).listar();
    }
    
    public ObservableList<ITCaj> itcaja;
    public ObservableList<ETCaj> etcaja;
    
    public void listarTcaja(){
        itcaja=FXCollections.observableArrayList(e->new Observable[]{
            e.descripcionProperty(),
            e.monedasProperty()
        });
        etcaja=FXCollections.observableArrayList(e->new Observable[]{
            e.descripcionProperty(),
            e.monedasProperty()
        });
        ArrayList p=listar();
        for(Object k:p){
             HashMap m=(HashMap)k  ;
             if(m.get(tipo).toString().compareTo("ingreso")==0)
                 itcaja.add(new ITCaj(new CCaja((HashMap)k, padre)));
             if(m.get(tipo).toString().compareTo("egreso")==0)
                 etcaja.add(new ETCaj(new CCaja((HashMap)k, padre)));             
        }
    }

    public double sumarIngresosCaja(ObservableList<ITCaj> l){
        if(l==null)return 0;
        double res=0;
        for(ITCaj o:l){
            CMoneda m=(CMoneda) o.monedasProperty().get();
            res+=m.getCantidad();
        }
        return res;
    }
    public double sumarEgresosCaja(ObservableList<ETCaj> l){
        if(l==null)return 0;
        double res=0;
        for(ETCaj o:l){
            CMoneda m=(CMoneda) o.monedasProperty().get();
            res+=m.getCantidad();
        }
        return res;
    }
    //</editor-fold>
}
