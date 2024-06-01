package Interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IShop {
    public ArrayList<ICashier> getCashiers();
    public ArrayList<IProduct> getDeliveredProducts();
    public ArrayList<IProduct> getSoldProducts();
    public ArrayList<IProduct> getProducts();
    public ArrayList<IReceipt> getReciepts();
    public void deliverProducts(int _id, String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible,
                                int _amount);
    public IReceipt createReceipt(ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price);
    public double calculateEmployeeSalarySpending();
    public double calculateProductDeliverySpending();
    public double calculateProductSoldEarnings();
    public double calculateTurnaroundRate();
}
