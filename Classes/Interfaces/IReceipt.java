package Interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IReceipt {
    public int getID();
    public ICashier getCashier();
    public LocalDateTime getCreationTime();
    public ArrayList<IProduct> getProducts();
    public double getPrice();
}
