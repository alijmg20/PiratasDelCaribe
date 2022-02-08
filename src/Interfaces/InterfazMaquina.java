
package Interfaces;

  import java.rmi.*;



public interface InterfazMaquina extends Remote {

    

    public String getNombre() throws RemoteException;
    public void recibirBarco (String nombreBarco,String nombreMaquina,int esOrigen)throws RemoteException;
    public void eliminarReferenciaBarco (String nombreBarco, String nombreMaquinaAnterior) throws RemoteException;
    

}
