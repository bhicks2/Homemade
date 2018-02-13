package com.homemade.homemade.model.food;

import java.io.Serializable;
import java.util.List;

/**
 * Created by brianhicks on 2/6/18.
 */

public class Recipe implements Edible, Serializable {

    private String key;
    private String name;
    private int numberOfServings;
    private List<Ingredient> ingredientList;
    private List<String> instructions;

    public Recipe(String name, int numberOfServings, List<Ingredient> ingredientList, List<String> instructions){
        this.name = name;
        this.numberOfServings = numberOfServings;
        this.ingredientList = ingredientList;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    @Override
    public NutritionFacts getNutritionFacts() {
        NutritionFacts total = NutritionFacts.zeroFacts();
        for(Ingredient ing : ingredientList){
            total = NutritionFacts.add(total, ing.getNutritionFacts());
        }

        double scaleFactor = 1.0 / numberOfServings;

        return NutritionFacts.scale(total, scaleFactor);
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
