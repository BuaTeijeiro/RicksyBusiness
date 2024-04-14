package ricksybusiness;

import java.util.ArrayList;
import java.util.List;

public class Receptivo {
    ArrayList<GuestDispatcher> dispatchers = new ArrayList<>();

    public Receptivo(){
        //La lista ya se inicializó
    }

    public void registra(GuestDispatcher newDispatcher){
        this.dispatchers.add(newDispatcher);
    }

    public void dispatch(CreditCard tarjeta){
        for (GuestDispatcher dispatcher : this.dispatchers){
            dispatcher.dispatch(tarjeta);
        }
    }

    public void dispatchMenu(List<CreditCard> tarjetas){
        for (CreditCard tarjeta: tarjetas){
            dispatch(tarjeta);
        }
    }

}
