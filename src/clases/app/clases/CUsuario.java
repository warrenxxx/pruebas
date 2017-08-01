package app.clases;

import app.DUsuario;
import app.usuario.TUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CUsuario extends C_Principal{
    // <editor-fold desc="DATOS DE LA BD">
        private final String entidad="entidad";
        private final String tipo="tipo";
        public  final String user="user";
        public final String password="pass";        
        public final String seccion="seccion";
        public final String nivel="nivel";  
        public final String caja="caja";  
        
    // </editor-fold>     
    
    // <editor-fold desc="Variables">    
        public Object entidadx;
        public ArrayList<CSession> sessionx;
    //</editor-fold>
        
    // <editor-fold desc="Constructor">        
    public CUsuario(){       
        this.datos=new HashMap();
        this.setNombreCollections("user");
    }       
    public CUsuario(HashMap aux){       
        this.datos=aux;
        this.setNombreCollections("user");
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
    public void setEntidad(CPersona x){
        if(x!=null)
        this.datos.put(entidad,x.get_datos());
    }

    public void setTipo(String x){
        this.datos.put(tipo, x);
    }
    public void setNivel(String x){
        this.datos.put(nivel, x);
    }
    public void setUser(String x){
        this.datos.put(user, x);
    }
    public void setPassword(String x){
        this.datos.put(password, x);
    }

    public void setSeccion(ArrayList<CSession> x){
        ArrayList aux=new ArrayList();
        for(CSession y:x)aux.add(y.get_datos());
        this.datos.put(seccion, aux);
    }
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    
   
    public Object getEntidad(){
        if(this.datos.get(entidad)==null)return new CPersona();
        return  new CPersona((HashMap)this.datos.get(entidad));        
    }
    public String getTipo(){
        return  (String) this.datos.get(tipo);        
    }
    public String getUser(){
        return  (String) this.datos.get(user);        
    }
    public String getPassword(){
        return  (String) this.datos.get(password);        
    }
    public String getNivel(){
        return  (String) this.datos.get(nivel);        
    }
    public ArrayList getSeccion(){
        ArrayList<CSession> x=new ArrayList();
        ArrayList<HashMap> y=new ArrayList<>();
        if(this.datos.get(seccion)!=null)
            y=( ArrayList )this.datos.get(seccion);
        for(HashMap h:y){
            CSession d=new CSession(this);
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
        return "CUSUARIO{" +aux+'}';
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    
    public void buscar(String x){
        set_datos( new DUsuario().buscar(new ObjectId(x)));
    }
    public void insert(){
        set_datos(new DUsuario().insert(get_datos()));
    }
    public void update(){
        set_datos(new DUsuario().update(getId(), datos));
    }
    public boolean delete(){
        return new DUsuario().delete(getId());
    }
    public void validar(String puser,String pass){
        HashMap aux=new HashMap();
        aux.put(user, puser);
        aux.put(password, pass);
        set_datos( new DUsuario().validar(aux));
    }
    public ObservableList listaObservableTUsuario(){
        ArrayList<HashMap> aux=new DUsuario().getAllUserBasic();
        ObservableList res=FXCollections.observableArrayList();   
        for(HashMap x:aux)
            res.add(new TUsuario( new CUsuario(x)));
       return res;
    }
    public ArrayList<CSession> getSeccionAbiertas(){
        ArrayList<HashMap> l= new DUsuario().getSessionAviertas(getId());
        ArrayList res=new ArrayList();
        for(HashMap m:l)res.add(new CSession(m,this));
        return res;
    }
    public ObservableList getSumVentasAll(){
        ArrayList<HashMap> l= new DUsuario().getAllTotalVentas();
        ObservableList res=FXCollections.observableArrayList();
        
        for(HashMap m:l){
            res.add(new Pair(new CUsuario((HashMap) m.get("_id")),m.get("sum")));
        }
        return res;
    }
    /*
    public void addSession(CSession x){
        x.insert(this);
    }*/

    //</editor-fold>
}
