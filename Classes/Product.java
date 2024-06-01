import java.time.LocalDate;

public class Product implements Interfaces.Product {
    private int id;
    private String name;
    private double deliveryPrice;
    private LocalDate expirationDate;
    public boolean edible;

    public Product(int _id, String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible){
        this.id = _id;
        this.deliveryPrice = _deliveryPrice;
        this.expirationDate = _expirationDate;
        this.edible = _edible;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getDeliveryPrice() {
        return this.deliveryPrice;
    }

    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public boolean getEdible() {
        return this.edible;
    }

    @Override
    public int getAmountLeft() {
        return 0;
    }

    @Override
    public double calculatePrice(double percentageAdded) {
        return 0;
    }
}
