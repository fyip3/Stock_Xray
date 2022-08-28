package com.example.stockx_ray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button rec;
    private Button two;
    private Button three;
    private Button four;
    private Button five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rec = findViewById(R.id.recommended);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);

    }
    public void signOut(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goCrit(View v) {
        Intent intent = new Intent(this, ViewCriteriaActivity.class);
        if(v.getId()==rec.getId()) {
            intent.putExtra("criteria", 0);
        }
        if(v.getId()==two.getId()) {
            intent.putExtra("criteria", 1);
        }
        if(v.getId()==three.getId()) {
            intent.putExtra("criteria", 2);
        }
        if(v.getId()==four.getId()) {
            intent.putExtra("criteria", 3);
        }
        if(v.getId()==five.getId()) {
            intent.putExtra("criteria", 4);
        }

        startActivity(intent);
    }
}