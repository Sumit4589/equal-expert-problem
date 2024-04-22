package com.equal.expert.shopping.gateway;

import com.equal.expert.shopping.client.ProductClient;
import com.equal.expert.shopping.exception.ProductNotFoundException;
import com.equal.expert.shopping.model.Money;
import com.equal.expert.shopping.model.Offer;
import com.equal.expert.shopping.model.dto.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.equal.expert.shopping.gateway.ProductGateway.PRODUCT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductGatewayTest {

    @InjectMocks
    private ProductGateway productGateway;

    @Mock
    private ProductClient productClient;

    @Test
    void getProductPrice_productFound_success() {
        String validProduct = "cheerios";
        Product product = Product.builder().title(validProduct).price(11.11).build();
        Mockito.when(productClient.getProduct(validProduct)).thenReturn(Optional.of(product));

        assertEquals(Money.of(11.11), productGateway.getProductPrice(validProduct));
    }

    @Test
    void getProductPrice_productNotFound_failure() {
        String invalidProduct = "invalid";
        Mockito.when(productClient.getProduct(invalidProduct)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productGateway.getProductPrice(invalidProduct));

        assertEquals(PRODUCT_NOT_FOUND.formatted(invalidProduct), exception.getMessage());
    }

    @Test
    void getProductOffer_BuyOneGetOne_success(){
        String productName = "cheerios";
        Offer offer = productGateway.getProductOffer(productName);
    }
}