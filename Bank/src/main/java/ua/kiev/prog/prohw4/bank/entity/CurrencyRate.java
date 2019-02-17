package ua.kiev.prog.prohw4.bank.entity;

import ua.kiev.prog.prohw4.bank.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencyRates")
public class CurrencyRate {
    @Id
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private Integer rate;

    public CurrencyRate() {
    }

    public CurrencyRate(Currency currency, Integer rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Integer getRate() {
        return rate;
    }
}
