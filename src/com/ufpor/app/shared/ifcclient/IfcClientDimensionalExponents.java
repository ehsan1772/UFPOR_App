package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientDimensionalExponents implements Serializable {
    private int lengthExponent;
    private int massExponent;
    private int timeExponent;
    private int electricCurrentExponent;
    private int thermodynamicTemperatureExponent;
    private int amountOfSubstanceExponent;
    private int luminousntensityExponent;

    public int getLengthExponent() {
        return lengthExponent;
    }

    public void setLengthExponent(int lengthExponent) {
        this.lengthExponent = lengthExponent;
    }

    public int getMassExponent() {
        return massExponent;
    }

    public void setMassExponent(int massExponent) {
        this.massExponent = massExponent;
    }

    public int getTimeExponent() {
        return timeExponent;
    }

    public void setTimeExponent(int timeExponent) {
        this.timeExponent = timeExponent;
    }

    public int getElectricCurrentExponent() {
        return electricCurrentExponent;
    }

    public void setElectricCurrentExponent(int electricCurrentExponent) {
        this.electricCurrentExponent = electricCurrentExponent;
    }

    public int getThermodynamicTemperatureExponent() {
        return thermodynamicTemperatureExponent;
    }

    public void setThermodynamicTemperatureExponent(int thermodynamicTemperatureExponent) {
        this.thermodynamicTemperatureExponent = thermodynamicTemperatureExponent;
    }

    public int getAmountOfSubstanceExponent() {
        return amountOfSubstanceExponent;
    }

    public void setAmountOfSubstanceExponent(int amountOfSubstanceExponent) {
        this.amountOfSubstanceExponent = amountOfSubstanceExponent;
    }

    public int getLuminousntensityExponent() {
        return luminousntensityExponent;
    }

    public void setLuminousntensityExponent(int luminousntensityExponent) {
        this.luminousntensityExponent = luminousntensityExponent;
    }
}
