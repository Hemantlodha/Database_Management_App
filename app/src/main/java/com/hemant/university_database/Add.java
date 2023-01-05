package com.hemant.university_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    EditText name;
    EditText scholar;
    EditText course;
    EditText dob;
    EditText year;
    DataHelper db;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name=findViewById(R.id.edit1);
        scholar=findViewById(R.id.edit2);
        course=findViewById(R.id.edit3);
        dob=findViewById(R.id.edit4);
        year=findViewById(R.id.edit5);
        db=new DataHelper(this);
        button=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nameT = name.getText().toString();
                    int scholarT = Integer.parseInt(scholar.getText().toString());
                    String courseT = (course.getText().toString());
                    String dobT = dob.getText().toString();
                    String yearT = year.getText().toString();
                    boolean check = db.insertdata(nameT, scholarT, courseT, dobT, yearT);
                    if (check)
                        Toast.makeText(Add.this, "The data is inserted successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Add.this, "Data cannot be inserted", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(Add.this, "Fill the entry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}