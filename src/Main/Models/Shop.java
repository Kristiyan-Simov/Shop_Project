package Main.Models;

import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;
import Main.Models.Contracts.IReceipt;
import Main.Models.Contracts.IShop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Shop implements IShop {
    private ArrayList<ICashier> cashiers;
    private ArrayList<IProduct> deliveredProducts;
    private ArrayList<IProduct> soldProducts;
    private ArrayList<IProduct> products;
    private ArrayList<IReceipt> receipts;
    private double percentageAddedEdible;
    private double percentageAddedInedible;
    private double percentageRemovedNearExpiry;
    private int minimumDaysUntilExpiry;

    public Shop(ArrayList<ICashier> _cashiers, ArrayList<IProduct> _deliveredProducts, double _percentageAddedEdible,
                double _percentageAddedInedible, double _percentageRemovedNearExpiry, int _minimumDaysUntilExpiry){
        this.cashiers = _cashiers;
        this.deliveredProducts = _deliveredProducts;
        this.soldProducts = new ArrayList<>();
        this.products = _deliveredProducts;
        this.receipts = new ArrayList<>();
        this.percentageAddedEdible = _percentageAddedEdible;
        this.percentageAddedInedible = _percentageAddedInedible;
        this.percentageRemovedNearExpiry = _percentageRemovedNearExpiry;
        this.minimumDaysUntilExpiry = _minimumDaysUntilExpiry;
    }

    @Override
    public ArrayList<ICashier> getCashiers() {
        return this.cashiers;
    }

    @Override
    public ArrayList<IProduct> getDeliveredProducts() {
        return this.deliveredProducts;
    }

    @Override
    public ArrayList<IProduct> getSoldProducts() {
        return this.soldProducts;
    }

    @Override
    public ArrayList<IProduct> getProducts() {
        return this.products;
    }

    @Override
    public ArrayList<IReceipt> getReciepts() {
        return this.receipts;
    }

    @Override
    public void deliverProducts(int _id, String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible, int _amount) {
        Product product = new Product(_id, _name, _deliveryPrice, _expirationDate, _edible,
                _edible ? percentageAddedEdible : percentageAddedInedible, percentageRemovedNearExpiry, minimumDaysUntilExpiry, _amount);
    }

    @Override
    public ICashier hireCashier(ICashier cashier) {
        this.cashiers.add(cashier);
        return cashier;
    }

    @Override
    public ICashier fireCasher(ICashier cashier) {
        this.cashiers.remove(cashier);
        return cashier;
    }

    @Override
    public double sellProducts(ArrayList<IProduct> products) {
        return 0;
    }

    @Override
    public IReceipt createReceipt(ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price) {
        int id = this.receipts.size();

        return new Receipt(id, _cashier, _creationTime, _products, _price);
    }

    @Override
    public double calculateEmployeeSalarySpending() {
        double spending = 0;

        for (ICashier cashier : this.cashiers){
            spending += cashier.getSalary();
        }

        return spending;
    }

    @Override
    public double calculateProductDeliverySpending() {
        double spending = 0;

        for (IProduct product : this.deliveredProducts){
            spending += product.getDeliveryPrice();
        }

        return spending;
    }

    @Override
    public double calculateProductSoldEarnings() {
        double earning = 0;

        for (IProduct product : this.soldProducts){
            earning += product.calculatePrice();
        }

        return earning;
    }

    @Override
    public double calculateTurnaroundRate() {
        return this.calculateProductSoldEarnings() - (this.calculateEmployeeSalarySpending() + calculateProductDeliverySpending());
    }

    //TODO
    // - Fix Main.Models.Receipt Creation
    // - Add Unit Tests
}
