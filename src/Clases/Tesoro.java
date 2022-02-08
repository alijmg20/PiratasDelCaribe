
package Clases;

import java.io.Serializable;


public class Tesoro implements Serializable{
    private String nombre;
    private Integer peso;

    public Tesoro(String nombre) {
        this.nombre = nombre;
        determinarPeso();
    }

    public String getNombre() {
        return nombre;
    }
    
    public Integer getPeso() {
        return peso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void determinarPeso(){
        String[] tesoros = {"Mapa","Barra de Oro","Barra de Plata","Bolsa de Perlas","Bolsa de Monedas de Oro","Cofre de Joyas","Cofre de Piedras Preciosas","Corazon de la Princesa"}; 
        for(int i=0; i<tesoros.length;i++){
            if(nombre.equalsIgnoreCase(tesoros[i])){
                switch(i){
                    case 0: peso = 5;
                            break;
                    case 1: peso = 25;
                            break;
                    case 2: peso = 50;
                            break;
                    case 3: peso = 10;
                            break;
                    case 4: peso = 15;
                            break;
                    case 5: peso = 15;
                            break;
                    case 6: peso = 10;
                            break;
                    default: peso = 5;
                            break;
                }
                return;
            }
        }
    }
    
}
