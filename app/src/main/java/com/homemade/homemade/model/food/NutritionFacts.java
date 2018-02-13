package com.homemade.homemade.model.food;

import java.io.Serializable;

/**
 * Created by brianhicks on 2/6/18.
 */

public class NutritionFacts implements Serializable {

    // use of Integers instead of ints allows us to distinguish between no value and 0.
    private Integer calories;

    private Integer totalFat; // grams
    private Integer saturatedFat; // grams
    private Integer transFat; // grams

    private Integer cholesterol; // milligrams
    private Integer sodium; // milligrams

    private Integer totalCarbohydrates; // grams
    private Integer dietaryFiber; // grams
    private Integer totalSugar; // grams
    private Integer addedSugar; //grams

    private Integer protein; // grams

    public NutritionFacts(){
        // left blank
    }

    public static NutritionFacts scale(NutritionFacts facts, double scaleFactor){
        NutritionFacts result = new NutritionFacts();

        if(facts.calories != null){
            result.setCalories((int) Math.round(facts.calories * scaleFactor));
        }

        if(facts.totalFat != null){
            result.setTotalFat((int) Math.round(facts.totalFat * scaleFactor));
        }

        if(facts.transFat != null){
            result.setTransFat((int) Math.round(facts.transFat * scaleFactor));
        }

        if(facts.cholesterol != null){
            result.setCholesterol((int) Math.round(facts.cholesterol * scaleFactor));
        }

        if(facts.sodium != null){
            result.setSodium((int) Math.round(facts.sodium * scaleFactor));
        }

        if(facts.totalCarbohydrates != null){
            result.setTotalCarbohydrates((int) Math.round(facts.totalCarbohydrates * scaleFactor));
        }

        if(facts.dietaryFiber != null){
            result.setDietaryFiber((int) Math.round(facts.dietaryFiber * scaleFactor));
        }

        if(facts.totalSugar != null){
            result.setTotalSugar((int) Math.round(facts.totalSugar * scaleFactor));
        }

        if(facts.addedSugar != null){
            result.setAddedSugar((int) Math.round(facts.addedSugar * scaleFactor));
        }

        if(facts.protein != null){
            result.setProtein((int) Math.round(facts.protein * scaleFactor));
        }

        return result;
    }

    public static NutritionFacts add(NutritionFacts factsOne, NutritionFacts factsTwo){
        NutritionFacts result = new NutritionFacts();

        if(factsOne.calories != null && factsTwo.calories != null){
            result.setCalories(factsOne.calories + factsTwo.calories);
        }

        if(factsOne.totalFat != null && factsTwo.totalFat != null){
            result.setTotalFat(factsOne.totalFat + factsTwo.totalFat);
        }

        if(factsOne.saturatedFat != null && factsTwo.saturatedFat != null){
            result.setSaturatedFat(factsOne.saturatedFat + factsTwo.saturatedFat);
        }

        if(factsOne.saturatedFat != null && factsTwo.saturatedFat != null){
            result.setSaturatedFat(factsOne.saturatedFat + factsTwo.saturatedFat);
        }

        if(factsOne.transFat != null && factsTwo.transFat != null){
            result.setTransFat(factsOne.transFat + factsTwo.transFat);
        }

        if(factsOne.cholesterol != null && factsTwo.cholesterol != null){
            result.setCholesterol(factsOne.cholesterol+ factsTwo.cholesterol);
        }

        if(factsOne.sodium != null && factsTwo.sodium != null){
            result.setSodium(factsOne.sodium + factsTwo.sodium);
        }

        if(factsOne.totalCarbohydrates != null && factsTwo.totalCarbohydrates != null){
            result.setTotalCarbohydrates(factsOne.totalCarbohydrates + factsTwo.totalCarbohydrates);
        }

        if(factsOne.dietaryFiber != null && factsTwo.dietaryFiber != null){
            result.setDietaryFiber(factsOne.dietaryFiber + factsTwo.dietaryFiber);
        }

        if(factsOne.totalSugar != null && factsTwo.totalSugar != null){
            result.setTotalSugar(factsOne.totalSugar + factsTwo.totalSugar);
        }

        if(factsOne.addedSugar != null && factsTwo.addedSugar != null){
            result.setAddedSugar(factsOne.addedSugar + factsTwo.addedSugar);
        }

        if(factsOne.protein != null && factsTwo.protein != null){
            result.setProtein(factsOne.protein + factsTwo.protein);
        }

        return result;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Integer totalFat) {
        this.totalFat = totalFat;
    }

    public Integer getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Integer saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Integer getTransFat() {
        return transFat;
    }

    public void setTransFat(Integer transFat) {
        this.transFat = transFat;
    }

    public Integer getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(Integer totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public Integer getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(Integer dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public Integer getTotalSugar() {
        return totalSugar;
    }

    public void setTotalSugar(Integer totalSugar) {
        this.totalSugar = totalSugar;
    }

    public Integer getAddedSugar() {
        return addedSugar;
    }

    public void setAddedSugar(Integer addedSugar) {
        this.addedSugar = addedSugar;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public static NutritionFacts zeroFacts() {
        NutritionFacts facts = new NutritionFacts();

        facts.setCalories(0);

        facts.setTotalFat(0);
        facts.setSaturatedFat(0);
        facts.setTransFat(0);

        facts.setCholesterol(0);
        facts.setSodium(0);

        facts.setTotalCarbohydrates(0);
        facts.setDietaryFiber(0);
        facts.setTotalSugar(0);
        facts.setAddedSugar(0);

        facts.setProtein(0);

        return facts;
    }
}
