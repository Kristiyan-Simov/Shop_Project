package Interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Receipt {
    public int getID();
    public Cashier getCashier();
    public LocalDateTime getCreationTime();
    public ArrayList<Product> getProducts();
    public double getPrice();
}