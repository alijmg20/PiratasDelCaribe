package piratascaribe;

import Interfaces.InterfazBarco;
import Clases.Maquina;
import Clases.Barco;
import Clases.Mapa;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public void partir(String url) {
        try {
            
            InterfazBarco barco = (InterfazBarco) Naming.lookup(url);
            System.out.println("partir");
            int i = barco.getSiguienteDestino();
            System.out.println("Siguiente destino:" + barco.getMapas().get(i).getNombreIsla());
            Naming.rebind(url, barco);

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
        }
    }

    public static void main(String[] args) {

        XMLParser xml = new XMLParser();

        try {
            InputStreamReader leer = new InputStreamReader(System.in);
            BufferedReader buffer=  new BufferedReader(leer);
            System.out.print("Escriba el id de su maquina: ");
            String nombreMaquina = buffer.readLine();
            int numMaquina = Integer.parseInt(nombreMaquina);
            System.out.println("Soy maquina: " + nombreMaquina);
            GestorRMI gestor = new GestorRMI();
            Maquina maquina = new Maquina(numMaquina, gestor.getPuerto("maquina" + numMaquina));

            System.setProperty("java.rmi.server.hostname", gestor.getIp(maquina.getNombre()));

            xml.leerMaquinas(numMaquina);
            maquina.setIslas(xml.islastemp);
            maquina.setCayos(xml.cayostemp);
            System.out.println("El tamaño de las islas: " + maquina.getIslas().size() + " y cayos es de : " + maquina.getCayos().size());
            for (int i = 0; i < maquina.getIslas().size(); i++) {
                System.out.println("Nombre Isla: " + maquina.getIslas().get(i).getNombre());
                for (int j = 0; j < maquina.getIslas().get(i).getSitios().size(); j++) {
                    System.out.println("\t " + maquina.getIslas().get(i).getSitios().get(j).getNombre());
                    for (int k = 0; k < maquina.getIslas().get(i).getSitios().get(j).getCofre().getMapas().size(); k++) {
                        System.out.println("\t\tMapas : cayo " + maquina.getIslas().get(i).getSitios().get(j).getCofre().getMapas().get(k).getNombreCayo()
                                + " o sitio " + maquina.getIslas().get(i).getSitios().get(j).getCofre().getMapas().get(k).getNombreSitio());
                    }
                    for (int k = 0; k < maquina.getIslas().get(i).getSitios().get(j).getCofre().getTesoros().size(); k++) {
                        System.out.println("\t\tTesoro : " + maquina.getIslas().get(i).getSitios().get(j).getCofre().getTesoros().get(k).getNombre());
                    }
                    for (int k = 0; k < maquina.getIslas().get(i).getSitios().get(j).getBarcos().size(); k++) {
                        System.out.println("\t\tBarcos: " + maquina.getIslas().get(i).getSitios().get(j).getBarcos().get(k).getName());
                    }
                }
            }

            for (int i = 0; i < maquina.getCayos().size(); i++) {
                System.out.println("Nombre Cayo: " + maquina.getCayos().get(i).getNombre());
                for (int j = 0; j < maquina.getCayos().get(i).getCofre().getMapas().size(); j++) {
                    System.out.println("\t Mapas: cayo" + maquina.getCayos().get(i).getCofre().getMapas().get(j).getNombreCayo()
                            + " o sitio " + maquina.getCayos().get(i).getCofre().getMapas().get(j).getNombreSitio());
                }
                for (int j = 0; j < maquina.getCayos().get(i).getCofre().getTesoros().size(); j++) {
                    System.out.println("\t Tesoros: " + maquina.getCayos().get(i).getCofre().getTesoros().get(j).getNombre());
                }
                for (int j = 0; j < maquina.getCayos().get(i).getBarcos().size(); j++) {
                    System.out.println("\t Barcos: " + maquina.getCayos().get(i).getBarcos().get(j).getName());
                }

            }
            Registry registro = LocateRegistry.createRegistry(gestor.getPuerto(maquina.getNombre()));
            registro.rebind(maquina.getNombre(), maquina);
            System.out.println("Ahora esperare a que me llegue una consulta");
            if (numMaquina == 1) {
                xml.leerBarcos(1);
                Barco bp = xml.barcotemp;

                Mapa mapaOrigen = new Mapa("Puerto Real", "Isla Nueva Esperanzas", "1");

                bp.setMapaOrigen(mapaOrigen);

                registro.rebind(bp.getName(), bp);
                Mapa mapa = new Mapa("Cayo de Barlovento", "3");
                Mapa mapa11 = new Mapa("Puerto Rico", "La Gran Isla de la Española", "4");
                bp.setMaquinaActual(maquina.getNombre());
                bp.setMaquinaAnterior(maquina.getNombre());
                bp.agregarMapa(mapa);
                bp.agregarMapa(mapa11);

                bp.partir();

            }
            if (numMaquina == 2) {
                xml.leerBarcos(2);
                Barco br1 = xml.barcotemp;
                xml.leerBarcos(3);
                Barco br2 = xml.barcotemp;
                Mapa mapaOrigen = new Mapa("Puerto de la Reina", "Isla La Holandesa", "2");

                br1.setMapaOrigen(mapaOrigen);
                br2.setMapaOrigen(mapaOrigen);

                Mapa mapa1 = new Mapa("Puerto Rico", "La Gran Isla de la Española", "4");
                br1.agregarMapa(mapa1);

                Mapa mapa2 = new Mapa("Cueva del Bucanero", "Isla del Naufrago", "2");

                br2.agregarMapa(mapa2);

                registro.rebind(br1.getName(), br1);
                br1.setMaquinaActual(maquina.getNombre());
                br1.setMaquinaAnterior(maquina.getNombre());

                registro.rebind(br2.getName(), br2);
                br2.setMaquinaActual(maquina.getNombre());
                br2.setMaquinaAnterior(maquina.getNombre());

                br2.partir(); //INTERCEPTOR
                br1.partir(); //INVENCIBLE

            }
            String[] names = registro.list();
            System.out.println("Imprimo Los Objetos Guardados");
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en Maquina ");
            
        }
    }
}
