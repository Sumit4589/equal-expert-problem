package com.equal.expert.shopping.util;

import com.equal.expert.shopping.model.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxUtilTest {

    @Test
    void calculateTax_success() {
        assertEquals(Money.of(0.125 * 10.10), TaxUtil.calculateTax(Money.of(10.10)));
    }
}