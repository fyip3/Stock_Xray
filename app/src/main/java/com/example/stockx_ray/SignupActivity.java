package com.example.stockx_ray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText agee;
    private EditText expertisee;
    private EditText frequencyy;
    private EditText periodd;
    private EditText maxtimee;
    int age;
    String name;
    String expertise;
    int frequency;
    String period;
    int maxtime;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        String emaill = email.getText().toString();
        String pass = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(emaill, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("SIGN UP", "Successfully signed up");

                    FirebaseUser user = mAuth.getCurrentUser();
                }
                else {
                    Log.w("SIGN UP", "createUserWithEmail:failure", task.getException());
//                    Toast.makeText(this, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createUser() {

    }

    public void createCriteria() {
        if(age<18) {
            Criteria recommend = new Criteria();
        }
        else {
            if(period.equals("Long term")) {
                if (expertise.equals("Professional")) {

                    if (maxtime < 5) {

                    } else if (maxtime < 15) {

                    } else if (maxtime < 25) {

                    }

                }
                else if (expertise.equals("Casual")) {

                    if (maxtime < 5) {

                    } else if (maxtime < 15) {

                    } else if (maxtime < 25) {

                    }
                }
            }
            else {
                if (expertise.equals("Professional")) {

                    if (maxtime < 5) {

                    } else if (maxtime < 15) {

                    } else if (maxtime < 25) {

                    }

                }
                else if (expertise.equals("Casual")) {

                    if (maxtime < 5) {

                    } else if (maxtime < 15) {

                    } else if (maxtime < 25) {

                    }
                }
            }
            }
    }
}