package com.example.kiosk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MakeAccount extends AppCompatActivity {

    EditText CA_Email, CA_Password, CA_AT;
    Button CA_Btn;
    ProgressBar CA_PB;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);

        CA_Email = findViewById(R.id.CA_Email);
        CA_Password = findViewById(R.id.CA_Password);
        CA_AT = findViewById(R.id.CA_AT);
        CA_Btn = findViewById(R.id.MD_LOGOUT);
        CA_PB = findViewById(R.id.CA_PB);


        fAuth = FirebaseAuth.getInstance();

        CA_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = CA_Email.getText().toString().trim();
                String password = CA_Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    CA_Email.setError("Email Required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    CA_Password.setError("Password Required!");
                    return;
                }
                if(password.length() < 3){
                    CA_Password.setError("At least 6 Characters");
                    return;
                }

                CA_PB.setVisibility(View.VISIBLE);

                //register the suer in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MakeAccount.this,"Account Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ManagerDashboard.class));

                        }else{
                            Toast.makeText(MakeAccount.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });





    }
}