package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.bson.types.ObjectId;
import static app.Main.CONNE;

/**
 *
 * @author WARREN
 */
public class DPersona extends DPrincipal_embebed{
//    private String table = "user";
//    conecion con=CONNE;    
    
    // <editor-fold desc="Contructor">
        
    public DPersona(ObjectId x) {
        this.table="user";
        this.elemento="persona";
        this.id=x;
    }

    //</editor-fold>
    // <editor-fold desc="Crud in secccion">    
    //</editor-fold>
    // <editor-fold desc="Consultas">
//</editor-fold>
    // <editor-fold desc="Operaciones">
//</editor-fold>
}
