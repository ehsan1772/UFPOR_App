package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by Ehsan Barekati on 2/20/15.
 */
public class TestBinder extends Composite {
    interface TestBinderUiBinder extends UiBinder<HTMLPanel, TestBinder> {
    }

    private static TestBinderUiBinder ourUiBinder = GWT.create(TestBinderUiBinder.class);

    public TestBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}