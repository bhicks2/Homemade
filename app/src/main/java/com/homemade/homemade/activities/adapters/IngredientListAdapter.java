package com.homemade.homemade.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.homemade.homemade.R;
import com.homemade.homemade.model.food.Ingredient;

import java.util.List;

/**
 * Created by brianhicks on 2/13/18.
 */

public class IngredientListAdapter extends ArrayAdapter<Ingredient> {
    public IngredientListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public IngredientListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public IngredientListAdapter(@NonNull Context context, int resource, @NonNull Ingredient[] objects) {
        super(context, resource, objects);
    }

    public IngredientListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Ingredient[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public IngredientListAdapter(@NonNull Context context, int resource, @NonNull List<Ingredient> objects) {
        super(context, resource, objects);
    }

    public IngredientListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Ingredient> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.ingredient_list_element, null);
        }

        Ingredient ingredient = getItem(position);

        if(ingredient != null){
            TextView ingredientDisplay = (TextView) v.findViewById(R.id.ingredient_list_text);
            ingredientDisplay.setText(ingredient.toString());
        }

        return v;
    }
}
