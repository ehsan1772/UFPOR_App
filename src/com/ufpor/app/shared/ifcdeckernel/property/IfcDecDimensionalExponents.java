package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.shared.ifcclient.IfcClientDimensionalExponents;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public class IfcDecDimensionalExponents implements Serializable {
    private int lengthExponent;
    private int massExponent;
    private int timeExponent;
    private int electricCurrentExponent;
    private int thermodynamicTemperatureExponent;
    private int amountOfSubstanceExponent;
    private int luminousntensityExponent;

    public static IfcDecDimensionalExponents getInstance(IfcClientDimensionalExponents client) {
        IfcDecDimensionalExponents result = new IfcDecDimensionalExponents();

        if (client != null) {
            result.setLengthExponent(client.getLengthExponent());
            result.setAmountOfSubstanceExponent(client.getAmountOfSubstanceExponent());
            result.setElectricCurrentExponent(client.getElectricCurrentExponent());
            result.setLuminousntensityExponent(client.getLuminousntensityExponent());
            result.setMassExponent(client.getMassExponent());
            result.setThermodynamicTemperatureExponent(client.getThermodynamicTemperatureExponent());
            result.setTimeExponent(client.getTimeExponent());
        }

        return result;
    }

    public int getLuminousntensityExponent() {
        return luminousntensityExponent;
    }

    public void setLuminousntensityExponent(int luminousntensityExponent) {
        this.luminousntensityExponent = luminousntensityExponent;
    }

    public int getAmountOfSubstanceExponent() {
        return amountOfSubstanceExponent;
    }

    public void setAmountOfSubstanceExponent(int amountOfSubstanceExponent) {
        this.amountOfSubstanceExponent = amountOfSubstanceExponent;
    }

    public int getThermodynamicTemperatureExponent() {
        return thermodynamicTemperatureExponent;
    }

    public void setThermodynamicTemperatureExponent(int thermodynamicTemperatureExponent) {
        this.thermodynamicTemperatureExponent = thermodynamicTemperatureExponent;
    }

    public int getElectricCurrentExponent() {
        return electricCurrentExponent;
    }

    public void setElectricCurrentExponent(int electricCurrentExponent) {
        this.electricCurrentExponent = electricCurrentExponent;
    }

    public int getTimeExponent() {
        return timeExponent;
    }

    public void setTimeExponent(int timeExponent) {
        this.timeExponent = timeExponent;
    }

    public int getMassExponent() {
        return massExponent;
    }

    public void setMassExponent(int massExponent) {
        this.massExponent = massExponent;
    }

    public int getLengthExponent() {
        return lengthExponent;
    }

    public void setLengthExponent(int lengthExponent) {
        this.lengthExponent = lengthExponent;
    }
}
