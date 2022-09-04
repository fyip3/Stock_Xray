package com.example.stockx_ray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private TextView emial;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        one = findViewById(R.id.s);
        two = findViewById(R.id.ss);
        three = findViewById(R.id.sss);
        four = findViewById(R.id.ssss);
        five = findViewById(R.id.sssss);
        emial = findViewById(R.id.emial);
        mAuth = FirebaseAuth.getInstance();
        emial.setText("Welcome, "+mAuth.getCurrentUser().getEmail());

    }


    public void goHistory(View v) {
        Intent intent = new Intent(this, DateActivity.class);
        if(v.getId()==one.getId()) {
            intent.putExtra("criteria", "Criteria 1");
        }
        if(v.getId()==two.getId()) {
            intent.putExtra("criteria", "Criteria 2");
        }
        if(v.getId()==three.getId()) {
            intent.putExtra("criteria", "Criteria 3");
        }
        if(v.getId()==four.getId()) {
            intent.putExtra("criteria", "Criteria 4");
        }
        if(v.getId()==five.getId()) {
            intent.putExtra("criteria", "Criteria 5");
        }

        startActivity(intent);
    }
}
