package com.example.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Button myButton = findViewById(R.id.myButton);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra el Toast sin el ícono
                Toast toast = new Toast(getApplicationContext());
                toast.setText("Qué rollito");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}