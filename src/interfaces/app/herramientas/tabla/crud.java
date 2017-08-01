/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

/**
 *
 * @author warren
 */
public interface crud {
   public boolean insert();
   public boolean remove();
   public boolean update();
   public boolean search(String s);
   public Object clone();
   public void clear();
   
}
