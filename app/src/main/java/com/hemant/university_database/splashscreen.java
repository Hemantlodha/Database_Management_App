package com.hemant.university_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {
    private static int timer=2200;
    ImageView imageView;
    TextView textView,textView1;
    Animation animation,upanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView11);
        textView1=findViewById(R.id.textView10);
        animation= AnimationUtils.loadAnimation(this,R.anim.slide_down);
        upanim= AnimationUtils.loadAnimation(this,R.anim.upanimation);
        textView.setAnimation(upanim);
        textView1.setAnimation(upanim);
        imageView.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splashscreen.this,login.class);
                startActivity(intent);
                finish();
            }
        },timer);
    }
}