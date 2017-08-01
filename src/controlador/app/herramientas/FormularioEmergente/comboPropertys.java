/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.FormularioEmergente;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */
public class comboPropertys {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty valor;

    public comboPropertys(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
        this.valor = new SimpleStringProperty("");
    }
    public StringProperty nombreProperty(){
        return nombre;
    }
    public StringProperty valorProperty(){
        return valor;
    }
    public void setValor(String x){
        this.valor.set(x);
    }
    @Override
    public String toString() {
        return nombre.getValue();
    }
    
}
