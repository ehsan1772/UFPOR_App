package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.ufpor.app.client.App;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

import java.util.List;

/**
 * Created by Ehsan Barekati on 2/24/15.
 */
public class SpaceTreeItem extends Composite {

    interface SpaceTreeItemUiBinder extends UiBinder<FocusPanel, SpaceTreeItem> {
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
    @UiField
    TextBox count;
    @UiField
    DivElement countContainer;

    public SpaceTreeItem() {
        initWidget(ourUiBinder.createAndBindUi(this));
        setTextValues();

        //adding drop handler
        addDomHandler(dropHandler, DropEvent.getType());
    }

    public SpaceTreeItem(IfcClientSpaceType type) {
        initWidget(ourUiBinder.createAndBindUi(this));
        setTextValues();
        //adding drop handler
        addDomHandler(dropHandler, DropEvent.getType());

        setName(type.getName());

    }

    private final DropHandler dropHandler = new DropHandler() {
        @Override
        public void onDrop(DropEvent event) {
            event.preventDefault();


            //this key will be used to get the spaceType from cache
            String index = event.getData("index");

            List<IfcClientSpaceType> spaceTypes = (List<IfcClientSpaceType>) App.getCache(HomeView.SPACE_TYPE);

            IfcClientSpaceType type = spaceTypes.get(Integer.valueOf(index));


        }
    };

    private void setTextValues() {
        note.setText("Note: Check the principle room furniture");
        title.setText("A Middle School");
        netArea.setText("Net:   80,000 sqft");
        grossArea.setText("Gross:   90,000 sqft");
        capacity.setResource(App.Resources.INSTANCE.equalIcon());

    }

    public void setCount(int count) {
        if (count > 1) {
            countContainer.getStyle().setVisibility(Style.Visibility.VISIBLE);
            this.count.setText(String.valueOf(count));
        }

    }

    public void setNetArea(double area) {
        netArea.setText("Net:    " + area + "sqft");
    }

    public void setGrossArea(double area) {
        grossArea.setText("Net:    " + area + "sqft");
    }

    public void setName(String name) {
        title.setText(name);
    }

    public void setNote(String note) {
        this.note.setText("Note: " + note);
    }

}