import Interfaces.Cashier;
import Interfaces.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Receipt implements Interfaces.Receipt {
    private int id;
    private Cashier cashier;
    private LocalDateTime creationTime;
    private ArrayList<Product> products;
    private double price;

    public Receipt(int _id, Cashier _cashier, LocalDateTime _creationTime, ArrayList<Product> _products, double _price){
        this.id = _id;
        this.cashier = _cashier;
        this.products = _products;
        this.creationTime = _creationTime;
        this.price = _price;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Cashier getCashier() {
        return this.cashier;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
