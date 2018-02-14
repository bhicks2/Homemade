package com.homemade.homemade.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.homemade.homemade.R;
import com.homemade.homemade.model.food.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianhicks on 2/11/18.
 */

public class RecipeListAdapter extends ArrayAdapter<Recipe> {
    public RecipeListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public RecipeListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public RecipeListAdapter(@NonNull Context context, int resource, @NonNull Recipe[] objects) {
        super(context, resource, objects);
    }

    public RecipeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Recipe[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public RecipeListAdapter(@NonNull Context context, int resource, @NonNull List<Recipe> objects) {
        super(context, resource, objects);
    }

    public RecipeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Recipe> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        if(v == null){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.recipe_list_element, null);
        }

        Recipe recipe = getItem(position);

        if(recipe != null){
            ImageView image = v.findViewById(R.id.recipe_list_element_image);
            TextView title = v.findViewById(R.id.recipe_list_element_title);
            TextView totalTime = v.findViewById(R.id.recipe_list_total_time);

            image.setImageResource(R.drawable.potato);

            title.setText(recipe.getName());

            if(recipe.getTotalTime() != null) {
                String text = "Total Time: " + recipe.getTotalTime();
                totalTime.setText(text);
            }
        }

        return v;
    }



}
