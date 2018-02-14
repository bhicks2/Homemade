package com.homemade.homemade.model.measurement;

import java.util.ResourceBundle;

/**
 * Created by brianhicks on 2/6/18.
 */

public enum Unit {

    // Volume Units
    TEASPOON("Teaspoon", "tsp.", UnitType.VOLUME, 1.0), // base unit for volume
    TABLESPOON("Tablespoon", "tbsp.", UnitType.VOLUME, 3.0),
    FLUID_OUNCE("Fluid Ounce", "fl. oz.", UnitType.VOLUME, 6.0),
    CUP("Cup", "C.", UnitType.VOLUME, 48.0),
    PINT("Pint", "pt.", UnitType.VOLUME, 96.0),
    QUART("Quart", "qt.", UnitType.VOLUME, 192.0),
    GALLON("Gallon", "gal.", UnitType.VOLUME, 768.0),

    // Mass Units
    OUNCE("Ounce", "oz.", UnitType.MASS, 1.0), // base unit for mass
    POUND("Pound", "lb.", UnitType.MASS, 16.0),

    // Count Units
    COUNT("Count", "", UnitType.COUNT, 1.0); // base unit for count

    private final String name;
    private final String abbr;
    private final UnitType type;
    private final double unitToBaseConversionFactor;

    Unit(String name, String abbreviation, UnitType type, double conversionFactor){
        this.name = name;
        this.abbr = abbreviation;
        this.type = type;
        this.unitToBaseConversionFactor = conversionFactor;
    }

    public String getAbbreviation(){
        return abbr;
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

    @Override
    public String toString(){
        return name;
    }
}

