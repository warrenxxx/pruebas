/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
    

/**
 *
 * @author WARREN
 */
public class conecion{
    
    // <editor-fold desc="Atributos">    
    private String database;
    private String tabla;
    private String url;
    private MongoClient mongo=null;    
    private DB db;
    public HashMap<Integer,String> errors;
    //</editor-fold>
    
    // <editor-fold desc="Constructores">       
    
    public conecion(){
        this.url="localhost";
        this.database="bd3";
        MongoClientURI uri = new MongoClientURI("mongodb://warren_x_x_x:amirvalentino123@cluster0-shard-00-00-pvnfj.mongodb.net:27017,cluster0-shard-00-01-pvnfj.mongodb.net:27017,cluster0-shard-00-02-pvnfj.mongodb.net:27017/bd3?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
        try{
            //mongo=new MongoClient(uri);        
            mongo=new MongoClient(url,27017);
            db=mongo.getDB(database);
        }catch(MongoExecutionTimeoutException e){
            System.out.println("try controlado por warren");
            Logger.getLogger(conecion.class.getName()).log(Level.SEVERE, null, e); 
            System.out.println(e.getMessage());
        }catch(MongoException e){
            System.out.println("try controlado por warren");
            Logger.getLogger(conecion.class.getName()).log(Level.SEVERE, null, e); 
            System.out.println(e.getMessage());
        }
        errores();
    }
    
    //</editor-fold>
    
    // <editor-fold desc="Metodos Alternos">        
    public void errores(){
        errors=new HashMap<>();
        errors.put(-3, "Error de Conneccion");
        errors.put(11000, "Duplicado de Key");
    }


    public boolean is_run_conection(){
            DBObject ping = new BasicDBObject("ping", "1");
            try{
                mongo.getDB(database).command(ping);
                return true;
            } catch (MongoException e) {
            System.out.println("try controlado por warren");
            Logger.getLogger(conecion.class.getName()).log(Level.SEVERE, null, e); 
                return false;
            }
    }
    
    public int end(){
        try{
     //       if(mongo!=null)mongo.close();
        }catch(Exception e){
            System.out.println("try controlado por warren");
            Logger.getLogger(conecion.class.getName()).log(Level.SEVERE, null, e); 
            return -1;
        }
        return 1;
    }
    //</editor-fold>
    
    // <editor-fold desc="Metodos set">
    public void set_conecion(String tabla) {
        this.tabla=tabla;
    }
    //</editor-fold>
    
    //<editor-fold desc="Metodos Get">
    public DBCollection get_colletion(){
        return db.getCollection(tabla);
    }
    public MongoDatabase get_DATABASE(){
        return mongo.getDatabase(database);
    }
    //</editor-fold>       
}
