package com.homemade.homemade.model.food;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianhicks on 2/6/18.
 */

public class Recipe implements Edible, Serializable, Comparable<Recipe> {

    private String name;
    private String source;
    private int numberOfServings;

    private Integer prepTime;
    private Integer cookTime;
    private Integer totalTime;

    private List<Ingredient> ingredientList;
    private List<String> instructions;

    public Recipe(){
        ingredientList = new ArrayList<>();
        instructions = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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


    @Override
    public int compareTo(@NonNull Recipe o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return name != null ? name.equals(recipe.name) : recipe.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
