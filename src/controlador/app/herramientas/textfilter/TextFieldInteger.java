/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.textfilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;

/**
 *
 * @author warren
 */
public class TextFieldInteger extends TextField {

    private Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*");

    public TextFieldInteger() {
        this.textProperty().addListener((l, o, n) -> {
            if (n.compareTo("") != 0) {
                Matcher mat = pat.matcher(n);
                if (!mat.matches()) {
                    setText(o);
                }
            }
        });
    }

    public int getInteger() {
        if (getText().compareTo("") == 0) 
            return 0;        
        Matcher mat = pat.matcher(getText());
        if (!mat.matches()) 
            return -1;
        else 
            return Integer.parseInt(getText());           
    }
}
