package com.hemant.university_database;

import static java.util.concurrent.TimeUnit.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class create_acc extends AppCompatActivity {
    Button button8,button11;
    TextInputLayout username,phone,gmail,password,passc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        button8=findViewById(R.id.button8);
        button11=findViewById(R.id.button11);
        username=findViewById(R.id.Username);
        phone=findViewById(R.id.phone);
        gmail=findViewById(R.id.gmail);
        password=findViewById(R.id.password);
        passc=findViewById(R.id.passc);
        ProgressBar progressBar=findViewById(R.id.progress);
        ProgressBar progressBar1=findViewById(R.id.progress1);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button11.setVisibility(View.INVISIBLE);
                progressBar1.setVisibility(View.VISIBLE);
                String email=gmail.getEditText().getText().toString();
                ActionCodeSettings actionCodeSettings =
                        ActionCodeSettings.newBuilder()
                                // URL you want to redirect back to. The domain (www.example.com) for this
                                // URL must be whitelisted in the Firebase Console.
                                .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                                // This must be true
                                .setHandleCodeInApp(true)
                                .setIOSBundleId("com.example.ios")
                                .setAndroidPackageName(
                                        "com.example.android",
                                        true, /* installIfNotAvailable */
                                        "12"    /* minimumVersion */)
                                .build();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendSignInLinkToEmail(email, actionCodeSettings)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(create_acc.this, "Mail is sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getEditText().getText().toString();
                String pho=phone.getEditText().getText().toString();
                String gma=gmail.getEditText().getText().toString();
                String p1=password.getEditText().getText().toString();
                String p2=passc.getEditText().getText().toString();
                if(!user.isEmpty()) {
                    if(!pho.isEmpty() && pho.length()==13) {
                        if(!gma.isEmpty()) {
                            if(!p1.isEmpty()) {
                                if(!p2.isEmpty()) {
                                    if(p1.equalsIgnoreCase(p2)){
                                        button8.setVisibility(View.INVISIBLE);
                                        progressBar.setVisibility(View.VISIBLE);
                                        PhoneAuthProvider.getInstance().verifyPhoneNumber(pho, 60, SECONDS, create_acc.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                button8.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(create_acc.this, "Account was created without otp", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                button8.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(create_acc.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                button8.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Intent intent = new Intent(create_acc.this,otp.class);
                                                intent.putExtra("mobile",pho);
                                                intent.putExtra("botp",s);
                                                intent.putExtra("gmail",gma);
                                                intent.putExtra("password",p1);
                                                startActivity(intent);
                                                finish();
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