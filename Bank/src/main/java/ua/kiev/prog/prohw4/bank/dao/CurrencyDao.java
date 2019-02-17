package ua.kiev.prog.prohw4.bank.dao;

import ua.kiev.prog.prohw4.bank.Currency;
import ua.kiev.prog.prohw4.bank.entity.CurrencyRate;

import java.util.List;

public interface CurrencyDao {
    List<CurrencyRate> getRates();
    Integer getRate(Currency currency);

    void setRate(CurrencyRate rate);
}
