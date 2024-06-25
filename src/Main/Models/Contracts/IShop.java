package Main.Models.Contracts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface IShop {
    ArrayList<ICashier> getCashiers();
    ArrayList<IProduct> getDeliveredProducts();
    ArrayList<IProduct> getSoldProducts();
    ArrayList<IProduct> getProducts();
    ArrayList<IReceipt> getReciepts();
    double getEdibleAdditionalPrice();
    double getInedibleAdditionalPrice();
    double getExpirationDiscount();
    int getMinDaysForDiscount();
    void deliverProducts(String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible,
                                int _amount);
    ICashier hireCashier(ICashier cashier);
    ICashier fireCasher(String name);
    IReceipt sellProducts(HashMap<String, Integer> products, int storeLine, double clientMoney);
    double calculateEmployeeSalarySpending();
    double calculateProductDeliverySpending();
    double calculateProductSoldEarnings();
    double calculateTurnaroundRate();
    void saveReceipts();
}
