package com.example.adrian.dluznicy.Search;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adrian.dluznicy.DbHelper.FeedReaderContract;
import com.example.adrian.dluznicy.R;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by Adrian on 10.02.2017.
 */

public class ShowAll extends ListActivity {

    private static final String TAG = "MYRECORDER";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all);

        ArrayList<String> listItems = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, listItems);

        ListView mylist = (ListView) findViewById(android.R.id.list);
        mylist.setAdapter(adapter);

        SQLiteDatabase db = openOrCreateDatabase("Debtors.db", Context.MODE_PRIVATE, null);

        Cursor c = db.rawQuery("SELECT name, surname, debt FROM debtors", null);

        if (c.moveToFirst()){
            do {
                String name = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME));
                String surname = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_SURNAME));
                String debt = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_DEBT));
                String Name_num = name + " " + surname + ": " + debt + " zł";
                listItems.add(Name_num);
            } while(c.moveToNext());

            adapter.notifyDataSetChanged();

            db.close();
        }
    }
}
