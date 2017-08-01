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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DPrincipal_embebed_array {
//coneccion
    protected final conecion con=CONNE;
//datos de collecions    
    protected String table;
    protected ObjectId id;
//datos del array    
    protected IterArrayList iter;
    protected String nombre;
    protected List<BasicDBObject>pipeline;
           
    
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
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 

            return aux;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
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
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        } catch (Exception e) {
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        }
    }
    public HashMap update(HashMap hash) {
        try {            
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("_id", id);
            BasicDBObject b = new BasicDBObject("$set", new BasicDBObject(iter.generar()+nombre+"."+hash.get("n"), hash));
            con.get_colletion().update(a, b);
            con.end();
            HashMap aux=(HashMap) b.toMap();
            return hash;
        } catch (MongoException e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return aux;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
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
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return false;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return false;
        }
    }
    public void generar_pipeline(){
            BasicDBObject a = new BasicDBObject("_id", id);    
          pipeline=new ArrayList<BasicDBObject>();
            pipeline.add(new BasicDBObject("$match",a));
            String s="";
            for(IterArray  x:iter)    {
                s+=x.getNombre()+".";
                pipeline.add(new BasicDBObject("$unwind","$"+s.substring(0,s.length()-1)));
                pipeline.add(new BasicDBObject("$match", new BasicDBObject(s+"n",x.getN())));
            }
            
            pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$"+s+nombre)));    
            pipeline.add(new BasicDBObject("$project",new BasicDBObject("_id",new BasicDBObject("$filter",new BasicDBObject().append("input", "$_id").append("as", "item").append("cond", new BasicDBObject("$ne",new Object[]{"$$item.delete",true}))))));    

    }

    public ArrayList listar(){
     try {       
         con.set_conecion(table);
            ArrayList res=new ArrayList();
            BasicDBObject a = new BasicDBObject("_id", id);    
            List<BasicDBObject>pipeline=new ArrayList<BasicDBObject>();
            pipeline.add(new BasicDBObject("$match",a));
            String s="";
            for(IterArray  x:iter)    {
                s+=x.getNombre()+".";
                pipeline.add(new BasicDBObject("$unwind","$"+s.substring(0,s.length()-1)));
                pipeline.add(new BasicDBObject("$match", new BasicDBObject(s+"n",x.getN())));
            }
            
            pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$"+s+nombre)));    
            pipeline.add(new BasicDBObject("$project",new BasicDBObject("_id",new BasicDBObject("$filter",new BasicDBObject().append("input", "$_id").append("as", "item").append("cond", new BasicDBObject("$ne",new Object[]{"$$item.delete",true}))))));    
             AggregationOutput out=con.get_colletion().aggregate(pipeline);
             for(DBObject dbObject : out.results()){
                res=(ArrayList) ((HashMap)dbObject).get("_id");
            }
            if(res==null)res=new ArrayList();
             return res;
            
        } catch (MongoException e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return null;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return null;
        }
    }
    public HashMap buscar(int num){
     try {       
            con.set_conecion(table);
            HashMap res=new HashMap();
            BasicDBObject a = new BasicDBObject("_id", id);    
            List<BasicDBObject>pipeline=new ArrayList<BasicDBObject>();
            pipeline.add(new BasicDBObject("$match",a));
            String s="";
            for(IterArray  x:iter)    {
                s+=x.getNombre()+".";
                pipeline.add(new BasicDBObject("$unwind","$"+s.substring(0,s.length()-1)));
                pipeline.add(new BasicDBObject("$match", new BasicDBObject(s+"n",x.getN())));
            }
            
            pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$"+s+nombre)));    
            pipeline.add(new BasicDBObject("$project",new BasicDBObject("_id",new BasicDBObject("$filter",new BasicDBObject().append("input", "$_id").append("as", "item").append("cond", new BasicDBObject("$ne",new Object[]{"$$item.delete",true}))))));    

            pipeline.add(new BasicDBObject("$unwind","$_id"));
            pipeline.add(new BasicDBObject("$match",new BasicDBObject("_id.n",num)));
            AggregationOutput out=con.get_colletion().aggregate(pipeline);
            for(DBObject dbObject : out.results()){
                res=(HashMap) ((HashMap)dbObject).get("_id");
            }
            if(res==null)res=new HashMap();
            return res;            
        } catch (MongoException e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return null;
        } catch (Exception e) {
            System.out.println(e);
            HashMap aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DPrincipal_embebed_array.class.getName()).log(Level.SEVERE, null, e); 
            return null;
        }
    }
    //</editor-fold>

}
