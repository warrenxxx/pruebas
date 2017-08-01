/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.login;

import app.clases.CUsuario;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author WARREN
 */
public class ServiceUser extends Service<CUsuario>{
    String user;
    String pass;
    
    @Override
    protected Task<CUsuario> createTask() {
        TaskUser aux= new TaskUser();
        aux.contraseña=pass;
        aux.usuario=user;
        return aux;
    }
    
}
