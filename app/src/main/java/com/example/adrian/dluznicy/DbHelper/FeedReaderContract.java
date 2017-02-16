package com.example.adrian.dluznicy.DbHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Adrian on 10.02.2017.
 */

public final class FeedReaderContract {

    private FeedReaderContract() {}

    public static ArrayList<String> listItems;
    public static ArrayAdapter<String> adapter;
    public static ListView listView;
    public static ListView listViewHistory;
    public static SQLiteDatabase db;
    public static Cursor c;
    public static String name, surname, debt, date, nameNum;
    public static EditText editListText;

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "debtors";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_DEBT = "debt";
        public static final String COLUMN_DATE = "datetime";
    }
}
