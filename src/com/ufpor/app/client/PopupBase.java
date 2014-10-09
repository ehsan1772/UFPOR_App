package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupBase extends Composite {
	@UiField TabLayoutPanel panel;
	@UiField Button save;
	@UiField Button cancel;
	
	private EnvironmentGeneral envGeneral;
	private PopupBaseHost host;

	private static PopupBaseUiBinder uiBinder = GWT
			.create(PopupBaseUiBinder.class);

	interface PopupBaseUiBinder extends UiBinder<Widget, PopupBase> {
	}

	public PopupBase(PopupBaseHost host) {
		initWidget(uiBinder.createAndBindUi(this));
		this.host = host;
	}

	@UiHandler("save")
	void handleClick1(ClickEvent e) {
        EnvironmentService.App.getInstance().addEnvironment(envGeneral.getName(),
                envGeneral.getArea(), new AsyncCallback<Void>() {

                    @Override
                    public void onSuccess(Void result) {
                        Window.alert(envGeneral.getName() + "Is saved");
                        ((Designertest) host).refreshSpaces();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Failed");

                    }
                });
		
	}
	
	@UiHandler("cancel")
	void handleClick(ClickEvent e) {
		host.closePopupBase();
	}

	@Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);
		envGeneral = new EnvironmentGeneral();
		panel.add(envGeneral, "General");

		panel.add(new EnvironmentGrouping(), "Grouping");

		EnvironmentGrouping adjacencies = new EnvironmentGrouping();
		adjacencies.setFirstTitle("Required Adjacencies");
		adjacencies.setSecondTitle("Avoid Adjacencies");
		panel.add(adjacencies, "Adjacencies");

		EnvironmentActivity activity = new EnvironmentActivity();
		activity.setFirstTitle("Activities");
		activity.setSecondTitle("People");
		panel.add(activity, "Activities");

		DataGridTest test = new DataGridTest();
		panel.add(test, "Requirements");

		panel.selectTab(0);
		test.redraw();
	}
	
	public static interface PopupBaseHost {
		public void closePopupBase();
	}

}
