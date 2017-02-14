package com.example.adrian.dluznicy.Add;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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

/**
 * Created by Adrian on 12.01.2017.
 */

public class AddDebt extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_debtor);

        Button addDebtorToBase = (Button) findViewById(R.id.bAddDebtorToBase);

        addDebtorToBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name, surname, debt, date;
                double debtD;
                name = (EditText) findViewById(R.id.nameT);
                surname = (EditText) findViewById(R.id.surnameT);
                debt = (EditText) findViewById(R.id.debtT);
                date = (EditText) findViewById(R.id.dateEditText);
                if (name.length() > 0 && surname.length() > 0) {
                    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();

                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, name.getText().toString());
                    values.put(FeedReaderContract.FeedEntry.COLUMN_SURNAME, surname.getText().toString());
                    values.put(FeedReaderContract.FeedEntry.COLUMN_DEBT, debt.getText().toString());
                    values.put(FeedReaderContract.FeedEntry.COLUMN_DATE, date.getText().toString());

                    long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                    Toast.makeText(getApplicationContext(), "Pomyślnie dodano dług", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(AddDebt.this)
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
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
