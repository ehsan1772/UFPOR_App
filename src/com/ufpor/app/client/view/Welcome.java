package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by Ehsan Barekati on 3/5/15.
 */
public class Welcome extends Composite {
    interface WelcomeUiBinder extends UiBinder<HTMLPanel, Welcome> {
    }

    private static WelcomeUiBinder ourUiBinder = GWT.create(WelcomeUiBinder.class);
    @UiField
    AnchorElement button;

    public Welcome(String login) {
        initWidget(ourUiBinder.createAndBindUi(this));
        button.setHref(login);
    }
}