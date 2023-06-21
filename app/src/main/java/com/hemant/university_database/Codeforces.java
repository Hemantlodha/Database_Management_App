package com.hemant.university_database;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class Codeforces extends AppCompatActivity {
    String api=" https://codeforces.com/api/user.info?handles=hemantlodha1000";
    TextView textView,textView12,textView13,textView14,textView15;
    ImageView imageView;
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
        textView13=findViewById(R.id.textView13);
        textView14=findViewById(R.id.textView14);
        imageView=findViewById(R.id.imageView3);
        textView15=findViewById(R.id.textView15);
        // String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String user[] = new String[]{"rank","handle","firstName","lastName","city","country","organization","titlePhoto","rating"};
                ArrayList<String> val=new ArrayList<>();
                String data=response.toString();
                for(String key:user) {
                    int lastn = data.indexOf(key) + key.length()+3;
                    String rank = "";
                    while (data.charAt(lastn) != '\"') {
                        rank += data.charAt(lastn);
                        lastn++;
                    }
                    if(val.size()==0)
                        rank=rank.substring(0, 1).toUpperCase() + rank.substring(1).toLowerCase();
                  val.add(rank);
                }
                URI url = null;
                try{
                    url = new URI(val.get(7));
                }
                catch (Exception e){
                    Toast.makeText(Codeforces.this, "error", Toast.LENGTH_SHORT).show();
                }
                Picasso.get().load(String.valueOf(url)).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                textView.setText(val.get(0));
                textView12.setText(val.get(1));
                textView13.setText(val.get(2)+" "+val.get(3)+", "+val.get(4)+", "+val.get(5));
                textView14.setText("From "+val.get(6));
                textView.setTextColor(Color.rgb(15, 208, 212));
                textView12.setTextSize(30);
                textView15.setText(val.get(8));
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