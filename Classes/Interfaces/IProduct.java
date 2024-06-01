package Interfaces;

import java.time.LocalDate;

public interface IProduct {
    int getID();
    String getName();
    double getDeliveryPrice();
    LocalDate getExpirationDate();
    boolean getEdible();
    int getAmount();
    double calculatePrice();
    double sell(int amount);
}
