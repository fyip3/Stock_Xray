package com.example.stockx_ray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalysisActivity extends AppCompatActivity {

    ArrayList<Stock> stocks;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        stocks = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        Scanner input = null;
        try {
            input = new Scanner(new File("C:\\Users\\flemi\\Downloads\\Stock Reader\\src\\Symbols.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int begin = 264;
        for (int i = 0; i<begin-1;i++) {
            input.nextLine();
        }
        for (int i = begin; i < 12123; i++) {
            String symbo;
            String symbol;
            //set line number??
            symbo = input.nextLine();
            int comma = symbo.indexOf("|");
            symbol = symbo.substring(0, comma);
            if (symbol.length() < 5) {
                System.out.println(symbol);
                URL url = null;
                try {
                    url = new URL("https://finance.yahoo.com/quote/" + symbol + "/key-statistics?p=" + symbol);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                //    URL url2 = new URL("https://finance.yahoo.com/quote/" + symbol + "/chart?p=" + symbol + "#eyJpbnRlcnZhbCI6ImRheSIsInBlcmlvZGljaXR5IjoxLCJ0aW1lVW5pdCI6bnVsbCwiY2FuZGxlV2lkdGgiOjUuODgwODI5MDE1NTQ0MDQxLCJmbGlwcGVkIjpmYWxzZSwidm9sdW1lVW5kZXJsYXkiOnRydWUsImFkaiI6dHJ1ZSwiY3Jvc3NoYWlyIjp0cnVlLCJjaGFydFR5cGUiOiJsaW5lIiwiZXh0ZW5kZWQiOmZhbHNlLCJtYXJrZXRTZXNzaW9ucyI6e30sImFnZ3JlZ2F0aW9uVHlwZSI6Im9obGMiLCJjaGFydFNjYWxlIjoibGluZWFyIiwicGFuZWxzIjp7ImNoYXJ0Ijp7InBlcmNlbnQiOjEsImRpc3BsYXkiOiJESVMiLCJjaGFydE5hbWUiOiJjaGFydCIsImluZGV4IjowLCJ5QXhpcyI6eyJuYW1lIjoiY2hhcnQiLCJwb3NpdGlvbiI6bnVsbH0sInlheGlzTEhTIjpbXSwieWF4aXNSSFMiOlsiY2hhcnQiLCLigIx2b2wgdW5kcuKAjCJdfX0sInNldFNwYW4iOm51bGwsImxpbmVXaWR0aCI6Miwic3RyaXBlZEJhY2tncm91bmQiOnRydWUsImV2ZW50cyI6dHJ1ZSwiY29sb3IiOiIjMDA4MWYyIiwic3RyaXBlZEJhY2tncm91ZCI6dHJ1ZSwicmFuZ2UiOm51bGwsImV2ZW50TWFwIjp7ImNvcnBvcmF0ZSI6eyJkaXZzIjp0cnVlLCJzcGxpdHMiOnRydWV9LCJzaWdEZXYiOnt9fSwic3ltYm9scyI6W3sic3ltYm9sIjoiRElTIiwic3ltYm9sT2JqZWN0Ijp7InN5bWJvbCI6IkRJUyIsInF1b3RlVHlwZSI6IkVRVUlUWSIsImV4Y2hhbmdlVGltZVpvbmUiOiJBbWVyaWNhL05ld19Zb3JrIn0sInBlcmlvZGljaXR5IjoxLCJpbnRlcnZhbCI6ImRheSIsInRpbWVVbml0IjpudWxsLCJzZXRTcGFuIjpudWxsfV0sInN0dWRpZXMiOnsi4oCMdm9sIHVuZHLigIwiOnsidHlwZSI6InZvbCB1bmRyIiwiaW5wdXRzIjp7ImlkIjoi4oCMdm9sIHVuZHLigIwiLCJkaXNwbGF5Ijoi4oCMdm9sIHVuZHLigIwifSwib3V0cHV0cyI6eyJVcCBWb2x1bWUiOiIjMDBiMDYxIiwiRG93biBWb2x1bWUiOiIjZmYzMzNhIn0sInBhbmVsIjoiY2hhcnQiLCJwYXJhbWV0ZXJzIjp7IndpZHRoRmFjdG9yIjowLjQ1LCJjaGFydE5hbWUiOiJjaGFydCIsInBhbmVsTmFtZSI6ImNoYXJ0In19LCLigIxtYeKAjCAoNTAsQyxtYSwwKSI6eyJ0eXBlIjoibWEiLCJpbnB1dHMiOnsiUGVyaW9kIjo1MCwiRmllbGQiOiJDbG9zZSIsIlR5cGUiOiJzaW1wbGUiLCJPZmZzZXQiOjAsImlkIjoi4oCMbWHigIwgKDUwLEMsbWEsMCkiLCJkaXNwbGF5Ijoi4oCMbWHigIwgKDUwLEMsbWEsMCkifSwib3V0cHV0cyI6eyJNQSI6IiNmZjgwYzUifSwicGFuZWwiOiJjaGFydCIsInBhcmFtZXRlcnMiOnsiY2hhcnROYW1lIjoiY2hhcnQifX0sIuKAjG1h4oCMICgxNTAsQyxtYSwwKSI6eyJ0eXBlIjoibWEiLCJpbnB1dHMiOnsiUGVyaW9kIjoiMTUwIiwiRmllbGQiOiJDbG9zZSIsIlR5cGUiOiJzaW1wbGUiLCJPZmZzZXQiOjAsImlkIjoi4oCMbWHigIwgKDE1MCxDLG1hLDApIiwiZGlzcGxheSI6IuKAjG1h4oCMICgxNTAsQyxtYSwwKSJ9LCJvdXRwdXRzIjp7Ik1BIjoiI2FkNmVmZiJ9LCJwYW5lbCI6ImNoYXJ0IiwicGFyYW1ldGVycyI6eyJjaGFydE5hbWUiOiJjaGFydCJ9fSwi4oCMbWHigIwgKDIwMCxDLG1hLDApIjp7InR5cGUiOiJtYSIsImlucHV0cyI6eyJQZXJpb2QiOiIyMDAiLCJGaWVsZCI6IkNsb3NlIiwiVHlwZSI6InNpbXBsZSIsIk9mZnNldCI6MCwiaWQiOiLigIxtYeKAjCAoMjAwLEMsbWEsMCkiLCJkaXNwbGF5Ijoi4oCMbWHigIwgKDIwMCxDLG1hLDApIn0sIm91dHB1dHMiOnsiTUEiOiIjNzJkM2ZmIn0sInBhbmVsIjoiY2hhcnQiLCJwYXJhbWV0ZXJzIjp7ImNoYXJ0TmFtZSI6ImNoYXJ0In19fX0-");
                //    System.out.println("\nChart (pink = 50 day; purple = 150 day; blue = 200 day): \n" + url2);

                URLConnection con = null;
                try {
                    con = url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStreamReader inStream = null;
                try {
                    inStream = new InputStreamReader(con.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BufferedReader buff = new BufferedReader(inStream);

                String line = "";
                String two = "not found";
                String fifty = "not found";
                String low = "not found";
                String high = "not found";
                String price = "not found";

                while (true) {
                    try {
                        if (!((line = buff.readLine()) != null)) break;
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
                    }

                    if (line.contains("\"fiftyDayAverage\"")) {
                        int target = line.indexOf("\"fiftyDayAverage\"");
                        int deci = line.indexOf(",", target);
                        int start = deci;
                        while (line.charAt(start) != ':') {
                            start--;
                        }
                        fifty = line.substring(start + 1, deci);
                    }

                    if (line.contains("\"currentPrice\"")) {
                        int target = line.indexOf("\"currentPrice\"");
                        int deci = line.indexOf(",", target);
                        int start = deci;
                        while (line.charAt(start) != ':') {
                            start--;
                        }
                        price = line.substring(start + 1, deci);
                    }

                    if (line.contains("\"fiftyTwoWeekHigh\"")) {
                        int target = line.indexOf("\"fiftyTwoWeekHigh\"");
                        int deci = line.indexOf(",", target);
                        int start = deci;
                        while (line.charAt(start) != ':') {
                            start--;
                        }
                        high = line.substring(start + 1, deci);
                    }

                    if (line.contains("\"fiftyTwoWeekLow\"")) {
                        int target = line.indexOf("\"fiftyTwoWeekLow\"");
                        int deci = line.indexOf(",", target);
                        int start = deci;
                        while (line.charAt(start) != ':') {
                            start--;
                        }
                        low = line.substring(start + 1, deci);

                    }
                }

                if (price == "not found" || two == "not found" || fifty == "not found" || low == "not found" || high == "not found")
                    continue;

//                System.out.println("Current price: " + price);
//                System.out.println("200-day average: " + two);
//                System.out.println("50-day average: " + fifty);
//                System.out.println("52 week low: " + low);
//                System.out.println("52 week high: " + high);

                double pricee = Double.parseDouble(price);
                double twoo = Double.parseDouble(two);
                double fiftyy = Double.parseDouble(fifty);
                double loww = Double.parseDouble(low);
                double highh = Double.parseDouble(high);

                if (pricee > twoo) {
                    if (fiftyy > twoo) {
                        if (pricee > fiftyy) {
                            if (pricee > (loww * 30 / 100) + loww) {
                                if (pricee > (highh - (highh * 25 / 100)) || pricee < (highh + (highh * 25 / 100))) {
                                    Stock stock = new Stock(pricee, symbol, twoo, fiftyy, loww, highh);
                                    stocks.add(stock);
                                    firestore.collection("stocks").document(stock.getSymbol()).set(stock);
                                    System.out.println("\nVerdict: Cashmoney69");
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
                System.out.println("\n");
                System.out.println(i+"\n");
            }
        }

    }
}


