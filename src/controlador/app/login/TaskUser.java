/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.login;

import app.clases.CUsuario;
import javafx.concurrent.Task;

/**
 *
 * @author WARREN
 */
public class TaskUser extends Task<CUsuario>{
    public String usuario;
    public String contraseña;
    
    @Override
    protected CUsuario call() throws Exception {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(5);
        }
        
        CUsuario d=new CUsuario();

        d.validar(usuario, contraseña);
        return d;
    }
    
}
