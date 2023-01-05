package com.hemant.university_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {
    EditText editText;
    Button button;
    DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delte);
        editText=findViewById(R.id.edit6);
        button=findViewById(R.id.button5);
        db=new DataHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int sc = Integer.parseInt(editText.getText().toString());
                    boolean ans = db.deletedata(sc);
                    if (ans) {
                        Toast.makeText(Delete.this, "Entry is successfully deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Delete.this, "Nothing can be deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(Delete.this, "Please fill the Scholar NO.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}