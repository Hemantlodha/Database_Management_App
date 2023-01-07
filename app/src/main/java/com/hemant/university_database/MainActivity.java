package com.hemant.university_database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button7;
    Button button8;
    Button button9;
    DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        db=new DataHelper(this);
        ImageView next =  (ImageView) findViewById(R.id.imageView);
        Button btn = (Button) findViewById(R.id.newBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                if(btn.getVisibility()!= View.VISIBLE)
                    btn.setVisibility(View.VISIBLE);
                else
                btn.setVisibility(View.INVISIBLE);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.insertdept("Computer Science Engineering","CSE",240,"Dr. Neelay Khare");
                    db.insertdept("Electronics and Engineering","ECE",162,"Dr. Navneet Kumar");
                    db.insertdept("Information Technology","IT",120,"Dr. Anand Patel");
                    db.insertdept("Electrical Engineering","EE",133,"Dr. Rajesh Kumar Nema");
                    db.insertdept("Chemical Engineering","CHE",113,"Dr. Savita Dixit");
                    db.insertdept("Civil Engineering","CE",93,"Dr. Nitin Dindorker");
                    Cursor cursor =  db.getdept();
                    if(cursor.getCount()==0){
                        Toast.makeText(MainActivity.this, "No entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer s= new StringBuffer();
                    while(cursor.moveToNext()){
                        s.append("Department Name: "+ cursor.getString(0)+"\n");
                        s.append("Dept_id: "+ cursor.getString(1)+"\n");
                        s.append("No_of_Student: "+ cursor.getInt(2)+"\n");
                        s.append("Head of Department: "+ cursor.getString(3)+"\n\n\n");
                    }
                    AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
                    b.setCancelable(true);
                    b.setTitle("Department Details");
                    b.setMessage(s.toString());
                    b.show();
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.insertcourse("B tech","1001",1203,4);
                    db.insertcourse("M tech","1002",184,2);
                    db.insertcourse("MCA","1004",251,2);
                    db.insertcourse("Phd","1120",30,2);
                    db.insertcourse("Msc","1005",209,2);
                    Cursor cursor =  db.getcourse();
                    if(cursor.getCount()==0){
                        Toast.makeText(MainActivity.this, "No entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer s= new StringBuffer();
                    while(cursor.moveToNext()){
                        s.append("Course Name: "+ cursor.getString(0)+"\n");
                        s.append("Course_id: "+ cursor.getString(1)+"\n");
                        s.append("No_of_Student: "+ cursor.getInt(2)+"\n");
                        s.append("Duration: "+ cursor.getInt(3)+"\n\n\n");
                    }
                    AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
                    b.setCancelable(true);
                    b.setTitle("Course Data");
                    b.setMessage(s.toString());
                    b.show();
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Student.class);
                startActivity(intent);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,prof.class);
                startActivity(intent);
            }
        });
    }
}