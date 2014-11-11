package com.ufpor.app.shared.ifcclient.measure;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientVolumeMeasure implements IfcClientMeasureValue {
    private IfcClientVolumeMeasure() {
    }

    public double getValue() {
        return value;
    }

    public IfcClientVolumeMeasure(double value) {

        this.value = value;
    }

    private double value;
}
