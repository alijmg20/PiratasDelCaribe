package Clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Cayo implements Serializable {

    private String nombre;
    private Cofre cofre;
    private Calamidad calamidad;
    private ArrayList<Barco> barcos;

    public Cayo(String nombre, Cofre cofre, Calamidad calamidad) {
        this.nombre = nombre;
        this.cofre = cofre;
        this.calamidad = calamidad;
        this.barcos = new ArrayList<Barco>();
    }

    public Cayo() {
        this.barcos = new ArrayList<Barco>();
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

    public void setCalamidad(Calamidad calamidad) {
        this.calamidad = calamidad;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void encallaBarco(Barco barco) {
        if (barcos != null && barco != null) {
            this.barcos.add(barco);
            barco.cargarCofre(this.cofre);

        } else {
            System.out.println("Error en la clase Cayo: barco o barcos null");
        }

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

}
