package com.frogobox.notesappsretrofit.views.activities.mains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.frogobox.notesappsretrofit.R;
import com.frogobox.notesappsretrofit.views.activities.editors.EditorActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.add_notes);
        fab.setOnClickListener(view -> {
            Intent i = new Intent(this, EditorActivity.class);
            startActivity(i);
        });

    }
}
