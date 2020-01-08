package com.example.notesapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    ListView notesListView;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayList<String> noteInfo = new ArrayList<>();
    ArrayList<Date> timeInfo = new ArrayList<>();
    private ListView list;

    int maxID = -1;
    int id = 0;
    int loadID = 0;
    String finishedSaveNote = "note" + id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Resources res = getResources();
        notesListView = (ListView) findViewById(R.id.NotesListView);
        //notes = res.getStringArray(R.array.notes);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharedPreferences();
        if(mPreferences.getBoolean("deleting",false) == true) {
            deletingItems();
            mEditor.putBoolean("deleting",false);
            mEditor.commit();
        }else {
            loadArrayListItems();
        }

        CustomAdapter adapter = new CustomAdapter(MainActivity.this,noteInfo,listItems,timeInfo);

        //list = (ListView) findViewById(R.id.NotesListView);
//        adapter=new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                listItems);
        notesListView.setAdapter(adapter);
        maxID = mPreferences.getInt("maxID",0);




        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(),DetailActivity.class);
                showDetailActivity.putExtra("com.example.notesapplication.ITEM_INDEX", position);
                startActivity(showDetailActivity);
            }
        });

        Button addNoteBtn = (Button) findViewById(R.id.addNoteBtn);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AddNoteBtn.class);
                //startIntent.putExtra("com.example.notesapplication.SOMETHING","HELLO WORLD!");
                startActivity(startIntent);
            }
        });


    }
    public void loadArrayListItems() {
        String shortenedMessage ="";

        if(maxID> 0) {
            while(loadID < maxID) {
                for(int i = 0; i <mPreferences.getString(finishedSaveNote,"").length(); i++) {
                    if(i >80 && i <84) {
                        shortenedMessage = shortenedMessage + '.';
                    } else if (i >= 84) {
                        break;
                    } else {
                    shortenedMessage = shortenedMessage + mPreferences.getString(finishedSaveNote,"").charAt(i);
                    }
                }

                Long time = mPreferences.getLong("noteDate"+loadID, 0L);
                Date theDate = new Date(time);
                noteInfo.add("Note "+ (loadID+1));
                listItems.add(shortenedMessage);
                timeInfo.add(theDate);
                loadID++;
                finishedSaveNote = "note"+loadID;
                shortenedMessage="";
            }
        }

        loadID = 0;
    }
//
    private void checkSharedPreferences() {
        maxID = mPreferences.getInt("maxID",0);

    }

    public void deletingItems() {
        Intent in = getIntent();
        int deleteIndex = in.getIntExtra("com.example.notesapplication.INDEX_ID_DETAIL_ACTIVITY",0);
        int deleteIndexChanging = deleteIndex+1;
        String movingString = "";
        String finishedSaveNote = "";
        String finishedSaveNoteMoved = "";
        Long movingLong;
        String finishedDateNote = "";
        String finishedDateNoteMoved = "";

        for(int i = deleteIndex; i < maxID; i++){
            finishedSaveNote = "note" + i;
            finishedSaveNoteMoved = "note" + deleteIndexChanging;
            movingString = mPreferences.getString(finishedSaveNoteMoved,"");
            finishedDateNote = "noteDate" + i;
            finishedDateNoteMoved = "noteDate"+ deleteIndexChanging;
            movingLong = mPreferences.getLong(finishedDateNoteMoved,0L);
            mEditor.putLong(finishedDateNote,movingLong);
            mEditor.putString(finishedSaveNote,movingString);
            deleteIndexChanging++;
            mEditor.putBoolean("deleting",true);
            mEditor.commit();
        }
        String removeNote = "note" + (maxID-1);
        String removeDate = "noteDate" + (maxID-1);
        mEditor.remove(removeDate);
        mEditor.remove(removeNote);
        maxID--;
        mEditor.putInt("maxID",maxID);
        mEditor.commit();
        loadArrayListItems();
    }


}