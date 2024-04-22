package com.equal.expert.shopping.gateway;

import com.equal.expert.shopping.client.ProductClient;
import com.equal.expert.shopping.exception.ProductNotFoundException;
import com.equal.expert.shopping.model.Money;
import com.equal.expert.shopping.model.dto.Product;

public class ProductGateway {
    public static final String PRODUCT_NOT_FOUND = "product not found : %s";
    private static ProductGateway productGateway;

    public static synchronized ProductGateway getInstance() {
        if (productGateway == null) {
            productGateway = new ProductGateway();
        }
        return productGateway;
    }
    ProductClient productClient;
    public ProductGateway() {
        productClient = ProductClient.getInstance();
    }

    public Money getProductPrice(String productName) {
        Product product = productClient.getProduct(productName).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND.formatted(productName)));
        return Money.of(product.getPrice());
    }
}
