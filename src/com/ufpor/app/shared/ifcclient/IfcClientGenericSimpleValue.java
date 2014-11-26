package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/25/14.
 */
public class IfcClientGenericSimpleValue<T> implements IfcClientSimpleValue {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public IfcClientGenericSimpleValue(T value) {
        this.value = value;
    }

    public IfcClientGenericSimpleValue() {
    }
}
