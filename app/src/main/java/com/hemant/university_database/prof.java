package com.hemant.university_database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class prof extends AppCompatActivity {
    private Button show;
    private Button insert;
    private Button delete;
    DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        insert=findViewById(R.id.button10);
        show=findViewById(R.id.button11);
        delete=findViewById(R.id.button12);
        db=new DataHelper(this);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Cursor res = db.getprof();
                        if (res.getCount() == 0) {
                            Toast.makeText(prof.this, "No entry Exists", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuffer s = new StringBuffer();
                        while (res.moveToNext()) {
                            s.append("Professor Name: " + res.getString(0) + "\n");
                            s.append("Employee_id: " + res.getInt(1) + "\n");
                            s.append("Salary: " + res.getString(2) + "\n");
                            s.append("Dept_id: " + res.getString(3) + "\n");
                            s.append("Date of Birth: " + res.getString(4) + "\n\n\n");
                        }
                        AlertDialog.Builder b = new AlertDialog.Builder(prof.this);
                        b.setCancelable(true);
                        b.setTitle("Professor Data");
                        b.setMessage(s.toString());
                        b.show();
                    } catch (Exception e) {
                        Toast.makeText(prof.this, "Dikkat hai", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(prof.this, ProfAdd.class);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(prof.this, delete2.class);
                    startActivity(intent);
                }
            });
    }
}