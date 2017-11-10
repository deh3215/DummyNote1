package com.example.a32150.dummynote1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        listView.setOnItemClickListener(this);
//        setAdapter();

        mDbHelper = new DB(this);
        mDbHelper.open();

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"新增").setIcon(android.R.drawable.ic_menu_add).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0,2,0,"刪除").setIcon(android.R.drawable.ic_menu_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0,3,0,"修改").setIcon(android.R.drawable.ic_menu_edit).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }
    int count;
    int rowId;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //count = mDbHelper.getAll().getCount()-1;
        switch (item.getItemId())   {
            case 1:
                count++;
                mDbHelper.create(count+".Note");
                setAdapter1();
                break;
            case 2:
                mDbHelper.delete(rowId);
                Log.d("Delete", ""+rowId);
                setAdapter1();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        rowId = i;
        Toast.makeText(this, "妳按了"+rowId, Toast.LENGTH_SHORT).show();
    }





}

