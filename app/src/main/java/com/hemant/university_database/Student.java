package com.hemant.university_database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Student extends AppCompatActivity {
    Button button2;
    Button button4;
    Button button6;
    DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        button4=findViewById(R.id.button4);
        db=new DataHelper(this);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getData();
                if(res.getCount()==0){
                    Toast.makeText(Student.this, "No entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer s= new StringBuffer();
                while(res.moveToNext()){
                    s.append("Name: "+ res.getString(0)+"\n");
                    s.append("SCHOLAR_NO: "+ res.getInt(1)+"\n");
                    s.append("Course_id: "+ res.getString(2)+"\n");
                    s.append("Date of Birth: "+ res.getString(3)+"\n");
                    s.append("Enrolled Year: "+ res.getString(4)+"\n\n\n");
                }
                AlertDialog.Builder b=new AlertDialog.Builder(Student.this);
                b.setCancelable(true);
                b.setTitle("Student Entries");
                b.setMessage(s.toString());
                b.show();
            }
        });
    }
}