package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.measure.IfcClientMeasureValue;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientCountMeasure implements IfcClientMeasureValue {
    private IfcClientCountMeasure() {
    }

    public int getValue() {
        return value;
    }

    public IfcClientCountMeasure(int value) {

        this.value = value;
    }

    private int value;
}
