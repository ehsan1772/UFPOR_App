package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EnvironmentGeneral extends Composite {
	@UiField protected TextBox nameTextBox;
	@UiField protected TextBox areaTextBox;

	private static EnvironmentGeneralUiBinder uiBinder = GWT
			.create(EnvironmentGeneralUiBinder.class);

	interface EnvironmentGeneralUiBinder extends
			UiBinder<Widget, EnvironmentGeneral> {
	}

	public EnvironmentGeneral() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public EnvironmentGeneral(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getName() {
		return nameTextBox.getText();
	}

	public String getArea() {
		return areaTextBox.getText();
	}

}
