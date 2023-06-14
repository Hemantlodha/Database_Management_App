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
                String data=response.toString();
                int lastn=data.indexOf("rank");
                lastn+=7;
                String rank="";
                while(data.charAt(lastn)!='\"') {
                    rank += data.charAt(lastn);
                    lastn++;
                }
                textView.setText(rank);
                lastn=data.indexOf("handle");
                lastn+=9;
                rank="";
                while(data.charAt(lastn)!='\"')
                {
                    rank+=data.charAt(lastn);
                    lastn++;
                }
                textView12.setText(rank);
                textView.setTextColor(Color.rgb(15, 208, 212));
                textView12.setTextSize(30);
                textView12.setTextColor(Color.rgb(15, 208, 212));
                Toast.makeText(getApplicationContext(), "Response :" + lastn, Toast.LENGTH_LONG).show();//display the response on screen
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