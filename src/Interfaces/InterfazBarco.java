
package Interfaces;

import Clases.Cofre;
import Clases.Mapa;
import java.rmi.*;
import java.util.ArrayList;


public interface InterfazBarco extends Remote{
   
    
    
     
     public String getName()throws RemoteException;
    public Integer getTripulacion()throws RemoteException;
     public void setRaciones(Integer nRaciones)throws RemoteException;
     public Integer getRaciones() throws RemoteException;

    public void setTripulacion(Integer nTripulacion) throws RemoteException;
    public Integer getMuniciones() throws RemoteException;
    public void setMuniciones(Integer nAmmo)throws RemoteException;

    public Integer getnTripulacionOriginal()throws RemoteException;
    public void setnTripulacionOriginal(Integer nTripulacionOriginal) throws RemoteException;
    
    public Cofre getCofre()throws RemoteException;
    
    public ArrayList<Mapa> getMapas()throws RemoteException;
    
    public int agregarMapa(Mapa mapa)throws RemoteException;

    public int getSiguienteDestino()throws RemoteException;
    
    public void imprimirCofre() throws RemoteException;
    public int marcarMapa(int i) throws RemoteException;
    public void partir() throws RemoteException;
    
      public void partirOrigen() throws RemoteException;
    
    public String getMaquinaAnterior() throws RemoteException;

    public void setMaquinaAnterior(String maquinaAnterior) throws RemoteException;

    public String getMaquinaActual() throws RemoteException;

    public void setMaquinaActual(String maquinaActual) throws RemoteException;
    
    public Boolean getPirata() throws RemoteException;

    public Integer getnRacionesOriginal() throws RemoteException;

    public String getPuertoOrigen() throws RemoteException;
    
    public Integer getMunicionesOriginal() throws RemoteException;

    public String getMaquinaOrigen() throws RemoteException ;
    
    public Mapa getMapaOrigen() throws RemoteException;
    
    

}
