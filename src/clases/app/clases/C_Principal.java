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
public class C_Principal {
    protected HashMap datos;

    protected int n;    
    protected String nombreCollection; 
    public String error;    
    public String error_extend;    

    public C_Principal() {
        datos=new HashMap();
    }
    public C_Principal(HashMap map) {
        datos=map;
    }
    
    public HashMap get_datos(){
        return (HashMap) datos.clone();
    }
    public void set_datos(HashMap set){
        this.datos=set;
    }
    public void set_element(String key,Object value){
        this.datos.put(key, value);
    }
    public Object get_element(String key){
        return this.datos.get(key);
    }
    public void setId(ObjectId valor){
        datos.put("_id",valor);
    }
    public ObjectId getId(){
        if(datos.get("_id")==null)return null;
        return (ObjectId) datos.get("_id");
    }
    public String getNombreCollections(){
        return this.nombreCollection;
    }
    public void setNombreCollections(String x){
        this.nombreCollection=x;
    }
    public String getError(){
        if( datos.get("error")==null){ return null;}
        return (String) datos.get("error");
    }
    public String getError_Extend(){
        if( datos.get("error_extends")==null){ return null;}
        return (String) datos.get("error_extends");
    }
    
    public boolean tiene_error(){
        if(getError()==null)return false;
        if(getError().compareTo("null")==0)return false;
        return true;

    }
    
        @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();

            aux+=s+" = "+datos.get(s)+",";
        }
        return "cPrincipal{" +aux+'}';
    }    
}
