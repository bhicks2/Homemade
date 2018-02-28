package com.homemade.homemade.model.groceryList;

import com.homemade.homemade.model.food.Ingredient;

import java.io.Serializable;

/**
 * Created by chris on 2/27/2018.
 */

public class ShoppingIngredient implements Serializable{

    // Shopping Ingredient class includes an ingredient and a boolean whether or not the item has been purchased
    private Ingredient ingredient;
    private boolean purchased;

    public ShoppingIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.purchased = false;
    }

    // Get Ingredient
    public Ingredient getIngredient() {
        return ingredient;
    }

    // Set Ingredient
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    // Get purchased boolean
    public boolean getPurchased() {
        return purchased;
    }

    // Set purchased boolean
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
