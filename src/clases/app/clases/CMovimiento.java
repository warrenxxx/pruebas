package app.clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.DMOvimiento;
import app.reportes.TVenta_Detalle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CMovimiento extends C_PrincipalArray{
    // <editor-fold desc="DATOS DE LA BD">    
        private final String hora="hora";
        private final String persona_atendida="persona_atendida";
        private final String movimientoDetalle="venta_detalle";
        private final String tipo="tipo";        
        private final String tipo_pago="tipo_pago";        
    // </editor-fold>     
    
    // <editor-fold desc="Variables">    
        public CSession padre;
        public CModoPago modoPagox;
        public ArrayList< CMovimientoDetalle > movimientoDetallex;
    //</editor-fold>
    
    // <editor-fold desc="Constructor">    
    
    public CMovimiento(CSession padre){       
        this.padre=padre;
        this.datos=new HashMap();
        this.datos.put("_id", new ObjectId());
    }       
    public CMovimiento(CSession padre,HashMap map){               
        this.padre=padre;
        this.datos=map;
        this.datos.put("_id", new ObjectId());
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setHora(Long x){
        this.datos.put(hora, x);
    }
    public void setPersona_atendida(CPersona x){
        this.datos.put(persona_atendida, x.get_datos());
    }
    public void setTipoCompra(){
        this.datos.put(tipo, "compra");
    }
    public void setTipoVenta(){
        this.datos.put(tipo, "venta");
    }
    public void setModoPago(String x){
        this.datos.put(tipo_pago, x);
    }
    public void setMovimientoDetalle(ArrayList<CMovimientoDetalle> x){
        ArrayList l=new ArrayList();
        for(CMovimientoDetalle y:x)l.add(y.get_datos());
        this.datos.put(movimientoDetalle,l);
    }
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
   
    public Long getHora(){
        if(this.datos.get(hora)==null)return 0L;
        return (Long) this.datos.get(hora);                
    }
    public String getHoraS(){
        SimpleDateFormat formateador = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' yyyy", new Locale("es","PE"));
        return formateador.format(new Date(getHora()));
    }
    public CPersona getPersona_atendida(){
        if(this.datos.get(persona_atendida)==null)return new CPersona();
        return new CPersona((HashMap) this.datos.get(persona_atendida));
    }
    public String getMogoPago(){
        if(this.datos.get(tipo_pago)==null)return "";
        return  this.datos.get(tipo_pago).toString();        
    }
    public ObservableList getMovimientosDetalleObserbable(){
        ObservableList res=FXCollections.observableArrayList();
        ArrayList<CMovimientoDetalle> l=getMovimientoDetalle();
        for(CMovimientoDetalle M:l ){
            res.add(new TVenta_Detalle(M));
        }
        return res;
    }
    public ArrayList getMovimientoDetalle(){
        ArrayList<CMovimientoDetalle> x=new ArrayList();
        ArrayList<HashMap> y=new ArrayList<>();
        if(this.datos.get(movimientoDetalle)!=null)
            y=( ArrayList )this.datos.get(movimientoDetalle);
        for(HashMap h:y){
            CMovimientoDetalle d=new CMovimientoDetalle();    
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
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CMovdetalle{" +aux+'}';
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public boolean is_compra(){
        Object x= this.datos.get(tipo);
        if(x==null)return false;
        if(x.toString().compareTo("compra")==0)return true;
        else return false;
    }
    public boolean is_venta(){
        Object x= this.datos.get(tipo);
        if(x==null)return true;
        if(x.toString().compareTo("compra")!=0)return true;
        else return false;
    }
    public void insert(){
        set_datos(new DMOvimiento(this.padre).insert(datos));
    }
    public void update(ObjectId usuario,CSession session,int n){
        set_datos(new DMOvimiento(this.padre).update(datos));
    }
    public boolean remove(ObjectId usuario,CSession session, int n){
        return (new DMOvimiento(this.padre).delete(this.getPosicion()));
    }
    public ArrayList listar(){
        ArrayList res=new ArrayList();
        ArrayList<HashMap> l=new DMOvimiento(padre).listar();
        for(HashMap m:l){
            res.add(new CMovimiento(padre, m));
        }
        return res;
    }
    public ArrayList listarVentasConArticulo(){
        ArrayList res=new ArrayList();
        ArrayList<HashMap> l=new DMOvimiento(padre).listarVentasConArticulo();
        for(HashMap m:l){
            System.out.println(m);
            res.add(new CMovimiento(padre, m));
        }
        return res;
    }
    public ArrayList listarComprasConArticulo(){
        ArrayList res=new ArrayList();
        ArrayList<HashMap> l=new DMOvimiento(padre).listarComprasConArticulo();
        for(HashMap m:l){
            System.out.println(m);
            res.add(new CMovimiento(padre, m));
        }
        return res;
    }
    public CMovimiento buscar(int n){
        return new CMovimiento(this.padre,new DMOvimiento(padre).buscar(n));
    }
    public double sumTotalVentas(){
        return new DMOvimiento(padre).getSumaVentas();
    }

    public ObservableList getTVentaDetalleList(){
        ArrayList<HashMap> l=new DMOvimiento(padre).getVentasTotal();
        ObservableList res=FXCollections.observableArrayList();
        for(HashMap m:l){
            res.add(new TVenta_Detalle(new CMovimientoDetalle(m)));
        }
        return res;
    }
    public ObservableList getTComprasDetalleList(){
        ArrayList<HashMap> l=new DMOvimiento(padre).getComprasTotal();
        ObservableList res=FXCollections.observableArrayList();
        for(HashMap m:l){
            res.add(new TVenta_Detalle(new CMovimientoDetalle(m)));
        }
        return res;
    }
    //</editor-fold>
}
