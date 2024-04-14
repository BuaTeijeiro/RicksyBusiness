package ricksybusiness;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UfosPark {
    private double fee = 500.0;
    private Map<String,String> flota = new HashMap<>();
    
    public UfosPark() {
        //Se añaden los ufos por separado
    }

    public void add(String ovni) {
        if (!this.flota.containsKey(ovni)){
            this.flota.put(ovni, "null");
        }
    }

    double getFee(){
        return this.fee;
    }

    public int getNumberUfos(){
        return this.flota.size();
    }

    public void dispatch(CreditCard tarjeta){
        Optional<String> availableUfo = getAvailableUfo();
        if (!hasUfo(tarjeta) && availableUfo.isPresent() && tarjeta.canAfford(getFee())){
            tarjeta.pay(fee);
            this.flota.replace(availableUfo.get(), tarjeta.number());
        }
    }
    
    Optional<String> getAvailableUfo(){
        Optional<Map.Entry<String,String>> availableUfo = 
        this.flota.entrySet().stream().filter(ovni -> ovni.getValue().equals("null")).findAny();
        return Optional.ofNullable(availableUfo.isPresent() ? availableUfo.get().getKey() : null);
    }

    void setUfoUser(String ufo, CreditCard card){
        this.flota.replace(ufo, card.number());
    }

    boolean hasUfo(CreditCard tarjeta){
        return this.flota.values().contains(tarjeta.number());
    }

    public String getUfoOf(String cardNumber){
        Optional<Map.Entry<String,String>> ufo = this.flota
                                                    .entrySet()
                                                    .stream()
                                                    .filter(a -> a.getValue().equals(cardNumber))
                                                    .findFirst();
        return ufo.isPresent()?  ufo.get().getKey() : null;
    }

    @Override
    public String toString(){
        return this.flota.keySet().toString();
    }
}
