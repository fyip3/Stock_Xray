package com.example.stockx_ray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void login(View v) {
        String emaill = email.getText().toString();
        String pass = password.getText().toString();

        FirebaseUser mUser = mAuth.getCurrentUser();

    }

    public void signup(View v) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void updateUI(FirebaseUser user) {
        if(user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}