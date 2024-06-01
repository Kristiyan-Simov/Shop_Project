import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product implements Interfaces.Product {
    private int id;
    private String name;
    private double deliveryPrice;
    private LocalDate expirationDate;
    private boolean edible;
    private double percentageAdded;
    private double percentageRemoved;
    private int periodForDiscount;
    private int amountLeft;

    public Product(int _id, String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible,
                   double _percentageAdded, double _percentageRemoved, int _periodForDiscount, int _amountLeft){
        this.id = _id;
        this.deliveryPrice = _deliveryPrice;
        this.expirationDate = _expirationDate;
        this.edible = _edible;
        this.percentageAdded = _percentageAdded;
        this.percentageRemoved = _percentageRemoved;
        this.periodForDiscount = _periodForDiscount;
        this.amountLeft = _amountLeft;
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
        return this.amountLeft;
    }

    @Override
    public double calculatePrice() {
        double price = this.deliveryPrice;
        price += deliveryPrice * percentageAdded;
        long daysUntilExpiry = this.expirationDate.until(LocalDate.now(), ChronoUnit.DAYS);

        if (daysUntilExpiry <= periodForDiscount && daysUntilExpiry >= 0){
            price -= price * percentageRemoved;
        }

        return price;
    }

    @Override
    public double sell(int amount) {

        if (this.amountLeft - amount >= 0){
            return this.calculatePrice() * amount;
        }

        throw new IllegalArgumentException("Not enough products! Products over limit: " + (amount - this.amountLeft));
    }
}
