package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import java.util.HashMap;
import org.bson.types.ObjectId;
import static app.Main.CONNE;
import app.principal.PrincipalController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WARREN
 */
public class DPrincipal {

    conecion con = CONNE;
    String table;

    //<editor-fold desc="Metodos Crud Basicos">
    public HashMap insert(HashMap hash) {
        try {
            con.set_conecion(table);
            BasicDBObject datos = new BasicDBObject(hash);
            con.get_colletion().insert(datos);
            con.end();
            HashMap aux = (HashMap) datos.toMap();
            aux.remove("error");
            aux.remove("error_extends");
            aux.remove("error_code");
            return aux;
        } catch (MongoException e) {
            HashMap aux = new HashMap();
            aux.put("error", "Error " + e.getMessage());
            aux.put("error_extends", "Error " + con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
             System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        } catch (Exception e) {
            HashMap aux = new HashMap();
            aux.put("error", "El error es " + e.getMessage());
//            aux.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
                         System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        }
    }

    public HashMap update(ObjectId id, HashMap hash) {
        try {
            con.set_conecion(table);
            BasicDBObject datos = new BasicDBObject("$set", new BasicDBObject(hash));
            con.get_colletion().update(new BasicDBObject("_id", id), datos);
            con.end();
            hash.remove("error");
            hash.remove("error_extends");
            hash.remove("error_code");
            return hash;
        } catch (MongoException e) {           
            hash.put("error", e.getMessage());
            hash.put("error_extends", con.errors.get(e.getCode()));
            hash.put("error_code", e.getCode());
            Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e);
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return hash;
        } catch (Exception e) {
            hash.put("error", "El error es " + e.getMessage());
            hash.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
            Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e);
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return hash;
        }

    }

    public boolean delete(ObjectId id) {
        try {
            con.set_conecion(table);
            con.get_colletion().remove(new BasicDBObject("_id", id));
            con.end();
            return true;
        } catch (MongoException e) {
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return false;
        } catch (Exception e) {
            HashMap aux = new HashMap();
            aux.put("error", "El error es " + e.getMessage());
            aux.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return false;
        }
    }

    public HashMap buscar(ObjectId id) {
        HashMap aux = new HashMap();
        try {
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            DBObject res = con.get_colletion().findOne(a);
            if (res == null) {
                aux.put("error", "No encontrado");
                return aux;
            }
            aux = (HashMap) res.toMap();
            aux.put("error", null);
            aux.put("error_extends", null);
            aux.put("error_code", null);
            return aux;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", "mongo" + e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error", "El error es " + e.getMessage());
            aux.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        }
    }

    public ArrayList<HashMap> listar() {
        ArrayList<HashMap> res = new ArrayList<>();
        HashMap aux = new HashMap();
        try {
            con.set_conecion(table);
            List<DBObject> f = con.get_colletion().find().toArray();
            if (f == null) {
                aux.put("error", "No encontrado");
                return res;
            }
            for (DBObject k : f) {
                res.add((HashMap) k.toMap());
            }

            return res;

        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", "mongo" + e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return res;
        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error", "El error es " + e.getMessage());
            aux.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
                                     System.out.println("try controlado por warren");
             Logger.getLogger(DPrincipal.class.getName()).log(Level.SEVERE, null, e); 

            return res;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Metodos Auxiliares">
    //</editor-fold>
}
