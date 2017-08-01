/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.reportes;

import app.clases.CMovimiento;
import app.clases.CPersona;
import app.clases.CSession;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author warren
 */
public class SeccionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ListView lista;
    @FXML
    Tab tv, tvd;

    CSession session;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setSession(CSession x) {
        this.session = x;
        lista.setItems(x.listarBasic());
        
        if(lista.getItems().size()>0){
            CSession aux=(CSession) lista.getItems().get(0);
            if(aux!=null){
                initv(aux);
                initvd(aux);            
            }
        }
        lista.setOnMouseClicked(e -> {
            Object k = lista.getSelectionModel().getSelectedItem();
            if (k != null) {
                CSession cs = (CSession) k;
                initv(cs);
                initvd(cs);
            }
        });
    }

    
    public void initv(CSession cs) {
        CMovimiento m = new CMovimiento(cs);
        m.setHora(cs.getFechaInicio());
        m.setModoPago("visa y efectivo");
        m.setPersona_atendida(new CPersona());
        modelo_venta v1 = new modelo_venta(m);
        v1.ini_hora();
        v1.ini_persona_atendida();
        v1.ini_tipo_pago();
        v1.ini_ventas_detalle_total(m.getTVentaDetalleList());
        tv.setContent(v1);
    }

    public void initvd(CSession cs) {
        CMovimiento m2 = new CMovimiento(cs);
        ArrayList<CMovimiento> l = m2.listarVentasConArticulo();
        VBox v = new VBox();
        ScrollPane scroll = new ScrollPane(v);
        v.prefWidthProperty().bind(scroll.widthProperty());
        for (CMovimiento mov : l) {
            modelo_venta b = new modelo_venta(mov);
            b.ini_hora();
            b.ini_persona_atendida();
            b.ini_tipo_pago();
            b.ini_ventas_detalle_total(mov.getMovimientosDetalleObserbable());
            v.getChildren().add(b);
        }
        tvd.setContent(scroll);
    }
}
