package com.homemade.homemade.model.food;

import com.homemade.homemade.model.measurement.Measurement;
import com.homemade.homemade.model.measurement.Unit;
import com.homemade.homemade.model.measurement.converter.MeasurementConverter;

import java.io.Serializable;

/**
 * Created by brianhicks on 2/6/18.
 */

public class FoodInformation implements Serializable {

    private final String key;
    private Measurement servingSize;
    private MeasurementConverter measurementConverter;
    private NutritionFacts nutritionFacts;

    public FoodInformation(String key, Measurement servingSize, MeasurementConverter measurementConverter,
                           NutritionFacts nutritionFacts){
        this.key = key;
        this.servingSize = servingSize;
        this.measurementConverter = measurementConverter;
        this.nutritionFacts = nutritionFacts;
    }

    public NutritionFacts getNutritionFactsForQuantity(Measurement quantity){
        Unit servingSizeUnit = servingSize.getUnit();
        double servingSizeQuantity = servingSize.getQuantity();

        Measurement convertedQuantity = measurementConverter.convert(quantity, servingSizeUnit);
        double scaleFactor = convertedQuantity.getQuantity() / servingSizeQuantity;

        return NutritionFacts.scale(nutritionFacts, scaleFactor);
    }

    public String getKey() {
        return key;
    }

    public Measurement getServingSize() {
        return servingSize;
    }

    public void setServingSize(Measurement servingSize) {
        this.servingSize = servingSize;
    }

    public MeasurementConverter getMeasurementConverter() {
        return measurementConverter;
    }

    public void setMeasurementConverter(MeasurementConverter measurementConverter) {
        this.measurementConverter = measurementConverter;
    }

    public NutritionFacts getNutritionFacts() {
        return nutritionFacts;
    }

    public void setNutritionFacts(NutritionFacts nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }

    @Override
    public String toString(){
        return getKey();
    }
}

