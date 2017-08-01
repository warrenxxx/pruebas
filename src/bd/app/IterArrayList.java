/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;

/**
 *
 * @author warren
 */
public class IterArrayList extends ArrayList<IterArray>{

    public IterArrayList() {
    }
    public String generar(){
        String h="";
        for(IterArray x: this){
            h+=x.getNombre()+"."+x.getN()+".";
        }
        return h;
    }
}
