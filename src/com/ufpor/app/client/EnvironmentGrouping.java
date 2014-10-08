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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class EnvironmentGrouping extends Composite {
	@UiField Label firstTitle;
	@UiField Label secondTitle;
	private static EnvironmentGeneralUiBinder uiBinder = GWT
			.create(EnvironmentGeneralUiBinder.class);

	interface EnvironmentGeneralUiBinder extends
			UiBinder<Widget, EnvironmentGrouping> {
	}

	public EnvironmentGrouping() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public EnvironmentGrouping(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setFirstTitle(String title) {
		firstTitle.setText(title);
	}
	
	public void setSecondTitle(String title) {
		secondTitle.setText(title);
	}
	

}
