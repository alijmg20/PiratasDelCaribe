
package piratascaribe;

import java.util.HashMap;
import java.util.Map;


public class GestorRMI {
    Map<String,String> ips;
    Map<String,Integer> puertos;
    
    public GestorRMI(){
        ips = new HashMap<String,String>();
        puertos =  new HashMap<String,Integer>();
        
        ips.put("server", "localhost");
        ips.put("maquina1", "localhost");
        ips.put("maquina2", "localhost");
        ips.put("maquina3", "localhost");
        ips.put("maquina4", "localhost");
        
        puertos.put("server", 8000);
        puertos.put("maquina1", 8001);
        puertos.put("maquina2", 8002);
        puertos.put("maquina3", 8003);
        puertos.put("maquina4", 8004);
    }
    
    public String getIp(String nombre){
        return ips.get(nombre);
    }
    
    public Integer getPuerto(String nombre){
        return puertos.get(nombre);
    }
    
}
