package com.example.adrian.dluznicy.MainClasses;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adrian.dluznicy.Add.AddDebt;
import com.example.adrian.dluznicy.DbHelper.FeedReaderDbHelper;
import com.example.adrian.dluznicy.R;
import com.example.adrian.dluznicy.Search.SearchDebtor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        mDbHelper.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addDebtor = (Button) findViewById(R.id.bAddDebtor);
        Button searchDebtor = (Button) findViewById(R.id.bSearchDebtor);
        Button exit = (Button) findViewById(R.id.bExit);

        addDebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDebt.class);
                startActivity(intent);
            }
        });

        searchDebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchDebtor.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
