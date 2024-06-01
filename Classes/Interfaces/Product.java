package Interfaces;

import java.time.LocalDate;

public interface Product {
    public int getID();
    public String getName();
    public double getDeliveryPrice();
    public LocalDate getExpirationDate();
    public double getPrice();
}
