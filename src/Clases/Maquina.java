package Clases;

import java.awt.Component;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import javax.swing.JLabel;
import piratascaribe.GestorRMI;
import Interfaces.InterfazBarco;
import Interfaces.InterfazMaquina;

public class Maquina extends UnicastRemoteObject implements InterfazMaquina {

    private Integer id;
    private ArrayList<Isla> islas;
    private ArrayList<Cayo> cayos;
    private String nombre;
    private Integer numPuertoRMI;
    private Map<String, String> nodos;
    private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";
    private static String actual = "actual";
    private static String siguiente = "siguiente";
    private ArrayList<ArrayList<String>> maquinas;
    private MaquinaGui maquinaInterfaz;

    public void setIslas(ArrayList<Isla> islas) {
        this.islas = islas;
    }

    public void setCayos(ArrayList<Cayo> cayos) {
        this.cayos = cayos;
    }

    public Maquina(int id, Integer numPuertoRMI) throws RemoteException {

        this.id = id;
        this.nombre = "maquina" + id;
        this.numPuertoRMI = numPuertoRMI;
        this.islas = new ArrayList<Isla>();
        this.cayos = new ArrayList<Cayo>();
        this.nodos = new HashMap<String, String>();
        this.maquinas = new ArrayList<ArrayList<String>>();
        maquinaInterfaz = new MaquinaGui(id);
        maquinaInterfaz.setVisible(true);
        nodos.put("maquina1", "localhost");
        nodos.put("maquina2", "localhost");
        nodos.put("maquina3", "localhost");
        nodos.put("maquina4", "localhost");
        nodos.put("servidor", "localhost");

    }

    public ArrayList<Isla> getIslas() {
        return islas;
    }

    public ArrayList<Cayo> getCayos() {
        return cayos;
    }

    @Override
    public String getNombre() throws RemoteException {
        return this.nombre;
    }

