package Main.Models;

import Main.Models.Contracts.IProduct;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Product implements IProduct {
    private int id;
    private String name;
    private double deliveryPrice;
    private LocalDate expirationDate;
    private boolean edible;
    private double percentageAdded;
    private double percentageRemoved;
    private int periodForDiscount;
    private int amount;

    public Product(int _id, String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible,
                   double _percentageAdded, double _percentageRemoved, int _periodForDiscount, int _amount){
        this.id = _id;
        this.name = _name;
        this.deliveryPrice = _deliveryPrice;
        this.expirationDate = _expirationDate;
        this.edible = _edible;
        this.percentageAdded = _percentageAdded;
        this.percentageRemoved = _percentageRemoved;
        this.periodForDiscount = _periodForDiscount;
        this.amount = _amount;
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
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void deliverProducts(int amount) {
        if (amount > 0){
            this.amount += amount;
        }
        else {
            throw new IllegalArgumentException("Cannot add negative amount of products!");
        }
    }

    @Override
    public double calculatePrice() {
        double price = this.deliveryPrice;
        price += deliveryPrice * (percentageAdded / 100);
        long daysUntilExpiry = LocalDate.now().until(this.expirationDate, ChronoUnit.DAYS);

        if (daysUntilExpiry <= 0){
            price = -1;
        }
        else if (daysUntilExpiry <= periodForDiscount){
            price -= price * (percentageRemoved / 100);
        }

        return price;
    }

    @Override
    public double sell(int amount) {

        if (LocalDate.now().until(this.expirationDate, ChronoUnit.DAYS) <= 0){
            throw new IllegalArgumentException("Product Expired!");
        }

        if (this.amount - amount >= 0){
            return this.calculatePrice() * amount;
        }

        throw new IllegalArgumentException("Not enough products! Products over limit: " + (amount - this.amount));
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append('\n');
        sb.append(this.edible?"--Edible--":"--Inedible--").append('\n');
        sb.append("Amount: ").append(this.amount).append('\n');
        sb.append("Price: ").append(this.calculatePrice()).append('\n');
        sb.append("Expiration Date: ").append(this.expirationDate.format(formatter)).append('\n');

        return sb.toString().trim();
    }

    //TODO
    // - Add Unit Tests
}
