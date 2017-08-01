/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.clases;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.plaf.basic.BasicListUI;

/**
 *
 * @author warren
 */
public class CMoneda extends C_Principal{
    private final String nombre="nombre";
    private final String tipo="tipo";
    private final String cantidad="cantidad";

    private int monedas[];
    
    public CMoneda(String nombre, int tipo, double cantidad) {
        this.datos.put(this.nombre, nombre);
        this.datos.put(this.tipo, tipo==0?"efectivo":"digital");
        this.datos.put(this.cantidad, cantidad);
    }
    public CMoneda(HashMap p){
        this.datos=p;
    }
    public CMoneda(String nombre, int tipo) {
        monedas=new int[11];
        this.datos.put(this.nombre, nombre);
        this.datos.put(this.tipo, tipo==0?"efectivo":"digital");
        this.datos.put(this.cantidad,monedas);
    }    
    
    public void setCantidad(double a){
        this.datos.put(cantidad, a);
    }
    public void setCantidad(int a,int b){
        ((int[])this.datos.get(cantidad))[a]=b;
    }
    public void setTipo(int a){
        if(a==0)
            this.datos.put(this.tipo, "efectivo");
        if(a==1)
            this.datos.put(this.tipo, "digital");
    }
    
    public void setNombew(String nombre){
        this.datos.put(this.nombre, nombre);
    }
    public String getNombre(){
        return (String) this.datos.get(nombre);
    }
    public String getTipo(){
        return (String) this.datos.get(tipo);    
    }
    public double getCantidad(){
        if(getTipo().compareTo("efectivo")==0){
            BasicDBList g;
            int k[];
            Object []o;
            try{
                o=((BasicDBList) this.datos.get(cantidad)).toArray();
                k=new int[o.length];
                for(int i=0;i<o.length;i++)
                   k[i]=(int)o[i];
            }catch(ClassCastException e){
                k=(int[]) this.datos.get(cantidad);
            }


            double []p={0.1,0.2,0.5,1,2,5,10,20,50,100,200};
            double cont=0;
            for(int i=0;i<p.length;i++){
                cont+=k[i]*p[i];
            }
            return cont;
        }else{
            return (double)this.datos.get(cantidad);
        }
    }
    @Override
    public String toString() {
//        return "";
        return getCantidad()+" "+getNombre();
    }    
}
