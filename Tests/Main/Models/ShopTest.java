package Main.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Main.Common.Exceptions.DuplicateCashierException;
import Main.Common.Exceptions.EmptyCartCheckoutException;
import Main.Common.Exceptions.InsufficientFundsException;
import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;
import Main.Models.Contracts.IReceipt;
import org.junit.jupiter.api.*;

class ShopTest {
    Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop(5, 10, 5, 2);
    }

    @Test
    void deliverProductsAddsFirstProductCorrectly() {
        shop.deliverProducts("Potato", 3.50, LocalDate.now().plusDays(15), true, 5);

        assertEquals("Potato", shop.getProducts().get(0).getName());
    }

    @Test
    void deliverProductsAddsSecondProductCorrectly() {
        shop.deliverProducts("Potato", 3.50, LocalDate.now().plusDays(15), true, 5);
        shop.deliverProducts("Tomato", 4.50, LocalDate.now().plusDays(60), true, 16);

        assertEquals("Tomato", shop.getProducts().get(1).getName());
    }

    @Test
    void hireCashierAddsCashierCorrectly() {
        ICashier cashier = new Cashier("Pesho", 1, 600);
        shop.hireCashier(cashier);

        assertEquals(cashier, shop.getCashiers().get(0));
    }

    @Test
    void hireCashierThrowsWhenSameCashierHiredTwice(){
        assertThrows(DuplicateCashierException.class, ()->{
            ICashier cashier = new Cashier("Pesho", 1, 600);
            shop.hireCashier(cashier);
            shop.hireCashier(cashier);
        });
    }

    @Test
    void fireCashierRemovesCashiers() {
        ICashier cashier = new Cashier("Pesho", 1, 600);
        shop.hireCashier(cashier);
        shop.fireCasher("Pesho");
        assertEquals(0, shop.getCashiers().size());
    }

    @Test
    void fireCashierFiresWhenMoreThanOneCashiersExist(){
        ICashier cashier1 = new Cashier("Pesho", 1, 600);
        ICashier cashier2 = new Cashier("Ivan", 2, 1000);
        shop.hireCashier(cashier1);
        shop.hireCashier(cashier2);
        ICashier fired = shop.fireCasher("Pesho");
        assertEquals(1, shop.getCashiers().size());
    }

    @Test
    void sellProductsWorksCorrectly() {
        shop.deliverProducts("Potato", 3.50, LocalDate.now().plusDays(15), true, 5);
        shop.deliverProducts("Tomato", 4.50, LocalDate.now().plusDays(60), true, 16);
        ICashier cashier = new Cashier("Pesho", 16, 600);
        shop.hireCashier(cashier);


        HashMap<String, Integer> products = new HashMap<>();
        products.put("Tomato", 2);
        products.put("Potato", 3);

        IReceipt receipt = shop.sellProducts(products, 1, 1000);
        assertEquals("Pesho", receipt.getCashier().getName());
    }

    @Test
    void sellProductsThrowsExceptionOnInsufficientFunds(){
        assertThrows(InsufficientFundsException.class, () ->{
            shop.deliverProducts("Potato", 3.50, LocalDate.now().plusDays(15), true, 5);
            shop.deliverProducts("Tomato", 4.50, LocalDate.now().plusDays(60), true, 16);
            ICashier cashier = new Cashier("Pesho", 16, 600);
            shop.hireCashier(cashier);


            HashMap<String, Integer> products = new HashMap<>();
            products.put("Tomato", 2);
            products.put("Potato", 3);
            IReceipt receipt = shop.sellProducts(products, 1, 0);
        });
    }

    @Test
    void sellProductsThrowsExceptionOnEmptyCart(){
        assertThrows(EmptyCartCheckoutException.class, () ->{
            shop.deliverProducts("Potato", 3.50, LocalDate.now().plusDays(15), true, 5);
            shop.deliverProducts("Tomato", 4.50, LocalDate.now().plusDays(60), true, 16);
            ICashier cashier = new Cashier("Pesho", 16, 600);
            shop.hireCashier(cashier);


            HashMap<String, Integer> products = new HashMap<>();
            IReceipt receipt = shop.sellProducts(products, 1, 1000);
        });
    }
}