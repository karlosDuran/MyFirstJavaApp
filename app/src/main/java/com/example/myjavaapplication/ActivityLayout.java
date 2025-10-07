package com.example.myjavaapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

public class ActivityLayout extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 2;

    private EditText noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        noteText = findViewById(R.id.note_text);
        Button saveButton = findViewById(R.id.save_button);
        Button lockButton = findViewById(R.id.lock_button);
        Button viewNotesButton = findViewById(R.id.view_notes_button);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String savedNote = sharedPreferences.getString("saved_note", "");
        noteText.setText(savedNote);

        noteText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLayout.this);
                builder.setTitle("Options");
                builder.setItems(new CharSequence[]{"Reset Passpoints", "Replace image...", "Save", "Lock"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // Reset Passpoints
                                Intent resetIntent = new Intent(ActivityLayout.this, MainActivity.class);
                                resetIntent.putExtra("reset_mode", true);
                                startActivity(resetIntent);
                                finish();
                                break;
                            case 1: // Replace image...
                                // Solicitamos el permiso antes de abrir la galería
                                if (ContextCompat.checkSelfPermission(ActivityLayout.this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(ActivityLayout.this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISSION_REQUEST_CODE);
                                } else {
                                    openGallery();
                                }
                                break;
                            case 2: // Save
                                String textToSave = noteText.getText().toString();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("saved_note", textToSave);
                                editor.apply();
                                Toast.makeText(ActivityLayout.this, "Nota guardada!", Toast.LENGTH_SHORT).show();
                                break;
                            case 3: // Lock
                                Intent lockIntent = new Intent(ActivityLayout.this, MainActivity.class);
                                startActivity(lockIntent);
                                finish();
                                break;
                        }
                    }
                });
                builder.show();
                return true;
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSave = noteText.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("saved_note", textToSave);
                editor.apply();
                Toast.makeText(ActivityLayout.this, "Nota guardada!", Toast.LENGTH_SHORT).show();
            }
        });

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lockIntent = new Intent(ActivityLayout.this, MainActivity.class);
                startActivity(lockIntent);
                finish();
            }
        });

        viewNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent(ActivityLayout.this, ViewNotesActivity.class);
                startActivity(viewIntent);
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permiso denegado. No se puede acceder a la galería.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("unlock_image_uri", imageUri.toString());
            editor.apply();

            Toast.makeText(this, "Imagen reemplazada exitosamente.", Toast.LENGTH_SHORT).show();
        }
    }
}