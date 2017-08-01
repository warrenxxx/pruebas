package app.recursos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.IterArray;
import app.IterArrayList;
import static app.Main.CONNE;
import app.conecion;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DPrincipalReucursos {
//coneccion    
    
    protected final conecion con=CONNE;
//datos de collecions    
    private final String table="recursos";
    protected String id;
//datos del array    
    protected IterArrayList iter;
    protected String nombre;
    
    //<editor-fold desc="Metodos Crud Basicos">
        public HashMap insert(HashMap hash) {
        try {
            con.set_conecion(table);//ObjectId de la tabla
            hash.put("_id", new ObjectId());
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new    BasicDBObject("$push", new BasicDBObject(iter.generar()+nombre, hash)) ;
            con.get_colletion().update(a, b);
            //insertamos la collecions
            //buscamo posssion
            List<BasicDBObject>pipeline=new ArrayList<BasicDBObject>();
            pipeline.add(new BasicDBObject("$match",a));
            String s="";
            for(IterArray  x:iter)    {
                s+=x.getNombre()+".";
                pipeline.add(new BasicDBObject("$unwind","$"+s.substring(0,s.length()-1)));
                pipeline.add(new BasicDBObject("$match", new BasicDBObject(s+"n",x.getN())));
            }
            
            pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$"+s+nombre)));
            ArrayList aux=new ArrayList();
            aux.add("$_id");
            aux.add(hash);
            pipeline.add(new BasicDBObject("$project",new BasicDBObject("index",new BasicDBObject("$indexOfArray",aux))));
                       
            AggregationOutput out=con.get_colletion().aggregate(pipeline);
            int n=-1;
            for(DBObject dbObject : out.results()){
                n=(int) dbObject.get("index");
            }   
            BasicDBObject c = new BasicDBObject("_id", id);
            BasicDBObject d = new    BasicDBObject("$set", new BasicDBObject(iter.generar()+nombre+"."+n+".n", n)) ;
            con.get_colletion().update(c, d);            
            con.end();
            
            hash.put("n", n);
            return hash;
        } catch (MongoException e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        }
    }
    public HashMap actId(HashMap hash){
            try {
            con.set_conecion(table);//ObjectId de la tabla
            BasicDBObject a = new BasicDBObject("_id", id);
            List<BasicDBObject>pipeline=new ArrayList<BasicDBObject>();
            pipeline.add(new BasicDBObject("$match",a));
            String s="";
            for(IterArray  x:iter)    {
                s+=x.getNombre()+".";
                pipeline.add(new BasicDBObject("$unwind","$"+s.substring(0,s.length()-1)));
                pipeline.add(new BasicDBObject("$match", new BasicDBObject(s+"_id",x.getN())));
            }
            
            pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$"+s+nombre)));

            ArrayList aux=new ArrayList();
            aux.add("$_id");
            aux.add(hash);
            pipeline.add(new BasicDBObject("$project",new BasicDBObject("index",new BasicDBObject("$indexOfArray",aux))));
                       
            AggregationOutput out=con.get_colletion().aggregate(pipeline);
            int n=-1;
            for(DBObject dbObject : out.results()){
                n=(int) dbObject.get("index");
            }   
            
            BasicDBObject c = new BasicDBObject("_id", id);
            BasicDBObject d = new    BasicDBObject("$set", new BasicDBObject(iter.generar()+nombre+"."+n+"._id", n)) ;
            con.get_colletion().update(c, d);
            
            con.end(); 
            return hash;

        } catch (MongoException e) {
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        } catch (Exception e) {
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        }
    }
    public HashMap update(HashMap hash) {
        try {            
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new BasicDBObject("$set", new BasicDBObject(iter.generar()+nombre+"."+hash.get("_id"), hash));
            con.get_colletion().update(a, b);
            con.end();
            HashMap aux=(HashMap) b.toMap();
            return (HashMap) ((HashMap)aux.get("$set")).get("seccion."+hash.get("_id"));
        } catch (MongoException e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        }
    }

    public boolean delete(int n) {
        try {
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new BasicDBObject("$set", new BasicDBObject(iter.generar()+nombre+"."+n+".delete",true));
            con.get_colletion().update(a, b);
            con.end();
            return true;
        } catch (MongoException e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return false;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipalReucursos.class.getName()).log(Level.SEVERE, null, e); 
            return false;
        }
    }
    //</editor-fold>
}
