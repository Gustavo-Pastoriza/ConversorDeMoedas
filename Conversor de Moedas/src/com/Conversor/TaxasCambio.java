package com.Conversor;

public class TaxasCambio {
    private String base_code;
    private Cotacoes conversion_rates;

    public String getCodigoBase() {
        return base_code;
    }

    public Cotacoes getTaxas() {
        return conversion_rates;
    }

    @Override
    public String toString() {
        return "Código Base: " + base_code + ", Taxas: " + conversion_rates;
    }
}