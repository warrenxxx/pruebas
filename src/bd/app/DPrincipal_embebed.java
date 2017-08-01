package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static app.Main.CONNE;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DPrincipal_embebed {

    conecion con=CONNE;
    String table;
    String elemento;
    ObjectId id;

    //<editor-fold desc="Metodos Crud Basicos">
    public HashMap insert(HashMap hash) {
        try {
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new BasicDBObject("$set", new BasicDBObject(elemento, hash));
            con.get_colletion().update(a, b);
            con.end();
            HashMap aux=(HashMap) b.toMap();
            aux.put("error",null);
            aux.put("error_extends",null);
            aux.put("error_code",null);
            return aux;
        } catch (MongoException e) {
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal_embebed.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        } catch (Exception e) {
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
                                                 System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal_embebed.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        }
    }

    public HashMap update(HashMap hash) {
        try {            
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new BasicDBObject("$set", new BasicDBObject(elemento, hash));
            con.get_colletion().update(a, b);
            con.end();
            HashMap aux=(HashMap) b.toMap();
            aux.put("error",null);
            aux.put("error_extends",null);
            aux.put("error_code",null);
            return aux;        
        } catch (MongoException e) {
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            return aux;
        } catch (Exception e) {
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            return aux;
        }
    }

    public boolean delete() {
        try {
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new BasicDBObject("$unset", new BasicDBObject(elemento, ""));
            con.get_colletion().update(a, b);
            con.end();
            return true;
        } catch (MongoException e) {
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            return false;
        } catch (Exception e) {
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            return false;
        }
    }

    public HashMap buscar() {
        HashMap aux = new HashMap();;
        try {
            ArrayList datos = new ArrayList();
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("$match", new BasicDBObject("_id",id));
            
            BasicDBObject b = new BasicDBObject("$group", new BasicDBObject("_id", "$"+elemento));

            AggregationOutput out = con.get_colletion().aggregate(a, b);    
            for (DBObject dbObject : out.results()) {
                datos.add((HashMap) dbObject);
            }
            con.end();
            if(datos.size()==0){
                aux.put("erro", "no encontrado ");
                return aux;
            }                        
            aux=(HashMap) datos.get(0);
            aux=(HashMap) aux.get("_id");

            aux.put("error",null);
            aux.put("error_extends",null);
            aux.put("error_code",null);
            return aux;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            return aux;
        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            return aux;
        }
    }

    //</editor-fold>
}
