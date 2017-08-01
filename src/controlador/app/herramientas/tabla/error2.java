/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

import app.clases.C_Principal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author ALIM
 */
public class error2 {
        Stage wind = new Stage();
    public error2(C_Principal p) {


        wind.initModality(Modality.WINDOW_MODAL);
        wind.initStyle(StageStyle.UNDECORATED);
        wind.setTitle("Error");

        Label b = new Label(p.getError_Extend());
        b.setId("b");
        Label c = new Label(p.getError());
            c.setId("c");
        b.setWrapText(true);
        c.setWrapText(true);

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(10), (ActionEvent event) -> {
            finalise();
        }));
        idlestage.setCycleCount(1);
        idlestage.play();

        Button d = new Button("Aceptar");
        d.setDefaultButton(true);
        d.setOnAction(e -> {
            wind.close();
        });
        VBox x = new VBox(b, c, d);
        x.setPrefWidth(200);
        Scene SC = new Scene(x);
        SC.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        wind.setScene(SC);
        wind.show();

    }
    public void finalise(){
        this.wind.hide();
        this.wind.close();
    }
}
