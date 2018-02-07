package com.homemade.homemade.model.measurement;

/**
 * Created by brianhicks on 2/6/18.
 */

public enum Unit {

    // Volume Units
    TEASPOON("teaspoon", UnitType.VOLUME, 1.0), // base unit for volume
    TABLESPOON("tablespoon", UnitType.VOLUME, 3.0),
    FLUID_OUNCE("fluid_ounce", UnitType.VOLUME, 6.0),
    CUP("cup", UnitType.VOLUME, 48.0),
    PINT("pint", UnitType.VOLUME, 96.0),
    QUART("quart", UnitType.VOLUME, 192.0),
    GALLON("gallon", UnitType.VOLUME, 768.0),

    // Mass Units
    OUNCE("ounce", UnitType.MASS, 1.0), // base unit for mass
    POUND("pound", UnitType.MASS, 16.0),

    // Count Units
    COUNT("count", UnitType.COUNT, 1.0); // base unit for count

    private final String key;
    private final UnitType type;
    private final double unitToBaseConversionFactor;

    Unit(String key, UnitType type, double conversionFactor){
        this.key = key;
        this.type = type;
        this.unitToBaseConversionFactor = conversionFactor;
    }

    public double convertToUnit(double baseQuantity){
        return baseQuantity / unitToBaseConversionFactor;
    }

    public double convertFromUnit(double unitQuantity){
        return unitQuantity * unitToBaseConversionFactor;
    }

    public UnitType getType() {
        return type;
    }
}

