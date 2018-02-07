package com.homemade.homemade.model.measurement.converter;

import com.homemade.homemade.model.measurement.Measurement;
import com.homemade.homemade.model.measurement.Unit;
import com.homemade.homemade.model.measurement.UnitType;

/**
 * Created by brianhicks on 2/6/18.
 */

class MeasurementConverterHelper {

    private final UnitType unitTypeOne;
    private final UnitType unitTypeTwo;

    private double typeOneBaseToTypeTwoBaseConversionFactor;

    public MeasurementConverterHelper(Measurement measurementOne, Measurement measurementTwo){
        Unit unitOne = measurementOne.getUnit();
        Unit unitTwo = measurementOne.getUnit();

        unitTypeOne = unitOne.getType();
        unitTypeTwo = unitTwo.getType();

        double baseOne = unitOne.convertFromUnit(measurementOne.getQuantity());
        double baseTwo = unitTwo.convertFromUnit(measurementTwo.getQuantity());

        typeOneBaseToTypeTwoBaseConversionFactor = baseTwo / baseOne;
    }

    public Measurement convertTo(Measurement measurement, Unit newUnit){
        double originalQuantity = measurement.getQuantity();
        double originalBaseQuantity = measurement.getUnit().convertFromUnit(originalQuantity);

        double newBaseQuantity = 0.0;
        if(measurement.getUnit().getType() == unitTypeOne){
            if(newUnit.getType() == unitTypeTwo){
                newBaseQuantity = originalBaseQuantity * typeOneBaseToTypeTwoBaseConversionFactor;
            }
        } else if(measurement.getUnit().getType() == unitTypeTwo){
            if(newUnit.getType() == unitTypeOne){
                newBaseQuantity = originalBaseQuantity / typeOneBaseToTypeTwoBaseConversionFactor;
            }
        } else {
            throw new RuntimeException("This MeasurementConverterHelper can only convert between "
                    + unitTypeOne + " and " + unitTypeTwo);
        }

        double newQuantity = newUnit.convertToUnit(newBaseQuantity);
        return new Measurement(newQuantity, newUnit);
    }

}
