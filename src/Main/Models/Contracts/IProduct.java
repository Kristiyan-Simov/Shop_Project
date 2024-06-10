package Main.Models.Contracts;

import java.time.LocalDate;

public interface IProduct {
    int getID();
    String getName();
    double getDeliveryPrice();
    LocalDate getExpirationDate();
    boolean getEdible();
    int getAmount();
    void deliverProducts(int amount);
    double calculatePrice();
    double sell(int amount);
}
