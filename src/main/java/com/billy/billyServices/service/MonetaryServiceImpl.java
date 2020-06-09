package com.billy.billyServices.service;

import com.billy.billyServices.model.CurrencyMapping;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class MonetaryServiceImpl implements MonetaryService {
    private CurrencyMapping currencyMapping;

    public MonetaryServiceImpl() {
        this.currencyMapping = new CurrencyMapping();
    }

    @Override
    public void refreshRatio() {
        currencyMapping.addRatio(
                Currency.getInstance("EUR"),
                Currency.getInstance("USD"),
                new BigDecimal(1.12)
        );
        currencyMapping.addRatio(
                Currency.getInstance("EUR"),
                Currency.getInstance("HRK"),
                new BigDecimal(0.18)
        );
    }

    @Override
    public BigDecimal getRatio(Currency currency1, Currency currency2) {

        return currencyMapping.getRatio(currency1, currency2);
    }
}
