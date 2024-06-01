import Interfaces.ICashier;
import Interfaces.IProduct;
import Interfaces.IReceipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReceiptHandler {
    private static int createdReceipts;

    private ReceiptHandler() {
        createdReceipts = 0;
    }

    public static IReceipt createReceipt(ICashier _cashier, LocalDateTime _creationTime, ArrayList<IProduct> _products, double _price) {
        createdReceipts += 1;
        return new Receipt(createdReceipts, _cashier, _creationTime, _products, _price);
    }

    public static String saveReceipt(Receipt receipt) {
        return null;
    }

    //TODO
    // - Add Unit Tests
    // - Add File Writing Functionality
}
