package com.equal.expert.shopping.model;

import com.equal.expert.shopping.exception.InvalidQuantityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchasedProductTest {

    private PurchasedProduct basePurchasedProduct;

    @BeforeEach
    public void before() {
        basePurchasedProduct = PurchasedProduct.builder()
                .name("cheerios")
                .quantity(1)
                .build();
    }

    @Test
    void addQuantity_validQunatity_success() {
        basePurchasedProduct.addQuantity(2);
        assertEquals(3, basePurchasedProduct.getQuantity());
    }

    @Test
    void addQuantity_InvalidQuantity_failure() {
        InvalidQuantityException exception = assertThrows(InvalidQuantityException.class, () -> basePurchasedProduct.addQuantity(-1));
        assertEquals("Invalid quantity provided", exception.getMessage());
    }
}