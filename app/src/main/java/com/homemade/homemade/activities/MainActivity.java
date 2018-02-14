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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final List<Recipe> RECIPE_DATABASE = new ArrayList<>();

    static {
        Recipe recipe = new Recipe();
        recipe.setName("Salmonella");
        recipe.setNumberOfServings(1);
        Recipe recipe2 = new Recipe();
        recipe2.setName("Dysentery");
        recipe2.setNumberOfServings(2);

        RECIPE_DATABASE.add(recipe);
        RECIPE_DATABASE.add(recipe2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter adapter = new RecipeListAdapter(this, R.layout.recipe_list_element, RECIPE_DATABASE);

        ListView recipes = (ListView) findViewById(R.id.recipe_list);
        recipes.setAdapter(adapter);

        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout element = (LinearLayout) view;
                TextView titleView = element.findViewById(R.id.recipe_list_element_title);
                Recipe selectedRecipe = RECIPE_DATABASE.get(position);

                Toast.makeText(getApplicationContext(), selectedRecipe.getName() + " selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SingleRecipe.class);
                intent.putExtra("RECIPE", selectedRecipe);
                startActivity(intent);
            }
        });

        TextView addRecipeButton = (TextView) findViewById(R.id.main_add_button);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddRecipeMeta.class);
                startActivity(intent);
            }
        });
    }
}
