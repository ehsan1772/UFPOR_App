package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.data.SpaceBuilderI;

public class EnvironmentGeneral extends Composite {
    private KeyUpHandler nameTextBoxValueChangedHandler = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceBuilder.setName(nameTextBox.getText());
        }
    };
    @UiField protected TextBox nameTextBox;
	@UiField protected TextBox areaTextBox;
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
	}


	public String getName() {
		return nameTextBox.getText();
	}

	public String getArea() {
		return areaTextBox.getText();
	}

}
