package Main.Models.Contracts;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IReceipt {
    int getID();
    ICashier getCashier();
    LocalDateTime getCreationTime();
    ArrayList<IProduct> getProducts();
    double getPrice();
}
