/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.home;

import app.clases.CPersona;
import app.clases.CUsuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author warren
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ImageView avatar;
    @FXML
    Label dni, nombre, usuario, cargo;
    @FXML
    AreaChart pvt;
    @FXML
    PieChart cv;
    @FXML
    LineChart pvi;
    ObservableList ob;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUser(CUsuario x) {
        CPersona p = (CPersona) x.getEntidad();
        dni.setText(p.getnroDocumento());
        nombre.setText(p.getNombre());
        usuario.setText(x.getUser());
        cargo.setText(x.getTipo());
        ob = FXCollections.observableArrayList();
        
        ObservableList<Pair> L=new CUsuario().getSumVentasAll();
        for(Pair<CUsuario,Object> m:L  ){
            ob.add(new PieChart.Data(m.getKey().getEntidad().toString(),-1*(double) m.getValue()));
        }
            
        cv.setData(ob);

    }

}
