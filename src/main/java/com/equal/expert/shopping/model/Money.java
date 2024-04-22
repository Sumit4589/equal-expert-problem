package com.equal.expert.shopping.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class Money {

    public static final Money ZERO = Money.of(BigDecimal.ZERO);
    public static final int ROUND_UP_SCALE = 2;
    public static final String MONEY_NOT_NULL_MSG = "money must not be null";
    public static final String AMOUNT_NOT_NULL_MSG = "amount must not be null";
    private BigDecimal amount;

    public static Money of(double amount) {
        return new Money(amount);
    }

    public static Money of(BigDecimal amount) {
        Objects.requireNonNull(amount, AMOUNT_NOT_NULL_MSG);
        return new Money(amount);
    }

    public static Money times(Money money, int times) {
        Objects.requireNonNull(money, MONEY_NOT_NULL_MSG);
        BigDecimal multipliedMoney = money.getAmount().multiply(BigDecimal.valueOf(times));
        return Money.of(multipliedMoney);
    }

    public static Money addMultipleMoney(Money... moneyArr) {
        Objects.requireNonNull(moneyArr, MONEY_NOT_NULL_MSG);

        return Arrays.stream(moneyArr)
                .reduce(Money.ZERO, (total, element) -> total.add(element));
    }

    public static Money multiply(Money base, Money multiplicant) {
        BigDecimal product = base.getAmount().multiply(multiplicant.getAmount());
        return Money.of(product);
    }

    public Money add(Money money) {
        Objects.requireNonNull(money, MONEY_NOT_NULL_MSG);
        BigDecimal addedAmount = amount.add(money.getAmount());
        return Money.of(addedAmount);
    }

    public Money(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return this.amount.setScale(ROUND_UP_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Money)) {
            return false;
        }
        BigDecimal otherAmount = ((Money) other).getAmount();
        return amount.compareTo((otherAmount)) == 0;
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}
