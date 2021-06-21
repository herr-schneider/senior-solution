public class Coffee {

    private CoffeeType type;
    private double price;

    public Coffee(CoffeeType type, double price) {
        this.type = type;
        this.price = price;
    }

    public CoffeeType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
