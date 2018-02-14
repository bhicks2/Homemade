package com.homemade.homemade.model.measurement.converter;

import com.homemade.homemade.model.measurement.Measurement;
import com.homemade.homemade.model.measurement.Unit;
import com.homemade.homemade.model.measurement.UnitType;

import java.io.Serializable;

/**
 * Created by brianhicks on 2/6/18.
 */

public class MeasurementConverter implements Serializable {

    private MeasurementConverterHelper volumeMassConverter;
    private MeasurementConverterHelper volumeCountConverter;
    private MeasurementConverterHelper countMassConverter;

    public MeasurementConverter(){
        // left empty intentionally
    }

    public void defineEquivalence(Measurement measurementOne, Measurement measurementTwo){
        Unit unitOne = measurementOne.getUnit();
        Unit unitTwo = measurementOne.getUnit();

        UnitType unitOneType = unitOne.getType();
        UnitType unitTwoType = unitTwo.getType();

        if(unitOneType == unitTwoType){
            return; // silently ignore; if the same unit type is used, there is no need for unique
            // equivalence assignments (they are defined in the unit type itself
        }

        switch(unitOneType){
            case COUNT:
                switch(unitTwoType){
                    case MASS:
                        countMassConverter = new MeasurementConverterHelper(measurementOne, measurementTwo);
                        break;
                    case VOLUME:
                        volumeCountConverter = new MeasurementConverterHelper(measurementOne, measurementTwo);
                        break;
                }
                break;
            case VOLUME:
                switch(unitTwoType){
                    case MASS:
                        volumeMassConverter = new MeasurementConverterHelper(measurementOne, measurementTwo);
                        break;
                    case COUNT:
                        volumeCountConverter = new MeasurementConverterHelper(measurementOne, measurementTwo);
                        break;
                }
                break;
            case MASS:
                switch(unitTwoType){
                    case VOLUME:
                        volumeMassConverter = new MeasurementConverterHelper(measurementOne, measurementTwo);
                        break;
                    case COUNT:
                        countMassConverter = new MeasurementConverterHelper(measurementOne, measurementTwo);
                        break;
                }
                break;
        }
    }

    public Measurement convert(Measurement original, Unit newUnit){
        Unit originalUnit = original.getUnit();

        UnitType unitOneType = originalUnit.getType();
        UnitType unitTwoType = newUnit.getType();

        if(unitOneType == unitTwoType){
            double newUnitQuantity = newUnit.convertToUnit(originalUnit.convertFromUnit(original.getQuantity()));
            return new Measurement(newUnitQuantity, newUnit);
        }

        switch(unitOneType){
            case COUNT:
                switch(unitTwoType){
                    case MASS:
                        return countMassConverter.convertTo(original, newUnit);
                    case VOLUME:
                        return volumeCountConverter.convertTo(original, newUnit);
                }
                break;
            case VOLUME:
                switch(unitTwoType){
                    case MASS:
                        return volumeMassConverter.convertTo(original, newUnit);
                    case COUNT:
                        return volumeCountConverter.convertTo(original, newUnit);
                }
            case MASS:
                switch(unitTwoType){
                    case VOLUME:
                        return volumeMassConverter.convertTo(original, newUnit);
                    case COUNT:
                        return countMassConverter.convertTo(original, newUnit);
                }
        }

        throw new RuntimeException("This part of the code should not be reached");
    }

}
