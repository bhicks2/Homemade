package com.homemade.homemade.model.food;

import com.homemade.homemade.model.measurement.Measurement;

/**
 * Created by brianhicks on 2/6/18.
 */

public class Ingredient implements Edible{

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
}
