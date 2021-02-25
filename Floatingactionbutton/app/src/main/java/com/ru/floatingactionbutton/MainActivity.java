package com.ru.floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list);
        String [] items = new String[101];
        for(int i = 0; i < 101; i++ ) {
            items[i] = i + "";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        com.melnykov.fab.FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.attachToListView(listView);
    }
}