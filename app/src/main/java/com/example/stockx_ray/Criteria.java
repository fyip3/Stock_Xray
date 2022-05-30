package com.example.stockx_ray;

public class Criteria {
    double price;
    double twoHundredAverage;
    double fiftyAverage;
    double fiftyTwoWeekLow;
    double fiftyTwoWeekHigh;
    double peRatio;
    double threeAvgVolume;
    double tenAvgVolume;

    public Criteria(double price, double twoHundredAverage, double fiftyAverage, double fiftyTwoWeekLow, double fiftyTwoWeekHigh, double peRatio, double threeAvgVolume, double tenAvgVolume) {
        this.price = price;
        this.twoHundredAverage = twoHundredAverage;
        this.fiftyAverage = fiftyAverage;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
        this.peRatio = peRatio;
        this.threeAvgVolume = threeAvgVolume;
        this.tenAvgVolume = tenAvgVolume;
    }

    public Criteria(double price, double twoHundredAverage, double fiftyAverage, double fiftyTwoWeekLow, double fiftyTwoWeekHigh) {
        this.price = price;
        this.twoHundredAverage = twoHundredAverage;
        this.fiftyAverage = fiftyAverage;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public Criteria() {
    }

    public boolean baseCheck(double pricee, double twoo, double fiftyy, double loww, double highh) {
        if (pricee > twoo) {
            if (fiftyy > twoo) {
                if (pricee > fiftyy) {
                    if (pricee > (loww * 30 / 100) + loww) {
                        if (pricee > (highh - (highh * 25 / 100)) || pricee < (highh + (highh * 25 / 100))) {
                            return true;
                        }
                        else {
                            System.out.println("Price is not within 25% of 52-week high");
                        }
                    } else {
                        System.out.println("Price is not 30% above 52-week low");
                    }
                } else {
                    System.out.println("Price is not higher than 50-day average");
                }
            } else {
                System.out.println("50-day average is not higher than 200-day average");
            }
        } else {
            System.out.println("Price is not higher than 200-day average");
        }
        return false;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTwoHundredAverage() {
        return twoHundredAverage;
    }

    public void setTwoHundredAverage(double twoHundredAverage) {
        this.twoHundredAverage = twoHundredAverage;
    }

    public double getFiftyAverage() {
        return fiftyAverage;
    }

    public void setFiftyAverage(double fiftyAverage) {
        this.fiftyAverage = fiftyAverage;
    }

    public double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public void setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    public double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public double getThreeAvgVolume() {
        return threeAvgVolume;
    }

    public void setThreeAvgVolume(double threeAvgVolume) {
        this.threeAvgVolume = threeAvgVolume;
    }

    public double getTenAvgVolume() {
        return tenAvgVolume;
    }

    public void setTenAvgVolume(double tenAvgVolume) {
        this.tenAvgVolume = tenAvgVolume;
    }
}
