package com.homemade.homemade.model.food;

import android.icu.util.Measure;

import com.homemade.homemade.model.measurement.Measurement;

import java.io.Serializable;

/**
 * Created by brianhicks on 2/6/18.
 */

public class Ingredient implements Edible, Serializable{

    private FoodInformation foodInfo;
    private Measurement quantity;

    public Ingredient(FoodInformation foodInfo, Measurement quantity){
        this.foodInfo = foodInfo;
        this.quantity = quantity;
    }

    @Override
    public NutritionFacts getNutritionFacts() {
        return foodInfo.getNutritionFactsForQuantity(quantity);
    }

    public FoodInformation getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(FoodInformation foodInfo) {
        this.foodInfo = foodInfo;
    }

    public Measurement getQuantity() {
        return quantity;
    }

    public void setQuantity(Measurement quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return quantity.toString() + " " + foodInfo.getKey();
    }
}
