package com.equal.expert.shopping.util;

import com.equal.expert.shopping.model.Money;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaxUtil {

    private static final double TAX_RATE = 0.125;

    public static Money calculateTax(Money amount){
        return Money.multiply(amount, Money.of(TAX_RATE));
    }
}
