package com.billy.billyServices.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyMapping {
    private Map<Map<Currency, Currency>, BigDecimal> currencyRations;

    public CurrencyMapping() {
        this.currencyRations = new HashMap<>();
    }

    public void addRatio(final Currency currency1,
                         final Currency currency2,
                         final BigDecimal ratio) {

        final Map<Currency, Currency> mapping = new HashMap<>();
        mapping.put(currency1, currency2);
        currencyRations.put(mapping, ratio);
    }

    public BigDecimal getRatio(final Currency currency1,
                         final Currency currency2) {
        final Map<Currency, Currency> currencies = new HashMap<>();

        currencies.put(currency1, currency2);
        return currencyRations.get(currencies);
    }
}
