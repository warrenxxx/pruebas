/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.usuario;

import app.clases.CPersona;
import app.clases.CUsuario;
import app.herramientas.tabla.crud;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author warren
 */
public class TUsuario implements crud{

    private final SimpleObjectProperty<CPersona> entidad;    
    private final SimpleStringProperty tipo;
    private final SimpleStringProperty user;
    private final SimpleStringProperty nivel;
    private final SimpleStringProperty password;

    private CUsuario a;

    public TUsuario(CUsuario x) {
        this.a = x;
        this.entidad = new SimpleObjectProperty(a.getEntidad());
        this.tipo = new SimpleStringProperty(a.getTipo());
        this.user = new SimpleStringProperty(a.getUser());
        this.nivel = new SimpleStringProperty(a.getNivel());
        this.password = new SimpleStringProperty(a.getPassword());       
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public ObjectProperty entidadProperty() {
        return entidad;
    }

    public StringProperty userProperty() {
        return user;
    }

    public StringProperty nivelProperty() {
        return nivel;
    }
    public StringProperty passwordProperty() {
        return password;
    }


    public CUsuario getUsuario() {
        return a;
    }

    public CUsuario toElement() {
        CUsuario b = new CUsuario();
        if(a.getId()!=null)
            b.setId(a.getId());
        b.setEntidad((CPersona) entidad.getValue());
        b.setTipo(tipo.getValue());
        b.setNivel(nivel.getValue());
        b.setUser(user.getValue());
        b.setPassword(password.getValue());
        return b;
    }

    public void toProperty(CUsuario x) {
        tipo.setValue(x.getTipo());
        entidad.setValue((CPersona)x.getEntidad());
        user.setValue(x.getUser());
        nivel.setValue(x.getNivel());
        password.setValue(x.getPassword());
    }
    @Override
    public boolean update(){
        CUsuario x=toElement();        
        x.update();
        if(x.getError()!=null){
            toProperty(a);
            System.out.println(x.getError());
            return false;
        }
        return true;
    }
    @Override
    public boolean remove(){
        if(!a.delete()){
            toProperty(a);
            return false;
        }
        return true;
    }

    @Override
    public boolean insert() {
        CUsuario x=toElement();        
        x.insert();
        if(x.getError()!=null){
            toProperty(a);
            System.out.println(x.getError());
            return false;
        }
        this.a=x;
        return true;
    }

    @Override
    public String toString() {
        return "TUsuario{" + "entidad=" + entidad + ", tipo=" + tipo + ", user=" + user + ", nivel=" + nivel + ", password=" + password + ", a=" + a + '}';
    }


    @Override
    public Object clone(){
        CUsuario x=toElement();
        TUsuario y=new TUsuario(x);
        return y;
    }
    @Override
    public void clear(){
        tipo.setValue("");
        entidad.setValue(null);
        user.setValue("");
        nivel.setValue("");
        password.setValue("");
        a=new CUsuario();
    }
    @Override
    public boolean search(String s){
        if(s==null||s.isEmpty())return true;
        String sm=s.toUpperCase();
        String h=tipo.getValue()+entidad.getValue()+user.getValue()+nivel.getValue();
        if(h.toUpperCase().indexOf(sm)!=-1)return true;        
        return false;
    }
}
