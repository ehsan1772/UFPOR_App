package com.ufpor.app.client.dependency;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;

/**
 * Created by Ehsan Barekati on 11/8/14.
 */
public class test {
    interface testUiBinder extends UiBinder<DivElement, test> {
    }

    private static testUiBinder ourUiBinder = GWT.create(testUiBinder.class);

    public test() {
        DivElement rootElement = ourUiBinder.createAndBindUi(this);

    }
}