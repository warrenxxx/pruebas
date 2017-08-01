/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;


import static app.Main.CONNE;
import javafx.scene.Node;

/**
 *
 * @author WARREN
 */
public class HConeccion extends Thread{
    
    private Node y;
    
    public HConeccion(Node y) {
        this.y=y;
    }
    public void set_node(Node d){
        this.y=d;
    }
    private boolean seguir=true;
    public void cancel(){
        seguir=false;
      }
    @Override
    public void run() {
        while(seguir=true){
            if(y!=null){
                try {
                    Thread.sleep(1000);
                    y.setVisible(!CONNE.is_run_conection());
                } catch (InterruptedException ex) {
           //         System.out.println(ex.toString()+"hilo hconeccion");                    
                }
            }//else System.out.println("nll");
        }
    }
    
}
