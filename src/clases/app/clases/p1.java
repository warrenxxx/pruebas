/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.clases;

import static app.Main.CONNE;
import app.conecion;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import org.bson.types.ObjectId;

/**
 *
 * @author warren
 */
public class p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        CONNE=new conecion();
        
        CPersona p=new CPersona();
        p.setApellidos("aroni");
        p.setDireccion("apv virgen ");
        p.setEmail("soledad.wa");
        p.setFecha_nacimiento(new Date());
        p.setNombre("warren");
        p.setSexo(0);
        p.setTipoDocumento(0);
        p.setnroDocumento("47724455");
        
        CUsuario user=new CUsuario();
        user.setId(new ObjectId("596fb6e6074df613bc5d71ff"));
        user.setEntidad(p);
        user.setNivel("0");
        user.setPassword("a");
        user.setTipo("0");
        user.setUser("a");
        user.insert();

     }
}