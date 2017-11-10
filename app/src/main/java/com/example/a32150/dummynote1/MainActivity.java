package com.example.a32150.dummynote1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    DB db;
    SimpleCursorAdapter adapter;
    String[] from;
    int[] to;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tell the list view which view to display when the list is empty

        listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.list));
//        setAdapter();

        setAdapter1();
    }


    private String[] note_array = {
            "Activities",
            "Services",
            "Content Providers",
            "Broadcast Receiver"
    };

        private DB mDbHelper;
        private Cursor mNotesCursor;

        private void setAdapter1() {
            mDbHelper = new DB(this);
            mDbHelper.open();
            mNotesCursor = mDbHelper.getAll();
            if(mNotesCursor.getCount() == 0)
                Toast.makeText(this, "No Data !", Toast.LENGTH_SHORT).show();
            //android.R.id.xxx ===> 系統預設
            //R.id.xxx ===> 自訂layout樣式
            startManagingCursor(mNotesCursor);
            from = new String[]{"note", "created"};
            to = new int[]{R.id.tex1, R.id.text2};
            adapter = new SimpleCursorAdapter(this, R.layout.simple_list_item_1, mNotesCursor, from, to);
            listView.setAdapter(adapter);
        }

        void onClick(View v)    {
            db.open();
            db.getAll();
            db.close();

            adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from, to);
            listView.setAdapter(adapter);
        }
    }

