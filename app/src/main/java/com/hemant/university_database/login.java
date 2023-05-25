package com.hemant.university_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
     Button button;
     Button button7;
     TextInputLayout user;
     TextInputLayout pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.button);
        button7=findViewById(R.id.button7);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us=user.getEditText().getText().toString();
                String pas=pass.getEditText().getText().toString();
                if(!us.isEmpty()) {
                    if(!pas.isEmpty()) {
                        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                        DatabaseReference reference=firebaseDatabase.getReference("Studata user");

                        Query check_u= reference.orderByChild("user").equalTo(us);
                        check_u.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String check_p=snapshot.child(us).child("password").getValue(String.class);
                                    if(check_p.equals(pas)){
                                        Intent intent = new Intent(login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        pass.setError("password is incorrect");
                                    }
                                }
                                else{
                                    user.setError("Username does not exits");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else{
                        pass.setError("Please enter the Password");
                    }
                }
                else
                {
                  user.setError("Please enter the username");
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,create_acc.class);
                startActivity(intent);
            }
        });
    }
}