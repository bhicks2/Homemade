package com.homemade.homemade.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.homemade.homemade.R;
import com.homemade.homemade.activities.adapters.InstructionListAdapter;
import com.homemade.homemade.model.food.Recipe;

public class AddRecipeInstructions extends AppCompatActivity {

    private TextView saveButton;
    private TextView backButton;

    private EditText instructionField;
    private Button addInstructionButton;
    private ListView instructionView;
    private InstructionListAdapter instructionAdapter;

    private Recipe editingRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_instructions);

        createActivity();
        populateActivity();
    }

    private void createActivity() {
        saveButton = findViewById(R.id.instructions_next_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        backButton = findViewById(R.id.instructions_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        instructionField = findViewById(R.id.instruction_field);
        addInstructionButton = findViewById(R.id.instructions_add_instruction_button);
        instructionView = findViewById(R.id.instructions_instructions_list);

        addInstructionButton = findViewById(R.id.instructions_add_instruction_button);
        addInstructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInstruction();
            }
        });
    }

    private void populateActivity() {
        Intent intent = getIntent();
        editingRecipe = (Recipe) intent.getSerializableExtra("RECIPE");

        instructionAdapter = new InstructionListAdapter(getApplicationContext(), R.layout.instruction_list_element, editingRecipe.getInstructions());
        instructionView.setAdapter(instructionAdapter);
    }

    private void saveInstruction(){
        if(instructionField.getText() == null || instructionField.getText().length() == 0){
            Toast.makeText(this, "You must enter an instruction before pressing \"Add Instruction\"", Toast.LENGTH_SHORT).show();
            return;
        }

        String instruction = instructionField.getText().toString();
        System.out.println(instruction);
        editingRecipe.getInstructions().add(instruction);
        System.out.println("New instruction array size: " + editingRecipe.getInstructions().size());
        instructionAdapter.notifyDataSetChanged();

        instructionField.setText("");
    }

    private void goBack(){
        Intent intent = new Intent(this, AddRecipeIngredients.class);
        intent.putExtra("RECIPE", editingRecipe);
        startActivity(intent);
    }

    private void save(){
        if(editingRecipe.getInstructions().size() == 0){
            Toast.makeText(this, "There must be at least one instruction.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        int index = MainActivity.RECIPE_DATABASE.indexOf(editingRecipe);
        if(index < 0) {
            MainActivity.RECIPE_DATABASE.add(editingRecipe);
        } else {
            MainActivity.RECIPE_DATABASE.set(index, editingRecipe);
        }
        startActivity(intent);
    }

}
