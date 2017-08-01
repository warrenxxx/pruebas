/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.FormularioEmergente;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author warren
 */
public class ComboForm extends ComboBox{
    ObservableList ol=FXCollections.observableArrayList();
    ArrayList<String> l;
    public ComboForm(ArrayList<String> l) { 
        this.l=l;
        Callback<ListView<comboPropertys>, ListCell<comboPropertys>> call=
                (ListView<comboPropertys> param)-> new ComboBoxFormCell();
        setCellFactory(call);
        for(String x:l){
            ol.add(new comboPropertys(x));
        }
        setItems(ol);

        setOnMouseClicked(e->{
            for(Object p :ol){
                comboPropertys o=(comboPropertys) p;
            }
        });        
    }
    public ArrayList getlist(){
        ArrayList l=new ArrayList();
        int k=0;
            for(Object p :ol){
                comboPropertys o=(comboPropertys) p;
                String h=o.valorProperty().getValue();
                l.add(h);
                if(h!=null)
                    if(!h.isEmpty())k++;
            }        
        if(k>0)return l;
        return null;
    }
    public void clear(){
            for(Object p :ol){
                comboPropertys o=(comboPropertys) p;
                o.setValor("");
            }
    }
}
