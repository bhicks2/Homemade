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
            builder.append(i + 1);
            builder.append(". ");
            builder.append(instructions.get(i));
            builder.append("\n\n");
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
        builder.append("(per serving; approximately ");
        builder.append(recipe.getNumberOfServings());
        builder.append(" servings in recipe)");
        builder.append("\n\n");

        Integer calories = facts.getCalories();
        if(calories != null) {
            builder.append("Calories: ");
            builder.append(calories);
            builder.append("\n\n");
        }

        Integer totalFat = facts.getTotalFat();
        if(totalFat != null) {
            builder.append("Total Fat (g): ");
            builder.append(facts.getTotalFat());
            builder.append("\n");

            Integer saturatedFat = facts.getSaturatedFat();
            if(saturatedFat != null) {
                builder.append("\tSaturated Fat (g): ");
                builder.append(saturatedFat);
                builder.append("\n");
            }

            Integer transFat = facts.getTransFat();
            if(transFat != null) {
                builder.append("\tTrans Fat (g.): ");
                builder.append(transFat);
                builder.append("\n");
            }
            builder.append("\n");
        }

        Integer totalCarbohydrates = facts.getTotalCarbohydrates();
        if(totalCarbohydrates != null){
            builder.append("Carbs (g.): ");
            builder.append(totalCarbohydrates);
            builder.append("\n");

            Integer dietaryFiber = facts.getDietaryFiber();
            if(dietaryFiber != null) {
                builder.append("\tDietary Fiber (g.): ");
                builder.append(dietaryFiber);
                builder.append("\n");
            }
            Integer totalSugar = facts.getTotalSugar();
            if(totalSugar != null){
                builder.append("\tTotal Sugar (g.): ");
                builder.append(totalSugar);
                builder.append("\n");

                Integer addedSugar = facts.getAddedSugar();
                if(addedSugar != null){
                    builder.append("\t\tAdded sugar (g.):");
                    builder.append(addedSugar);
                    builder.append("\n");
                }
            }

            builder.append("\n");
        }

        Integer protein = facts.getProtein();
        if(protein != null){
            builder.append("Protein (g.): ");
            builder.append(protein);
            builder.append("\n\n");
        }

        Integer sodium = facts.getSodium();
        if(sodium != null){
            builder.append("Sodium (mg.): ");
            builder.append(sodium);
            builder.append("\n\n");
        }

        Integer cholesterol = facts.getCholesterol();
        if(cholesterol != null){
            builder.append("Cholesterol (mg.): ");
            builder.append(cholesterol);
            builder.append("\n\n");
        }



        return builder.toString();
    }
}
