package com.homemade.homemade.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.homemade.homemade.R;
import com.homemade.homemade.activities.AddIngredientDialog.AddIngredientDialogListener;
import com.homemade.homemade.activities.adapters.IngredientDatabaseAdapter;
import com.homemade.homemade.activities.adapters.IngredientListAdapter;
import com.homemade.homemade.model.food.FoodInformation;
import com.homemade.homemade.model.food.Ingredient;
import com.homemade.homemade.model.food.NutritionFacts;
import com.homemade.homemade.model.food.Recipe;
import com.homemade.homemade.model.measurement.Measurement;
import com.homemade.homemade.model.measurement.Unit;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeIngredients extends AppCompatActivity implements AddIngredientDialogListener {

    private TextView nextButton;
    private TextView backButton;

    private ListView ingredientDatabaseView;
    private ListView selectedIngredientsView;

    private Recipe editingRecipe;

    private static List<FoodInformation> availableIngredients;

    private ArrayAdapter<FoodInformation> ingredientDatabaseAdapter;
    private ArrayAdapter<Ingredient> selectedIngredientAdapter;

    static {
        availableIngredients = new ArrayList<>();

        Measurement jalapenoServing = new Measurement(1, Unit.COUNT);
        NutritionFacts jalapenoFacts = new NutritionFacts();
        FoodInformation jalapenoInfo = new FoodInformation("jalapeño", null, null, null);


        availableIngredients.add(jalapenoInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_ingredients);

        createActivity();
        populateActivity();
    }

    private void createActivity() {
        nextButton = (TextView) findViewById(R.id.ingredients_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAndMoveForward();
            }
        });
        backButton = (TextView) findViewById(R.id.ingredients_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities(AddRecipeMeta.class);
            }
        });

        ingredientDatabaseView = (ListView) findViewById(R.id.ingredients_ingredients_database);
        ingredientDatabaseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodInformation info = availableIngredients.get(position);
                openAddIngredientDialog(info);
            }
        });

        selectedIngredientsView = (ListView) findViewById(R.id.ingredients_added_ingredients_list);
    }

    private void populateActivity() {
        ingredientDatabaseAdapter = new IngredientDatabaseAdapter(getApplicationContext(), R.layout.ingredient_list_element, availableIngredients);
        ingredientDatabaseView.setAdapter(ingredientDatabaseAdapter);

        Intent intent = getIntent();
        editingRecipe = (Recipe) intent.getSerializableExtra("RECIPE");

        selectedIngredientAdapter = new IngredientListAdapter(getApplicationContext(), R.layout.ingredient_list_element, editingRecipe.getIngredientList());
        selectedIngredientsView.setAdapter(selectedIngredientAdapter);
    }


    private void openAddIngredientDialog(FoodInformation info){
        AddIngredientDialog dialog = new AddIngredientDialog();
        dialog.onAttach((Context) this);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("FOOD_NAME", info.getKey());
        dialog.setArguments(dataBundle);
        dialog.setFood(info);

        dialog.show(getFragmentManager(), "AddDialogFragment");
    }

    private void tryAndMoveForward(){
        String errorMessage = checkIfActivityCompleted();
        if(errorMessage != null){
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        switchActivities(AddRecipeInstructions.class);
    }

    private String checkIfActivityCompleted() {
        if(editingRecipe.getIngredientList().size() == 0){
            return "You must have at least one ingredient.";
        }

        return null;
    }

    private void switchActivities(Class<? extends Activity> activity){
        Intent intent = new Intent(this, activity);
        intent.putExtra("RECIPE", editingRecipe);
        startActivity(intent);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment fragment) {
        Dialog dialog = fragment.getDialog();
        Spinner spinner = (Spinner) dialog.findViewById(R.id.unit_selector);

        EditText quantityField = (EditText) dialog.findViewById(R.id.quantity);
        String quantityString = quantityField.getText().toString();
        double quantity = Double.parseDouble(quantityString);

        Unit unit = (Unit) spinner.getSelectedItem();

        Measurement measurement = new Measurement(quantity, unit);
        FoodInformation foodInfo = ((AddIngredientDialog) fragment).getFood();

        editingRecipe.getIngredientList().add(new Ingredient(foodInfo, measurement));
        selectedIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment fragment) {
        // purposely left empty
    }
}
