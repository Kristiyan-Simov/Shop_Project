package Interfaces;

import java.time.LocalDate;

public interface Product {
    public int getID();
    public String getName();
    public double getDeliveryPrice();
    public LocalDate getExpirationDate();
    public boolean getEdible();
    public int getAmountLeft();
    public double calculatePrice(double percentageAdded, double percentageRemoved, int daysTillExpiry);
}
