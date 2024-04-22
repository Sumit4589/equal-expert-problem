package com.equal.expert.shopping.model;

import com.equal.expert.shopping.gateway.ProductGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartTest {
    @InjectMocks
    private Cart cart;

    @Mock
    private ProductGateway productGateway;

    @Test
    void addProduct_addNewProductInCart_success() {
        String productName = "cheerios";
        when(productGateway.getProductPrice(productName)).thenReturn(Money.of(10.10));

        cart.addProduct(productName, 1);

        assertEquals(Money.of(10.10), cart.getSubTotal());
        assertEquals(Money.of(1.2625), cart.getTax());
        assertEquals(Money.of(11.3625), cart.getTotalPayable());

        assertEquals(1, cart.getPurchasedProducts().size());
    }

    @Test
    void addProduct_ExistingNewProductInCart_success() {
        String productName = "cheerios";
        when(productGateway.getProductPrice(productName)).thenReturn(Money.of(10.10));

        cart.addProduct(productName, 1);
        cart.addProduct(productName, 1);
        int productQuantity = cart.getPurchasedProducts().get(0).getQuantity();

        assertEquals(Money.of(20.20), cart.getSubTotal());
        assertEquals(Money.of(2.525), cart.getTax());
        assertEquals(Money.of(22.725), cart.getTotalPayable());

        assertEquals(1, cart.getPurchasedProducts().size());
        assertEquals(2, productQuantity);
    }

    @Test
    void addProduct_multipleProductInCart_success() {
        String productName1 = "cheerios";
        String productName2 = "cornflakes";
        when(productGateway.getProductPrice(productName1)).thenReturn(Money.of(10.10));
        when(productGateway.getProductPrice(productName2)).thenReturn(Money.of(11.11));

        cart.addProduct(productName1, 1);
        cart.addProduct(productName1, 1);
        cart.addProduct(productName2, 1);

        PurchasedProduct product1 = cart.getPurchasedProducts().get(0);
        PurchasedProduct product2 = cart.getPurchasedProducts().get(1);

        assertEquals(Money.of(31.31), cart.getSubTotal());
        assertEquals(Money.of(3.91375), cart.getTax());
        assertEquals(Money.of(35.22375), cart.getTotalPayable());

        assertEquals(2, cart.getPurchasedProducts().size());
        assertEquals(2, product1.getQuantity());
        assertEquals(productName1, product1.getName());
        assertEquals(1, product2.getQuantity());
        assertEquals(productName2, product2.getName());
    }
}