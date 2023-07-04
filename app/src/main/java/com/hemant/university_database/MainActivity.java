package com.hemant.university_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton button;
    ImageButton imageButton,button5,button4;
    ImageView button7;
    ImageView button8;
    ImageView button9;
    DataHelper db;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.imageButton2);
        imageButton=findViewById(R.id.imageButton3);
        button5=findViewById(R.id.imageButton5);
        button4=findViewById(R.id.imageButton4);
//        button9=findViewById(R.id.button9);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationview);
        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(true);
//        db=new DataHelper(this);
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    db.insertdept("Computer Science Engineering","CSE",240,"Dr. Neelay Khare");
//                    db.insertdept("Electronics and Engineering","ECE",162,"Dr. Navneet Kumar");
//                    db.insertdept("Information Technology","IT",120,"Dr. Anand Patel");
//                    db.insertdept("Electrical Engineering","EE",133,"Dr. Rajesh Kumar Nema");
//                    db.insertdept("Chemical Engineering","CHE",113,"Dr. Savita Dixit");
//                    db.insertdept("Civil Engineering","CE",93,"Dr. Nitin Dindorker");
//                    Cursor cursor =  db.getdept();
//                    if(cursor.getCount()==0){
//                        Toast.makeText(MainActivity.this, "No entry Exists", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    StringBuffer s= new StringBuffer();
//                    while(cursor.moveToNext()){
//                        s.append("Department Name: "+ cursor.getString(0)+"\n");
//                        s.append("Dept_id: "+ cursor.getString(1)+"\n");
//                        s.append("No_of_Student: "+ cursor.getInt(2)+"\n");
//                        s.append("Head of Department: "+ cursor.getString(3)+"\n\n\n");
//                    }
//                    AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
//                    b.setCancelable(true);
//                    b.setTitle("Department Details");
//                    b.setMessage(s.toString());
//                    b.show();
//                }
//                catch(Exception e)
//                {
//                    Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    db.insertcourse("B tech","1001",1203,4);
//                    db.insertcourse("M tech","1002",184,2);
//                    db.insertcourse("MCA","1004",251,2);
//                    db.insertcourse("Phd","1120",30,2);
//                    db.insertcourse("Msc","1005",209,2);
//                    Cursor cursor =  db.getcourse();
//                    if(cursor.getCount()==0){
//                        Toast.makeText(MainActivity.this, "No entry Exists", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    StringBuffer s= new StringBuffer();
//                    while(cursor.moveToNext()){
//                        s.append("Course Name: "+ cursor.getString(0)+"\n");
//                        s.append("Course_id: "+ cursor.getString(1)+"\n");
//                        s.append("No_of_Student: "+ cursor.getInt(2)+"\n");
//                        s.append("Duration: "+ cursor.getInt(3)+"\n\n\n");
//                    }
//                    AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
//                    b.setCancelable(true);
//                    b.setTitle("Course Data");
//                    b.setMessage(s.toString());
//                    b.show();
//                }
//                catch(Exception e)
//                {
//                    Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Codeforces.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Add.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Student.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Delete.class);
                startActivity(intent);
            }
        });
    }
}