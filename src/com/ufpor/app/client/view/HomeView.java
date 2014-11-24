package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
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
import com.ufpor.app.client.dependency.test;
import com.ufpor.app.client.eventbus.ServerResultEvent;
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
//    @UiField
    HTML southLabel;
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
    private ScrollPanel resultContainer;


    @Inject
    public HomeView(LoginInfo loginInfo) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.loginInfo = loginInfo;
        greeting.setText("Hello " + loginInfo.getNickname());
        signOut.setHref(loginInfo.getLoginUrl());
        eastPanel.selectTab(0);
        App.injector.getSimpleEventBus().addHandler(ServerResultEvent.TYPE, mServerResultHandler);


    }

    private ServerResultEvent.ServerResultEventHandler mServerResultHandler = new ServerResultEvent.ServerResultEventHandler() {
        @Override
        public void onServerResultEvent(ServerResultEvent event) {
           // southLabel.setText(event.getResult());
            if (southLabel != null ) {
                resultContainer.remove(southLabel);
            }
            southLabel = new HTML(new SafeHtmlBuilder().appendEscapedLines(event.getResult()).toSafeHtml());
            resultContainer.add(southLabel);
        }
    };

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


                addResultPanel();

            }
        });
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






//        String test = "#1=IFCPROJECT(\u009200ZhrqZYLBcgy$rVVaiu2A\u0092, $, \u0092Example project\u0092, $, $, $, $, $, #2);<br />" +
//                "#2=IFCUNITASSIGNMENT((#3, #4, #5, #6, #7, #8, #9, #10));<br/>" +
//                "#3=IFCSIUNIT(*, .LENGTHUNIT., .MILLI., .METRE.);<br/>" +
//                "#4=IFCSIUNIT(*, .AREAUNIT., $, .SQUARE_METRE.);<br/>" +
//                "#5=IFCSIUNIT(*, .VOLUMEUNIT., $, .CUBIC_METRE.);<br>" +
//                "#6=IFCSIUNIT(*, .TIMEUNIT., $, .SECOND.);<br>" +
//                "#7=IFCSIUNIT(*, .ENERGYUNIT., $, .JOULE.);<br/>" +
//                "#8=IFCSIUNIT(*, .MASSUNIT., .KILO., .GRAM.);\n" +
//                "#9=IFCSIUNIT(*, .THERMODYNAMICTEMPERATUREUNIT., $, .KELVIN.);\n" +
//                "#10=IFCDERIVEDUNIT((#11, #12, #13), .SPECIFICHEATCAPACITYUNIT., $);\n" +
//                "#11=IFCDERIVEDUNITELEMENT(#7, 1);\n" +
//                "#12=IFCDERIVEDUNITELEMENT(#8, -1);\n" +
//                "#13=IFCDERIVEDUNITELEMENT(#9, -1);\n";
//        for (int i = 1 ; i < 10 ; i++) {
//            test = test.concat(test);
//
//        }

 //       southLabel = new HTML(new SafeHtmlBuilder().appendEscapedLines(test).toSafeHtml());
   //     southLabel = new HTML();
   //     resultContainer.add(southLabel);

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