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
import com.homemade.homemade.model.measurement.converter.MeasurementConverter;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeIngredients extends AppCompatActivity implements AddIngredientDialogListener {

    public static final FoodInformation JALAPENO;
    public static final FoodInformation RED_ONION;
    public static final FoodInformation GARLIC_CLOVE;
    public static final FoodInformation CILANTRO;
    public static final FoodInformation TOMATO;

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
        jalapenoFacts.setCalories(4);
        jalapenoFacts.setProtein(0);
        jalapenoFacts.setTotalCarbohydrates(1);
        jalapenoFacts.setTotalFat(0);
        FoodInformation jalapenoInfo = new FoodInformation("Jalapeño", jalapenoServing, new MeasurementConverter(), jalapenoFacts);
        JALAPENO = jalapenoInfo;

        Measurement redOnionServing = new Measurement(1, Unit.COUNT);
        NutritionFacts redOnionFacts = new NutritionFacts();
        redOnionFacts.setCalories(66);
        redOnionFacts.setProtein(2);
        redOnionFacts.setTotalCarbohydrates(14);
        redOnionFacts.setTotalFat(0);
        FoodInformation redOnionInfo = new FoodInformation("Red Onion", redOnionServing, new MeasurementConverter(), redOnionFacts);
        RED_ONION = redOnionInfo;

        Measurement garlicCloveServing = new Measurement(1, Unit.COUNT);
        NutritionFacts garlicCloveFacts = new NutritionFacts();
        garlicCloveFacts.setCalories(4);
        garlicCloveFacts.setProtein(0);
        garlicCloveFacts.setTotalCarbohydrates(1);
        garlicCloveFacts.setTotalFat(0);
        FoodInformation garlicCloveInfo = new FoodInformation("Garlic Clove", garlicCloveServing, new MeasurementConverter(), garlicCloveFacts);
        GARLIC_CLOVE = garlicCloveInfo;

        Measurement cilantroServing = new Measurement(0.25, Unit.CUP);
        NutritionFacts cilantroFacts = new NutritionFacts();
        cilantroFacts.setCalories(1);
        cilantroFacts.setProtein(0);
        cilantroFacts.setTotalCarbohydrates(0);
        cilantroFacts.setTotalFat(0);
        FoodInformation cilantroInfo = new FoodInformation("Cilantro", cilantroServing, new MeasurementConverter(), cilantroFacts);
        CILANTRO = cilantroInfo;

        Measurement tomatoServing = new Measurement(1, Unit.COUNT);
        NutritionFacts tomatoFacts = new NutritionFacts();
        tomatoFacts.setCalories(22);
        tomatoFacts.setProtein(1);
        tomatoFacts.setTotalCarbohydrates(5);
        tomatoFacts.setTotalFat(0);
        FoodInformation tomatoInfo = new FoodInformation("Tomato", tomatoServing, new MeasurementConverter(), tomatoFacts);
        TOMATO = tomatoInfo;

        Measurement eggServing = new Measurement(1, Unit.COUNT);
        NutritionFacts eggFacts = new NutritionFacts();
        eggFacts.setCalories(78);
        eggFacts.setProtein(6);
        eggFacts.setTotalCarbohydrates(1);
        eggFacts.setTotalFat(5);
        FoodInformation eggInfo = new FoodInformation("Egg (Large)", eggServing, new MeasurementConverter(), eggFacts);

        Measurement wholeMilkServing = new Measurement(1, Unit.CUP);
        NutritionFacts wholeMilkFacts = new NutritionFacts();
        wholeMilkFacts.setCalories(148);
        wholeMilkFacts.setProtein(8);
        wholeMilkFacts.setTotalCarbohydrates(12);
        wholeMilkFacts.setTotalFat(8);
        FoodInformation wholeMilkInfo = new FoodInformation("Milk (Large)", wholeMilkServing, new MeasurementConverter(), wholeMilkFacts);


        availableIngredients.add(jalapenoInfo);
        availableIngredients.add(redOnionInfo);
        availableIngredients.add(garlicCloveInfo);
        availableIngredients.add(cilantroInfo);
        availableIngredients.add(tomatoInfo);
        availableIngredients.add(eggInfo);
        availableIngredients.add(wholeMilkInfo);
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
