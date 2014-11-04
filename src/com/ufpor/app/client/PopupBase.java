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
import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;
import com.ufpor.app.shared.ifckernel.IfcText;

public class PopupBase extends Composite {
    private static PopupBaseUiBinder uiBinder = GWT
            .create(PopupBaseUiBinder.class);
    private final LoginInfo loginInfo;
    @UiField
    TabLayoutPanel panel;
    @UiField
    Button save;
    @UiField
    Button cancel;
    private EnvironmentGeneral envGeneral;
    private PopupBaseHost host;
    private String newGUID;

    public PopupBase(PopupBaseHost host, LoginInfo loginInfo) {
        initWidget(uiBinder.createAndBindUi(this));
        this.loginInfo = loginInfo;
        this.host = host;
    }

    @UiHandler("save")
    void handleClick1(ClickEvent e) {

        getGUID();

//        EnvironmentService.App.getInstance().addEnvironment(envGeneral.getName(),
//                envGeneral.getArea(), new AsyncCallback<Void>() {
//
//                    @Override
//                    public void onSuccess(Void result) {
//                        Window.alert(envGeneral.getName() + "Is saved");
//                        ((Designertest) host).refreshSpaces();
//                    }
//
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        Window.alert("Failed");
//
//                    }
//                });

    }

    private void getGUID() {
        IfcGUID.App.getInstance().getNewIfcGloballyUniqueId(new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(String result) {
                newGUID = result;
                addNewIfcSpace(newGUID, loginInfo);
            }
        });
    }


    private void addNewIfcSpace(String guid, LoginInfo loginInfo) {
        IfcClientSpace space = new IfcClientSpace(guid, loginInfo);

        IfcText description = new IfcText("This is the description");
        space.setDescription(description);

        com.ufpor.app.shared.ifcclient.IfcDecLabel name = new com.ufpor.app.shared.ifcclient.IfcDecLabel("This is the name");
        space.setName(name);
        EnvironmentService.App.getInstance().addIfcDecSpace(space, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failed");
            }

            @Override
            public void onSuccess(Void result) {
                Window.alert(envGeneral.getName() + "Is saved");
                ((Designertest) host).refreshSpaces();
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

    interface PopupBaseUiBinder extends UiBinder<Widget, PopupBase> {
    }

    public static interface PopupBaseHost {
        public void closePopupBase();
    }

}
