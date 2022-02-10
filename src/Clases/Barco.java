
package Clases;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import piratascaribe.GestorRMI;
import Interfaces.InterfazBarco;
import Interfaces.InterfazMaquina;


public class Barco extends UnicastRemoteObject implements InterfazBarco {

    private String nombre;
    private Boolean pirata;
    private Integer tripulacion;
    private Integer raciones;
    private Integer municiones;
    private Integer tripulacionOriginal;
    private Integer racionesOriginal;
    private Integer municionesOriginal;
    private String maquinaOrigen;
    public boolean enRetirada;
    private String maquinaAnterior;
    private String maquinaActual;
    private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";

    private Cofre cofre;
    private String puertoOrigen;
    private Mapa mapaOrigen;

    public Barco() throws RemoteException {

    }

    public Barco(String nombre, Boolean pirata, Integer nTripulacionOriginal, Integer nRacionesOriginal, Integer nAmmoOriginal) throws RemoteException {
        this.nombre = nombre;
        this.pirata = pirata;
        this.tripulacionOriginal = nTripulacionOriginal;
        this.racionesOriginal = nRacionesOriginal;
        this.municionesOriginal = nAmmoOriginal;
        this.municiones = nAmmoOriginal;
        this.tripulacion = nTripulacionOriginal;
        this.raciones = nRacionesOriginal;
        this.enRetirada = false;
        if (nombre.equalsIgnoreCase("La_Venganza_Errante")) {
            this.cofre = new Cofre(100);
        } else {
            this.cofre = new Cofre(50);
        }

    }

    @Override
    public Mapa getMapaOrigen() {
        return mapaOrigen;
    }

    public void setMapaOrigen(Mapa mapaOrigen) {
        this.mapaOrigen = mapaOrigen;
    }

    public void setMaquinaOrigen(String maquinaOrigen) {
        this.maquinaOrigen = maquinaOrigen;
    }

    public void setPuertoOrigen(String puertoOrigen) {
        this.puertoOrigen = puertoOrigen;
    }

    public void imprimirContenido() {
        System.out.println("--------------CONTENIDO BARCO--------------");
        System.out.println("Numero Tripulacion: " + this.tripulacion);
        System.out.println("Numero Raciones: " + this.raciones);
        System.out.println("Numero municiones: " + this.municiones);
        System.out.println("Retirada: " + this.enRetirada);
        System.out.println("--------------***************--------------");
    }

    public void imprimirOriginales() {
        System.out.println("--------------ORIGINALES BARCO--------------");
        System.out.println("Numero Tripulacion Original: " + this.tripulacionOriginal);
        System.out.println("Numero Raciones Original: " + this.racionesOriginal);
        System.out.println("Numero municiones Original: " + this.municionesOriginal);
        System.out.println("--------------***************--------------");
    }

    @Override
    public void imprimirCofre() throws RemoteException {

        //codigo del metodo
        if (cofre != null) {
            if (cofre.getTesoros() != null) {
                if (cofre.getTesoros().isEmpty()) {
                    System.out.println("El cofre no tiene tesoros...");
                } else {
                    System.out.println("----------TESOROS----------");
                    System.out.println("Nombre \t\t Peso");
                    for (int i = 0; i < cofre.getTesoros().size(); i++) {
                        System.out.println(cofre.getTesoros().get(i).getNombre() + " -> " + cofre.getTesoros().get(i).getPeso());
                    }
                    System.out.println();
                }
            }
            if (cofre.getMapas() != null) {
                if (cofre.getMapas().isEmpty()) {
                    System.out.println("El cofre no tiene mapas...");
                } else {
                    System.out.println("-----------MAPAS-----------");
                    System.out.println("Destino \t\t Estado");
                    for (int i = 0; i < cofre.getMapas().size(); i++) {
                        Mapa mapa = cofre.getMapas().get(i);
                        String dest;
                        if (mapa.esIsla()) {
                            dest = mapa.getNombreIsla();
                            dest = dest + "/" + mapa.getNombreSitio();
                        } else {
                            dest = mapa.getNombreCayo();
                        }

                        System.out.println("Destino :" + dest + "->" + mapa.getEstado());
                    }
                }
            }
        } else {
            System.out.println("Error en Barco: imprimirCofre el cofre posee valor nulo.");
        }

    }

