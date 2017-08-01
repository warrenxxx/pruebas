/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.herramientas.tabla;

import app.Main;
import app.herramientas.textfilter.TextFieldDouble;
import app.herramientas.textfilter.TextFieldInteger;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author warren
 */
public class table extends TableView {

    private ArrayList<Node> crud_insert;
    private crud crud;
    private ObservableList data=FXCollections.observableArrayList();
    public table() {
        this.setEditable(true);
    }
    public void add_Item(Object a){
        this.data.add(a);
        act();
    }
    public ObservableList getData(){
        return this.data;
    }
    public table(crud crud,ObservableList l) {
        this.setEditable(true);
        this.crud_insert = new ArrayList<>();
        this.crud=crud;
        this.setItems(l);
        this.data.addAll(l);
    }

    public void addButtonColumn( String nombre, String property,Callable fun) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> buttonCellFactory
                = (TableColumn<Object, Object> param) -> new ButtonEditingCell(){
            @Override
            public Object action() {
                Object m=null;
                try {
                    m=fun.call();
                } catch (Exception ex) {
              System.out.println("try controlado por warren");
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex); 
              }
                return m;
            }                    
        } ;
        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(buttonCellFactory);
        getColumns().add(col);        
    }

    public void addButtonColumnCrud( String nombre, String property,  ObjectProperty pro,Callable fun) {        
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> buttonCellFactory
                = (TableColumn<Object, Object> param) -> new ButtonEditingCell(){
            @Override
            public Object action() {
                Object m=null;
                try {
                    m=fun.call();
                } catch (Exception ex) {
            System.out.println("try controlado por warren");
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex); 
                }
                return m;
            }                    
        } ;
        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(buttonCellFactory);
        getColumns().add(col);
        Button b = new Button(nombre);
        b.setOnAction(e -> {
            Object m=null;
                try {
                    m=fun.call();
                } catch (Exception ex) {
             System.out.println("try controlado por warren");
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex); 
               }
            if(m!=null)b.setText(m.toString());
            else b.setText(nombre);
            pro.setValue(m);
        });
        b.prefWidthProperty().bind(col.widthProperty());
        crud_insert.add(b);
    }

    public void addEditingCell(String nombre, String property) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                = (TableColumn<Object, Object> param) -> new EditingCell();

        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(cellFactory);
        getColumns().add(col);

    }

    public void addEditingCellCrud(String nombre, String property,  StringProperty pro) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                = (TableColumn<Object, Object> param) -> new EditingCell();

        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(cellFactory);
        getColumns().add(col);
        TextField t = new TextField();
        t.setPromptText(nombre);
        pro.bindBidirectional(t.textProperty());
        t.prefWidthProperty().bind(col.widthProperty());
        crud_insert.add(t);
    }

    public void addEditingCellDouble(String nombre, String property) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                = (TableColumn<Object, Object> param) -> new EditingCellDouble(false);

        TableColumn<Object, Object> col = new TableColumn(nombre);
                        col.setResizable(false);
        col.setPrefWidth(60);

        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(cellFactory);
        getColumns().add(col);
    }


    public void addEditingCellDoubleCrud(String nombre, String property, DoubleProperty pro) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                = (TableColumn<Object, Object> param) -> new EditingCellDouble(false);

        TableColumn<Object, Object> col = new TableColumn(nombre);
                col.setResizable(false);
        col.setPrefWidth(60);

        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(cellFactory);
        getColumns().add(col);

        TextFieldDouble t = new TextFieldDouble();
        
        t.setPromptText(nombre);
        StringConverter<Number> converter = new NumberStringConverter(){};
        Bindings.bindBidirectional(t.textProperty(), pro, converter);
        t.prefWidthProperty().bind(col.widthProperty());
 
        crud_insert.add(t);
    }

    public void addEditingCellEntero(String nombre, String property) {
            Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                    = (TableColumn<Object, Object> param) -> new EditingCellDouble(true);

        TableColumn<Object, Object> col = new TableColumn(nombre);
                col.setResizable(false);
        col.setPrefWidth(55);

        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(cellFactory);
        getColumns().add(col);
    }
    public void addEditingCellEnteroCrud(String nombre, String property, DoubleProperty pro) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> cellFactory
                = (TableColumn<Object, Object> param) -> new EditingCellDouble(true);

        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setResizable(false);
        col.setPrefWidth(55);

        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(cellFactory);
        getColumns().add(col);

        TextFieldInteger t = new TextFieldInteger();
        t.setPromptText(nombre);
        t.selectAll();
        StringConverter<Number> converter = new NumberStringConverter(){};
        Bindings.bindBidirectional(t.textProperty(), pro, converter);
        t.prefWidthProperty().bind(col.widthProperty());

        crud_insert.add(t);
        
    }

    public void addComboBoxEditingCell(ObservableList l, String nombre, String property,boolean editable) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> comboBoxCellFactory
                = (TableColumn<Object, Object> param) -> new ComboBoxEditingCell(l,editable);

        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(comboBoxCellFactory);
        getColumns().add(col);
    }
    public void addComboBoxEditingCellCrud(ObservableList l, String nombre, String property,boolean editable, ObjectProperty pro) {
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> comboBoxCellFactory
                = (TableColumn<Object, Object> param) -> new ComboBoxEditingCell(l,editable);

        TableColumn<Object, Object> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(comboBoxCellFactory);
        getColumns().add(col);
        ComboBox c = new ComboBox(l);
        c.setEditable(editable);
        c.setPromptText(nombre);
        c.prefWidthProperty().bind(col.widthProperty());
        pro.bindBidirectional(c.valueProperty());
        crud_insert.add(c);
    }

    public void addDateEditingCell(String nombre, String property) {
        Callback<TableColumn<Object, Date>, TableCell<Object, Date>> dateCellFactory
                = (TableColumn<Object, Date> param) -> new DateEditingCell();
        TableColumn<Object, Date> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(dateCellFactory);
        getColumns().add(col);
    }
    public void addDateEditingCellCrud(String nombre, String property, ObjectProperty pro) {
        Callback<TableColumn<Object, Date>, TableCell<Object, Date>> dateCellFactory
                = (TableColumn<Object, Date> param) -> new DateEditingCell();
        TableColumn<Object, Date> col = new TableColumn(nombre);
        col.setCellValueFactory(new PropertyValueFactory(property));
        col.setCellFactory(dateCellFactory);
        getColumns().add(col);
        DatePicker d = new DatePicker();
        d.prefWidthProperty().bind(col.widthProperty());
        pro.bindBidirectional(d.valueProperty());
        crud_insert.add(d);
    }
    public void addNumerableTable() {
        
        Callback<TableColumn<Object, Object>, TableCell<Object, Object>> dateCellFactory
                = (TableColumn<Object, Object> param) -> new NumerableCell();

        TableColumn<Object, Object> col = new TableColumn("#");
        col.setCellFactory(dateCellFactory);
        col.setResizable(false);
        col.setPrefWidth(25);

        getColumns().add(col);
    }

    public void addRemoveButtonCrud() {
        TableColumn<Object, Object> col = new TableColumn("Eliminar");
        col.setResizable(false);
        col.setPrefWidth(68);
    
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col.setCellFactory(param -> new TableCell<Object, Object>() {
            private final Button deleteButton = new Button("Eliminar");

            @Override
            protected void updateItem(Object person, boolean empty) {
                super.updateItem(person, empty);

                if (person == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    crud x = (crud) person;
                    if (x.remove()) {                        
                        data.remove(person);
                        act();
                    }

                });
            }
        });
        getColumns().add(col);
    }
    public void addRemoveButto() {
        TableColumn<Object, Object> col = new TableColumn("Eliminar");
        col.setResizable(false);
        col.setPrefWidth(68);
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col.setCellFactory(param -> new TableCell<Object, Object>() {
            private final Button deleteButton = new Button("Eliminar");

            @Override
            protected void updateItem(Object person, boolean empty) {
                super.updateItem(person, empty);

                if (person == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                        data.remove(person);
                        act();                    
                });
            }
        });
        getColumns().add(col);
    }

    public Pair<Initializable, Node> crud(String name) throws IOException {
        String url = "herramientas/tabla/crud.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(url);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(url));
        Node page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
            CrudController crudcon = (CrudController) loader.getController();
        crudcon.setTitle(name);
        HBox hb = crudcon.getHbox();
        for (Node o : crud_insert) {
            hb.getChildren().add(o);
        }
        Button b = new Button("AGREGAR");
        b.setOnAction(e -> {
            if(crud.insert()){
                data.add(crud.clone());
                act();
                crud.clear();
            }
            else System.out.println("no inserto");
            
        });
        TextField tf = crudcon.filtro();
        tf.textProperty().addListener((l, o, n) -> {
            try {
                busca_agregar(n);
            } catch (InterruptedException ex) {
               System.out.println("try controlado por warren");
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex); 
               System.out.println(ex);
            }
        });
        hb.getChildren().add(b);
        crudcon.getPane().add(this, 0, 4, 3, 1);
        return new Pair(crudcon, page);
    }
    public Node crudOnlyTableAndInsert() {

        GridPane grid=new GridPane();
        HBox hb = new HBox();
        for (Node o : crud_insert) {
            hb.getChildren().add(o);
        }
        Button b = new Button("AGREGAR");
        b.setOnAction(e -> {
            if(crud.insert()){
                System.out.println("si inserto");
                data.add(crud.clone());
                act();
                crud.clear();
            }
            else System.out.println("no inserto");
            
        });
        hb.getChildren().add(b);
        grid.add(hb, 0, 0);
        grid.add(this, 0, 1);
        return grid;
    }
    StringConverter<Double> scd = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                if (object != null)
                    return Double.toString(object);
                else
                    return null;
            }

            @Override
            public Double fromString(String string) {
                Double d = Double.parseDouble(string);               
                return d;
            }
        };
    
    private void busca_agregar(String p) throws InterruptedException{
        this.getItems().clear();
        for(Object x:data){
            crud c=(crud) x;
            if(c.search(p)){
                getItems().add(c);
            }
        }        
    }
    
    public void act(){
        this.getItems().clear();
        this.getItems().addAll(data);
    }
    public void clear_items(){
        this.data.clear();
        this.getItems().clear();
    }
}
    