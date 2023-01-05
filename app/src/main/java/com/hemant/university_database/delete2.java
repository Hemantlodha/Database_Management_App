package com.hemant.university_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText editText;
        Button button;
        DataHelper db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete2);
        editText=findViewById(R.id.edit12);
        button=findViewById(R.id.button14);
        db=new DataHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String sc =editText.getText().toString();
                    boolean ans = db.deletedataProf(sc);
                    if (ans) {
                        Toast.makeText(delete2.this, "Entry is successfully deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(delete2.this, "Nothing can be deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(delete2.this, "Please fill the Scholar NO.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}