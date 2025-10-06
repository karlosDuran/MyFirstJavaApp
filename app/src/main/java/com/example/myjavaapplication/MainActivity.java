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

        //  Encuentra el control Button en el layout (activity_hello.xml) usando su ID.
        Button myButton = findViewById(R.id.myButton);

        //  Configura un listener para el evento "click" del botón.
        //    El código dentro de onClick() se ejecutará cada vez que el usuario presione el botón.
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Crea una instancia de Toast (un mensaje emergente).
                //    Se usa el contexto de la aplicación para que sepa dónde mostrarse.
                Toast toast = new Toast(getApplicationContext());

                // Establece el texto que se mostrará en el mensaje.
                toast.setText("Qué rollito");

                // Define la duración del mensaje (en este caso, corta).
                toast.setDuration(Toast.LENGTH_SHORT);

                //  Muestra el mensaje Toast en la pantalla.
                toast.show();
            }
        });
    }
}
