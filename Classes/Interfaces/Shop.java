package Interfaces;

import java.util.ArrayList;

public interface Shop {
    public ArrayList<Cashier> getCashiers();
    public ArrayList<Product> getDeliveredProducts();
    public ArrayList<Product> getSoldProducts();
    public ArrayList<Receipt> getReciepts();
}
