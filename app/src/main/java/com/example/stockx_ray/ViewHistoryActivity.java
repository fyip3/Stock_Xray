package com.example.stockx_ray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ViewHistoryActivity extends AppCompatActivity{

    private String date;
    private String no;
    RecyclerView rec;
    private FirebaseAuth mAuth;
    private FirebaseUser userr;
    private ArrayList<String> symbols;
    int amount;
    FirebaseFirestore db;
    Adap adap;
    private Stock stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        rec = findViewById(R.id.recc);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        symbols = new ArrayList<>();


        Intent intent = getIntent();
        date = (String) intent.getSerializableExtra("date");
        no = (String) intent.getSerializableExtra("no");

        db.collection("users").document(mAuth.getCurrentUser().getUid()).collection(no).document(date).collection("symbols").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot ds : Objects.requireNonNull(task.getResult())) {
                    stock = ds.toObject(Stock.class);
                    amount++;
                    symbols.add(stock.getSymbol());

                }
                adap = new Adap(symbols, amount);
                rec.setLayoutManager(new LinearLayoutManager(this));
                rec.setAdapter(adap);
            }
        });
    }

}