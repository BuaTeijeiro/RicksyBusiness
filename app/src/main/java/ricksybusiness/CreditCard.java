package ricksybusiness;

public class CreditCard {
    private String owner;
    private String number;
    private double credit;
    private static final String SYMBOL = "EZI";

    public CreditCard(String owner, String number) {
        this.owner = owner;
        this.number = number;
        this.credit = 3000.0;
    }

    String getOwner() {
        return owner;
    }

    public String number() {
        return number;
    }

    public double credit() {
        return credit;
    }

    public void pay(double cost){
        if (this.canAfford(cost)){
            this.credit -= cost;
        }
    }

    public boolean canAfford(double cost){
        return cost <= this.credit;
    }

    @Override
    public String toString(){
        return new StringBuilder("owner: ")
        .append(getOwner())
        .append("\nnumber: ")
        .append(number())
        .append("\ncredit: ")
        .append(credit())
        .append(CreditCard.SYMBOL)
        .toString();
    }
}
