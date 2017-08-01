/*
 * I don't care
 */
package app.articulo;

import static app.Main.CONNE;
import app.clases.CArticulo;
import app.clases.CMedida;
import app.conecion;
import app.herramientas.tabla.table;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Hasan Kara <hasan.kara@fhnw.ch>
 */
public class Main extends Application {

    private table table;
//    final HBox hb = new HBox();

    @Override
    public void start(Stage stage) {
         CONNE=new conecion();

         TArticulo D=new TArticulo(new CArticulo());

        CArticulo x=new CArticulo();

        ObservableList categorias=x.getAllCategoriasObserbable();

        table=new table(D,x.listaObservableTarcitulo());
       
        Scene scene = new Scene(new Group());
        stage.setWidth(550);
        stage.setHeight(550);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        
      
        table.setOnMouseClicked(e->{
           System.out.println(
           table.getSelectionModel().getSelectedItems());
        });
        
        
        table.addEditingCellCrud("Codigo", "codigo",D.codigoProperty());        
        table.addComboBoxEditingCellCrud(categorias, "Categoria", "categoria",true,D.categoriaProperty());
        table.addEditingCellCrud( "Marca", "nombre",D.nombreProperty());
        table.addEditingCellCrud( "Descripcion", "descripcion",D.descripcionProperty());        
        table.addButtonColumnCrud("Medida", "medida",D.medidaProperty(),new Callable() {
            @Override
            public Object call() throws Exception {
                CMedida m=new modal_asignar().display();                
                return m;
            }
        });

        table.addEditingCellDoubleCrud( "Costo", "costo",D.costoProperty());
        table.addEditingCellDoubleCrud( "Precio Dia", "pdia",D.pdiaProperty());
        table.addEditingCellDoubleCrud( "Precio Noche", "pnoche",D.pnocheProperty());
        table.addEditingCellDoubleCrud( "Stock", "stock",D.stockProperty());
        table.addRemoveButtonCrud();
      
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        try {
            vbox.getChildren().addAll(label, table.crud("WARREN").getValue() );
        } catch (IOException ex) {
            System.out.println("try controlado por warren");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex); 
        }

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }










}