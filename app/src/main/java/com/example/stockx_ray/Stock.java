package com.example.stockx_ray;

public class Stock {
    double price;
    String symbol;
   // String name;
    double twoHundredAverage;
    double fiftyAverage;
    double fiftyTwoWeekLow;
    double fiftyTwoWeekHigh;

    public Stock(double price, String symbol, double twoHundredAverage, double fiftyAverage, double fiftyTwoWeekLow, double fiftyTwoWeekHigh) {
        this.price = price;
        this.symbol = symbol;
        this.twoHundredAverage = twoHundredAverage;
        this.fiftyAverage = fiftyAverage;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
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
}
