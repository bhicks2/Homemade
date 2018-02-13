package com.homemade.homemade.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.homemade.homemade.R;

public class SingleRecipe extends AppCompatActivity {

    private enum DisplayState {
        INGREDIENTS, INSTRUCTIONS, NUTRITION;
    }

    private static final int ACTIVE_COLOR = Color.CYAN;
    private static final int INACTIVE_COLOR = Color.GRAY;

    private static DisplayState state;

    private TextView textField;

    private Button ingredientsButton;
    private Button instructionsButton;
    private Button nutritionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        state = DisplayState.INSTRUCTIONS;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        Intent intent = getIntent();

        String recipeName = intent.getStringExtra("RECIPE_NAME");

        ImageView imgView = (ImageView)findViewById(R.id.recipe_image);
        imgView.setImageResource(R.drawable.potato);
        TextView title = (TextView)findViewById(R.id.recipe_name);
        title.setText(recipeName);

        textField = (TextView) findViewById(R.id.text_field);

        ingredientsButton = (Button) findViewById(R.id.ingredients_button);
        instructionsButton = (Button) findViewById(R.id.instructions_button);
        nutritionButton = (Button) findViewById(R.id.nutrition_button);

        ingredientsButton.setBackgroundColor(INACTIVE_COLOR);
        instructionsButton.setBackgroundColor(INACTIVE_COLOR);
        nutritionButton.setBackgroundColor(INACTIVE_COLOR);

        changeState(DisplayState.INGREDIENTS);

        ingredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If already showing ingredients, do nothing
                if(state == DisplayState.INGREDIENTS){
                    return;
                }

                changeState(DisplayState.INGREDIENTS);
            }
        });

        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If already showing ingredients, do nothing
                if(state == DisplayState.INSTRUCTIONS){
                    return;
                }

                changeState(DisplayState.INSTRUCTIONS);


            }
        });

        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If already showing ingredients, do nothing
                if(state == DisplayState.NUTRITION){
                    return;
                }

                changeState(DisplayState.NUTRITION);
            }
        });

        TextView editButton = (TextView) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Edit button pressed");
            }
        });
    }

    private void changeState(DisplayState newState){
        Button currentStateButton = findCurrentStateButton();

        currentStateButton.setBackgroundColor(INACTIVE_COLOR);

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
        textField.setText("I'm an ingredients display");

        state = DisplayState.INGREDIENTS;
        ingredientsButton.setBackgroundColor(ACTIVE_COLOR);
    }

    private void displayInstructions() {
        textField.setText("I'm an instructions display");

        state = DisplayState.INSTRUCTIONS;
        instructionsButton.setBackgroundColor(ACTIVE_COLOR);
    }

    private void displayNutrition() {
        textField.setText("I'm a nutrition display");

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
        }

        throw new RuntimeException("Unhandled DisplayState encountered; check all are accounted for.");
    }


}
