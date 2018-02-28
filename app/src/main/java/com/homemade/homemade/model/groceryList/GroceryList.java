package com.homemade.homemade.model.groceryList;

import com.homemade.homemade.model.food.FoodInformation;
import com.homemade.homemade.model.food.Ingredient;
import com.homemade.homemade.model.measurement.Measurement;
import com.homemade.homemade.model.measurement.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 2/27/2018.
 */

public class GroceryList implements Serializable{

    // Basic grocery list implementation contains name and list of shopping ingredients
    private String name;
    private List<ShoppingIngredient> shoppingList;

    public GroceryList(String name) {
        this.name = name;
        this.shoppingList = new ArrayList<>();
    }

    // Get name of grocery list
    public String getName() {
        return name;
    }

    // Set name of grocery list
    public void setName(String name) {
        this.name = name;
    }

    // Get list of shopping ingredients
    public List<ShoppingIngredient> getShoppingList() {
        return shoppingList;
    }

    // Set list of shopping ingredients
    public void setShoppingList(ArrayList<ShoppingIngredient> shoppingList) {
        this.shoppingList = shoppingList;
    }

    // Add an ingredient to the shopping list
    public void addIngredient(Ingredient ingredient) {
        ShoppingIngredient shoppingIngredient = new ShoppingIngredient(ingredient);
        shoppingList.add(shoppingIngredient);
    }

    // Remove an ingredient from shopping list by name (first instance)
    public void removeIngredient(String ingredientName) {

        // Iterate through each shopping ingredient, remove first instance if ingredient name matches
        for (int i = 0; i < shoppingList.size(); i++) {

            ShoppingIngredient shoppingIngredient = shoppingList.get(i);
            Ingredient ingredient = shoppingIngredient.getIngredient();
            FoodInformation foodInformation = ingredient.getFoodInfo();

            if (foodInformation.getKey().equals(ingredientName)) {
                shoppingList.remove(i);
                return;
            }
        }
    }

    // Modify a shopping list ingredient's quantity by name (first instance)
    public void modifyIngredientQuantity(String ingredientName, double newQuantityValue) {

        // Iterate through each shopping ingredient, if ingredient name matches, set new quantity
        for (int i = 0; i < shoppingList.size(); i++) {

            ShoppingIngredient shoppingIngredient = shoppingList.get(i);
            Ingredient ingredient = shoppingIngredient.getIngredient();
            FoodInformation foodInformation = ingredient.getFoodInfo();

            if (foodInformation.getKey().equals(ingredientName)) {

                Measurement quantity = ingredient.getQuantity();
                Unit unit = quantity.getUnit();

                Measurement newQuantity = new Measurement(newQuantityValue, unit);
                ingredient.setQuantity(newQuantity);
                shoppingIngredient.setIngredient(ingredient);

                shoppingList.set(i, shoppingIngredient);
                return;
            }
        }
    }

    // Modify a shopping list ingredient's purchased boolean by name (first instance)
    public void modifyIngredientPurchased(String ingredientName, boolean newPurchasedValue) {

        // Iterate through each shopping ingredient, if ingredient name matches, set new purchased boolean
        for (int i = 0; i < shoppingList.size(); i++) {

            ShoppingIngredient shoppingIngredient = shoppingList.get(i);
            Ingredient ingredient = shoppingIngredient.getIngredient();
            FoodInformation foodInformation = ingredient.getFoodInfo();

            if (foodInformation.getKey().equals(ingredientName)) {

                shoppingIngredient.setPurchased(newPurchasedValue);
                shoppingList.set(i, shoppingIngredient);
                return;
            }
        }
    }


}
