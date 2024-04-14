package ricksybusiness;

public class CrystalExpender implements GuestDispatcher{
    private int stock;
    private double itemCost;

    public CrystalExpender(int stock, double itemCost) {
        this.stock = stock;
        this.itemCost = itemCost;
    }

    public int stock() {
        return stock;
    }

    public double cost() {
        return itemCost;
    }

    @Override
    public void dispatch(CreditCard tarjeta) {
        
    }

    @Override
    public String toString(){
        return new StringBuilder("stock: ")
        .append(this.stock)
        .append("\ncost: ")
        .append(this.cost())
        .toString();
    }

}
