package app;

import app.clases.CUsuario;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WARREN
 */


public class DUsuario extends DPrincipal{

    CUsuario u=new CUsuario(); 
    
    // <editor-fold desc="Contructor">        
    public DUsuario() {
        this.table=u.getNombreCollections();
    }
    //</editor-fold>
    
    // <editor-fold desc="Consultas">    
   public ArrayList<HashMap> getAllUserBasic() {
        ArrayList<HashMap> res = new ArrayList<>();
        HashMap aux = new HashMap();
        try {
            con.set_conecion(table);
            CUsuario USER=new CUsuario();
            BasicDBObject consulta=new BasicDBObject();
            BasicDBObject options=new BasicDBObject("USER.seccion",false);
            
            List<DBObject> f = con.get_colletion().find(consulta,options).toArray();
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
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;
        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error", "El error es " + e.getMessage());
            aux.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;
        }
    }
    public HashMap validar(HashMap user){
        HashMap aux=new HashMap();  
        try{
            con.set_conecion(table);
            BasicDBObject a=new BasicDBObject(user);
            BasicDBObject b=new BasicDBObject("seccion",false);
            DBObject res= con.get_colletion().findOne(a,b);

             if(res==null){
                aux.put("error","No encontrado");
                return aux;
            }
            aux=(HashMap) res.toMap();
            aux.remove("error");
            aux.remove("error_extends");
            aux.remove("error_code");
            return aux;          
        }catch(MongoException e){
            System.out.println(e.getMessage()+e.getCause());
            aux=new HashMap();            
            aux.put("error","mongo"+e.getMessage());
            aux.put("error_extends",con.errors.get(e.getCode()));
            aux.put("error_code",e.getCode());
            return aux;
        }catch(Exception e){        
            System.out.println(e.getMessage()+e.getCause()+"dd");
            aux=new HashMap();
            aux.put("error","El error es "+ e.getMessage());

                    return aux;
        }
    }
    public ArrayList getAllTotalVentas(){
        HashMap aux = new HashMap();
        ArrayList datos = new ArrayList();
        try {
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("$unwind","$seccion");            
            BasicDBObject b = new BasicDBObject("$unwind","$seccion.movimiento");            
            BasicDBObject c = new BasicDBObject("$unwind","$seccion.movimiento.venta_detalle");            
            BasicDBObject d = new BasicDBObject("$project", new BasicDBObject("seccion.movimiento.venta_detalle",true).
                                                            append("_id", true).
                                                            append("entidad", 1));            
            BasicDBObject e = new BasicDBObject("$group", new BasicDBObject("_id",new BasicDBObject("_id","$_id").append("entidad", "$entidad")).
                                                          append("otro",new BasicDBObject("$push","$seccion.movimiento.venta_detalle") ));            
            BasicDBObject f = new BasicDBObject("$unwind", "$otro");            

            BasicDBObject g = new BasicDBObject("$group", new BasicDBObject("_id","$_id").
                                                          append("sum", new BasicDBObject("$sum",new BasicDBObject("$multiply",new Object[]{"$otro.precio","$otro.cantidad"}))));

            AggregationOutput out = con.get_colletion().aggregate(a, b,c,d,e,f,g);    

            for (DBObject dbObject : out.results()) {
                datos.add(((HashMap) dbObject));
            }
            
            con.end();              
            return datos;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            datos=new ArrayList();
            datos.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return datos;
        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            datos=new ArrayList();
            datos.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return datos;
        }
    }
        //</editor-fold>
    
    // <editor-fold desc="Operaciones">    
    public ArrayList getSessionAviertas(ObjectId usuario){
        HashMap aux = new HashMap();
        ArrayList datos = new ArrayList();
        try {
            con.set_conecion(table);
            BasicDBObject a = new BasicDBObject("$match", new BasicDBObject("_id",usuario));            
            BasicDBObject b = new BasicDBObject("$project", new BasicDBObject("seccion",true).
                                                                        append("_id", false));            
            BasicDBObject c = new BasicDBObject("$unwind", "$seccion");            
            BasicDBObject cc = new BasicDBObject("$project", new BasicDBObject("movimiento",false));            

            BasicDBObject d = new BasicDBObject("$match", new BasicDBObject("seccion.estado",true));

            AggregationOutput out = con.get_colletion().aggregate(a, b,c,cc,d);    
            for (DBObject dbObject : out.results()) {
                datos.add(((HashMap) dbObject).get("seccion"));
            }
            con.end();              
            return datos;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            datos=new ArrayList();
            datos.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return datos;
        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            datos=new ArrayList();
            datos.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return datos;
        }
    }
    //</editor-fold>
        
}
