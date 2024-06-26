package Main.Models.Contracts;

import java.time.LocalDate;

public interface IProduct {
    void setExpirationDate(LocalDate newExpirationDate);
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
