/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.textfilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author warren
 */
public class TextFieldDouble extends TextField {

    private Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*([.][0-9]*)?");

    public TextFieldDouble() {
        textProperty().addListener((l, o, n) -> {
            if (n.compareTo("") != 0) {
                Matcher mat = pat.matcher(n);
                if (!mat.matches()) {
                    setText(o);
                }
            }
        });
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (isFocused() && !getText().isEmpty()) 
                    selectAll();                
            }
        });
    }
    public TextFieldDouble(double a) {
        this.setText(String.valueOf(a));
        textProperty().addListener((l, o, n) -> {
            if (n.compareTo("") != 0) {
                Matcher mat = pat.matcher(n);
                if (!mat.matches()) {
                    setText(o);
                }
            }
        });
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (isFocused() && !getText().isEmpty()) 
                    selectAll();                
            }
        });
    }
    
    public double getDouble() {
        if (getText().compareTo("") == 0) {
            return 0;
        }

        Matcher mat = pat.matcher(getText());
        if (!mat.matches()) {
            return -1;
        } else {
            return Double.parseDouble(getText());
        }
    }
}
