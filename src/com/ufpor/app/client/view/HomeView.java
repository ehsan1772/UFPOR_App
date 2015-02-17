package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.ufpor.app.client.App;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.eventbus.MenuEvent;
import com.ufpor.app.client.eventbus.ServerResultEvent;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.service.EnvironmentServiceAsync;
import com.ufpor.app.client.view.project.PopupSpaceType;
import com.ufpor.app.shared.ifcclient.*;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

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
    App.Resources res;
    @UiField
    Anchor signOut;
    @UiField
    Label greeting;
    @UiField
    public HeadingElement projectNameHeader;
    //    @UiField
    HTML southLabel;
    @UiField
    Image logo;
    @UiField
    SplitLayoutPanel mainPanel;
    @UiField
    HTMLPanel tabPanel1;
    @UiField
    HTMLPanel center;
    @UiField
    HTMLPanel south;
    //	@UiField
    ScrollPanel treeContainer;
    @UiField
    Image searchButton;
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
    @Inject
    private PopupSpaceType popUpSpace;
    private int count = 0;
    private Logger logger = Logger.getLogger(HomeView.class.getSimpleName());
    private ScrollPanel resultContainer;
    //TODO fix this!
    public static String projectName;
    private ServerResultEvent.ServerResultEventHandler mServerResultHandler = new ServerResultEvent.ServerResultEventHandler() {
        @Override
        public void onServerResultEvent(ServerResultEvent event) {
            // southLabel.setText(event.getResult());
            if (southLabel != null) {
                resultContainer.remove(southLabel);
            }
            southLabel = new HTML(new SafeHtmlBuilder().appendEscapedLines(event.getResult()).toSafeHtml());
            resultContainer.add(southLabel);
            loadSpaceTypes(projectName);
        }
    };
    private MenuEvent.MenuEventHandler mMenuEventHandler = new MenuEvent.MenuEventHandler() {
        @Override
        public void onMenuEvent(MenuEvent event) {
            if (event.getEvent() == MenuEvent.Event.OPEN_FILE) {
                loadSpaceTypes((String) event.getValue());
            }
        }
    };

    @Inject
    public HomeView(LoginInfo loginInfo) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.loginInfo = loginInfo;
        greeting.setText("Hello   " + loginInfo.getNickname());
        signOut.setHref(loginInfo.getLoginUrl());
        eastPanel.selectTab(0);
        logo.setResource(App.Resources.INSTANCE.submitButtonIcon());

        App.injector.getSimpleEventBus().addHandler(ServerResultEvent.TYPE, mServerResultHandler);
        App.injector.getSimpleEventBus().addHandler(MenuEvent.TYPE, mMenuEventHandler);

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
              //  treeContainer.getElement().getStyle().setProperty("backgroundColor", "#FFC");

                treeContainer.setStyleName(style.treeContainer());

                center.add(treeContainer);

                addMenu();

                populateTree(treeContainer);

                //   refreshSpaces();


                addResultPanel();

            }
        });

    }

    private void addMenu() {
        tabPanel1.add(MenuBuilder.getMenu());

        //finding the div that contains the menu, it's the last child
        Element menuParent = (Element) tabPanel1.getElement().getChild(tabPanel1.getElement().getChildCount() - 1);

        //tweaking the style to position it at the bottom
        menuParent.getStyle().setPosition(Style.Position.ABSOLUTE);
        menuParent.getStyle().setBottom(0, Style.Unit.PX);
    }

    private void addResultPanel() {
        int h = south.getElement().getOffsetHeight();
        int w = south.getElement().getOffsetWidth();

        logger.log(Level.INFO, "South Width is " + w + " and South Height is " + h);

        resultContainer = new ScrollPanel();
        resultContainer.setWidth(String.valueOf(w) + "px");
        resultContainer.setHeight(String.valueOf(h) + "px");
        resultContainer.getElement().getStyle().setProperty("backgroundColor", "#99C");

        south.add(resultContainer);


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

//    public void refreshSpaces() {
//        EnvironmentService.App.getInstance().getEnvironments(new AsyncCallback<List<EnvironmentDM>>() {
//
//            @Override
//            public void onSuccess(List<EnvironmentDM> result) {
//                envContainer.clear();
//                for (EnvironmentDM env : result) {
//                    EnvironmentTreeItem b = new EnvironmentTreeItem(true, true);
//                    b.setName(env.getName());
//                    b.setArea(env.getArea());
//                    envContainer.add(b);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable caught) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//    }

    public void loadSpaceTypes(String projectName) {
        EnvironmentService.App.getInstance().getSpaceTypes(projectName, new AsyncCallback<List<IfcClientSpaceType>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<IfcClientSpaceType> result) {
                envContainer.clear();
                for (IfcClientSpaceType env : result) {
                    EnvironmentTreeItem b = new EnvironmentTreeItem(true, true);
                    b.setName(env.getName());
                    for (IfcClientPropertySetDefinition propset : env.getProperties()) {
                        if (propset instanceof IfcClientPropertySet) {
                            //  if (propset.getName().equals("Pset_SpaceCommon")) {
                            for (IfcClientProperty nextProp : ((IfcClientPropertySet) propset).getProperties()) {
                                if (nextProp.getName().equals("GrossPlannedArea")) {
                                    String area = ((IfcClientText) ((IfcClientPropertySingleValue) nextProp).getNominalValue()).getValue();
                                    b.setArea(area);
                                }
                            }
                            //  }
                        }
                    }
                    envContainer.add(b);
                }
            }
        });
    }

//    @UiHandler("button")
//    void onButtonClick(ClickEvent event) {
//        int width = (Window.getClientWidth() / 2);
//        popup.setWidth(width + "px");
//
//        int height = (Window.getClientHeight() / 2);
//        popup.setHeight(height + "px");
//
//        popup.setGlassEnabled(true);
//
//
//        popUpSpace.setHost(this);
//        popup.setWidget(popUpSpace);
//
//        popup.center();
//    }

    public void removePopUp() {

    }


    interface HomeViewUiBinder extends UiBinder<SplitLayoutPanel, HomeView> {
    }

    interface MyStyle extends CssResource {
        String header();

        String menu();

        String treeNode();

        String treeContainer();

    }
}