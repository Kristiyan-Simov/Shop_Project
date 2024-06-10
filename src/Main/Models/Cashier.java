package Main.Models;

import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cashier implements ICashier {
    private String name;
    private int id;
    private double salary;

    public Cashier(String _name, int _id, double _salary){
        this.name = _name;
        this.id = _id;
        this.salary = _salary;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public Receipt sell(ArrayList<IProduct> products, double customerMoney) {
        double price = 0;

        int amount = 0;
        IProduct previousProduct = products.get(0);

        for (int i = 1; i < products.size(); i++){
            if (products.get(i).getID() == previousProduct.getID()){
                amount++;
            }
            else{
                price += previousProduct.sell(amount);
                amount = 1;
            }

            previousProduct = products.get(i);
        }

        if (price > customerMoney){
            throw new IllegalArgumentException("Customer doesn't have enough money! Money over limit: " + (price - customerMoney));
        }

        Receipt receipt = new Receipt(1234, this, LocalDateTime.now(), products, price);

        return receipt;
    }
    //TODO
    // - Add Unit Tests

    //FixMe
    // - Selling Products Functionality
}
