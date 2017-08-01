package app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class gg {
    public static void mos(ArrayList x){
        for(int i=0;i<x.size();i++){
        }
    }
    
    public static void main(String[] args) {        
/*        CUsuario user=new CUsuario();        

        CPersona per=new CPersona();
        per.setApellidos("apellido");
        per.setDireccion("direccion");
        per.setEmail("email.com");
        per.setFecha_nacimiento(new Date());
        per.setNombre("nombre");
        per.setSexo(0);
        per.setTipoDocumento(0);
        per.setnroDocumento("1241225");
  
        user.setEntidad(per);
        user.insert();
        if(user.tiene_error())System.out.println(user.getError());
        else System.out.println(user);
        
        CUsuario aux=new CUsuario();
        aux.setId(new ObjectId("5919ddc8b10e9e1ec8da4649"));
        aux.setNivel("nivelado");
        if(aux.delete())System.out.println("si");
*/
/*
        CMedida medida=new CMedida();
        medida.setCantidad(45.0);
        medida.setNombre("paquete");

        CArticulo art=new CArticulo();
        art.setCategoria("cat1");
        art.setCodigo("dddd");
        art.setCosto(66);
        art.setMedida(medida);
        art.setNombre("papel");
        art.setOrganizacion("org");
        art.setPrecio_dia(45.0);
        art.setPrecio_noche(52.3);
        art.setStock(4);
        art.insert();
        if(art.tiene_error())System.out.println(art.getError());
        else System.out.println(art);
 */
        System.out.println();
            Long k=new Date().getTime();
        
        SimpleDateFormat formateador = new SimpleDateFormat("EEEE  dd 'de' MMMM 'de' yyyy", new Locale("es","PE"));
        System.out.println(formateador.format(new Date(1L)));
    }    
}