    @Override
    public String getName() {
        return nombre;
    }

    @Override
    public Integer getTripulacion() throws RemoteException {
        return tripulacion;
    }

    @Override
    public Integer getRaciones() throws RemoteException {
        return raciones;
    }

    @Override
    public void setRaciones(Integer raciones) throws RemoteException {
        this.raciones = raciones;
    }

    @Override
    public void setTripulacion(Integer Tripulacion) throws RemoteException {
        this.tripulacion = Tripulacion;
    }

    @Override
    public Integer getMuniciones() throws RemoteException {
        return municiones;
    }

    @Override
    public void setMuniciones(Integer municiones) throws RemoteException {
        this.municiones = municiones;
    }

    @Override
    public Integer getnTripulacionOriginal() {
        return tripulacionOriginal;
    }

    @Override
    public void setnTripulacionOriginal(Integer nTripulacionOriginal) {
        this.tripulacionOriginal = nTripulacionOriginal;
    }

    @Override
    public String getMaquinaAnterior() {
        return maquinaAnterior;
    }

    @Override
    public void setMaquinaAnterior(String maquinaAnterior) {
        this.maquinaAnterior = maquinaAnterior;
    }

    @Override
    public String getMaquinaActual() {
        return maquinaActual;
    }

    @Override
    public void setMaquinaActual(String maquinaActual) {
        this.maquinaActual = maquinaActual;
    }

    @Override
    public Cofre getCofre() {
        return this.cofre;
    }

    @Override
    public ArrayList<Mapa> getMapas() {
        return cofre.getMapas();
    }

    @Override
    public int agregarMapa(Mapa mapa) {
        if (cofre != null && cofre.getMapas() != null) {
            cofre.agregarMapa(mapa);
            return 1;
        } else {
            System.out.println("Error en la clase barco en la función agregarMapa, mapa contiene valores nulos ");
            return -1;
        }
    }

