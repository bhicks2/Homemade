package com.homemade.homemade.model.measurement;

/**
 * Created by brianhicks on 2/6/18.
 */

public class Measurement {

    private final double quantity;
    private final Unit unit;

    public Measurement(double quantity, Unit unit){
        this.quantity = quantity;
        this.unit = unit;
    }

    public double getQuantity(){
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }
}
