package com.Conversor;

public class Cotacoes {
    private double BRL;
    private double EUR;
    private double CNY;
    private double USD;

    public double getBRL() {
        return BRL;
    }

    public double getEUR() {
        return EUR;
    }

    public double getCNY() {
        return CNY;
    }

    public double getUSD() {
        return USD;
    }

    @Override
    public String toString() {
        return "BRL: " + BRL + ", EUR: " + EUR + ", CNY: " + CNY + ", USD: " + USD;
    }
}