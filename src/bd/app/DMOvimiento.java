/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import app.clases.CMovimientoDetalle;
import app.clases.CSession;
import app.clases.CUsuario;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author warren
 */
public class DMOvimiento extends DPrincipal_embebed_array{
    private CSession seccion;
    private CUsuario user=new CUsuario();

    public DMOvimiento(CSession seccion) {
        this.seccion=seccion;
        this.table=user.getNombreCollections();
        this.nombre=seccion.movimiento;
        this.id=seccion.padre.getId();
        this.iter=new IterArrayList();        
        if(seccion.getPosicion()==-1){
            seccion.set_datos(new DSession(seccion.padre.getId()).actId(seccion.get_datos()));
        }
        this.iter.add(new IterArray(user.seccion, seccion.getPosicion()));        
    }

    @Override
    public HashMap insert(HashMap hash) {        
        if(hash.get("venta_detalle")!=null){
            ArrayList<HashMap>aux=(ArrayList<HashMap>) hash.get("venta_detalle");
            ArrayList l1=new ArrayList();
            ArrayList l2=new ArrayList();
            for(HashMap p:aux){
                l1.add(p.get("id_articulo"));
                l2.add(p.get("cantidad"));
            }
            actualizarStock(l1, l2);
            return super.insert(hash); //To change body of generated methods, choose Tools | Templates.   
        }
        return hash;
    }
    
    public void actualizarStock(ArrayList ids,ArrayList n){        
        this.con.get_DATABASE().runCommand(new Document("$eval", "db.loadServerScripts()"));
        this.con.get_DATABASE().runCommand(new Document("$eval", "RegistrarProductos("+JSON.serialize(ids)+","+JSON.serialize(n)+")"));        
    }
    public double getSumaVentas(){
        HashMap aux = new HashMap();
        ArrayList datos = new ArrayList();
        try {
            con.set_conecion(table);
                    generar_pipeline();
        pipeline.add(new BasicDBObject("$unwind","$_id"));
        pipeline.add(new BasicDBObject("$match",new BasicDBObject("_id.tipo","venta")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle"));
        pipeline.add(new BasicDBObject("$addFields",new BasicDBObject("total",new BasicDBObject("$multiply",new Object[]{"$_id.venta_detalle.precio","$_id.venta_detalle.cantidad",-1}))));
        pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$_id.venta_detalle.id_articulo").
                                                           append("sum", new BasicDBObject("$sum","$total"))));
        pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","").append("sum", new BasicDBObject("$sum","$sum"))));
        AggregationOutput out = con.get_colletion().aggregate(pipeline);    
        double res=0;
        for (DBObject dbObject : out.results()) {
            res=(double) dbObject.get("sum");
        }
          
            con.end();              
            return res;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            datos=new ArrayList();
            datos.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return -1;

        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            datos=new ArrayList();
            datos.add(aux);
            System.out.println("try controlado por warren");

            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return -1;
        }
    }
    public ArrayList getVentasTotal(){
        HashMap aux = new HashMap();
        ArrayList res = new ArrayList();
        try {
            con.set_conecion(table);
                    generar_pipeline();
        pipeline.add(new BasicDBObject("$unwind","$_id"));
        pipeline.add(new BasicDBObject("$match",new BasicDBObject("_id.tipo","venta")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle"));

        pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$_id.venta_detalle.id_articulo").
                                                           append("cantidad", new BasicDBObject("$sum","$_id.venta_detalle.cantidad")).
                                                           append("descuento", new BasicDBObject("$sum","$_id.venta_detalle.descuento")).
                                                           append("precio", new BasicDBObject("$first","$_id.venta_detalle.precio"))));
        pipeline.add(new BasicDBObject("$lookup",new BasicDBObject("from","ARTICULO").
                                                            append("localField", "_id").
                                                            append("foreignField", "_id").
                                                            append("as", "articulo")));
        pipeline.add(new BasicDBObject("$unwind","$articulo"));
        AggregationOutput out = con.get_colletion().aggregate(pipeline);    
        for (DBObject dbObject : out.results()) {
            res.add((HashMap)dbObject);
        }
          
            con.end();              
            return res;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;

        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");

            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;
        }
    }

