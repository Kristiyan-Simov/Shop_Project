import Main.Models.Cashier;
import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;
import Main.Models.Shop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome, please register Shop...");
        System.out.println("Percentage Added For Edible Products: ");
        double ediblePercentageIncrease = in.nextDouble();
        System.out.println("Percentage Added For Inedible Products: ");
        double inediblePercentageIncrease = in.nextDouble();
        System.out.println("Percentage Removed Near Expiry: ");
        double expiryPercentageDecrease = in.nextDouble();
        System.out.println("Minimum Days Before Considered Near Expiry: ");
        int daysNearExpiryForDiscount = in.nextInt();

        Shop shop = new Shop(ediblePercentageIncrease, inediblePercentageIncrease, expiryPercentageDecrease, daysNearExpiryForDiscount);

        String line = in.nextLine();
        while (line != "stop") {

            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            try{
                switch (command){
                    case "AddProduct":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        shop.deliverProducts(tokens[1].toLowerCase(), Double.parseDouble(tokens[2]), LocalDate.parse(tokens[3], formatter), Boolean.parseBoolean(tokens[4]), Integer.parseInt(tokens[5]));

                        for (IProduct product : shop.getProducts()){
                            System.out.println(product.toString());
                        }
                        break;
                    case "HireCashier":
                        shop.hireCashier(new Cashier(tokens[1].toLowerCase(), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3])));
                        for (ICashier cashier : shop.getCashiers()){
                            System.out.println(cashier.getName());
                        }
                        break;
                    case "FireCashier":
                        ICashier cashier = shop.fireCasher(tokens[1].toLowerCase().trim());
                        System.out.println(cashier.getName());
                        break;
                    case "Buy":
                        HashMap<String, Integer> cart = new HashMap<String, Integer>();
                        int checkoutLine = Integer.parseInt(tokens[1]);

                        line = in.nextLine();

                        while (!line.equals("checkout")){
                            tokens = line.split("\\s+");
                            cart.put(tokens[0], Integer.parseInt(tokens[1]));
                            line = in.nextLine();
                        }

                        System.out.println(shop.sellProducts(cart, checkoutLine));
                        break;
                    case "SaveReceipts":
                        shop.saveReceipts();
                        break;
                    case "CalculateSalaries":
                        System.out.println(shop.calculateEmployeeSalarySpending());
                        break;
                    case "CalculateDeliverySpending":
                        System.out.println(shop.calculateProductDeliverySpending());
                        break;
                    case "CalculateTurnaroundRate":
                        System.out.println(shop.calculateTurnaroundRate());
                        break;
                    case "CalculateEarnings":
                        System.out.println(shop.calculateProductSoldEarnings());
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Uh Oh, Sinky!");
                throw e;
            }

            line = in.nextLine();
        }
    }
}