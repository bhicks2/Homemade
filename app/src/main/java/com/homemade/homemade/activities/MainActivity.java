package com.homemade.homemade.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.homemade.homemade.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> recipesList = new ArrayList<>();
        recipesList.add("Potato");
        recipesList.add("Watermelon");
        recipesList.add("Qumquat");

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipesList);

        ListView recipes = (ListView) findViewById(R.id.recipe_list);
        recipes.setAdapter(adapter);

        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView recipes = (TextView) view;
                String recipeName = (String) recipes.getText();

                Toast.makeText(getApplicationContext(), recipeName + " selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SingleRecipe.class);
                intent.putExtra("RECIPE_NAME", recipeName);
                startActivity(intent);
            }
        });
    }

    public void goToRecipe(View view) {

        Intent intent = new Intent(this, SingleRecipe.class);
        startActivity(intent);
    }
}