    @Override
    public void recibirBarco(String nombreBarco, String nombreMaquinaAnterior, int esOrigen) throws RemoteException {
        try {
            GestorRMI g = new GestorRMI();
            System.out.println("He recibido el barco: " + nombreBarco + " en mis Aguas");
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(nombreMaquinaAnterior), g.getPuerto(nombreMaquinaAnterior));
            InterfazBarco barco = (InterfazBarco) registroRemoto.lookup(nombreBarco);

            //Añado el objeto a mi registro local
            Registry registroLocal = LocateRegistry.getRegistry(g.getPuerto(nombre));
            Barco b = new Barco();
            b.copiarBarco(barco);

            registroLocal.rebind(b.getName(), b);
            b.imprimirContenido();
            b.imprimirCofre();

            //Elimino la referencia en la maquina anterior
            if (!nombreMaquinaAnterior.equalsIgnoreCase(this.nombre)) {
                System.out.println("Hola soy maquina " + this.nombre + "y debo eliminar la refenrecia de: " + nombreMaquinaAnterior);
                InterfazMaquina m = (InterfazMaquina) registroRemoto.lookup(nombreMaquinaAnterior);
                m.eliminarReferenciaBarco(nombreBarco, nombreMaquinaAnterior);
            }
            //hacer aqui procedimiento para dibujar interfaz barco moviendose a destino
            b.setMaquinaActual(this.nombre);
            b.setMaquinaAnterior(nombreMaquinaAnterior);

            Runnable hg = new HiloGui(b, esOrigen);
            new Thread(hg).start();

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error en Maquina: recibirBarco()");
        }
    }

    @Override
    public void eliminarReferenciaBarco(String nombreBarco, String nombreMaquinaAnterior) throws RemoteException {
        try {
            GestorRMI g = new GestorRMI();
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(nombreMaquinaAnterior), g.getPuerto(nombreMaquinaAnterior));
            registroRemoto.unbind(nombreBarco);
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error Maquina en funcion eliminarReferenciaBarco() ");
        }
    }

    public String getNombreMaquina() {
        return this.nombre;
    }

    public String getDireccionMaquina(String maquina) {
        return this.nodos.get(maquina);
    }

    public void addIsla(Isla isla) {
        if (this.islas != null && isla != null) {
            this.islas.add(isla);
        } else {
            System.out.println("Error en Maquina en funcion addIsla");
        }
    }

    public void addCayo(Cayo cayo) {
        if (this.cayos != null && cayo != null) {
            this.cayos.add(cayo);
        } else {
            System.out.println("Error en Maquina en funcion addCayos cayos o cayo tiene un valor nulo");
        }
    }

    public void izarVelas(String nombreBarco) throws RemoteException {
        System.out.println("Debo izarVelas: " + nombreBarco);
        for (int i = 0; i < islas.size(); i++) {
            System.out.println("\t\tnombre isla: " + islas.get(i).getNombre());
            for (int j = 0; j < islas.get(i).getSitios().size(); j++) {
                System.out.println("\t\t\tnombre sitio: " + islas.get(i).getSitios().get(j).getNombre());
                for (int k = 0; islas.get(i).getSitios().get(j).getBarcos() != null && k < islas.get(i).getSitios().get(j).getBarcos().size(); k++) {
                    System.out.println("\t\t\t\tnombre barco: " + islas.get(i).getSitios().get(j).getBarcos().get(k).getName());
                    if (islas.get(i).getSitios().get(j).getBarcos().get(k).getName().equalsIgnoreCase(nombreBarco)) {
                        Barco barco = islas.get(i).getSitios().get(j).getBarcos().get(k);
                        if (barco.retornarOrigen() && !barco.getMapaOrigen().getNombreSitio().equalsIgnoreCase(islas.get(i).getSitios().get(j).getNombre())) {
                            System.out.println("Debo retornar origen imprimo condiciones");
                            borrarBarcoGui(barco.getName());
                            barco.imprimirContenido();
                            barco.imprimirOriginales();
                            barco.partirOrigen();
                            islas.get(i).getSitios().get(j).getBarcos().remove(barco);
                            return;

                        }
                        int x = barco.getSiguienteDestino();//ACOMODAR
                        if (x >= 0) {
                            System.out.println("Siguiente destino:" + barco.getMapas().get(x).getNombreIsla());
                            System.out.println("partir");
                            borrarBarcoGui(barco.getName());
                            barco.partir();
                        } else if (barco.getMapaOrigen().getNombreMaquina().equalsIgnoreCase(this.nombre)) {
                            System.out.println("He retornado a mi origen hacer algo...");
                            if (barco.getCofre().poseeCorazon() >= 0) {
                                System.out.println("FINALIZADO EL BARCO :" + barco.getName() + " CONSIGUIO EL CORAZON");
                            } else {
                                System.out.println("Recargando...");
                                barco.recargar();
                                islas.get(i).getSitios().get(j).getCofre().getTesoros().addAll(barco.getCofre().getTesoros());
                                barco.getCofre().getTesoros().clear();
                                if (barco.getSiguienteDestino() >= 0) {
                                    borrarBarcoGui(barco.getName());
                                    barco.partir();
                                } else {
                                    System.out.println("Barco : " + barco.getName() + " no tiene mas destinos...");
                                }
                            }

                        } else {
                            System.out.println("He visitado todos mis lugares, me regreso al inicio");
                            borrarBarcoGui(barco.getName());

                            barco.partirOrigen();
                        }

                        islas.get(i).getSitios().get(j).getBarcos().remove(barco);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < cayos.size(); i++) {
            for (int j = 0; cayos.get(i).getBarcos() != null && j < cayos.get(i).getBarcos().size(); j++) {
                if (cayos.get(i).getBarcos().get(j).getName().equalsIgnoreCase(nombreBarco)) {

                    Barco barco = cayos.get(i).getBarcos().get(j);
                    borrarBarcoGui(barco.getName());
                    if (barco.retornarOrigen()) {
                        System.out.println("Debo retornar origen imprimo condiciones");
                        barco.imprimirContenido();
                        barco.imprimirOriginales();
                        barco.partirOrigen();
                        cayos.get(i).getBarcos().remove(barco);
                        return;

                    }
                    int x = barco.getSiguienteDestino();
                    if (x >= 0) {
                        System.out.println("Siguiente destino:" + barco.getMapas().get(x).getNombreIsla());//ACOMODAR NO SOLO ISLA
                        System.out.println("partir");
                        barco.partir();
                    } else if (barco.getMapaOrigen().getNombreMaquina().equalsIgnoreCase(this.nombre)) {
                        System.out.println("He retornado a mi origen hacer algo...");
                        if (barco.getCofre().poseeCorazon() >= 0) {
                            System.out.println("FINALIZADO EL BARCO :" + barco.getName() + " CONSIGUIO EL CORAZON");
                        } else {
                            System.out.println("Recargando...");
                            barco.recargar();
                            cayos.get(i).getCofre().getTesoros().addAll(barco.getCofre().getTesoros());
                            barco.getCofre().getTesoros().clear();
                            if (barco.getSiguienteDestino() >= 0) {
                                barco.partir();
                            } else {
                                System.out.println("Barco : " + barco.getName() + " no tiene mas destinos...");
                            }
                        }

                    } else {
                        System.out.println("He visitado todos mis lugares, me regreso al inicio");
                        barco.partirOrigen();
                    }

                    cayos.get(i).getBarcos().remove(barco);
                    return;
                }

            }
        }
    }

    public void ubicarBarco(Barco barco, int esOrigen) throws RemoteException {
        try {
            Mapa mapa = null;
            int sigDest;
            if (esOrigen == 1) {
                mapa = barco.getMapaOrigen();
                sigDest = 1;
            } else {
                sigDest = barco.getSiguienteDestino();
                if (sigDest >= 0) {
                    barco.marcarMapa(sigDest);//marcamos el sitio donde estabamos anteriormente como visitado
                    mapa = barco.getMapas().get(sigDest); //marcamos el mapa en el sitio como actual
                    mapa.setEstado("actual");
                }
            }

            if (sigDest >= 0) {//encontro destino con exito

                //System.out.println("Entro en sigDest ");
                if (mapa.esIsla()) {
                    System.out.println("El nombre del siguiente sitio es: " + mapa.getNombreIsla() + "/" + mapa.getNombreSitio());
                } else {
                    System.out.println("El nombre del siguiente cayo es: " + mapa.getNombreCayo());
                }
                if (mapa.esIsla() && islas != null) {
                    for (int i = 0; i < islas.size(); i++) {
                        if (islas.get(i).getNombre().equalsIgnoreCase(mapa.getNombreIsla())) {
                            System.out.print(i);
                            ArrayList<Sitio> sitios;
                            sitios = islas.get(i).getSitios();
                            for (int j = 0; j < sitios.size(); j++) {
                                if (sitios.get(j).getNombre().equalsIgnoreCase(mapa.getNombreSitio())) {

                                    System.out.println("Lo he ubicado en el sitio: " + sitios.get(j).getNombre());
                                    //pintamos el barco en la interfaz grafica..
                                    pintarBarco(barco.getName(), maquinaInterfaz.getCoordenadas().get(sitios.get(j).getNombre()).getX(), maquinaInterfaz.getCoordenadas().get(sitios.get(j).getNombre()).getY());

                                    if (sitios.get(j).getBarcos() != null && sitios.get(j).getBarcos().size() >= 1) {
                                        System.out.println("Se han encontado dos barcos en la isla: "
                                                + this.nombre);
                                        if (sitios.get(j).getBarcos().size() >= 2) {
                                            if (sitios.get(j).getBarcos().get(0).enRetirada != true) {
                                            }
                                            if (barco.enRetirada != true && sitios.get(j).getBarcos().get(1).enRetirada != true) {
                                            }
                                        } else {
                                            if (sitios.get(j).getBarcos().get(0).enRetirada != true) {
                                            }
                                        }

                                        //verificar que faccion son
                                        //si son diferentes pelear
                                        //si sn 3 barcos pelear de una
                                    } else {//ocurre calamidad solo ocurre calamidad si hay un solo barco
                                        Calamidad calamidad = sitios.get(j).getCalamidad();
                                        if (calamidad != null && calamidad.ocurreCalamidad()) {//true ocurre calamidad
                                            System.out.println("Ha ocurrido una calamidad: " + calamidad.getNombre());
                                            System.out.println("ELEMENTOS ORIGINALES");
                                            barco.imprimirContenido();
                                            barco.setMuniciones(barco.getMuniciones() - calamidad.getResta_municiones());
                                            barco.setTripulacion(barco.getTripulacion() - calamidad.getResta_trip());
                                            barco.setRaciones(barco.getRaciones() - calamidad.getResta_racion());
                                            System.out.println("ELEMENTOS LUEGO CALAMIDAD");
                                            barco.imprimirContenido();

                                        }
                                    }
                                    if (barco.enRetirada != true) {
                                        sitios.get(j).encallaBarco(barco);
                                    }
                                }
                            }
                        }
                    }

                } else {
                    int i;
                    for (i = 0; i < cayos.size(); i++) {
                        if (cayos.get(i).getNombre().equalsIgnoreCase(mapa.getNombreCayo())) {

                            System.out.println("Lo he ubicado en el cayo: " + cayos.get(i).getNombre());
                            System.out.println("Que tiene como coordenadas:  X:"
                                    + maquinaInterfaz.getCoordenadas().get(cayos.get(i).getNombre()).getX() + "  Y:"
                                    + maquinaInterfaz.getCoordenadas().get(cayos.get(i).getNombre()).getY());
                            pintarBarco(barco.getName(), maquinaInterfaz.getCoordenadas().get(cayos.get(i).getNombre()).getX(), maquinaInterfaz.getCoordenadas().get(cayos.get(i).getNombre()).getY());
                            if (cayos.get(i).getBarcos() != null && cayos.get(i).getBarcos().size() >= 1) {
                                if (cayos.get(i).getBarcos().size() >= 2) {
                                    if (cayos.get(i).getBarcos().get(0).enRetirada != true) {
                                    }
                                    if (barco.enRetirada != true && cayos.get(i).getBarcos().get(1).enRetirada != true) {
                                    }
                                } else {
                                }
                                System.out.println("Se han encontado dos barcos en la maquina(cayo): "
                                        + this.nombre + "IMPLEMENTAR CODIGO PELEA");
                            } else {//ocurre calamidad solo ocurre calamidad si hay un solo barco
                                Calamidad calamidad = cayos.get(i).getCalamidad();
                                if (calamidad != null && calamidad.ocurreCalamidad()) {//true ocurre calamidad
                                    System.out.println("Ha ocurrido una calamidad: " + calamidad.getNombre());
                                    System.out.println("ELEMENTOS ORIGINALES");
                                    System.out.println("Tripulacion: " + barco.getTripulacion());
                                    System.out.println("municiones: " + barco.getMuniciones());
                                    System.out.println("Raciones: " + barco.getRaciones());
                                    barco.setMuniciones(barco.getMuniciones() - calamidad.getResta_municiones());
                                    barco.setTripulacion(barco.getTripulacion() - calamidad.getResta_trip());
                                    barco.setRaciones(barco.getRaciones() - calamidad.getResta_racion());
                                    System.out.println("ELEMENTOS LUEGO CALAMIDAD");
                                    System.out.println("Tripulacion: " + barco.getTripulacion());
                                    System.out.println("Municiones: " + barco.getMuniciones());
                                    System.out.println("Raciones: " + barco.getRaciones());

                                }
                            }
                            if (barco.enRetirada != true) {
                                cayos.get(i).encallaBarco(barco);
                            }
                        }
                    }
                    if (i == cayos.size()) {
                        System.out.println("Error en clase Maquina Nombre de Cayo no se logra ubicar en esta maquina");
                    }
                }

            } else { //no encontro destino
                if (barco.getMapaOrigen().esIsla()) {
                    for (int i = 0; islas != null && i < islas.size(); i++) {
                        if (islas.get(i).getNombre().equalsIgnoreCase(barco.getMapaOrigen().getNombreIsla())) {
                            for (int j = 0; islas.get(i).getSitios() != null && j < islas.get(i).getSitios().size(); j++) {
                                if (islas.get(i).getSitios().get(j).getNombre().equalsIgnoreCase(barco.getMapaOrigen().getNombreSitio())) {
                                    if (barco.enRetirada != true) {
                                        islas.get(i).getSitios().get(j).encallaBarco(barco);
                                        System.out.println("Lo he ubicado en sitio origen: " + islas.get(i).getSitios().get(j).getNombre());
                                        pintarBarco(barco.getName(), maquinaInterfaz.getCoordenadas().get(islas.get(i).getSitios().get(j).getNombre()).getX(), maquinaInterfaz.getCoordenadas().get(islas.get(i).getSitios().get(j).getNombre()).getY());
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; cayos != null && i < cayos.size(); i++) {
                        if (cayos.get(i).getNombre().equalsIgnoreCase(barco.getMapaOrigen().getNombreCayo())) {
                            if (barco.enRetirada != true) {
                                cayos.get(i).encallaBarco(barco);
                                System.out.println("Lo he ubicado en el cayo origen: " + cayos.get(i).getNombre());
                                pintarBarco(barco.getName(), maquinaInterfaz.getCoordenadas().get(cayos.get(i).getNombre()).getX(), maquinaInterfaz.getCoordenadas().get(cayos.get(i).getNombre()).getY());
                            }
                        }
                    }

                }
            }

        } catch (RemoteException e) {

        }

    }

    public void combate(Barco b1, Barco b2) throws RemoteException {
        if (b1.getPirata() == b2.getPirata()) {
            return;
        }
        if (2 * b1.getTripulacion() < b2.getTripulacion() || 2 * b1.getMuniciones() < b2.getMuniciones()) {
            System.out.println("El barco " + b1.getName() + " se ha retirado!");
            b1.enRetirada = true;
            if (b1.getSiguienteDestino() == -1) {
                b1.partirOrigen();
            } else {
                b1.partir();
            }
        } else {
            if (2 * b2.getTripulacion() < b1.getTripulacion() || 2 * b2.getMuniciones() < b1.getMuniciones()) {
                System.out.println("El barco " + b2.getName() + " se ha retirado!");
                b2.enRetirada = true;
                if (b2.getSiguienteDestino() == -1) {
                    b2.partirOrigen();
                } else {
                    b2.partir();
                }
            } else {
                int aux;
                if (b1.getTripulacion() > b2.getTripulacion()) {
                    aux = b1.getTripulacion() - b2.getTripulacion();
                } else {
                    aux = b2.getTripulacion() - b1.getTripulacion();
                }
                b1.setTripulacion(b1.getTripulacion() - (aux / 2));
                b2.setTripulacion(b2.getTripulacion() - (aux / 2));
                System.out.println(b1.getName() + " y " + b2.getName() + " han perdido " + (aux / 2) + " tripulantes");
                if (b1.getMuniciones() > b2.getMuniciones()) {
                    aux = b1.getMuniciones() - b2.getMuniciones();
                } else {
                    aux = b2.getMuniciones() - b1.getMuniciones();
                }
                b1.setMuniciones(b1.getMuniciones() - (aux / 2));
                b2.setMuniciones(b2.getMuniciones() - (aux / 2));
                System.out.println(b1.getName() + " y " + b2.getName() + " han perdido " + (aux / 2) + " municiones");
                System.out.println(b1.getName() + " ha quedado con " + b1.getTripulacion() + " tripulantes");
                System.out.println(b2.getName() + " ha quedado con " + b2.getTripulacion() + " tripulantes");
            }
            if (3 * b1.getTripulacion() <= b1.getnTripulacionOriginal()) {
                System.out.println(b1.getName() + " ha sido derrotado!");
                b1.enRetirada = true;
                b1.partirOrigen();
            }
            if (3 * b2.getTripulacion() <= b2.getnTripulacionOriginal()) {
                System.out.println(b2.getName() + " ha sido derrotado!");
                b2.enRetirada = true;
                b2.partirOrigen();
            }
            System.out.println("Combate Finalizado!");
        }
    }

    private void pintarBarco(String nombreBarco, int x, int y) {
        JLabel BarcoImg = new JLabel();
        BarcoImg.setName(nombreBarco);
        if (nombreBarco.equalsIgnoreCase("La_Venganza_Errante")) {
            BarcoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/venganzaerrante.png")));

        } else if (nombreBarco.equalsIgnoreCase("El_Invencible")) {
            BarcoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/invencible.png")));
        } else if (nombreBarco.equalsIgnoreCase("El_Interceptor")) {
            BarcoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interceptor.png")));
        }

        BarcoImg.setBounds(x,
                 y, 130, 120);
        maquinaInterfaz.getContentPane().add(BarcoImg, 0);
        maquinaInterfaz.revalidate();
        maquinaInterfaz.repaint();
    }

    private void borrarBarcoGui(String nombreBarco) {
        Component c[] = maquinaInterfaz.getContentPane().getComponents();
        for (int index = 0; c != null && index < c.length; index++) {
            if (c[index].getName() != null && c[index].getName().equalsIgnoreCase(nombreBarco)) {
                maquinaInterfaz.remove(c[index]);
                maquinaInterfaz.revalidate();
                maquinaInterfaz.repaint();
            }
        }
    }

    private class HiloGui implements Runnable {

        private Barco b;
        private int esOrigen;

        public HiloGui(Barco barco, int esOrigen) {
            this.b = barco;
            this.esOrigen = esOrigen;
        }

        @Override
        public void run() {
            try {
                ubicarBarco(b, esOrigen);
                Thread.sleep((long) (5 * 1000.0));
                izarVelas(b.getName());
            } catch (Exception e) {
                System.out.println("Error en Maquina: HiloGui");
                e.printStackTrace();
            }
        }
    }
}
