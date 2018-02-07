package com.homemade.homemade.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.homemade.homemade.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> recipesList = new ArrayList<>();
        recipesList.add("Potato");
        recipesList.add("Water");
        recipesList.add("Syracuse");

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main, recipesList);

        ListView listView = (ListView) findViewById(R.id.recipe_list);
        listView.setAdapter(adapter);
    }
}
