package com.equal.expert.shopping.model;

import com.equal.expert.shopping.exception.InvalidQuantityException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchasedProduct {

    private String name;
    private int quantity;

    public void addQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = this.quantity + quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity provided");
        }
    }

}

