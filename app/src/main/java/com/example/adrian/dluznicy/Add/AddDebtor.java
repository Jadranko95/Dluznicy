package com.example.adrian.dluznicy.Add;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adrian.dluznicy.DbHelper.FeedReaderContract;
import com.example.adrian.dluznicy.DbHelper.FeedReaderDbHelper;
import com.example.adrian.dluznicy.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Adrian on 12.01.2017.
 */

public class AddDebtor extends AppCompatActivity {

    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_debtor);

        Button addDebtorToBase = (Button) findViewById(R.id.bAddDebtorToBase);

        addDebtorToBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name, surname, debt;
                double debtD;
                name = (EditText) findViewById(R.id.nameT);
                surname = (EditText) findViewById(R.id.surnameT);
                debt = (EditText) findViewById(R.id.debtT);
                if (name.length() > 0 && surname.length() > 0) {
                    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();

                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, name.getText().toString());
                    values.put(FeedReaderContract.FeedEntry.COLUMN_SURNAME, surname.getText().toString());
                    values.put(FeedReaderContract.FeedEntry.COLUMN_DEBT, debt.getText().toString());

                    long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                    Toast.makeText(getApplicationContext(), "Pomyślnie dodano dłużnika", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(AddDebtor.this)
                            .setTitle("Błąd!")
                            .setMessage("Zostawiłeś puste pola w formularzu, spróbuj ponownie.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("AddDebtor Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}
