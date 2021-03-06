package com.homemade.homemade.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.homemade.homemade.R;
import com.homemade.homemade.model.RecipeFormatter;
import com.homemade.homemade.model.food.Recipe;

public class SingleRecipe extends AppCompatActivity {

    private enum DisplayState {
        UNINITIALIZED, INGREDIENTS, INSTRUCTIONS, NUTRITION;
    }

    private static final int ACTIVE_COLOR = Color.CYAN;
    private static final int INACTIVE_COLOR = Color.GRAY;

    private static DisplayState state;

    private TextView textField;

    private Button ingredientsButton;
    private Button instructionsButton;
    private Button nutritionButton;

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("RECIPE");

        constructActivity();
        populateActivity();

        TextView editButton = (TextView) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddRecipe(recipe);
            }
        });
    }

    private void constructActivity(){
        textField = (TextView) findViewById(R.id.text_field);

        ingredientsButton = (Button) findViewById(R.id.ingredients_button);
        instructionsButton = (Button) findViewById(R.id.instructions_button);
        nutritionButton = (Button) findViewById(R.id.nutrition_button);

        ingredientsButton.setBackgroundColor(INACTIVE_COLOR);
        instructionsButton.setBackgroundColor(INACTIVE_COLOR);
        nutritionButton.setBackgroundColor(INACTIVE_COLOR);

        state = DisplayState.UNINITIALIZED;

        createAllDisplayStateButtonListeners();
        changeState(DisplayState.INGREDIENTS);
    }

    private void populateActivity(){
        ImageView imgView = (ImageView)findViewById(R.id.recipe_image);
        imgView.setImageResource(R.drawable.potato);

        TextView title = (TextView)findViewById(R.id.recipe_name);
        title.setText(recipe.getName());
    }

    private void goToAddRecipe(Recipe recipe){
        Intent intent = new Intent(this, AddRecipeMeta.class);
        intent.putExtra("RECIPE", recipe);
        startActivity(intent);
    }

    private void createAllDisplayStateButtonListeners() {
        createDisplayStateButtonListener(ingredientsButton, DisplayState.INGREDIENTS);
        createDisplayStateButtonListener(instructionsButton, DisplayState.INSTRUCTIONS);
        createDisplayStateButtonListener(nutritionButton, DisplayState.NUTRITION);
    }

    private void createDisplayStateButtonListener(Button button, final DisplayState newState){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // If state already shown, do nothing
                if(state == newState){
                    return;
                }

                changeState(newState);
            }
        });
    }

    private void changeState(DisplayState newState){
        Button currentStateButton = findCurrentStateButton();
        if(currentStateButton != null) {
            currentStateButton.setBackgroundColor(INACTIVE_COLOR);
        }

        switch(newState){
            case INGREDIENTS:
                displayIngredients();
                return;
            case NUTRITION:
                displayNutrition();
                return;
            case INSTRUCTIONS:
                displayInstructions();
                return;
        }
    }

    private void displayIngredients() {
        String ingredientsText = RecipeFormatter.createIngredientText(recipe);
        textField.setText(ingredientsText);

        state = DisplayState.INGREDIENTS;
        ingredientsButton.setBackgroundColor(ACTIVE_COLOR);
    }

    private void displayInstructions() {
        String instructionsText = RecipeFormatter.createInstructionsText(recipe);
        textField.setText(instructionsText);

        state = DisplayState.INSTRUCTIONS;
        instructionsButton.setBackgroundColor(ACTIVE_COLOR);
    }

    private void displayNutrition() {
        String nutritionText = RecipeFormatter.createNutritionText(recipe);
        textField.setText(nutritionText);

        state = DisplayState.NUTRITION;
        nutritionButton.setBackgroundColor(ACTIVE_COLOR);
    }

    private Button findCurrentStateButton(){
        switch(state){
            case INGREDIENTS:
                return ingredientsButton;
            case INSTRUCTIONS:
                return instructionsButton;
            case NUTRITION:
                return nutritionButton;
            case UNINITIALIZED:
                return null;
        }

        throw new RuntimeException("Unhandled DisplayState encountered; check all are accounted for.");
    }


}
