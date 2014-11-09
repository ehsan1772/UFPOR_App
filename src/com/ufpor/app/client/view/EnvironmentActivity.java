package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class EnvironmentActivity extends Composite {
	@UiField Label firstTitle;
	@UiField Label secondTitle;
	private static EnvironmentGeneralUiBinder uiBinder = GWT
			.create(EnvironmentGeneralUiBinder.class);

	interface EnvironmentGeneralUiBinder extends
			UiBinder<Widget, EnvironmentActivity> {
	}

	public EnvironmentActivity() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public EnvironmentActivity(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setFirstTitle(String title) {
		firstTitle.setText(title);
	}
	
	public void setSecondTitle(String title) {
		secondTitle.setText(title);
	}
	

}
