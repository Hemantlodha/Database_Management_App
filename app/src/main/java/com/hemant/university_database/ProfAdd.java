package com.hemant.university_database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.animation.*;

public class ProfAdd extends AppCompatActivity {
    EditText p_name;
    EditText emp_id;
    EditText salary;
    EditText dob;
    EditText dept_id;
    DataHelper db;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_add);
        p_name=findViewById(R.id.edit7);
        emp_id=findViewById(R.id.edit8);
        dept_id=findViewById(R.id.edit9);
        salary=findViewById(R.id.edit10);
        dob=findViewById(R.id.edit11);
        db=new DataHelper(this);
        button=findViewById(R.id.button13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nameT = p_name.getText().toString();
                    int salaryT = Integer.parseInt(salary.getText().toString());
                    String empT = (emp_id.getText().toString());
                    String dobT = dob.getText().toString();
                    String deptT = dept_id.getText().toString();
                    boolean check = db.insertprof(nameT, salaryT, empT, dobT, deptT);
                    if (check)

                        Toast.makeText(ProfAdd.this, "The data is inserted successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ProfAdd.this, "Data cannot be inserted", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(ProfAdd.this, "Fill the entry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}