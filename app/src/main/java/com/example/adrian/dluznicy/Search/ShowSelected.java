package com.example.adrian.dluznicy.Search;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.adrian.dluznicy.DbHelper.FeedReaderContract;
import com.example.adrian.dluznicy.R;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian on 14.02.2017.
 */

public class ShowSelected extends ListActivity {

    ArrayList<String> listItems;
    ArrayAdapter<String> listAdapter;
    ListView listView;
    EditText listText;

    Button back;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_selected);

        back = (Button)findViewById(R.id.backButton);
        back.setOnClickListener(mBackListener);

        listView = (ListView)findViewById(android.R.id.list);
        listText = (EditText)findViewById(R.id.showSelectedEditText);
        initList();
        listText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("before: " + start + count + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("on: " + start + before + count);
                if (s.toString().equals("")){
                    initList();
                } else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("editable: " + s);
                if (s.toString().equals("")){
                    initList();
                } else {
                    searchItem(s.toString());
                }
            }
        });
    }

    protected void initList(){
        listItems = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, listItems);
        listView.setAdapter(listAdapter);
        SQLiteDatabase db = openOrCreateDatabase("Debtors.db", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT name, surname, debt FROM debtors", null);

        if (c.moveToFirst()){
            do {
                String name = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME));
                String surname = c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_SURNAME));
                String Name_num = name + " " + surname;
                listItems.add(Name_num);
            } while(c.moveToNext());

            listAdapter.notifyDataSetChanged();

            db.close();
        }
    }

    public void searchItem(String textToSearch){
        for (Iterator<String> iterator = listItems.iterator(); iterator.hasNext(); ){
            String value = iterator.next();
            if (!value.contains(textToSearch)){
                iterator.remove();
            }
        }

        listAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener mBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /*back.setOnClickListener(new View.OnClickListener()  {
        @Override
        public void onClick(View v) {
            finish();
        }
    });*/
}
