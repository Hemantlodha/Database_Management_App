package com.hemant.university_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_acc extends AppCompatActivity {
    Button button8;
    TextInputLayout username,phone,gmail,password,passc;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        button8=findViewById(R.id.button8);
        username=findViewById(R.id.Username);
        phone=findViewById(R.id.phone);
        gmail=findViewById(R.id.gmail);
        password=findViewById(R.id.password);
        passc=findViewById(R.id.passc);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getEditText().getText().toString();
                String pho=phone.getEditText().getText().toString();
                String gma=gmail.getEditText().getText().toString();
                String p1=password.getEditText().getText().toString();
                String p2=passc.getEditText().getText().toString();
                if(!user.isEmpty()) {
                    if(!pho.isEmpty()) {
                        if(!gma.isEmpty()) {
                            if(!p1.isEmpty()) {
                                if(!p2.isEmpty()) {
                                    if(p1.equalsIgnoreCase(p2)){
                                        mAuth = FirebaseAuth.getInstance();
                                        mAuth.createUserWithEmailAndPassword(gma, p1)
                                                .addOnCompleteListener(create_acc.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            // Sign in success, update UI with the signed-in user's information
                                                            firebaseDatabase=FirebaseDatabase.getInstance();
                                                            reference=firebaseDatabase.getReference("Studata user");
                                                            storing_data str= new storing_data(user,pho,gma,p1);
                                                            reference.child(user).setValue(str);
                                                            Toast.makeText(create_acc.this, "Account is created", Toast.LENGTH_SHORT).show();
                                                            Intent intent= new Intent(create_acc.this,login.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                    }
                                    else{
                                        passc.setError("password does not match");
                                    }
                                }
                                else{
                                    passc.setError("Please confirm the Password");
                                }
                            }
                            else{
                                password.setError("Please enter the Password");
                            }
                        }
                        else{
                            gmail.setError("Please enter the gmail");
                        }
                    }
                    else{
                        phone.setError("Please enter the Phone number");
                    }
                }
                else{
                    username.setError("Please enter the username");
                }
            }
        });
    }
}