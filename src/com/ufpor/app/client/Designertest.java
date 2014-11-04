package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Designertest extends Composite implements PopupBase.PopupBaseHost {
    private static DesignertestUiBinder uiBinder = GWT
            .create(DesignertestUiBinder.class);
    private final EnvironmentServiceAsync environmentService = GWT.create(EnvironmentService.class);
    @UiField
    Anchor signOut;
    @UiField
    Label greeting;
    @UiField
    SplitLayoutPanel mainPanel;
    @UiField
    HTMLPanel tabPanel1;
    @UiField
    HTMLPanel center;
    //	@UiField
    ScrollPanel treeContainer;
    @UiField
    Button button;
    @UiField
    HTMLPanel envContainer;
    @UiField
    DecoratedTabPanel eastPanel;
    @UiField
    MyStyle style;
    private LoginInfo loginInfo;
    private PopupPanel popup;
    private int count = 0;
    private Logger logger = Logger.getLogger(Designertest.class.getSimpleName());

//    public Designertest(LoginInfo loginInfo) {
//        initWidget(uiBinder.createAndBindUi(this));
//        this.loginInfo = loginInfo;
//        greeting.setText("Hello " + loginInfo.getNickname());
//        signOut.setHref(loginInfo.getLoginUrl());
//        eastPanel.selectTab(0);
//    }

    public Designertest() {
        initWidget(uiBinder.createAndBindUi(this));
     //   this.loginInfo = loginInfo;
        greeting.setText("Hello " + loginInfo.getNickname());
        signOut.setHref(loginInfo.getLoginUrl());
        eastPanel.selectTab(0);
    }

    @Override
    protected void onLoad() {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                int h = center.getElement().getOffsetHeight();
                int w = center.getElement().getOffsetWidth();

                logger.log(Level.INFO, "Width is " + w + " and Height is " + h);

                treeContainer = new ScrollPanel();
                treeContainer.setWidth(String.valueOf(w) + "px");
                treeContainer.setHeight(String.valueOf(h) + "px");
                treeContainer.getElement().getStyle().setProperty("backgroundColor", "#FFC");

                center.add(treeContainer);

                populateTree(treeContainer);

                refreshSpaces();
            }
        });
    }

    private void populateTree(ScrollPanel panel) {
        Tree t = new Tree();

        EnvironmentTreeItem b = new EnvironmentTreeItem(true, true);

        b.setName("Tree");

        TreeItem root = new TreeItem(b);
        t.addItem(root);
        panel.add(t);

    }

    public void refreshSpaces() {
        EnvironmentService.App.getInstance().getEnvironments(new AsyncCallback<List<EnvironmentDM>>() {

            @Override
            public void onSuccess(List<EnvironmentDM> result) {
                envContainer.clear();
                for (EnvironmentDM env : result) {
                    EnvironmentTreeItem b = new EnvironmentTreeItem(true, true);
                    b.setName(env.getName());
                    b.setArea(env.getArea());
                    envContainer.add(b);
                }

            }

            @Override
            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub

            }
        });
    }

    @UiHandler("button")
    void onButtonClick(ClickEvent event) {
        popup = new PopupPanel();

        int width = (Window.getClientWidth() / 2);
        popup.setWidth(width + "px");

        int height = (Window.getClientHeight() / 2);
        popup.setHeight(height + "px");

        popup.setGlassEnabled(true);
        popup.setWidget(new PopupBase(this, loginInfo));

        popup.center();
    }

    public void removePopUp() {

    }

    @Override
    public void closePopupBase() {
        popup.removeFromParent();
    }

    interface DesignertestUiBinder extends UiBinder<Widget, Designertest> {
    }


    interface MyStyle extends CssResource {
        String header();

        String treeNode();

    }
}
