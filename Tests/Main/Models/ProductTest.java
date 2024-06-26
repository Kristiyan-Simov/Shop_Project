package Main.Models;

import Main.Common.Exceptions.ExpirationDateBeforeTodayException;
import Main.Common.Exceptions.InsufficientProductsException;
import Main.Common.Exceptions.NegativeAmountAddedException;
import Main.Common.Exceptions.NegativeProductsSoldException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void SetUp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDate = LocalDate.parse("15/12/2026", formatter);

        product = new Product(1, "Pie", 4, expirationDate, true,
                10, 10, 3, 10);
    }

    @Test
    void setExpirationDateChangesExpirationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate newExpirationDate = LocalDate.parse("30/01/2026", formatter);

        product.setExpirationDate(newExpirationDate);

        assertEquals(newExpirationDate, product.getExpirationDate());
    }

    @Test
    void setExpirationDateThrowsExceptionOnDateBeforeToday(){
        assertThrows(ExpirationDateBeforeTodayException.class, () ->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate newExpirationDate = LocalDate.parse("01/01/2003", formatter);

            product.setExpirationDate(newExpirationDate);
        });
    }

    @Test
    void deliverProductsAddsProducts() {
        product.deliverProducts(3);
        assertEquals(13, product.getAmount());
    }

    @Test
    void deliverProductsThrowsExceptionOnNegativeProducts(){
        assertThrows(NegativeAmountAddedException.class, () -> {
            product.deliverProducts(-2);
        });
    }

    @Test
    void sellRemovesProducts() {
        product.sell(5);
        assertEquals(5, product.getAmount());
    }

    @Test
    void sellThrowsExceptionOnInsufficientAmount(){
        assertThrows(InsufficientProductsException.class, () -> {
            product.sell(20);
        });
    }

    @Test
    void sellThrowsExceptionOnNegativeAmount(){
        assertThrows(NegativeProductsSoldException.class, () -> {
            product.sell(-10);
        });
    }
}