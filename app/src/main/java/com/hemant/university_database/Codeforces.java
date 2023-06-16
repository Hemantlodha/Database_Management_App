package com.hemant.university_database;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Codeforces extends AppCompatActivity {
    String api=" https://codeforces.com/api/user.info?handles=hemantlodha1000";
    TextView textView,textView12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeforces);
        getData();
    }
    private void getData() {
        // RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        textView=findViewById(R.id.textView9);
        textView12=findViewById(R.id.textView12);
        // String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String user[] = new String[]{"rank","handle"};
                ArrayList<String> val=new ArrayList<>();
                String data=response.toString();
                for(String key:user) {
                    int lastn = data.indexOf(key) + key.length()+3;
                    String rank = "";
                    while (data.charAt(lastn) != '\"') {
                        rank += data.charAt(lastn);
                        lastn++;
                    }
                  val.add(rank);
                }
                textView.setText(val.get(0));
                textView12.setText(val.get(1));
                textView.setTextColor(Color.rgb(15, 208, 212));
                textView12.setTextSize(30);
                textView12.setTextColor(Color.rgb(15, 208, 212));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Codeforces.this, "Error", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}