package com.example.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    // Posiciones predeterminadas del passpoint. -1 indica que no se ha guardado uno.
    private int savedX = -1;
    private int savedY = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        ImageView unlockImage = findViewById(R.id.unlock_image);
        View unlockContainer = findViewById(R.id.unlock_container);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Verificamos si la aplicación está en modo de reinicio
        boolean isResetMode = getIntent().getBooleanExtra("reset_mode", false);

        if (isResetMode) {
            Toast.makeText(this, "Toca la imagen para establecer el nuevo passpoint.", Toast.LENGTH_LONG).show();
            // Desactivamos el passpoint guardado para que no desbloquee automáticamente
            savedX = -1;
            savedY = -1;
        } else {
            // Cargamos el passpoint guardado si no estamos en modo de reinicio
            savedX = sharedPreferences.getInt("passpoint_x", -1);
            savedY = sharedPreferences.getInt("passpoint_y", -1);

            // Cargamos la imagen guardada
            String imageUriString = sharedPreferences.getString("unlock_image_uri", null);
            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                unlockImage.setImageURI(imageUri);
            }
        }

        unlockContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (isResetMode) {
                        // Modo de reinicio: Guardamos el nuevo punto.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("passpoint_x", (int) event.getX());
                        editor.putInt("passpoint_y", (int) event.getY());
                        editor.apply();

                        Toast.makeText(MainActivity.this, "¡Nuevo passpoint guardado!", Toast.LENGTH_SHORT).show();

                        // Lanzamos la actividad principal
                        Intent mainIntent = new Intent(MainActivity.this, ActivityLayout.class);
                        startActivity(mainIntent);
                        finish();

                    } else {
                        // Modo normal: Verificamos si el toque coincide.
                        // Solo verificamos si ya existe un passpoint guardado
                        if (savedX != -1) {
                            int currentX = (int) event.getX();
                            int currentY = (int) event.getY();
                            // Usamos una pequeña tolerancia para la precisión del toque
                            if (Math.abs(currentX - savedX) < 100 && Math.abs(currentY - savedY) < 100) {
                                Toast.makeText(MainActivity.this, "¡Desbloqueado!", Toast.LENGTH_SHORT).show();
                                Intent mainIntent = new Intent(MainActivity.this, ActivityLayout.class);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Passpoint incorrecto.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Si no hay passpoint, el primer toque lo guarda y desbloquea
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("passpoint_x", (int) event.getX());
                            editor.putInt("passpoint_y", (int) event.getY());
                            editor.apply();
                            Toast.makeText(MainActivity.this, "Passpoint inicial guardado. ¡Desbloqueado!", Toast.LENGTH_SHORT).show();
                            Intent mainIntent = new Intent(MainActivity.this, ActivityLayout.class);
                            startActivity(mainIntent);
                            finish();
                        }
                    }
                }
                return false;
            }
        });
    }
}