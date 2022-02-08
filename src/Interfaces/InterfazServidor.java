
package Interfaces;

import java.rmi.*;


public interface InterfazServidor extends Remote{
    public void registroRebind(Remote objeto,int tipo) throws RemoteException;
    
}
