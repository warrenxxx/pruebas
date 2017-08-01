package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.DSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WARREN
 */
public class CSession extends C_PrincipalArray{
    // <editor-fold desc="DATOS DE LA BD">    
    public final String fechaa="fechaInicio";
    public final String fechab="fechaFin";
    public final String estado="estado";
    public final String movimiento="movimiento";
    public final String caja="caja";
    
        
    // </editor-fold>     
    
    // <editor-fold desc="Variables">        
//</editor-fold>
    
    // <editor-fold desc="Constructor">    
    public CUsuario padre;
    
    public CSession(CUsuario padre){       
        this.datos=new HashMap();
        this.padre=padre;
    }
    public CSession(HashMap x,CUsuario padre){       
        this.datos=x;
        this.padre=padre;
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setFecha_inicio(Long x){
        this.datos.put(fechaa, x);
    }
    public void setFecha_fina(Long x){
        this.datos.put(fechab, x);
    }
    public void setEstado(boolean x){
        this.datos.put(estado, x);
    }
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
    public Long getFechaInicio(){
        Object aux=this.datos.get(fechaa);        
        return aux==null?0L:(Long)aux;        
    }
    public Long getFechaFin(){
        Object aux=this.datos.get(fechab);        
        return aux==null?0L:(Long)aux;        
    }
    public boolean getEstado(){
        Object aux=this.datos.get(estado);       
        if(aux==null)return false;
        else return (boolean)aux;
    }
    public ArrayList getMovimientos(){
        ArrayList<CMovimiento> x=new ArrayList();
        ArrayList<HashMap> y=new ArrayList<>();
        if(this.datos.get(movimiento)!=null)
            y=( ArrayList )this.datos.get(movimiento);
        for(HashMap h:y){
            CMovimiento d=new CMovimiento(this);
            d.set_datos(h);
            x.add(d);
        }
        return   x;  
    }
    
//    </editor-fold>

    // <editor-fold desc="Overrride">    
    
    @Override
    public String toString() {
        String aux="";
        SimpleDateFormat f=new SimpleDateFormat("EEEE F 'del' MMMM 'del' yyyy ",new Locale("es"));
        return f.format(new Date(getFechaInicio()));    
    }    
//        @Override
  //  public String toString() {
    //    return "Seccion de dia " +getFechaInicio();
    //}    

    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public void insert(){
        set_datos(new DSession(padre.getId()).insert(datos));
    }
    public void update(){

        if(this.getPosicion()==-1){
            //actualizar}            
        }
        set_datos(new DSession(padre.getId()).update(datos));
    }
    public boolean remove(){
        if(this.getPosicion()==-1){
            //actualizar}            
        }
        return (new DSession(padre.getId()).delete(this.getPosicion()));
    }
    public ObservableList listarBasic(){
        ArrayList<HashMap> l=new DSession(padre.getId()).getSessionBasic();
        ObservableList res=FXCollections.observableArrayList();
        for(HashMap m:l){
            res.add(new CSession(m, padre));
        }
        return res;
    }

    public void addMovimiento(ArrayList<CMovimiento> x){
    //        new DUsuario().
    }
    public String getFecha(Long fcha){
        SimpleDateFormat formateador = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' yyyy", new Locale("es","PE"));
        return formateador.format(new Date(fcha));
    }
    //</editor-fold>
}
