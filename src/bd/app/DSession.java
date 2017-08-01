package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import app.clases.CUsuario;
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
public class DSession extends DPrincipal_embebed_array {
//    private String table = "user";
//    conecion con=CONNE;    

    private final CUsuario user = new CUsuario();

    //private CSession session=new CSession();
    // <editor-fold desc="Contructor">
    public DSession(ObjectId usuario) {
        CUsuario x = new CUsuario();
        this.table = x.getNombreCollections();
        this.nombre = x.seccion;
        this.id = usuario;
        this.iter = new IterArrayList();
    }

    public ArrayList getSessionBasic() {
        HashMap aux = new HashMap();
        ArrayList res = new ArrayList();
        try {
            con.set_conecion(table);
            generar_pipeline();
            pipeline.add(new BasicDBObject("$project", new BasicDBObject("_id.movimiento",false).append("_id.caja", false)));
            pipeline.add(new BasicDBObject("$unwind","$_id"));
            pipeline.add(new BasicDBObject("$sort",new BasicDBObject("_id.fechaInicio",-1)));
            for(BasicDBObject x:pipeline){
                System.out.println(x+",");
            }
            AggregationOutput out = con.get_colletion().aggregate(pipeline);
            for (DBObject dbObject : out.results()) {
                res.add(((HashMap) dbObject).get("_id"));
            }

            con.end();
            return res;
        } catch (MongoException e) {
            aux = new HashMap();
            aux.put("error", e.getMessage());
            aux.put("error_extends", con.errors.get(e.getCode()));
            aux.put("error_code", e.getCode());
            res = new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");
            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e);
            return res;

        } catch (Exception e) {
            aux = new HashMap();
            aux.put("error", "El error es " + e.getMessage());
            aux.put("error_extends", "El error extendido es " + con.errors.get(e.getCause()));
            res = new ArrayList();
            res.add(aux);
            System.out.println("try controlado por warren");

            Logger.getLogger(DUsuario.class.getName()).log(Level.SEVERE, null, e);
            return res;
        }
    }
    //</editor-fold>
    // <editor-fold desc="Crud">
    //</editor-fold>
    // <editor-fold desc="Crud in secccion">    
    //</editor-fold>
    // <editor-fold desc="Consultas">
//</editor-fold>
    // <editor-fold desc="Operaciones">

//</editor-fold>
}
