package com.billy.billyServices.service;

import java.math.BigDecimal;
import java.util.Currency;

public interface MonetaryService {

    void refreshRatio();
    BigDecimal getRatio(final Currency currency1, final Currency currency2);
}
