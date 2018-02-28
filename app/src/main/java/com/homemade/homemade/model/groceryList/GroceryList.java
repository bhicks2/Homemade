package com.homemade.homemade.model.groceryList;

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
        shoppingList = new ArrayList<>();
    }


}
