/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.caja;

import app.clases.CMoneda;
import app.herramientas.textfilter.TextFieldDouble;
import app.herramientas.textfilter.TextFieldInteger;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ALIM
 */
public class modal_moneda {

    CMoneda res=null;

    public CMoneda display() {
        Dialog<CMoneda> dialog = new Dialog<>();        
        dialog.setTitle("Cantidad de dinero");
        dialog.setHeaderText("Designe el Dinero y la cantidad");

        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        GridPane monedasefectivo=new GridPane();
        monedasefectivo.add(new Label("10 centimos"), 0, 0);
        monedasefectivo.add(new Label("20 centimos"), 0, 1);
        monedasefectivo.add(new Label("50 centimos"), 0, 2);
        monedasefectivo.add(new Label("1 sol"), 0, 3);
        monedasefectivo.add(new Label("2 soles"), 0, 4);
        monedasefectivo.add(new Label("5 soles"), 0, 5);
        monedasefectivo.add(new Label("10 soles"), 0, 6);
        monedasefectivo.add(new Label("20 soles"), 0, 7);
        monedasefectivo.add(new Label("50 soles"), 0, 8);
        monedasefectivo.add(new Label("100 soles"), 0, 9);
        monedasefectivo.add(new Label("200 soles"), 0, 10);
        
        TextFieldInteger t0=new TextFieldInteger();
        TextFieldInteger t1=new TextFieldInteger();
        TextFieldInteger t2=new TextFieldInteger();
        TextFieldInteger t3=new TextFieldInteger();
        TextFieldInteger t4=new TextFieldInteger();
        TextFieldInteger t5=new TextFieldInteger();
        TextFieldInteger t6=new TextFieldInteger();
        TextFieldInteger t7=new TextFieldInteger();
        TextFieldInteger t8=new TextFieldInteger();
        TextFieldInteger t9=new TextFieldInteger();
        TextFieldInteger t10=new TextFieldInteger();

       
        monedasefectivo.add(t0, 1, 0);
        monedasefectivo.add(t1, 1, 1);
        monedasefectivo.add(t2, 1, 2);
        monedasefectivo.add(t3, 1, 3);
        monedasefectivo.add(t4, 1, 4);
        monedasefectivo.add(t5, 1, 5);
        monedasefectivo.add(t6, 1, 6);
        monedasefectivo.add(t7, 1, 7);
        monedasefectivo.add(t8, 1, 8);
        monedasefectivo.add(t9, 1, 9);
        monedasefectivo.add(t10, 1, 10);

        TextFieldDouble monedasdigital=new TextFieldDouble();
        
        TextField nombre=new TextField("Sol");
        nombre.setPromptText("Nombre de La Moneda");
        
        GridPane cantidad=new GridPane();
        cantidad.add(monedasefectivo, 0, 0);
        
        ComboBox tipo=new ComboBox(FXCollections.observableArrayList("Efectivo","Digital"));
        tipo.getSelectionModel().select(0);
        tipo.setOnAction(e->{
            if(tipo.getSelectionModel().getSelectedIndex()==0){
                cantidad.getChildren().clear();
                cantidad.add(monedasefectivo, 0, 0);
                dialog.setHeight(500);
            }
            if(tipo.getSelectionModel().getSelectedIndex()==1){
                cantidad.getChildren().clear();
                cantidad.add(monedasdigital, 0, 0);
                dialog.setHeight(350);
            }
        });


        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nombre, 1, 0);
        grid.add(new Label("Tipo:"), 0, 1);
        grid.add(tipo, 1, 1);
        grid.add(new Label("Cantidad:"), 0, 2);
        grid.add(cantidad, 1, 2);


        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(false);

// Do some validation (using the Java 8 lambda syntax).

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> nombre.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                if(tipo.getSelectionModel().getSelectedIndex()==0){                    
                    res=new CMoneda(nombre.getText(),0);
                    res.setCantidad(0, t0.getInteger());
                    res.setCantidad(1, t1.getInteger());
                    res.setCantidad(2, t2.getInteger());
                    res.setCantidad(3, t3.getInteger());
                    res.setCantidad(4, t4.getInteger());
                    res.setCantidad(5, t5.getInteger());
                    res.setCantidad(6, t6.getInteger());
                    res.setCantidad(7, t7.getInteger());
                    res.setCantidad(8, t8.getInteger());
                    res.setCantidad(9, t9.getInteger());
                    res.setCantidad(10, t10.getInteger());
                }
                if(tipo.getSelectionModel().getSelectedIndex()==1){
                    res=new CMoneda(nombre.getText(), 1,monedasdigital.getDouble() );
                }
                return res;
            }
            return null;
        });

        Optional<CMoneda> result = dialog.showAndWait();

        result.ifPresent(moneda -> {
            res=moneda;            
        });

        return res;
    }
    
}
