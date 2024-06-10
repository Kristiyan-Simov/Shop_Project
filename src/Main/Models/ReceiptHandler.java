package Main.Models;

import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;
import Main.Models.Contracts.IReceipt;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;

public class ReceiptHandler {
    private static int createdReceipts;

    private ReceiptHandler() {
        createdReceipts = 0;
    }

    public static IReceipt createReceipt(ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price) {
        createdReceipts += 1;
        return new Receipt(createdReceipts, _cashier, _creationTime, _products, _price);
    }

    public static String saveReceipt(IReceipt receipt) {
        String fileName = "Receipts/" + "receipt_" + receipt.getID() + ".txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(receipt.toString());
        } catch (IOException e){
            System.err.println("Error saving the receipt to file: " + e.getMessage());
        }

        return receipt.toString();
    }

    public static String readReceiptFromFile(String serialNumber) {
        String fileName = "Receipts/" + "receipt_" + serialNumber + ".txt";
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line!= null){
                content.append(line).append("\n");
                line = reader.readLine();
            }
        }
        catch (Exception e){
            throw new UnsupportedOperationException(String.format("Error reading receipt from file: %s", e.getMessage()));
        }

        return content.toString();
    }
}
