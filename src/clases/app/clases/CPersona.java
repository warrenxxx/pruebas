package app.clases;


import app.DPersona;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CPersona extends C_Principal{
    
    // <editor-fold desc="DATOS DE LA BD">    
    private final String tipo_documento="tipo_documento";
    private final String nro_documento="nro_documento";
    private final String nombre="nombre";
    private final String apellidos="apellidos";
    private final String fecha_nacimiento="fecha_nacimiento";
    private final String sexo="sexo";
    private final String email="email";
    private final String direccion="direccion";
    
// </editor-fold>     
    
    // <editor-fold desc="Constructor">    
    
    public CPersona(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public CPersona(HashMap map){       
        this.datos=map;
        n=this.datos.size();
    }       
    //</editor-fold>
    
    // <editor-fold desc="Metodos Set">    
   
    public void setTipoDocumento(int x){        
        ArrayList tipos=new ArrayList();
        tipos.add("DNI");
        tipos.add("RUC");
        tipos.add("VISA");
        tipos.add("OTROS");
        this.datos.put(tipo_documento, tipos.get(x));
    }
    public void setnroDocumento(String x){
        this.datos.put(nro_documento, x);
    }
    public void setNombre(String x){
        this.datos.put(nombre, x);
    }
    public void setApellidos(String x){
        this.datos.put(apellidos, x);
    }
    public void setFecha_nacimiento(Date x){
        this.datos.put(fecha_nacimiento, x);        
    }
    public void setSexo(int x){
        if(x==0)
            this.datos.put(sexo, "Hombre");
        else this.datos.put(sexo, "mujer");
    }
    public void setDireccion(String x){
        this.datos.put(direccion, x);
    }
    public void setEmail(String x){
        this.datos.put(email, x);
    }    
    //</editor-fold>

    // <editor-fold desc="Metodos Get">    

    public String getnroDocumento(){
        Object aux=this.datos.get(nro_documento);
        return aux==null?"":aux.toString();
    }    
    public String getTipoDocumento(){        
        Object aux=this.datos.get(tipo_documento);
        return aux==null?"":aux.toString();
    }    
    public String getNombre(){
        Object aux=this.datos.get(nombre);
        return aux==null?"":aux.toString();
    }    
    public String getApellidos(){
        Object aux=this.datos.get(apellidos);
        return aux==null?"":aux.toString();
    }
    public Date getFecha_nacimiento(){
        Object aux=this.datos.get(tipo_documento);
        return aux==null?new Date():(Date)aux;
    }
    public String getSexo(){
        Object aux=this.datos.get(sexo);
        return aux==null?"":aux.toString();
    }
    public String getEmail(){
        Object aux=this.datos.get(email);
        return aux==null?"":aux.toString();
    }
    public String getDireccion(){
        Object aux=this.datos.get(direccion);
        return aux==null?"":aux.toString();
    }    
//    </editor-fold>

    // <editor-fold desc="Overrride">    
    
    @Override
    public String toString() {
        return getNombre()+" "+getApellidos();
    }    
    //</editor-fold>
    
    // <editor-fold desc="Metodos Auxiliares">    
    public void save(ObjectId usuario){
       this.set_datos( new DPersona(usuario).insert(datos));
    }
    public boolean delete(ObjectId usuario){
       return new DPersona(usuario).delete();
    }
    public void find(ObjectId usuario){
        this.set_datos( new DPersona(usuario).buscar());       
    }
    //</editor-fold>
}
