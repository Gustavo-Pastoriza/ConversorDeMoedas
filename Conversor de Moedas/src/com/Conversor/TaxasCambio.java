package com.Conversor;

public class TaxasCambio {
    private String base_code;
    private Cotacoes rates;

    public String getBaseCode() {
        return base_code;
    }

    public Cotacoes getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "Base Code: " + base_code + ", Rates: " + rates;
    }
}