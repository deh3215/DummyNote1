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

//    private void setAdapter() {
//        ListAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                note_array);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "你選擇的是第" + + (position+1)+"項 : "+note_array[position], Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
            private DB mDbHelper;
            private Cursor mNotesCursor;
            private void setAdapter1() {
                mDbHelper = new DB(this);
                mDbHelper.open();
                mNotesCursor = mDbHelper.getAll();
                startManagingCursor(mNotesCursor);
                String[] from = new String[]{"note"};
                int[] to = new int[]{android.R.id.text1};
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from, to);
                listView.setAdapter(adapter);
            }
    }

