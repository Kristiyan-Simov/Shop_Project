package Interfaces;

import java.util.ArrayList;

public interface ICashier {
    public String getName();
    public int getID();
    public double getSalary();
    public IReceipt sell(ArrayList<IProduct> products, double customerMoney);
}
