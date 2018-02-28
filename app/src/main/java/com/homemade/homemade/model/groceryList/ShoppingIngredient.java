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

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
