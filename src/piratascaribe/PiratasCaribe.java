
package piratascaribe;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;


public class PiratasCaribe {
        static int puertoServer=8000;
        String ipServer = "127.0.0.1";
        String urlServer = "rmi://"+ipServer+":"+puertoServer+"/";
    public static void main(String[] args) {
      
        try{   
            arrancarRegistro(puertoServer);         
        }
        catch (RemoteException e){
            System.out.println("Excepcion en Servidor en la funcion main: "+ e);
        } 
    }
    
    
    private static void arrancarRegistro (int puertoRMI)throws RemoteException{
        try{
            Registry registro = LocateRegistry.getRegistry(puertoRMI);
            registro.list();
            //El metodo anterior lanza una excepcion
            //Si el registro no existe
            System.out.println("Se ha arrancado el registro en el Server");
        }
        catch (RemoteException e){
            //No existe un registro valido en este puerto 
            System.out.println("El registro RMI no se puede localizar en el puerto: "+puertoRMI);
            Registry registro = LocateRegistry.createRegistry(puertoRMI);
            System.out.println("Registro RMI creado en el puerto: "+ puertoRMI);
            System.out.println("escuchando... "+ registro.list().toString());
            while(true){}
                

        }
    }
    
}
