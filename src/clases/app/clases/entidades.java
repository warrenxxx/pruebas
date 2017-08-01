/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.clases;

import java.util.Map;

/**
 *
 * @author WARREN
 */
public class entidades {

    public static Map<String, String> ent;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            String a = "des"+i;
            String b = "material"+i;
            String c = ""+Math.rint((Math.random()*20)*100)/100;
            String d = ""+Math.round(Math.random()*200000);
            String e = "categoria"+i;
            String f = ""+Math.round(Math.random()*20);
            String g = "nombre"+i;
            String h= "db.articulo.insert({  \"descripcion\" : \"" + a + "\", \"material\"\n"
                    + ": \"" + b + "\", \"costo\" : \"" + c + "\", \"barras\" : \"" + d + "\", \"categoria\" : \"" + e + "\", \"stock\" : \"" + f + "\",\n"
                    + "\"nombre\" : \"" + g + "\" });";
            System.out.println(h);
        }
    }
    // TODO code application logic here

}
