package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;

/**
 * Created by Ehsan Barekati on 11/6/14.
 */
public class DivTest {
    interface DivTestUiBinder extends UiBinder<DivElement, DivTest> {
    }

    private static DivTestUiBinder ourUiBinder = GWT.create(DivTestUiBinder.class);

    public DivTest() {
        DivElement rootElement = ourUiBinder.createAndBindUi(this);

    }
}