package com.example.stockx_ray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private EditText emaill;
    private EditText passwordd;
    private EditText agee;
    private Spinner expertisee;

    private Spinner termm; // long or short term investor
    String age;
    String emailll;
    String expertise;
    String pass;

    String term;
    String timelimit;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emaill = findViewById(R.id.emaill);
        passwordd = findViewById(R.id.passwordd);
        agee = findViewById(R.id.agee);
        expertisee = findViewById(R.id.expertisee);
        termm = findViewById(R.id.termm);

        ArrayList<String> exp = new ArrayList<String>() {
            {
                add("Professional");
                add("Casual");
            }
        };
        ArrayList<String> ter = new ArrayList<String>() {
            {
                add("Short Term");
                add("Long Term");
            }
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, exp);
        expertisee.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ter);
        termm.setAdapter(adapter2);



    }

    public void createUser(View v) {
        try {
            emailll = emaill.getText().toString();
            pass = passwordd.getText().toString();
            age = agee.getText().toString();
            expertise = expertisee.getSelectedItem().toString();
            term = termm.getSelectedItem().toString();
            User userrr = new User(age, emailll, expertise, term, timelimit);
            Criteria crit = createRecommended();
            if(crit!=null) {
                userrr.addCriteria(crit);
            }
            else {
                Toast.makeText(this, "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
            }
            if(!emailll.isEmpty() && !pass.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(emailll, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("SIGN UP", "Successfully signed up the user");

                            FirebaseUser user = mAuth.getCurrentUser();
                            firestore.collection("users").document(user.getUid()).set(userrr);
                            updateUI(user, crit);
                        } else {
                            Log.w("SIGN UP", "createUserWithEmail:failure", task.getException());
//                    Toast.makeText(this, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show();
                            updateUI(null, null);
                        }
                    }
                });
            }


            else {
                Toast.makeText(SignupActivity.this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
            }
        } catch(Exception e) {
            Toast.makeText(SignupActivity.this, "Invalid Information", Toast.LENGTH_SHORT).show();

        }

    }

    public Criteria createRecommended() {
        if (!(age.equals("") && term.equals("") && expertise.equals("") &&  term.equals(""))) {
            if (Integer.parseInt(age) < 18) {
                Criteria recommended = new Criteria("Price is within 25% of 52-week high, Price is 30% above 52-week low, Price is higher than 50-day average, 50-day average is higher than 200-day average, Price is higher than 200-day average", "DWA");
                return recommended;
            } else {
                if (term.equals("Long term")) {
                    if (expertise.equals("Professional")) {
                            Criteria recommended = new Criteria(2, 5, 5, 2, 20, 30);
                        recommended.setName("Criteria 1");
                        return recommended;


                    } else if (expertise.equals("Casual")) {
                        Criteria recommended = new Criteria("Price is within 25% of 52-week high, Price is 30% above 52-week low, Price is higher than 50-day average, 50-day average is higher than 200-day average, Price is higher than 200-day average", "DWA");
                        return recommended;
                    }
                } else {
                    if (expertise.equals("Professional")) {
                        Criteria recommended = new Criteria(5, 1, 1, 1, 10, 15);
                        recommended.setName("Criteria 1");
                        return recommended;

                    } else if (expertise.equals("Casual")) {
                        Criteria recommended = new Criteria(5, 1, 1, 1, 20, 22);
                        recommended.setName("Criteria 1");
                        return recommended;
                    }
                }
            }
        }
        else {
            Toast.makeText(this, "Please fill in all fields",
                            Toast.LENGTH_SHORT).show();
        }
            return null;
        }


    public void updateUI(FirebaseUser user, Criteria criteria) {
            if(user != null) {
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("criteria", criteria);
                startActivity(intent);
            }
        }
}