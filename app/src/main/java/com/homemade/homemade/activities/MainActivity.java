package com.homemade.homemade.activities;

import android.content.Intent;
import android.sax.Element;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.homemade.homemade.R;
import com.homemade.homemade.activities.adapters.RecipeListAdapter;
import com.homemade.homemade.model.food.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Recipe> recipesList = new ArrayList<>();

        Recipe recipe = new Recipe("Salmonella", 1, null, null);
        Recipe recipe2 = new Recipe("Dysentery", 1, null, null);

        recipesList.add(recipe);
        recipesList.add(recipe2);

        ListAdapter adapter = new RecipeListAdapter(this, R.layout.recipe_list_element, recipesList);

        ListView recipes = (ListView) findViewById(R.id.recipe_list);
        recipes.setAdapter(adapter);

        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout element = (LinearLayout) view;
                TextView titleView = element.findViewById(R.id.recipe_list_element_title);
                String recipeName = (String) titleView.getText();

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