    @Override
    public int getSiguienteDestino() throws RemoteException {
        for (int i = 0; i < cofre.getMapas().size(); i++) {
            if (cofre.getMapas().get(i).getEstado().equalsIgnoreCase(no_visitado)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int marcarMapa(int i) throws RemoteException {

        if (i < cofre.getMapas().size()) {
            cofre.getMapas().get(i).setEstado(visitado);
            return 1;
        } else {
            return -1;
        }

    }

    @Override
    public Boolean getPirata() throws RemoteException {
        return pirata;
    }

    @Override
    public Integer getnRacionesOriginal() throws RemoteException {
        return racionesOriginal;
    }

    @Override
    public String getPuertoOrigen() throws RemoteException {
        return puertoOrigen;
    }

    @Override
    public Integer getMunicionesOriginal() throws RemoteException {
        return municionesOriginal;
    }

    @Override
    public String getMaquinaOrigen() throws RemoteException {
        return maquinaOrigen;
    }

    @Override
    public void partir() throws RemoteException {

        try {
            GestorRMI g = new GestorRMI();
            String maquinaSiguiente = cofre.getMapas().get(this.getSiguienteDestino()).getNombreMaquina();
            System.out.println("Maquina Siguiente : " + maquinaSiguiente + " Mapas sitio " + cofre.getMapas().get(getSiguienteDestino()).getNombreSitio() + " Cayo " + cofre.getMapas().get(getSiguienteDestino()).getNombreCayo());
            Thread.sleep((long) (7 * 1000.0));
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(maquinaSiguiente), g.getPuerto(maquinaSiguiente));
            InterfazMaquina m = (InterfazMaquina) registroRemoto.lookup(maquinaSiguiente);
            System.out.println("nombre: " + m.getNombre());

            m.recibirBarco(this.getName(), maquinaActual, 0);

        } catch (InterruptedException | NotBoundException | RemoteException e) {
            System.out.println("Error en clase Barco en la funcion partir() el barco no pudo realizar su partida");
        }

    }

    @Override
    public void partirOrigen() throws RemoteException {
        try {
            GestorRMI g = new GestorRMI();
            String maquinaSiguiente = this.mapaOrigen.getNombreMaquina();
            System.out.println("Maquina Siguiente : " + maquinaSiguiente + "Mapas sitio " + mapaOrigen.getNombreSitio() + "Cayo " + mapaOrigen.getNombreCayo());
            Thread.sleep((long) (7 * 1000.0));
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(maquinaSiguiente), g.getPuerto(maquinaSiguiente));
            InterfazMaquina m = (InterfazMaquina) registroRemoto.lookup(maquinaSiguiente);
            System.out.println("nombre maquina " + m.getNombre());
            m.recibirBarco(this.getName(), maquinaActual, 1);

        } catch (InterruptedException | NotBoundException | RemoteException e) {
            System.out.println("Error en Barco : partirOrigen()");
        }

    }

    public void copiarBarco(InterfazBarco barco) throws RemoteException {
        this.nombre = barco.getName();
        this.pirata = barco.getPirata();
        this.tripulacion = barco.getTripulacion();
        this.raciones = barco.getRaciones();
        this.municiones = barco.getMuniciones();
        this.tripulacionOriginal = barco.getnTripulacionOriginal();
        this.racionesOriginal = barco.getnRacionesOriginal();
        this.municionesOriginal = barco.getMunicionesOriginal();
        this.maquinaOrigen = barco.getMaquinaOrigen();
        this.mapaOrigen = barco.getMapaOrigen();
        this.maquinaAnterior = barco.getMaquinaAnterior();
        this.maquinaActual = barco.getMaquinaActual();
        this.cofre = barco.getCofre();
        this.puertoOrigen = barco.getPuertoOrigen();
    }

    public int cargarCofre(Cofre cofreLugar) {
        System.out.println("Recogemos tesoros...");
        int corazon = cofreLugar.poseeCorazon();
        if (corazon >= 0) {///posee el corazon
            while (cofre.agregarTesoro(cofreLugar.getTesoros().get(corazon)) == 1) {
                if (cofre.getMapas().size() > 0) {
                    cofre.eliminarMapa(cofre.getMapas().size() - 1);
                }
            }
            cofreLugar.getTesoros().remove(corazon);
            if (cofre.poseeCorazon() >= 0) {
                return 0;
            }
        } else {//no posee el corazon
            for (int i = 0; i < cofreLugar.getMapas().size(); i++) {
                
                cofre.agregarMapa(cofreLugar.getMapas().get(i));


            }

            //aqui coloo todos los elementos de mi cofre en el cofre del sitio, posteriormente vacio mi cofre
            //para luego tomar los objetos mas livianos
            cofreLugar.getTesoros().addAll(cofre.getTesoros());
            cofre.getTesoros().clear();

            while (!cofreLugar.getTesoros().isEmpty()) {
                int rest = 0;
                int iMenorPeso = cofreLugar.getTesoroMenorPeso();

                Tesoro t = cofreLugar.getTesoros().get(iMenorPeso);

                rest = cofre.agregarTesoro(t);

                cofreLugar.getTesoros().remove(t);

                if (rest == 1) {

                    break;
                }
            }
            // 
        }
        return 1;
    }

    public boolean retornarOrigen() {
        if (this.tripulacion <= (this.tripulacionOriginal / 3)) {
            return true;
        }
        if (this.municiones <= 0) {
            return true;
        }
        if (this.raciones <= 0) {
            return true;
        }
        if (this.cofre.getPeso() >= 90) {
            return true;
        }
        if (this.cofre.poseeCorazon() >= 0) {
            return true;
        }

        return false;
    }

    public void recargar() {
        this.municiones = this.municionesOriginal;
        this.raciones = this.racionesOriginal;
        this.tripulacion = this.tripulacionOriginal;
        this.enRetirada = false;
    }

}
