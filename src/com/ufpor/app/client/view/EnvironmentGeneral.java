package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.ufpor.app.client.App;
import com.ufpor.app.client.data.SpaceBuilderI;

public class EnvironmentGeneral extends Composite {
    private final KeyUpHandler longNameTextBoxValueChangedHandler = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceBuilder.setLongName(longNameTextBox.getText());
        }
    };
    private final ChangeHandler spaceTypeListChangeHandler = new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
            String selected = spaceTypeList.getItemText(spaceTypeList.getSelectedIndex());
            spaceBuilder.setType(selected);
        }
    };
    private final KeyUpHandler descriptionTextBoxChangeHandler = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceBuilder.setDescription(descriptionTextBox.getText());
        }
    };
    private KeyUpHandler nameTextBoxValueChangedHandler = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceBuilder.setName(nameTextBox.getText());
        }
    };
    @UiField protected TextBox nameTextBox;
	@UiField protected TextBox areaTextBox;
    @UiField protected TextArea longNameTextBox;
    @UiField ListBox spaceTypeList;
    @UiField TextArea descriptionTextBox;
    SpaceBuilderI spaceBuilder;


	private static EnvironmentGeneralUiBinder uiBinder = GWT
			.create(EnvironmentGeneralUiBinder.class);

	interface EnvironmentGeneralUiBinder extends
			UiBinder<Widget, EnvironmentGeneral> {
	}


	public EnvironmentGeneral() {
		initWidget(uiBinder.createAndBindUi(this));
        spaceBuilder = App.getInjector().getSpaceBuilderI();
        nameTextBox.addKeyUpHandler(nameTextBoxValueChangedHandler);
        longNameTextBox.addKeyUpHandler(longNameTextBoxValueChangedHandler);
        spaceTypeList.addChangeHandler(spaceTypeListChangeHandler);
        descriptionTextBox.addKeyUpHandler(descriptionTextBoxChangeHandler);
	}


	public String getName() {
		return nameTextBox.getText();
	}

	public String getArea() {
		return areaTextBox.getText();
	}

}
