package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.clases.CSession;
import app.clases.CUsuario;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DCaja extends DPrincipal_embebed_array{
//    private String table = "user";
//    conecion con=CONNE;    
    private final CUsuario   user=new CUsuario();
    //private CSession session=new CSession();
    // <editor-fold desc="Contructor">
    public DCaja(CSession seccion) {
        CUsuario x=new CUsuario();
        this.table=x.getNombreCollections();
        this.nombre=x.caja;
        this.id=seccion.padre.getId();
        this.iter=new IterArrayList();        
        if(seccion.getPosicion()==-1){
            seccion.set_datos(new DSession(seccion.padre.getId()).actId(seccion.get_datos()));
        }
        this.iter.add(new IterArray(user.seccion, seccion.getPosicion()));        
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
