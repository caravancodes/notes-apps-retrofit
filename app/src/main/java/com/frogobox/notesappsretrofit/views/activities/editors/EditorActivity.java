package com.frogobox.notesappsretrofit.views.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.frogobox.notesappsretrofit.R;
import com.frogobox.notesappsretrofit.models.Note;
import com.frogobox.notesappsretrofit.presenters.EditorPresenter;
import com.frogobox.notesappsretrofit.views.interfaces.EditorView;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView {

    public static final String EXTRA_DATA = "extra_data";

    private EditText editTextTitle;
    private EditText editTextNotes;
    private ProgressDialog progressDialog;
    private SpectrumPalette palette;

    private EditorPresenter presenter;

    private int color, id;
    private String parcelTitle, parcelNotes;

    private Menu actionMenu;

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


        presenter = new EditorPresenter(this);

        Intent intent = getIntent();


        Note getIntentData = getIntent().getParcelableExtra(EXTRA_DATA);

        if (getIntentData != null) {
            id = getIntentData.getId();
            color = getIntentData.getColor();
            parcelNotes = getIntentData.getNote();
            parcelTitle = getIntentData.getTitle();
            setDataFromIntent();
        }
    }

    private void setDataFromIntent() {

        if (id!=0) {
            editTextTitle.setText(parcelTitle);
            editTextNotes.setText(parcelNotes);
            palette.setSelectedColor(color);

            getSupportActionBar().setTitle(getString(R.string.title_update));
            readMode();

        } else {
            palette.setSelectedColor(getResources().getColor(R.color.orange));
            color = getResources().getColor(R.color.orange);
            editMode();
        }

    }

    private void editMode() {
        editTextTitle.setFocusableInTouchMode(true);
        editTextNotes.setFocusableInTouchMode(true);
        palette.setEnabled(true);
    }

    private void readMode() {
        editTextTitle.setFocusableInTouchMode(false);
        editTextNotes.setFocusableInTouchMode(false);
        editTextTitle.setFocusable(false);
        editTextNotes.setFocusable(false);
        palette.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        actionMenu = menu;

        if (id!=0) {
            actionMenu.findItem(R.id.toolbar_menu_edit).setVisible(true);
            actionMenu.findItem(R.id.toolbar_menu_delete).setVisible(true);
            actionMenu.findItem(R.id.toolbar_menu_save).setVisible(false);
            actionMenu.findItem(R.id.toolbar_menu_update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.toolbar_menu_edit).setVisible(false);
            actionMenu.findItem(R.id.toolbar_menu_delete).setVisible(false);
            actionMenu.findItem(R.id.toolbar_menu_save).setVisible(true);
            actionMenu.findItem(R.id.toolbar_menu_update).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String isi_title = editTextTitle.getText().toString();
        String isi_notes = editTextNotes.getText().toString();
        int isi_color = this.color;

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_menu_save:

                if (isi_title.isEmpty()) {
                    editTextTitle.setError("Wajib di isi");
                } else if (isi_notes.isEmpty()) {
                    editTextNotes.setError("Wajib di isi");
                } else {
                    presenter.saveNotes(isi_title, isi_notes, isi_color);
                }
                finish();
                break;

            case R.id.toolbar_menu_edit:
                editMode();
                actionMenu.findItem(R.id.toolbar_menu_edit).setVisible(false);
                actionMenu.findItem(R.id.toolbar_menu_delete).setVisible(false);
                actionMenu.findItem(R.id.toolbar_menu_save).setVisible(false);
                actionMenu.findItem(R.id.toolbar_menu_update).setVisible(true);
                break;

            case R.id.toolbar_menu_update:
                if (isi_title.isEmpty()) {
                    editTextTitle.setError("Wajib di isi");
                } else if (isi_notes.isEmpty()) {
                    editTextNotes.setError("Wajib di isi");
                } else {
                    presenter.updateNotes(id, isi_title, isi_notes, isi_color);
                }
                finish();
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
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
