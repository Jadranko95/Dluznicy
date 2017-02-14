package com.example.adrian.dluznicy.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.adrian.dluznicy.R;

/**
 * Created by Adrian on 12.01.2017.
 */

public class SearchDebtor extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_debtor);

        Button show_all = (Button) findViewById(R.id.show_allB);
        Button show_one = (Button) findViewById(R.id.show_oneB);
        Button back = (Button) findViewById(R.id.backB);

        show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchDebtor.this, ShowAll.class);
                startActivity(intent);
            }
        });

        show_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchDebtor.this, ShowSelected.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
