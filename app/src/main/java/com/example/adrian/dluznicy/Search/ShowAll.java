package com.example.adrian.dluznicy.Search;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrian.dluznicy.DbHelper.FeedReaderContract;
import com.example.adrian.dluznicy.R;

import java.util.ArrayList;

import static android.R.id.list;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.adapter;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.c;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.date;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.db;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.debt;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listItems;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listView;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.name;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.nameNum;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.surname;

/**
 * Created by Adrian on 10.02.2017.
 */

public class ShowAll extends ListActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all);

        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, listItems);

        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        db = openOrCreateDatabase("Debtors.db", Context.MODE_PRIVATE, null);

        c = db.rawQuery("SELECT name, surname, debt, datetime FROM debtors", null);

        if (c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME));
                surname = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_SURNAME));
                debt = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_DEBT));
                date = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_DATE));
                nameNum = name + " " + surname + ": " + debt + " zł\n" + "Data: " + date;
                listItems.add(nameNum);
            } while(c.moveToNext());

            adapter.notifyDataSetChanged();

            db.close();
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object o = listView.getItemAtPosition(position);
                        String pen = o.toString();
                        Toast.makeText(getApplicationContext(), pen, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
