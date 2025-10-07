package com.example.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityLayout extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout); // Assumes you've created this XML file
        imageView = (ImageView) findViewById(R.id.iv);
    }
    public void youtube(View v){
        imageView.setVisibility(View.VISIBLE);

    }
}