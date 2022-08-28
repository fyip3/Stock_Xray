package com.example.stockx_ray;

public class Stock {
    double price;
    String symbol;
   // String name;
    double twoHundredAverage;
    double fiftyAverage;
    double fiftyTwoWeekLow;
    double fiftyTwoWeekHigh;
    double profitMargin;
    double fiftyChange;
    double insider;
    double institution;
    double tpe;
    double fpe;

    public Stock(double price, String symbol, double twoHundredAverage, double fiftyAverage, double fiftyTwoWeekLow, double fiftyTwoWeekHigh) {
        this.price = price;
        this.symbol = symbol;
        this.twoHundredAverage = twoHundredAverage;
        this.fiftyAverage = fiftyAverage;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public Stock(double price, String symbol, double twoHundredAverage, double fiftyAverage, double fiftyTwoWeekLow, double fiftyTwoWeekHigh, double profitMargin, double fiftyChange, double insider, double institution, double tpe, double fpe) {
        this.price = price;
        this.symbol = symbol;
        this.twoHundredAverage = twoHundredAverage;
        this.fiftyAverage = fiftyAverage;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }


    public Stock(double profitMargin, double fiftyChange, double insider, double institution, double tpe, double fpe, String symbol) {
        this.profitMargin = profitMargin;
        this.fiftyChange = fiftyChange;
        this.insider = insider;
        this.institution = institution;
        this.tpe = tpe;
        this.fpe = fpe;
        this.symbol=symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }



    public double getTwoHundredAverage() {
        return twoHundredAverage;
    }

    public double getFiftyAverage() {
        return fiftyAverage;
    }

    public double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTwoHundredAverage(double twoHundredAverage) {
        this.twoHundredAverage = twoHundredAverage;
    }

    public void setFiftyAverage(double fiftyAverage) {
        this.fiftyAverage = fiftyAverage;
    }

    public void setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    public void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double getFiftyChange() {
        return fiftyChange;
    }

    public void setFiftyChange(double fiftyChange) {
        this.fiftyChange = fiftyChange;
    }

    public double getInsider() {
        return insider;
    }

    public void setInsider(double insider) {
        this.insider = insider;
    }

    public double getInstitution() {
        return institution;
    }

    public void setInstitution(double institution) {
        this.institution = institution;
    }

    public double getTpe() {
        return tpe;
    }

    public void setTpe(double tpe) {
        this.tpe = tpe;
    }

    public double getFpe() {
        return fpe;
    }

    public void setFpe(double fpe) {
        this.fpe = fpe;
    }
}
