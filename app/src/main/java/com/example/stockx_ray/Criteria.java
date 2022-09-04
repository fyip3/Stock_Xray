package com.example.stockx_ray;

import android.content.Context;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Criteria implements Serializable {
    double price;
    double profitMargin;
    double fiftyChange;
    double insider;
    double institution;
    double tpe;
    double fpe;
    String name;
    boolean ma;
    String req;
    boolean var;
 //   ArrayList<Stock> stocks;
    private FirebaseAuth mAuth;
    private Context con;
    private FirebaseFirestore firestore;

    public Criteria(double profitMargin, double fiftyChange, double insider, double institution, double tpe, double fpe) {
        this.profitMargin = profitMargin;
        this.fiftyChange = fiftyChange;
        this.insider = insider;
        this.institution = institution;
        this.tpe = tpe;
        this.fpe = fpe;
        var=true;
    }

    public Criteria(String req, String nae) {
        ma=true;

        this.req = req;
        name = "Criteria 1";
    }

    public Criteria(String name) {
        this.name = name;
    }

    public Criteria() {
    }


    public void run(TextView res, Context co, Criteria crit) throws IOException {


        //stocks = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
            try  {
                InputStream is = co.getAssets().open("symbols");
                //Scanner input = null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

//        try {
//            input = new Scanner(new File("C:\\Users\\flemi\\Downloads\\Stock Reader\\src\\Symbols.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        for (int i = 0; i < begin - 1; i++) {
//            reader.nextLine();
//        }
                String lin;
//                    while ((lin = reader.readLine()) != null && count<begin) {
//                        reader.readLine();
//                        count++;
//                    }
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            boolean pass = false;
                            int begin = 1;
                            int count = 1;
                            int end = 12123;
                            Date date = new Date(); // current time from here
                            SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
                            for (int i = begin; i < end; i++) {
                                double pricee = 0;
                                double twoo = 0;
                                double fiftyy  = 0;
                                double loww  = 0;
                                double highh  = 0;
                                String symbo;
                                String symbol;
                                //set line number??
                                symbo = reader.readLine();
                                int comma = symbo.indexOf("|");
                                symbol = symbo.substring(0, comma);
                                if (symbol.length() < 5) {
                                    URL url = null;
                                    URLConnection con= null;
                                    InputStreamReader inStream = null;


                                    String line = "";
                                    if (ma == true) {
                                        try {
                                            url = new URL("https://finance.yahoo.com/quote/" + symbol + "/key-statistics?p=" + symbol);
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            con = url.openConnection();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            inStream = new InputStreamReader(con.getInputStream());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        int bum = 0;
                                        BufferedReader buff = new BufferedReader(inStream);

                                        String two = "not found";
                                        String fifty = "not found";
                                        String low = "not found";
                                        String high = "not found";
                                        String price = "not found";

                                        while (true) {
                                            try {
                                                if (bum==5 || (line = buff.readLine()) == null) break;
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            if (line.contains("\"twoHundredDayAverage\"")) {
                                                int target = line.indexOf("\"twoHundredDayAverage\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                two = line.substring(start + 1, deci);
                                                bum++;
                                            }

                                            if (line.contains("\"fiftyDayAverage\"")) {
                                                int target = line.indexOf("\"fiftyDayAverage\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                fifty = line.substring(start + 1, deci);
                                                bum++;
                                            }

                                            if (line.contains("\"currentPrice\"")) {
                                                int target = line.indexOf("\"currentPrice\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                price = line.substring(start + 1, deci);
                                                bum++;
                                            }

                                            if (line.contains("\"fiftyTwoWeekHigh\"")) {
                                                int target = line.indexOf("\"fiftyTwoWeekHigh\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                high = line.substring(start + 1, deci);
                                                bum++;
                                            }

                                            if (line.contains("\"fiftyTwoWeekLow\"")) {
                                                int target = line.indexOf("\"fiftyTwoWeekLow\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                low = line.substring(start + 1, deci);
                                                bum++;

                                            }
                                        }


                                        if(count%6==0) {
                                            res.setText("");
                                        }
                                        count++;
                                        res.append(symbol);
                                        res.append("\n");
                                        if (price == "not found" || two == "not found" || fifty == "not found" || low == "not found" || high == "not found")
                                            continue;

                                         pricee = Double.parseDouble(price);
                                         twoo = Double.parseDouble(two);
                                         fiftyy = Double.parseDouble(fifty);
                                         loww = Double.parseDouble(low);
                                         highh = Double.parseDouble(high);

                                        if (pricee > twoo) {
                                            if (fiftyy > twoo) {
                                                if (pricee > fiftyy) {
                                                    if (pricee > (loww * 30 / 100) + loww) {
                                                        if (pricee > (highh - (highh * 25 / 100)) || pricee < (highh + (highh * 25 / 100))) {
                                                            Stock stockx = new Stock(pricee, symbol, twoo, fiftyy, loww, highh);
                                                            //stocks.add(stock);
                                                            if(var==false) {
                                                                Stock ss = new Stock();
                                                                firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection(crit.getName()).document(sfd.format(date)).set(ss);

                                                                firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection(crit.getName()).document(sfd.format(date)).collection("symbols").document(stockx.getSymbol()).set(stockx);
                                                            }
                                                            res.append("MA Passed");
                                                            pass=true;
                                                        } else {
                                                            res.append("Price is not within 25% of 52-week high");
                                                        }
                                                    } else {
                                                        res.append("Price is not 30% above 52-week low");
                                                    }
                                                } else {
                                                    res.append("Price is not higher than 50-day average");
                                                }
                                            } else {
                                                res.append("50-day average is not higher than 200-day average");
                                            }
                                        } else {
                                            res.append("Price is not higher than 200-day average");
                                        }
                                        res.append("\n");
                                        if(var==false)
                                        res.append("\n");

                                    }
                                    if(var==true) {
                                        try {
                                            url = new URL("https://finance.yahoo.com/quote/" + symbol + "/key-statistics?p=" + symbol);
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            con = url.openConnection();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            inStream = new InputStreamReader(con.getInputStream());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        BufferedReader buff = new BufferedReader(inStream);

                                        int sum = 0;
                                        String xtpe = "not found";
                                        String xprofitMargin = "not found";
                                        String xfpe = "not found";
                                        String xinsider = "not found";
                                        String xinstitution = "not found";
                                        String xfiftyChange = "not found";

                                        while (true) {
                                            //  try {
                                                if (sum == 5 || (line = buff.readLine()) == null) break;
                                          //  } catch (IOException e) {
                                         //       e.printStackTrace();
                                         //   }
                                            if (line.contains("\"profitMargins\"")) {
                                                int target = line.indexOf("\"profitMargins\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                xprofitMargin = line.substring(start + 1, deci);
                                                sum++;
                                            }

                                            if (line.contains("\"trailingPE\"")) {
                                                int target = line.indexOf("\"trailingPE\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                xtpe = line.substring(start + 1, deci);
                                                sum++;
                                            }

                                            if (line.contains("\"forwardPE\"")) {
                                                int target = line.indexOf("\"forwardPE\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                xfpe = line.substring(start + 1, deci);

                                                sum++;
                                            }

                                            if (line.contains("\"heldPercentInsiders\"")) {
                                                int target = line.indexOf("\"heldPercentInsiders\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                xinsider = line.substring(start + 1, deci);
                                                sum++;
                                            }

                                            if (line.contains("\"heldPercentInstitutions\"")) {
                                                int target = line.indexOf("\"heldPercentInstitutions\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                xinstitution = line.substring(start + 1, deci);
                                                sum++;

                                            }

                                            if (line.contains("\"52WeekChange\"")) {
                                                int target = line.indexOf("\"52WeekChange\"");
                                                int deci = line.indexOf(",", target);
                                                int start = deci;
                                                while (line.charAt(start) != ':') {
                                                    start--;
                                                }
                                                xfiftyChange = line.substring(start + 1, deci);
                                                sum++;

                                            }
                                        }
                                        if (ma == false) {
                                            if (count % 8 == 0) {
                                                res.setText("");
                                            }
                                            count++;
                                            res.append(symbol);
                                            res.append("\n");
                                        }


                                        if (xprofitMargin == "not found" || xfpe == "not found" || xinstitution == "not found" || xinsider == "not found" || xfiftyChange == "not found") {
//                                            res.append(xfpe+"\n");
//                                            res.append(xprofitMargin+"\n");
//                                            res.append(xinsider+"\n");
//                                            res.append(xinstitution+"\n");
//                                            res.append(xfiftyChange+"\n");

                                            res.append("Insufficient Data \n \n");
                                            continue;
                                        }
                                        if (xprofitMargin.equals("{}") || xfpe.equals("{}") || xinstitution.equals("{}") || xinsider.equals("{}") || xfiftyChange.equals("{}")) {
//                                            res.append("Insufficient Data \n \n");
//                                            res.append(xfpe+"\n");
//                                            res.append(xprofitMargin+"\n");
//                                            res.append(xinsider+"\n");
//                                            res.append(xinstitution+"\n");
//                                            res.append(xfiftyChange+"\n");
                                            res.append("Insufficient Data \n \n");

                                            continue;
                                        }


                                        double xxprofitMargin = Double.parseDouble(xprofitMargin);
                                        double xxtpe;
                                        if(!xtpe.equals("{}")&& !xtpe.equals("not found")) {
                                            xxtpe = Double.parseDouble(xtpe);
                                        }
                                        else {
                                            xxtpe=0;
                                        }
                                        double xxfpe = Double.parseDouble(xfpe);
                                        double xxinstitution = Double.parseDouble(xinstitution);
                                        double xxinsider = Double.parseDouble(xinsider);
                                        double xxfiftyChange = Double.parseDouble(xfiftyChange);

                                        if (xxprofitMargin*100 > profitMargin) {
                                            if (xxtpe< tpe) {
                                                if (xxfpe > fpe) {
                                                    if (xxinstitution*100 > institution) {
                                                        if (xxinsider*100 > insider) {
                                                            if (xxfiftyChange*100 > fiftyChange) {
                                                                Stock stock = null;

                                                                if(ma==true) {
                                                                    if(pass==true) {
                                                                        stock = new Stock(pricee, symbol, twoo, fiftyy, loww, highh, xxprofitMargin, xxfiftyChange, xxinsider, xxinstitution, xxtpe, xxfpe);
                                                                        //firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection("criterias").document(crit.getName()).collection("stocks").document(stock.getSymbol()).set(stock);
                                                                        Stock ss = new Stock();
                                                                        firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection(crit.getName()).document(sfd.format(date)).set(ss);

                                                                        firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection(crit.getName()).document(sfd.format(date)).collection("symbols").document(stock.getSymbol()).set(stock);

                                                                        res.append("All Tests Passed");
                                                                    }
                                                                    else {
                                                                        res.append("Other Tests Passed");

                                                                    }
                                                                }
                                                                else {
                                                                    //stocks.add(stock);
                                                                     stock = new Stock(xxprofitMargin, xxfiftyChange, xxinsider, xxinstitution, xxtpe, xxfpe, symbol);
                                                                    Stock ss = new Stock();
                                                                    firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection(crit.getName()).document(sfd.format(date)).set(ss);
                                                                    firestore.collection("users").document(mAuth.getCurrentUser().getUid()).collection(crit.getName()).document(sfd.format(date)).collection("symbols").document(stock.getSymbol()).set(stock);
                                                                    res.append("All Tests Passed");
                                                                }
                                                            }
                                                            else {
                                                                res.append("Fifty Two Week Change: Test Failed");
                                                            }
                                                        } else {
                                                            res.append("Held by Insider: Test Failed");
                                                        }
                                                    } else {
                                                        res.append("Held by Institution: Test Failed");
                                                    }
                                                } else {
                                                    res.append("Forward P/E: Test Failed");
                                                }
                                            } else {
                                                res.append("Trailing P/E: Test Failed");
                                            }
                                        } else {
                                            res.append("Profit Margin: Test Failed");
                                        }
                                        res.append("\n");
                                        res.append("\n");
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public boolean isMa() {
        return ma;
    }

    public void setMa(boolean ma) {
        this.ma = ma;
    }

//    public ArrayList<Stock> getStocks() {
//        return stocks;
//    }

//    public void setStocks(ArrayList<Stock> stocks) {
//        this.stocks = stocks;
//    }



    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public Criteria (Context con) {
        this.con = con;
    }

    public boolean isVar() {
        return var;
    }

    public void setVar(boolean var) {
        this.var = var;
    }
}
