package com.equal.expert.shopping.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void createMoneyFromDouble_roundOff_success() {
        Money money1 = Money.of(11.1121);
        BigDecimal roundUpToLowerFraction = BigDecimal.valueOf(11.11);
        Money money2 = Money.of(12.1156);
        BigDecimal roundUpToUpperFraction = BigDecimal.valueOf(12.12);
        assertAll(
                () -> assertEquals(roundUpToLowerFraction.toString(), money1.toString()),
                () -> assertEquals(roundUpToUpperFraction.toString(), money2.toString())
        );
    }

    @Test
    void createMoneyFromBigDecimal_roundOff_success() {
        Money money1 = Money.of(BigDecimal.valueOf(11.1123456));
        BigDecimal roundUpToLowerFraction = BigDecimal.valueOf(11.11);
        Money money2 = Money.of(12.1156789);
        BigDecimal roundUpToUpperFraction = BigDecimal.valueOf(12.12);
        assertAll(
                () -> assertEquals(roundUpToLowerFraction.toString(), money1.toString()),
                () -> assertEquals(roundUpToUpperFraction.toString(), money2.toString())
        );
    }

    @Test
    void createMoneyFromBigDecimal_NullInput_failure() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> Money.of(null));
        assertEquals(Money.AMOUNT_NOT_NULL_MSG, exception.getMessage());
    }


    @Test
    void times_NullInput_failure() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> Money.times(null, 2));
        assertEquals(Money.MONEY_NOT_NULL_MSG, exception.getMessage());
    }

    @Test
    void times_MultipleMoney_success() {
        assertEquals(Money.of(22.24), Money.times(Money.of(11.12), 2));
    }

    @Test
    void addMultipleMoney_success() {
        Money money1 = Money.of(11.11);
        Money money2 = Money.of(0.50);
        assertEquals(Money.of(11.61), Money.addMultipleMoney(money1, money2));
        assertEquals(Money.of(11.11), money1);
        assertEquals(Money.of(0.50), money2);
    }

    @Test
    void multiply_success() {
        Money money1 = Money.of(11.11);
        Money money2 = Money.of(0.50);
        assertEquals(Money.of(5.555), Money.multiply(money1, money2));
        assertEquals(Money.of(11.11), money1);
        assertEquals(Money.of(0.50), money2);
    }

    @Test
    void add_success() {
        Money money1 = Money.of(11.11);
        Money money2 = Money.of(0.50);
        assertEquals(Money.of(11.61), money1.add(money2));
        assertEquals(Money.of(11.11), money1);
        assertEquals(Money.of(0.50), money2);
    }
}