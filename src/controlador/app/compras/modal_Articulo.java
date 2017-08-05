/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.compras;


import app.clases.CArticulo;
import app.clases.CMedida;
import app.herramientas.textfilter.TextFieldDouble;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ALIM
 */
public class modal_Articulo {
    CArticulo x;    
    CMedida med;
    public CArticulo display(CArticulo x)  {
        /*
        this.x=x;
        this.med=x.getMedida();
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("Elegir Productos Asignados");
        
        
        GridPane grid=new GridPane();
        grid.add(new Label("CODIGO"), 0, 0);
        grid.add(new Label("CATEGORIA"), 0, 1);
        grid.add(new Label("MARCA"), 0, 2);
        grid.add(new Label("DESCRIPCION"), 0, 3);
        grid.add(new Label("MEDIDA"), 0, 4);
        grid.add(new Label("COSTO"), 0, 5);
        grid.add(new Label("PRECIO DIA"), 0, 6);
        grid.add(new Label("PRECIO NOCHE"), 0, 7);
        grid.add(new Label("STOCK"), 0, 8);
         
        TextField a1=new TextField(med.getCodigo());
        ComboBox a2=new ComboBox(new CArticulo().getAllCategoriasObserbable());
            a2.setEditable(true);
            a2.getSelectionModel().select(x.getCategoria());
        TextField a3=new TextField(x.getNombre());
        TextField a4=new TextField(x.getDescripcion());
        Button a5=new Button(x.getMedida().toString());
            a5.setOnAction(e->{
                Object o=new modal_asignar().display();
                if(o!=null){
                    this.x.setMedida((CMedida) o);
                    a5.setText(o.toString());
                }
            });
        TextFieldDouble a6=new TextFieldDouble(x.getCosto());   
        TextFieldDouble a7=new TextFieldDouble(x.getPrecio_dia());
        TextFieldDouble a8=new TextFieldDouble(x.getPrecio_noche());
        TextFieldDouble a9=new TextFieldDouble(x.getStock());
        grid.add(a1, 1, 0);
        grid.add(a2, 1, 1);
        grid.add(a3, 1, 2);
        grid.add(a4, 1, 3);
        grid.add(a5, 1, 4);
        grid.add(a6, 1, 5);
        grid.add(a7, 1, 6);
        grid.add(a8, 1, 7);
        grid.add(a9, 1, 8);
        Button ac=new Button("ACEPTAR");
        ac.setOnAction(e->{
            this.x.setCodigo(a1.getText());
            this.x.setCategoria(a2.getSelectionModel().getSelectedItem().toString());
            this.x.setNombre(a3.getText());
            this.x.setDescricion(a4.getText());
            this.x.setCosto(a6.getDouble());
            this.x.setPrecio_dia(a7.getDouble());
            this.x.setPrecio_noche(a8.getDouble());
            this.x.setStock(a9.getDouble());
            this.x.update();            
            wind.close();
        });
        Button cs=new Button("CANCELAR");
        cs.setOnAction(e->{
            this.x=null;
            wind.close();
        });
        grid.add(ac, 0, 9);
        grid.add(cs, 1, 9);

        Scene SC = new Scene((Parent) grid);
        wind.setScene(SC);
        wind.showAndWait();
        return this.x;*/
        return null;
    }

}
