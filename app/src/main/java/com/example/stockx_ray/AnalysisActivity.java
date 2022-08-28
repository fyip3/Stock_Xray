package com.example.stockx_ray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    Criteria criteria;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        res = findViewById(R.id.res);
        stocks = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        criteria = (Criteria) intent.getSerializableExtra("criteria");
        try {
            criteria.run(res, this, criteria);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }


}


