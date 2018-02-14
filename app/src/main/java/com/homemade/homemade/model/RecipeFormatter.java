package com.homemade.homemade.model;

import com.homemade.homemade.model.food.Ingredient;
import com.homemade.homemade.model.food.NutritionFacts;
import com.homemade.homemade.model.food.Recipe;

import java.util.List;

/**
 * Created by brianhicks on 2/14/18.
 */

public class RecipeFormatter {

    public static String createIngredientText(Recipe recipe){
        if(recipe == null) return null;

        StringBuilder builder = new StringBuilder();

        List<Ingredient> ingredientList = recipe.getIngredientList();

        for(Ingredient i : ingredientList){
            builder.append(" - ");
            builder.append(i.getQuantity().toString());
            builder.append(" ");
            builder.append(i.getFoodInfo().getKey());
            builder.append("\n");
        }

        return builder.toString();
    }

    public static String createInstructionsText(Recipe recipe){
        if(recipe == null) return null;

        StringBuilder builder = new StringBuilder();

        List<String> instructions = recipe.getInstructions();

        for(int i = 0; i < instructions.size(); i++){
            builder.append(" ");
            builder.append(i + 1);
            builder.append(") ");
            builder.append(instructions.get(i));
            builder.append("\n");
        }

        return builder.toString();
    }

    public static String createNutritionText(Recipe recipe){
        if(recipe == null) return null;

        NutritionFacts facts = recipe.getNutritionFacts();
        if(facts == null){
            return "No nutrition information is available\n\nLikely, some of the nutrition information is missing in our database. Sorry about that!";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("(per serving;");
        builder.append(recipe.getNumberOfServings());
        builder.append("servings in recipe");
        



        return builder.toString();
    }
}
