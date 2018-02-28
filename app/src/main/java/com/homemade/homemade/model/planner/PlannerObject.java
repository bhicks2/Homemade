package com.homemade.homemade.model.planner;

import com.homemade.homemade.model.food.Recipe;

import java.io.Serializable;

/**
 * Created by chris on 2/27/2018.
 */

public class PlannerObject implements Serializable{

    private String name;
    private MealType mealType;
    private Recipe recipe;
    // eventually add a date object
    // private Date date;

    public PlannerObject(MealType mealType, Recipe recipe) {
        this.mealType = mealType;
        this.recipe = recipe;
        // add date here
    }

    // Get name
    public String getName() {
        return name;
    }

    // Set name
    public void setName(String name) {
        this.name = name;
    }

    // Get meal type
    public MealType getMealType() {
        return mealType;
    }

    // Set meal type
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    // Get recipe
    public Recipe getRecipe() {
        return recipe;
    }

    // Set recipe
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    // TODO: implement get/set date

}
