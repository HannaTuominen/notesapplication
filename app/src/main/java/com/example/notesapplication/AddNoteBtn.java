package com.example.notesapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class AddNoteBtn extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    private EditText note;
    private static int maxID;
    Button saveNotesBtn;
    String finishedSaveNote = "note"+ maxID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_btn);
        //Preferences for saving notes
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
//        mEditor.putInt("maxID",maxID);
        mEditor.commit();
        //The amount of notes created currently to keep track of the list
        maxID = mPreferences.getInt("maxID",0);
        note = (EditText) findViewById(R.id.NoteTextEditText);
        //note.setText(mPreferences.getString(finishedSaveNote,""));
        saveNotesBtn = (Button) findViewById(R.id.SaveNotesBtn);

        saveNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the save note that is used for preferences to ID
                finishedSaveNote = "note" + maxID;
                //saving the created string
                mEditor.putString(finishedSaveNote,note.getText().toString());
                //getting the current date and time and saving it as a long
                Date currentTime = Calendar.getInstance().getTime();
                mEditor.putLong(("noteDate"+maxID),currentTime.getTime());
                //increasing max ID with every new note created
                maxID++;
                //saving the new id
                mEditor.putInt("maxID",maxID);
                mEditor.commit();
                //Going back to the mainactivity an sending the created note for later use
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startIntent.putExtra("com.example.notesapplication.SOMETHING",note.getText().toString());
                startActivity(startIntent);
            }
        });
    }
}
