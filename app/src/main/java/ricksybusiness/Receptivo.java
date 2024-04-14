package ricksybusiness;

import java.util.ArrayList;

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

}
