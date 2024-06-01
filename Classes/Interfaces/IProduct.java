package Interfaces;

import java.time.LocalDate;

public interface IProduct {
    public int getID();
    public String getName();
    public double getDeliveryPrice();
    public LocalDate getExpirationDate();
    public boolean getEdible();
    public int getAmount();
    public double calculatePrice();
    public double sell(int amount);
}
