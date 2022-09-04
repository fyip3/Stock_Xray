package com.example.stockx_ray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewCriteriaActivity extends AppCompatActivity {
    int no;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser userr;
    private User user;
    private Criteria crit;
    private EditText prof;
    private EditText ins;
    private EditText inst;
    private EditText fo;
    private EditText tra;
    private EditText fif;
    private TextView name;
    boolean ma;
    private Button maa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_criteria);

        prof = findViewById(R.id.prof);
        ins = findViewById(R.id.ins);
        inst = findViewById(R.id.inst);
        fo = findViewById(R.id.fo);
        tra = findViewById(R.id.tra);
        fif = findViewById(R.id.fif);
        maa = findViewById(R.id.maa);
        name = findViewById(R.id.name);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        no = (Integer) intent.getSerializableExtra("criteria");
        userr = mAuth.getCurrentUser();
        db.collection("users").document(userr.getUid()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                DocumentSnapshot ds = task.getResult();

                user = ds.toObject(User.class);
                crit = user.getCriterias().get(no);
                prof.setText(String.valueOf(crit.getProfitMargin()));
                ins.setText(String.valueOf( crit.getInsider()));
                inst.setText(String.valueOf(crit.getInstitution()));
                fo.setText(String.valueOf(crit.getFpe()));
                tra.setText(String.valueOf(crit.getTpe()));
                fif.setText(String.valueOf(crit.getFiftyChange()));
                name.setText(crit.getName());
                if(crit.isMa()) {
                    maa.setText("EXCLUDE MOVING AVERAGE TESTS");
                }

            } else {
                Toast.makeText(ViewCriteriaActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        maa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maa.getText().toString().equals("EXCLUDE MOVING AVERAGE TESTS")) {
                    maa.setText("INCLUDE MOVING AVERAGE TESTS");

                } else {
                    maa.setText("EXCLUDE MOVING AVERAGE TESTS");
                }

            }
        });

    }

    public void save(View v) {
        try {
            user.getCriterias().get(no).setProfitMargin(Double.parseDouble(prof.getText().toString()));
            user.getCriterias().get(no).setFpe(Double.parseDouble(fo.getText().toString()));
            user.getCriterias().get(no).setTpe(Double.parseDouble(tra.getText().toString()));
            user.getCriterias().get(no).setInsider(Double.parseDouble(ins.getText().toString()));
            user.getCriterias().get(no).setInstitution(Double.parseDouble(inst.getText().toString()));
            user.getCriterias().get(no).setFiftyChange(Double.parseDouble(fif.getText().toString()));
            if(fif.getText().toString()!="0") {
                user.getCriterias().get(no).setVar(true);
            }
            if(maa.getText().toString().equals("EXCLUDE MOVING AVERAGE TESTS")) {
                user.getCriterias().get(no).setMa(true);
            }
            else {
                user.getCriterias().get(no).setMa(false);
            }
            db.collection("users").document(mAuth.getCurrentUser().getUid()).update("criterias", user.getCriterias());
        }
        catch (Exception e) {
            Toast.makeText(ViewCriteriaActivity.this, "Invalid Values", Toast.LENGTH_SHORT).show();

        }

    }

    public void run(View v) {
        Intent intent = new Intent(this, AnalysisActivity.class);
        intent.putExtra("criteria", user.getCriterias().get(no));
        startActivity(intent);
    }
}