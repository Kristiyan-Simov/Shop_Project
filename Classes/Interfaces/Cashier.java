package Interfaces;

import java.util.ArrayList;

public interface Cashier {
    public String getName();
    public int getID();
    public double getSalary();
    public Receipt sell(ArrayList<Product> products, double customerMoney);
}
