package com.hemant.university_database;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
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
    String api=" https://codeforces.com/api/user.info?handles=";
    TextView textView,textView12,textView13,textView14,textView15,textView16,textView17,textView22;
    EditText editText;
    Button button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeforces);
        button=findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText=findViewById(R.id.edit2);
                getData(api+editText.getText().toString());
            }
        });
    }
    private void getData(String api) {
        // RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        textView=findViewById(R.id.textView9);
        textView12=findViewById(R.id.textView12);
        textView13=findViewById(R.id.textView13);
        textView14=findViewById(R.id.textView14);
        imageView=findViewById(R.id.imageView3);
        textView15=findViewById(R.id.textView15);
        textView16=findViewById(R.id.textView16);
        textView17=findViewById(R.id.textView17);
        textView22=findViewById(R.id.textView22);
        // String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String user[] = new String[]{"rank","handle","firstName","lastName","city","country","organization","titlePhoto","maxRank"};
                String userint[]=new String[]{"rating","maxRating","contribution","friendOfCount"};
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
                for(String key:userint) {
                    int lastn = data.indexOf(key) + key.length()+2;
                    String rank = "";
                    while (data.charAt(lastn) != ',') {
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
                textView15.setText("Contest rating: "+val.get(9));
                textView16.setText("(max. "+val.get(8)+", "+val.get(10)+")");
                textView17.setText("Contribution : "+val.get(11));
                textView22.setText("FriendOfCount : "+val.get(12));
                textView12.setTextColor(Color.rgb(15, 208, 212));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Codeforces.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}