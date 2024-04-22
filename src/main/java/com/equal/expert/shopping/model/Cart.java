package com.equal.expert.shopping.model;

import com.equal.expert.shopping.gateway.ProductGateway;
import com.equal.expert.shopping.util.TaxUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private UUID cartId = UUID.randomUUID();
    private Map<String, PurchasedProduct> purchasedProductsMap = new HashMap<>();
    private Money subTotal = Money.ZERO;
    private Money tax = Money.ZERO;
    private Money totalPayable = Money.ZERO;

    private ProductGateway productGateway = ProductGateway.getInstance();

    public void addProduct(String productName, int quantity) {
        Money productPrice = productGateway.getProductPrice(productName);
        updateCartDetails(productName, quantity, productPrice);
    }

    private void updateCartDetails(String productName, int quantity, Money productPrice) {
        updateProductMap(productName, quantity);
        subTotal = subTotal.add(Money.times(productPrice, quantity));
        tax = TaxUtil.calculateTax(subTotal);
        totalPayable = Money.addMultipleMoney(subTotal, tax);
    }

    private void updateProductMap(String productName, int quantity) {
        PurchasedProduct purchasedProduct = purchasedProductsMap.get(productName);
        if (productNotPurchased(purchasedProduct)) {
            purchasedProduct = PurchasedProduct.builder()
                    .name(productName)
                    .quantity(quantity)
                    .build();
        } else {
            purchasedProduct.addQuantity(quantity);
        }
        purchasedProductsMap.put(productName, purchasedProduct);

    }

    private boolean productNotPurchased(PurchasedProduct purchasedProduct) {
        return purchasedProduct == null;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public Money getTax() {
        return tax;
    }

    public Money getTotalPayable() {
        return totalPayable;
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchasedProductsMap.values().stream()
                .sorted(Comparator.comparing(PurchasedProduct::getName))
                .collect(Collectors.toList());
    }
}
