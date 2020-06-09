package com.billy.billyServices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class for Monetary amount
 */
public class MonetaryAmount implements Serializable {
    private static final String EMPTY_SPACE = " ";

    private final BigDecimal amount;
    private final Currency currency;

    /**
     * Instantiates a new Monetary amount.
     *
     * @param amount   the amount
     * @param currency the currency
     */
    public MonetaryAmount(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Override equals
     *
     * @param o {@link Object} the object
     * @return {@link boolean} the boolean representing equality of {link Object} o with this
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof MonetaryAmount)) return false;

        final MonetaryAmount monetaryAmount = (MonetaryAmount) o;

        if (!(amount.equals(monetaryAmount.amount))) return false;
        if (!(currency.equals(monetaryAmount.currency))) return false;

        return true;
    }

    /**
     * Override hashCode
     *
     * @return {@link int} the int hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + amount.hashCode();
        result = 31 * result + currency.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return getAmount() + " " + getCurrency();
    }

    /**
     * From string monetary amount.
     *
     * @param s the s
     * @return the monetary amount
     */
    public static MonetaryAmount fromString(final String s) {

        String[] split = s.split(EMPTY_SPACE);
        return new MonetaryAmount(
                new BigDecimal(split[0]),
                Currency.getInstance(split[1])
        );
    }
}
