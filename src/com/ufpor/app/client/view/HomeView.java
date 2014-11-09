package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.ufpor.app.client.*;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.service.EnvironmentServiceAsync;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ovenbits on 10/9/14.
 */
public class HomeView extends Composite implements PopupBase.PopupBaseHost {
    private static HomeViewUiBinder ourUiBinder = GWT.create(HomeViewUiBinder.class);
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
    @Inject
    private PopupPanel popup;
    @Inject
    private PopupBase popUpBase;
    private int count = 0;
    private Logger logger = Logger.getLogger(HomeView.class.getSimpleName());


    @Inject
    public HomeView(LoginInfo loginInfo) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.loginInfo = loginInfo;
        greeting.setText("Hello " + loginInfo.getNickname());
        signOut.setHref(loginInfo.getLoginUrl());
        eastPanel.selectTab(0);
    }

    @Override
    protected void onLoad() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
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

                tabPanel1.add(MenuBuilder.getMenu());

                populateTree(treeContainer);

                refreshSpaces();
            }
        });
    }

    @Override
    public void closePopupBase() {
        popup.removeFromParent();
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
        int width = (Window.getClientWidth() / 2);
        popup.setWidth(width + "px");

        int height = (Window.getClientHeight() / 2);
        popup.setHeight(height + "px");

        popup.setGlassEnabled(true);
     //   popup.setWidget(new PopupBase(this, loginInfo));
        popUpBase.setHost(this);
        popup.setWidget(popUpBase);

        popup.center();
    }

    public void removePopUp() {

    }


    interface HomeViewUiBinder extends UiBinder<SplitLayoutPanel, HomeView> {
    }

    interface MyStyle extends CssResource {
        String header();

        String treeNode();

    }
}