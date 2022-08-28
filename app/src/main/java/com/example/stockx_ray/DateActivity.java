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

public class DateActivity extends AppCompatActivity implements Adapter.ItemClickListener {

    private String no;
    RecyclerView rec;
    private FirebaseAuth mAuth;
    private ArrayList<String> dates;
    int amount;
    FirebaseFirestore db;
    Adapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        rec = findViewById(R.id.rec);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        dates = new ArrayList<>();

        Intent intent = getIntent();
        no = (String) intent.getSerializableExtra("criteria");

        db.collection("users").document(mAuth.getCurrentUser().getUid()).collection(no).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot ds : Objects.requireNonNull(task.getResult())) {
                    System.out.println("DWADWA");
                    dates.add(ds.getId());
                    amount++;

                }
                adap = new Adapter(dates, amount);
                rec.setLayoutManager(new LinearLayoutManager(this));
                adap.setClickListener(this);
                rec.setAdapter(adap);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), ViewHistoryActivity.class);
        intent.putExtra("date", dates.get(position));
        intent.putExtra("no",no);
        view.getContext().startActivity(intent);
    }
}