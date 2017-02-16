package com.example.adrian.dluznicy.Search;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adrian.dluznicy.DbHelper.FeedReaderContract;
import com.example.adrian.dluznicy.R;

import java.util.ArrayList;

import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.adapter;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.c;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.date;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.db;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.debt;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listItems;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listView;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listViewHistory;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.name;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.nameNum;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.surname;

/**
 * Created by Adrian on 14.02.2017.
 */

public class DebtorHistory extends ListActivity {

    String debtorNameSurname;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debtor_history);

        TextView textViewToChange = (TextView)findViewById(R.id.debtorHistoryTextView);
        debtorNameSurname = getIntent().getStringExtra("DEBTOR");

        textViewToChange.setText(debtorNameSurname);
        String debtor[] = debtorNameSurname.split("\\s+");

        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, listItems);

        listViewHistory = (ListView) findViewById(android.R.id.list);
        listViewHistory.setAdapter(adapter);

        db = openOrCreateDatabase("Debtors.db", Context.MODE_PRIVATE, null);

        c = db.rawQuery("SELECT debt, datetime " +
                "FROM debtors " +
                "WHERE name='" + debtor[0] + "' AND surname='" + debtor[1] + "'", null);

        if (c.moveToFirst()){
            do {
                debt = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_DEBT));
                date = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_DATE));
                nameNum = "Wysokość długu: " + debt + " zł\n" + "Data: " + date;
                listItems.add(nameNum);
            } while(c.moveToNext());

            adapter.notifyDataSetChanged();

            db.close();
        }
    }
}
