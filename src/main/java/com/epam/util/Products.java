package com.epam.util;

import lombok.Getter;
import org.joda.money.CurrencyUnit;

public class Products {
    @Getter
    private final static CurrencyUnit BYN_CURRENCY_UNIT = CurrencyUnit.of("BYN");
}
