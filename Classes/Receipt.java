import Interfaces.ICashier;
import Interfaces.IProduct;
import Interfaces.IReceipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Receipt implements IReceipt {
    private int id;
    private ICashier cashier;
    private LocalDateTime creationTime;
    private ArrayList<IProduct> products;
    private double price;

    public Receipt(int _id, ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price){
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
    public ICashier getCashier() {
        return this.cashier;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    @Override
    public ArrayList<IProduct> getProducts() {
        return this.products;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    //TODO
    // - Add Unit Tests
}
