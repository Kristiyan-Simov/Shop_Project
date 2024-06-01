package Interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IShop {
    ArrayList<ICashier> getCashiers();
    ArrayList<IProduct> getDeliveredProducts();
    ArrayList<IProduct> getSoldProducts();
    ArrayList<IProduct> getProducts();
    ArrayList<IReceipt> getReciepts();
    void deliverProducts(int _id, String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible,
                                int _amount);
    ICashier hireCashier(ICashier cashier);
    ICashier fireCasher(ICashier cashier);
    double sellProducts(ArrayList<IProduct> products);
    IReceipt createReceipt(ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price);
    double calculateEmployeeSalarySpending();
    double calculateProductDeliverySpending();
    double calculateProductSoldEarnings();
    double calculateTurnaroundRate();
}
