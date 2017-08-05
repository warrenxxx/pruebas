/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.usuario;

import app.clases.CPersona;
import app.clases.CUsuario;
import app.herramientas.tabla.function;
import app.herramientas.tabla.table;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.util.Pair;

/**
 *
 * @author warren
 */
public class CrudUsuario {
    private table table;
    private TUsuario D=new TUsuario(new CUsuario());
    private CUsuario x=new CUsuario();
    private Node nodo;
    private Initializable controler;
    public CrudUsuario(){
        table=new table(D,x.listaObservableTUsuario());
        table.addEditingCellCrud("Tipo", "tipo",D.tipoProperty());        
        table.addEditingCellCrud( "User", "user",D.userProperty());
        table.addEditingCellCrud( "Password", "descripcion",D.passwordProperty());        
        table.addEditingCellCrud( "Nivel", "nivel",D.nivelProperty());        
        table.addButtonColumnCrud("Entidad", "entidad",D.entidadProperty(),new function() {
            @Override
            public Object func(Object o) {
               CPersona p=null;
                try {
                    p = new modal_Persona().display();
                } catch (IOException ex) {
                    Logger.getLogger(CrudUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                return p;                
             }
        });

        table.addRemoveButtonCrud();
        try {
            Pair<Initializable,Node> x=table.crud("Usuarios");
            this.nodo=x.getValue();
            this.controler=x.getKey();
        } catch (IOException ex) {
            Logger.getLogger(CrudUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public Node get_node(){
        return this.nodo;
    }
}
