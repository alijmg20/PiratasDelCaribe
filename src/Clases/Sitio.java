
package Clases;

import java.io.Serializable;
import java.util.ArrayList;


public class Sitio implements Serializable {
    private String nombre;
    private Cofre cofre;
    private Calamidad calamidad;
    private ArrayList<Barco>barcos;
    
    
    public Sitio(String nombre, Cofre cofre, Calamidad calamidad){
        this.nombre = nombre;
        this.cofre = cofre;
        
        this.barcos = new ArrayList<Barco>();
    }
    public Sitio(){
        this.barcos = new ArrayList<Barco>();
    }

    public void setCalamidad(Calamidad calamidad) {
        this.calamidad = calamidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Cofre getCofre() {
        return cofre;
    }

    public Calamidad getCalamidad() {
        return calamidad;
    }
    
    public ArrayList<Barco> getBarcos(){
        return barcos;
    }
    
    public void encallaBarco (Barco barco){
        if (barcos!=null && barco!= null){
            this.barcos.add(barco);
            barco.cargarCofre(this.cofre);
        }
        else
            System.out.println("Error en la clase Sitio en la funcion encallaBarco 'barcos' o 'barco' con valor nulos");
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }
    

    
    
}
