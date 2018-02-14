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
import com.homemade.homemade.model.food.FoodInformation;
import com.homemade.homemade.model.food.Ingredient;

import java.util.List;

/**
 * Created by brianhicks on 2/13/18.
 */

public class IngredientDatabaseAdapter extends ArrayAdapter<FoodInformation> {
    public IngredientDatabaseAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public IngredientDatabaseAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public IngredientDatabaseAdapter(@NonNull Context context, int resource, @NonNull FoodInformation[] objects) {
        super(context, resource, objects);
    }

    public IngredientDatabaseAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull FoodInformation[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public IngredientDatabaseAdapter(@NonNull Context context, int resource, @NonNull List<FoodInformation> objects) {
        super(context, resource, objects);
    }

    public IngredientDatabaseAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<FoodInformation> objects) {
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

        FoodInformation ingredient = getItem(position);

        if(ingredient != null){
            TextView ingredientDisplay = (TextView) v.findViewById(R.id.ingredient_list_text);
            ingredientDisplay.setText(ingredient.getKey());
        }

        return v;
    }
}
