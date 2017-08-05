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
    public final String categoria = "categoria";
    public final String nombre = "nombre";
    public final String descripcion = "descripcion";
    
    public final String medida="medida";        


    // </editor-fold>
    
    // <editor-fold desc="Variables">
    private CArticulo articulox;
//</editor-fold>  
    
    // <editor-fold desc="Constructor">        
    public CArticulo(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       

    public CArticulo(HashMap map){       
        this.datos=map;

    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    

    public void setNombre(String x) {
        this.datos.put(nombre, x);
    }
    public void setDescricion(String x) {
        this.datos.put(descripcion, x);
    }

    public void setCategoria(String x) {
        this.datos.put(categoria, x);
    }
    
    public void setMedidas(ArrayList<CMedida> x) {
        if(x==null)return ;
        ArrayList <HashMap> map=new ArrayList<>();
        for(CMedida m:x){
            map.add(m.get_datos());
        }            
        this.datos.put(medida, map);
    }

    //</editor-fold>

    
    // <editor-fold desc="Metodos Get">    

    public String getNombre() {
        return this.datos.get(nombre) == null ? "" : (String) this.datos.get(nombre);
    }
    public String getDescripcion() {
        return this.datos.get(descripcion) == null ? "" : (String) this.datos.get(descripcion);
    }


    public String getCategoria(){
         return this.datos.get(categoria) == null ? "" : String.valueOf( this.datos.get(categoria));
    }
    public CMedida getMedida(){
        if(this.datos.get(medida)==null)return null;
        return new CMedida((HashMap) this.datos.get(medida));
    }
    public ArrayList getMedidas(){
        if(this.datos.get(medida)==null)return new ArrayList();
        ArrayList<HashMap> l=(ArrayList) this.datos.get(medida);
        ArrayList res=new ArrayList();
        for(HashMap m:l)res.add(new CMedida(m));
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
        return "CArticulo{" +aux+'}';
    }    
    //</editor-fold>   
    
    // <editor-fold desc="Metodos get Auxiliares">    

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
