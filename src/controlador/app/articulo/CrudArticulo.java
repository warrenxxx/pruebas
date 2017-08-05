/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.articulo;

import app.clases.CArticulo;
import app.clases.CMedida;
import app.herramientas.tabla.function;
import app.herramientas.tabla.table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author warren
 */
public class CrudArticulo {

    private TArticulo D=new TArticulo(new CArticulo());
    private CArticulo x=new CArticulo();
    private ObservableList categorias=x.getAllCategoriasObserbable();
    private ObservableList organizaciones=x.getAllOrganizacionObserbable();
    private Node nodo;
    private Initializable controler;
    public table table;
    public CrudArticulo(){
        table=new table(D,x.listaObservableTarcitulo());
        table.addComboBoxEditingCellCrud(categorias, "Categoria", "categoria",true,D.categoriaProperty());
        table.addEditingCellCrud( "Marca", "nombre",D.nombreProperty());
        table.addEditingCellCrud( "Descripcion", "descripcion",D.descripcionProperty());        
        table.addButtonColumnCrud("Medida", "medida",D.medidaProperty(),new function() {
            @Override
            public Object func(Object o) {
                TArticulo t=(TArticulo) o;                
                ArrayList<CMedida> m=new modal_medidas().display(t.getA().getMedidas());                
                for(CMedida OO:m){
                    System.out.println("r "+OO);
                }
                return m;               
            }
        });

        table.addRemoveButtonCrud();
        try {
            Pair<Initializable,Node> x=table.crud("Artculo");
            this.nodo=x.getValue();
            this.controler=x.getKey();
        } catch (IOException ex) {
            Logger.getLogger(CrudArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public Node get_node(){
        return this.nodo;
    }
    


}
