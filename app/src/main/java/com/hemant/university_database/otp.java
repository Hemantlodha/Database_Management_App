package com.hemant.university_database;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class otp extends AppCompatActivity {
    Button button,button9;
    TextView textView19;
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        button=findViewById(R.id.button10);
        button9=findViewById(R.id.button9);
        textView19=findViewById(R.id.textView19);
        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        editText5=findViewById(R.id.editText5);
        editText6=findViewById(R.id.editText6);
        textView19.setText("mobile no.: "+getIntent().getStringExtra("mobile"));
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String sotp=getIntent().getStringExtra("botp") ;
              String gma=getIntent().getStringExtra("gmail");
              String p1=getIntent().getStringExtra("password");
              String uotp=editText1.getText().toString()+editText2.getText().toString()+editText3.getText().toString()+editText4.getText().toString()+editText5.getText().toString()+editText6.getText().toString();
              if(uotp!=null){
                  PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(sotp,uotp);
                  FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              mAuth = FirebaseAuth.getInstance();
                              mAuth.createUserWithEmailAndPassword(gma, p1)
                                      .addOnCompleteListener(otp.this, new OnCompleteListener<AuthResult>() {
                                          @Override
                                          public void onComplete(@NonNull Task<AuthResult> task) {
                                              if (task.isSuccessful()) {
                                                  // Sign in success, update UI with the signed-in user's information
//                                      firebaseDatabase= FirebaseDatabase.getInstance();
//                                      reference=firebaseDatabase.getReference("Studata user");
//                                      storing_data str= new storing_data(user,pho,gma,p1);
//                                      reference.child(user).setValue(str);
                                                  mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                      @Override
                                                      public void onComplete(@NonNull Task<Void> task) {
                                                          if(task.isSuccessful())
                                                          {
                                                              Toast.makeText(otp.this, "Email sent", Toast.LENGTH_SHORT).show();
                                                          }
                                                          else{
                                                              Toast.makeText(otp.this, "Error occured", Toast.LENGTH_SHORT).show();
                                                          }
                                                      }
                                                  });
                                                  Toast.makeText(otp.this, "Account is created", Toast.LENGTH_SHORT).show();
                                                  Intent intent= new Intent(otp.this,login.class);
                                                  startActivity(intent);
                                                  finish();
                                              }
                                          }
                                      });
                          }
                          else{
                              Toast.makeText(otp.this, "Wrong otp", Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
              }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(otp.this,create_acc.class);
                startActivity(intent);
                finish();
            }
        });
    }
}