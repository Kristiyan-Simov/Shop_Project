package Main.Models.Contracts;

import java.util.ArrayList;

public interface ICashier {
    String getName();
    int getID();
    double getSalary();
    IReceipt sell(ArrayList<IProduct> products, double customerMoney);
}
