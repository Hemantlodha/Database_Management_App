package com.hemant.university_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class create_acc extends AppCompatActivity {
    Button button8;
    TextInputLayout username,phone,gmail,password,passc;
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
                                    if(p1==p2){

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