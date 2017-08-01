package app;

import app.clases.CArticulo;
import static app.Main.CONNE;
import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author WARREN
 */


public class DArticulo extends DPrincipal{
    conecion con;

    // <editor-fold desc="Contructor">        1
    public DArticulo() {
        this.con=CONNE;
        this.table="ARTICULO";
    }
    //</editor-fold>
    
    // <editor-fold desc="Consultas">    
    
    public ArrayList getAllCategorias(){
        ArrayList res=new ArrayList();
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        res=(ArrayList) con.get_colletion().distinct(new CArticulo().categoria);
        return res;
    }
    public ArrayList getAllOrganizaciones(){
        ArrayList res=new ArrayList();
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        res=(ArrayList) con.get_colletion().distinct(new CArticulo().organizacion);
        return res;
    }
    //</editor-fold>
    
    // <editor-fold desc="Operaciones">    
    
    //</editor-fold>
        
}
