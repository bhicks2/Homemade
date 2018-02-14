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
import com.homemade.homemade.model.food.Ingredient;
import com.homemade.homemade.model.food.Recipe;
import com.homemade.homemade.model.measurement.Measurement;
import com.homemade.homemade.model.measurement.Unit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final List<Recipe> RECIPE_DATABASE = new ArrayList<>();

    static {
        Recipe salsaRecipe = new Recipe();
        salsaRecipe.setName("Salsa");
        salsaRecipe.setCookTime(0);
        salsaRecipe.setPrepTime(10);
        salsaRecipe.setTotalTime(10);
        salsaRecipe.setSource("My grandfather");
        salsaRecipe.setNumberOfServings(6);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(AddRecipeIngredients.JALAPENO, new Measurement(1, Unit.COUNT)));
        ingredients.add(new Ingredient(AddRecipeIngredients.RED_ONION, new Measurement(0.25, Unit.COUNT)));
        ingredients.add(new Ingredient(AddRecipeIngredients.GARLIC_CLOVE, new Measurement(1, Unit.COUNT)));
        ingredients.add(new Ingredient(AddRecipeIngredients.CILANTRO, new Measurement(2, Unit.TABLESPOON)));
        ingredients.add(new Ingredient(AddRecipeIngredients.TOMATO, new Measurement(3, Unit.COUNT)));
        salsaRecipe.setIngredientList(ingredients);

        List<String> instructions = new ArrayList<>();
        instructions.add("Put all of the ingredients (except the tomatoes) into the food processor and pulse until minced.");
        instructions.add("Add tomatos and continue pulsing until the tomatos are roughly chopped");
        instructions.add("Store in an airtight container in the refrigerator for up to two days");
        salsaRecipe.setInstructions(instructions);

        RECIPE_DATABASE.add(salsaRecipe);
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
