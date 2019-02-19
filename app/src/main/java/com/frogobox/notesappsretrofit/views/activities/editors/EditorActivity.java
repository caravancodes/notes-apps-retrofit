package com.frogobox.notesappsretrofit.views.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.frogobox.notesappsretrofit.R;
import com.frogobox.notesappsretrofit.presenters.EditorPresenter;
import com.frogobox.notesappsretrofit.views.interfaces.EditorView;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText editTextTitle;
    EditText editTextNotes;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    EditorPresenter presenter;

    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextTitle = findViewById(R.id.edit_title);
        editTextNotes = findViewById(R.id.note);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progress_dialog));
        palette = findViewById(R.id.color_palete);

        palette.setOnColorSelectedListener(
                clr -> color = clr
        );

        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);
        presenter = new EditorPresenter(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_menu_save:

                String isi_title = editTextTitle.getText().toString();
                String isi_notes = editTextNotes.getText().toString();
                int isi_color = this.color;

                if (isi_title.isEmpty()) {
                    editTextTitle.setError("Wajib di isi");
                } else if (isi_notes.isEmpty()) {
                    editTextNotes.setError("Wajib di isi");
                } else {
                    presenter.saveNotes(isi_title, isi_notes, isi_color);
                }

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
