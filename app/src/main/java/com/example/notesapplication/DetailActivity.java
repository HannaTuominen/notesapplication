package com.example.notesapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private static int maxID;
    String finishedSaveNote ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        Intent in = getIntent();
        maxID = in.getIntExtra("com.example.notesapplication.ITEM_INDEX",0);
        finishedSaveNote = "note" + maxID;
        TextView ViewNoteTextView = (TextView) findViewById(R.id.ViewNoteTextView);
        ViewNoteTextView.setText(mPreferences.getString(finishedSaveNote,""));

        Button editBtn = (Button) findViewById(R.id.editBtn);
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        Button backBtn = (Button) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), EditNoteActivity.class);
                startIntent.putExtra("com.example.notesapplication.SOMETHING",mPreferences.getString(finishedSaveNote,""));
                startIntent.putExtra("com.example.notesapplication.INDEX_ID_DETAIL_ACTIVITY",maxID);
                startActivity(startIntent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Deleting note");
                builder.setMessage("Are you sure you want to delete this note?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mEditor.putBoolean("deleting",true);
                        mEditor.commit();
                        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        //                startIntent.putExtra("com.example.notesapplication.SOMETHING",mPreferences.getString(finishedSaveNote,""));
                        startIntent.putExtra("com.example.notesapplication.INDEX_ID_DETAIL_ACTIVITY",maxID);
                        startActivity(startIntent);
                    }
                });
            builder.show();
            }
        });

    }
}
