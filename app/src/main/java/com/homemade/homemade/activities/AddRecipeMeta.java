package com.homemade.homemade.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.homemade.homemade.R;
import com.homemade.homemade.model.food.Recipe;

import org.w3c.dom.Text;

public class AddRecipeMeta extends AppCompatActivity {


    private EditText titleField;
    private EditText sourceField;
    private EditText prepTimeField;
    private EditText cookTimeField;
    private EditText totalTimeField;
    private EditText servingsField;

    private Recipe editingRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_meta);

        createActivity();

        editingRecipe = retrieveRecipeFromIntent();
        populateActivity();
    }

    private void createActivity() {
        titleField = (EditText) findViewById(R.id.title_field);
        sourceField = (EditText) findViewById(R.id.source_field);
        prepTimeField = (EditText) findViewById(R.id.prep_time_field);
        cookTimeField = (EditText) findViewById(R.id.cook_time_field);
        totalTimeField = (EditText) findViewById(R.id.total_time_field);
        servingsField = (EditText) findViewById(R.id.servings_field);
    }

    private void populateActivity() {
        if(editingRecipe != null) {
            titleField.setText(editingRecipe.getName());
            servingsField.setText(Integer.toString(editingRecipe.getNumberOfServings()));

            if(editingRecipe.getSource() != null){
                sourceField.setText(editingRecipe.getSource());
            }

            if(editingRecipe.getPrepTime() != null){
                prepTimeField.setText(editingRecipe.getPrepTime().toString());
            }

            if(editingRecipe.getCookTime() != null){
                cookTimeField.setText(editingRecipe.getCookTime().toString());
            }

            if(editingRecipe.getTotalTime() != null){
                totalTimeField.setText(editingRecipe.getTotalTime().toString());
            }
        }


        TextView nextButton = (TextView) findViewById(R.id.meta_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToMoveToNextActivity();
            }
        });

        TextView cancelButton = (TextView) findViewById(R.id.meta_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Recipe retrieveRecipeFromIntent() {
        Intent intent = getIntent();
        if(!intent.hasExtra("RECIPE")){
            return new Recipe();
        }

        return (Recipe) intent.getSerializableExtra("RECIPE");
    }

    private void tryToMoveToNextActivity(){
        String errorMessage = checkIfActivityCompleted();
        if(errorMessage != null){
            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        updateRecipe();
        goToAddRecipeIngredients();
    }

    private void updateRecipe() {
        String newTitle = titleField.getText().toString();
        editingRecipe.setName(newTitle);

        int newServings = Integer.parseInt(servingsField.getText().toString());
        editingRecipe.setNumberOfServings(newServings);

        if(sourceField.getText() != null && sourceField.getText().length() > 0){
            String newSource = sourceField.getText().toString();
            editingRecipe.setSource(newSource);
        }

        if(prepTimeField.getText() != null && prepTimeField.getText().length() > 0){
            String newPrepTimeString = prepTimeField.getText().toString();
            Integer newPrepTime = Integer.parseInt(newPrepTimeString);
            editingRecipe.setPrepTime(newPrepTime);
        }

        if(cookTimeField.getText() != null && cookTimeField.getText().length() > 0){
            String newCookTimeString = cookTimeField.getText().toString();
            Integer newCookTime = Integer.parseInt(newCookTimeString);
            editingRecipe.setCookTime(newCookTime);
        }

        if(totalTimeField.getText() != null && totalTimeField.getText().length() > 0){
            String newTotalTimeString = totalTimeField.getText().toString();
            Integer newTotalTime = Integer.parseInt(newTotalTimeString);
            editingRecipe.setTotalTime(newTotalTime);
        }
    }

    private String checkIfActivityCompleted(){
        if(titleField.getText() == null || titleField.getText().length() == 0){
            return "You must give the recipe a title.";
        }

        if(servingsField.getText() == null || servingsField.getText().length() == 0){
            return "You must provide the number of servings.";
        }

        return null;
    }

    private void goToAddRecipeIngredients(){
        Intent intent = new Intent(this, AddRecipeIngredients.class);
        System.out.println("Sending: " + editingRecipe);
        intent.putExtra("RECIPE", editingRecipe);
        startActivity(intent);
    }


}
