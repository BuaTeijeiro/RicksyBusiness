package ricksybusiness;

import java.util.HashMap;
import java.util.Map;

public class UfosPark {
    private double fee = 500.0;
    private Map<String,String> flota = new HashMap<>();
    
    public UfosPark() {
        //Se añaden los ufos por separado
    }

    public void add(String ovni) {
        this.flota.put(ovni, null);
    }



    

}
