package com.example.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RelativeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout); // Assumes you've created this XML file
    }
}