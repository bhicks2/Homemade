package com.homemade.homemade.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.homemade.homemade.R;

public class SingleRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        Intent intent = getIntent();

        String recipeName = intent.getStringExtra("RECIPE_NAME");

        ImageView imgView = (ImageView)findViewById(R.id.recipe_image);
        imgView.setImageResource(R.drawable.potato);
        TextView title = (TextView)findViewById(R.id.recipe_name);
        title.setText(recipeName);
    }
}
