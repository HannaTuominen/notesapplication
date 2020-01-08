package com.example.notesapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText note;
    private static int editID;
    String finishedSaveNote = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        Intent in = getIntent();
        editID = in.getIntExtra("com.example.notesapplication.INDEX_ID_DETAIL_ACTIVITY",0);
        finishedSaveNote = "note" + editID;
        note = (EditText) findViewById(R.id.NoteTextEditEditText);
        note.setText(mPreferences.getString(finishedSaveNote,""));

        Button SaveEditNotesBtn = (Button) findViewById(R.id.SaveEditNotesBtn);

        SaveEditNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                finishedSaveNote = "note" + editID;
                mEditor.putString(finishedSaveNote,note.getText().toString());
                mEditor.commit();
                startActivity(startIntent);
            }
        });
    }
}
