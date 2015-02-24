package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.ufpor.app.client.App;

/**
 * Created by Ehsan Barekati on 2/24/15.
 */
public class SpaceTreeItem extends Composite {
    interface SpaceTreeItemUiBinder extends UiBinder<HTMLPanel, SpaceTreeItem> {
    }

    private static SpaceTreeItemUiBinder ourUiBinder = GWT.create(SpaceTreeItemUiBinder.class);
    @UiField
    TextBox title;
    @UiField
    TextBox netArea;
    @UiField
    TextBox grossArea;
    @UiField
    Image capacity;
    @UiField
    TextBox note;

    public SpaceTreeItem() {
        initWidget(ourUiBinder.createAndBindUi(this));
        setTextValues();
    }

    private void setTextValues() {
        note.setText("Note: Check the principle room furniture");
        title.setText("A Middle School");
        netArea.setText("Net:   80,000 sqft");
        grossArea.setText("Gross:   90,000 sqft");
        capacity.setResource(App.Resources.INSTANCE.equalIcon());

    }
}