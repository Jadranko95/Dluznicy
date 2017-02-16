package com.example.adrian.dluznicy.Search;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adrian.dluznicy.DbHelper.FeedReaderContract;
import com.example.adrian.dluznicy.R;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.adapter;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.editListText;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listItems;
import static com.example.adrian.dluznicy.DbHelper.FeedReaderContract.listView;

/**
 * Created by Adrian on 14.02.2017.
 */

public class ShowSelected extends ListActivity {

    Button back;
    String debtor = "";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_selected);

        back = (Button)findViewById(R.id.backButton);
        back.setOnClickListener(mBackListener);

        listView = (ListView)findViewById(android.R.id.list);
        editListText = (EditText)findViewById(R.id.showSelectedEditText);
        initList();
        editListText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    initList();
                } else {
                    searchItem(s.toString());
                }
                if (count < before) {
                    initList();
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object o = listView.getItemAtPosition(position);
                        debtor = o.toString();
                        System.out.println(debtor + " " + position + " " + id);
                        Intent intent = new Intent(ShowSelected.this, DebtorHistory.class);
                        intent.putExtra("DEBTOR", debtor);
                        startActivity(intent);
                    }
                }
        );
    }

    // show all debrots in ListView
    protected void initList(){
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, listItems);
        listView.setAdapter(adapter);
        SQLiteDatabase db = openOrCreateDatabase("Debtors.db", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT DISTINCT name, surname FROM debtors", null);

        if (c.moveToFirst()){
            do {
                String name = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME));
                String surname = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_SURNAME));
                String Name_num = name + " " + surname;
                listItems.add(Name_num);
            } while(c.moveToNext());

            adapter.notifyDataSetChanged();

            db.close();
        }
    }

    // search debtor with string "textToSearch"
    public void searchItem(String textToSearch){
        for (Iterator<String> iterator = listItems.iterator(); iterator.hasNext(); ){
            String value = iterator.next();
            if (!value.contains(textToSearch)){
                iterator.remove();
            }
        }

        adapter.notifyDataSetChanged();
    }

    private View.OnClickListener mBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
