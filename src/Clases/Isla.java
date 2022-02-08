
package Clases;

import java.io.Serializable;
import java.util.ArrayList;


public class Isla implements Serializable{
    private String nombre;
   
    private ArrayList<Sitio> sitios;
    
    public Isla(String nombre){
        this.nombre = nombre;
        this.sitios = new ArrayList<Sitio>();
    }
    public Isla(){
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Sitio> getSitios() {
        return sitios;
    }
    
    public void addSitio(Sitio sitio){
        if (sitios!=null && sitio!=null)
            this.sitios.add(sitio);
        else
            System.out.println("Error en la clase Isla en la funcion addSitio islas o isla tienen valores nulos");
    }
    
    public void setSitios(ArrayList<Sitio> sitios){
        if (sitios!=null){
            this.sitios = sitios;
        }
        else
            System.out.println("Error en la clase Isla en la funcion setSitios 'sitios' es null");
    }
    
    
    
    
}
