package Main.Models;

import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;
import Main.Models.Contracts.IReceipt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Receipt implements IReceipt {
    private int id;
    private ICashier cashier;
    private LocalDateTime creationTime;
    private ArrayList<IProduct> products;
    private double price;

    public Receipt(int _id, ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price){
        this.id = _id;
        this.cashier = _cashier;
        this.products = _products;
        this.creationTime = _creationTime;
        this.price = _price;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public ICashier getCashier() {
        return this.cashier;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    @Override
    public ArrayList<IProduct> getProducts() {
        return this.products;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("Receipt: ").append(this.id).append('\n');
        sb.append("Cashier: ").append(this.cashier.getName()).append('\n');
        sb.append("Date: ").append(this.creationTime.format(formatter)).append('\n');
        sb.append("Products:\n");
        for (IProduct product : this.products){
            sb.append(product.toString()).append('\n');
        }
        sb.append("------------------------------------------\n");
        sb.append("Total Price: ").append(this.price);

        return sb.toString().trim();
    }
}
