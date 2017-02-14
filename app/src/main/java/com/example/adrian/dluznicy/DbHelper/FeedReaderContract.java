package com.example.adrian.dluznicy.DbHelper;

import android.provider.BaseColumns;

/**
 * Created by Adrian on 10.02.2017.
 */

public final class FeedReaderContract {

    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "debtors";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_DEBT = "debt";
    }
}