    public ArrayList listarVentasConArticulo(){
        HashMap aux = new HashMap();
        ArrayList res = new ArrayList();
        try {
            con.set_conecion(table);
                    generar_pipeline();
        pipeline.add(new BasicDBObject("$unwind","$_id"));
        pipeline.add(new BasicDBObject("$match",new BasicDBObject("_id.tipo","venta")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle"));

        pipeline.add(new BasicDBObject("$lookup",new BasicDBObject("from","ARTICULO").
                                                            append("localField", "_id.venta_detalle.id_articulo").
                                                            append("foreignField","_id").
                                                            append("as", "_id.venta_detalle.articulo")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle.articulo"));
        pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$_id._id")   .
                                                           append("n", new BasicDBObject("$first","$_id.n")).
                                                           append("venta_detalle", new BasicDBObject("$push","$_id.venta_detalle")).
                                                           append("tipo", new BasicDBObject("$first","$_id.tipo")).
                                                           append("tipo_pago", new BasicDBObject("$first","$_id.tipo_pago")).
                                                           append("hora", new BasicDBObject("$first","$_id.hora"))));
        pipeline.add(new BasicDBObject("$sort",new BasicDBObject("n",1)));

        AggregationOutput out = con.get_colletion().aggregate(pipeline);    
        for (DBObject dbObject : out.results()) {
            res.add((HashMap)dbObject);
        }
          
            con.end();              
            return res;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;

        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");

            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;
        }
    }
    public ArrayList getComprasTotal(){
        HashMap aux = new HashMap();
        ArrayList res = new ArrayList();
        try {
            con.set_conecion(table);
                    generar_pipeline();
        pipeline.add(new BasicDBObject("$unwind","$_id"));
        pipeline.add(new BasicDBObject("$match",new BasicDBObject("_id.tipo","compra")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle"));

        pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$_id.venta_detalle.id_articulo").
                                                           append("cantidad", new BasicDBObject("$sum","$_id.venta_detalle.cantidad")).
                                                           append("descuento", new BasicDBObject("$sum","$_id.venta_detalle.descuento")).
                                                           append("precio", new BasicDBObject("$first","$_id.venta_detalle.precio"))));
        pipeline.add(new BasicDBObject("$lookup",new BasicDBObject("from","ARTICULO").
                                                            append("localField", "_id").
                                                            append("foreignField", "_id").
                                                            append("as", "articulo")));
        pipeline.add(new BasicDBObject("$unwind","$articulo"));
        AggregationOutput out = con.get_colletion().aggregate(pipeline);    
        for (DBObject dbObject : out.results()) {
            res.add((HashMap)dbObject);
        }
          
            con.end();              
            return res;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;

        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");

            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;
        }
    }

    public ArrayList listarComprasConArticulo(){
        HashMap aux = new HashMap();
        ArrayList res = new ArrayList();
        try {
            con.set_conecion(table);
                    generar_pipeline();
        pipeline.add(new BasicDBObject("$unwind","$_id"));
        pipeline.add(new BasicDBObject("$match",new BasicDBObject("_id.tipo","compras")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle"));

        pipeline.add(new BasicDBObject("$lookup",new BasicDBObject("from","ARTICULO").
                                                            append("localField", "_id.venta_detalle.id_articulo").
                                                            append("foreignField","_id").
                                                            append("as", "_id.venta_detalle.articulo")));
        pipeline.add(new BasicDBObject("$unwind","$_id.venta_detalle.articulo"));
        pipeline.add(new BasicDBObject("$group",new BasicDBObject("_id","$_id._id")   .
                                                           append("n", new BasicDBObject("$first","$_id.n")).
                                                           append("venta_detalle", new BasicDBObject("$push","$_id.venta_detalle")).
                                                           append("tipo", new BasicDBObject("$first","$_id.tipo")).
                                                           append("tipo_pago", new BasicDBObject("$first","$_id.tipo_pago")).
                                                           append("hora", new BasicDBObject("$first","$_id.hora"))));
        pipeline.add(new BasicDBObject("$sort",new BasicDBObject("n",1)));

        AggregationOutput out = con.get_colletion().aggregate(pipeline);    
        for (DBObject dbObject : out.results()) {
            res.add((HashMap)dbObject);
        }
          
            con.end();              
            return res;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;

        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error","El error es "+ e.getMessage());
            aux.put("error_extends","El error extendido es "+ con.errors.get(e.getCause()));
            res=new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");

            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e); 
            return res;
        }
    }


}
