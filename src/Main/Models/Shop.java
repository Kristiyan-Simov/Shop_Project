package Main.Models;

import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;
import Main.Models.Contracts.IReceipt;
import Main.Models.Contracts.IShop;

import java.io.Console;
import java.lang.constant.Constable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Shop(double _percentageAddedEdible, double _percentageAddedInedible,
                double _percentageRemovedNearExpiry, int _minimumDaysUntilExpiry){
        this.cashiers = new ArrayList<>();
        this.deliveredProducts = new ArrayList<IProduct>();
        this.soldProducts = new ArrayList<IProduct>();
        this.products = new ArrayList<IProduct>();
        this.receipts = new ArrayList<IReceipt>();
        this.percentageAddedEdible = _percentageAddedEdible;
        this.percentageAddedInedible = _percentageAddedInedible;
        this.percentageRemovedNearExpiry = _percentageRemovedNearExpiry;
        this.minimumDaysUntilExpiry = _minimumDaysUntilExpiry;
    }

    @Override
    public double getEdibleAdditionalPrice() {
        return this.percentageAddedEdible;
    }

    @Override
    public double getInedibleAdditionalPrice() {
        return this.percentageAddedInedible;
    }

    @Override
    public double getExpirationDiscount() {
        return this.percentageRemovedNearExpiry;
    }

    @Override
    public int getMinDaysForDiscount() {
        return this.minimumDaysUntilExpiry;
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
    public void deliverProducts(String _name, double _deliveryPrice, LocalDate _expirationDate, boolean _edible, int _amount) {
        IProduct product = new Product(this.products.size(), _name, _deliveryPrice, _expirationDate, _edible,
                _edible ? percentageAddedEdible : percentageAddedInedible, percentageRemovedNearExpiry, minimumDaysUntilExpiry, _amount);

        if (this.products.isEmpty()){
            this.products.add(product);
            this.deliveredProducts.add(product);
        }
        else{
            for (IProduct p : this.products){
                if (p.getName() == product.getName()){
                    p.deliverProducts(product.getAmount());
                    this.deliveredProducts.add(product);
                    return;
                }
            }

            this.products.add(product);
            this.deliveredProducts.add(product);
        }
    }

    @Override
    public ICashier hireCashier(ICashier cashier) {
        for (ICashier iCashier : this.cashiers){
            if (iCashier.getID() == cashier.getID()){
                throw new IllegalArgumentException("Cashier Already Hired!");
            }
        }

        this.cashiers.add(cashier);
        return cashier;
    }

    @Override
    public ICashier fireCasher(String name) {
        for (int i = 0; i < this.cashiers.size(); i++){
            if (this.cashiers.get(i).getName().equals(name)){
                ICashier firedCashier = this.cashiers.get(i);
                this.cashiers.remove(this.cashiers.get(i));
                return firedCashier;
            }
        }

        return null;
    }

    @Override
    public IReceipt sellProducts(HashMap<String, Integer> _products, int storeLine, double clientMoney) {
        ArrayList<IProduct> productsSoldToCustomer = new ArrayList<>();
        double endPrice = 0;

        for (IProduct product : this.products){
            try {
                if (_products.containsKey(product.getName())){
                    product.sell(_products.get(product.getName()));
                    endPrice += product.calculatePrice() * _products.get(product.getName());

                    IProduct temp = new Product(product.getID(), product.getName(), product.getDeliveryPrice(), product.getExpirationDate(),
                            product.getEdible(), (product.getEdible()?(this.getEdibleAdditionalPrice()):(this.getInedibleAdditionalPrice())),
                            this.percentageRemovedNearExpiry, this.getMinDaysForDiscount(), _products.get(product.getName()));

                    productsSoldToCustomer.add(temp);
                    this.soldProducts.add(temp);
                }
            }catch (Exception e){
                System.out.println(product.getName() + " - " + e.toString());
            }
        }

        if (clientMoney >= endPrice){
            System.out.println("Change - " + String.format( "%.2f", (clientMoney - endPrice)) + "\n----\n");
        }
        else {
            throw new IllegalArgumentException("Not enough money!");
        }

        IReceipt receipt = ReceiptHandler.createReceipt(this.cashiers.get(storeLine - 1), LocalDateTime.now(), productsSoldToCustomer, endPrice);
        this.receipts.add(receipt);

        return receipt;
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
            earning += product.calculatePrice() * product.getAmount();
        }

        return earning;
    }

    @Override
    public double calculateTurnaroundRate() {
        return this.calculateProductSoldEarnings() - (this.calculateEmployeeSalarySpending() + calculateProductDeliverySpending());
    }

    @Override
    public void saveReceipts() {
        for (IReceipt receipt : this.receipts){
            ReceiptHandler.saveReceipt(receipt);
        }
    }
}
