package com.homemade.homemade.model.measurement;

import java.io.Serializable;

/**
 * Created by brianhicks on 2/6/18.
 */

public class Measurement implements Serializable {

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

    @Override
    public String toString(){
        return Double.toString(quantity) + " " + unit.getAbbreviation();
    }
}
