package com.example.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        //  Encuentra el control Button en el layout (activity_hello.xml) usando su ID.
        Button myButton = findViewById(R.id.myButton);

        //  Configura un listener para el evento "click" del botón.
        //    El código dentro de onClick() se ejecutará cada vez que el usuario presione el botón.
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate from the current activity to RelativeActivity.
                Intent intent = new Intent(MainActivity.this, ActivityLayout.class);

                // Start the new activity.
                startActivity(intent);
            }
        });
    }
}
