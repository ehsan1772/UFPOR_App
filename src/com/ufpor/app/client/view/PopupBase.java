package com.ufpor.app.client.view;

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
import com.google.inject.Inject;
import com.ufpor.app.client.App;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.data.SpaceBuilderI;
import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;

public class PopupBase extends Composite {
    private static PopupBaseUiBinder uiBinder = GWT
            .create(PopupBaseUiBinder.class);
    private final SpaceBuilderI spaceBuilder;


    protected LoginInfo loginInfo;

    protected PopupBaseHost host;

    @UiField
    public TabLayoutPanel panel;
    @UiField
    public Button save;
    @UiField
    public Button cancel;

    @Inject
    private EnvironmentGeneral envGeneral;

    private String newGUID;



    @Inject
    public PopupBase(LoginInfo loginInfo) {
        initWidget(uiBinder.createAndBindUi(this));
        this.loginInfo = loginInfo;
        spaceBuilder = App.getInjector().getSpaceBuilderI();
    }

    @UiHandler("save")
    public void handleClick1(ClickEvent e) {
        addNewIfcSpace();
    }


    private void addNewIfcSpace() {
        IfcClientSpace space = spaceBuilder.getSpace();

        EnvironmentService.App.getInstance().addIfcDecSpace(space, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failed");
            }

            @Override
            public void onSuccess(Void result) {
                Window.alert(spaceBuilder.getSpace().getName() + "Is saved");
                ((HomeView) host).refreshSpaces();
            }
        });
    }

    @UiHandler("cancel")
    public void handleClick(ClickEvent e) {
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
    protected void initWidgetSuper(Widget widget) {
        super.initWidget(widget);
    }

    public void setHost(PopupBaseHost host) {
        this.host = host;
    }

    interface PopupBaseUiBinder extends UiBinder<Widget, PopupBase> {
    }

    public static interface PopupBaseHost {
        public void closePopupBase();
    }

}
