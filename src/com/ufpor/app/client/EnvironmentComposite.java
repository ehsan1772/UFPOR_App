package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EnvironmentComposite extends Composite {

	private static EnvironmentUiBinder uiBinder = GWT
			.create(EnvironmentUiBinder.class);

	interface EnvironmentUiBinder extends UiBinder<Widget, EnvironmentComposite> {
	}

	public EnvironmentComposite() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public EnvironmentComposite(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
