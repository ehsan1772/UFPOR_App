package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
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
import com.ufpor.app.client.eventbus.ProjectCreatedEvent;
import com.ufpor.app.client.eventbus.ServerResultEvent;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.service.EnvironmentServiceAsync;
import com.ufpor.app.client.view.custom.ResizingSplitLayoutPanel;
import com.ufpor.app.client.view.project.PopupSpaceType;
import com.ufpor.app.shared.ifcclient.*;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ovenbits on 10/9/14.
 */
public class HomeView extends Composite implements PopupBase.PopupBaseHost, ResizingSplitLayoutPanel.ResizeListener {

    public static final String SPACE_TYPE = "space_type";
    public static HomeView instance;
    //TODO fix this!
    public static String projectName;
    private static HomeViewUiBinder ourUiBinder = GWT.create(HomeViewUiBinder.class);
    private final EnvironmentServiceAsync environmentService = GWT.create(EnvironmentService.class);
    @UiField
    public HeadingElement projectNameHeader;
    @UiField
    App.Resources res;
    @UiField
    Anchor signOut;
    @UiField
    Label greeting;
    //    @UiField
    //  HTML southLabel;
    @UiField
    Image logo;
    @UiField(provided = true)
    ResizingSplitLayoutPanel mainPanel;
    @UiField
    HTMLPanel tabPanel1;
    @UiField
    HTMLPanel center;
    @UiField
    ScrollPanel ifcPanel;
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
    @UiField
    DecoratedTabPanel southPanel;
    @UiField
    Image addButton;
    ProjectCreatedEvent.ProjectCreatedEventHandler mProjectCreatedHandler = new ProjectCreatedEvent.ProjectCreatedEventHandler() {
        @Override
        public void onProjectCreatedEvent(ProjectCreatedEvent event) {
            IfcClientProject project = event.getProject();

            populateTree(treeContainer, project);
            projectNameHeader.setTitle(project.getName());
        }
    };
    private HTML ifcFileHtml;
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
    private ServerResultEvent.ServerResultEventHandler mServerResultHandler = new ServerResultEvent.ServerResultEventHandler() {
        @Override
        public void onServerResultEvent(ServerResultEvent event) {
            resizeScrollPanel();
            GWT.log("Here goes the result:" + event.getResult());
            //   ifcPanel.remove(ifcFileHtml);
            //   ifcFileHtml = new HTML(new SafeHtmlBuilder().appendEscapedLines(event.getResult()).toSafeHtml());

            SafeHtml content = new SafeHtmlBuilder().appendEscapedLines(event.getResult()).toSafeHtml();

            //removing the old text
//            if (ifcPanel.getElement().getChildCount() > 0) {
//                ifcPanel.getElement().removeAllChildren();
//            }

            //   GWT.log("Here is the html content " +southLabel.getHTML());
            //   ifcPanel.add(ifcFileHtml);

            ifcFileHtml.setHTML(content);


            //   ifcPanel.add(new HTML("ISO-10303-21;<br>HEADER;<br>FILE_DESCRIPTION (<br>        ('long name'),<br>        '2;1');<br>FILE_NAME (<br>        'two.ifc',<br>        '2015/03/18T08:35:46',<br>        ('test@example.com'),<br>        ('test@example.com'),<br>        'UFPOR APP 0.0.1',<br>        'UFPOR DEMO beta',<br>        'gmail.com');<br>FILE_SCHEMA (('IFC4RC4'));<br>ENDSEC;<br>DATA;<br>#13= IFCPROJECT ('3$blSXKSfEG805zIk$zyrt',  $, two,    $,     $, long name,       $,        $,       #14);<br>#14= IFCUNITASSIGNMENT(#15,#16,#17);<br>#15= IFCSIUNIT(*, .AREAUNIT,   $, .SQUARE_METRE);<br>#16= IFCSIUNIT(*, .VOLUMEUNIT,   $, .CUBIC_METRE);<br>#17= IFCSIUNIT(*, .LENGTHUNIT,   $, .METRE);<br>#18= IFCQUANTITYAREA(NetFloorArea,  *,   *,  0.0,     *);<br>#19= IFCMETRIC ('MAX_VALUE', null, HARD, null,     *,      *,       *, LESSTHANOREQUALTO,      null,     2000.0,           *);<br>#20= IFCMETRIC ('MIN_VALUE', null, HARD, null,     *,      *,       *, GREATERTHANOREQUALTO,      null,     1000.0,           *);<br>#21= IFCOBJECTIVE ('null',  *, HARD,    *,     *,      *,       *,  (19,20), LOGICALAND, REQUIREMENT,           *);<br>#22= IFCRESOURCECONSTRAINTRELATIONSHIP(*,  *,  21,   18);<br>#23= IFCELEMENTQUANTITY(3keBxne$D3OfejUUlko$q7,  *,   *,    *,     *,   (18));<br>#24= IFCRELDEFINESBYPROPERTIES(0OJ5H6RWbB6AWAE2HxWqA$,  *,   *,    *,  (13),     23);<br>ENDSEC;<br>END-ISO-10303-21;"));

            loadSpaceTypes(projectName);
        }
    };

    public MenuBar getMenu() {
        return mMenue;
    }

    private MenuBar mMenue;
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
        mainPanel = new ResizingSplitLayoutPanel();
        initWidget(ourUiBinder.createAndBindUi(this));
        this.loginInfo = loginInfo;
        greeting.setText("Hello   " + loginInfo.getNickname());
        signOut.setHref(loginInfo.getLogoutUrl());
        eastPanel.selectTab(0);
        southPanel.selectTab(0);
        logo.setResource(App.Resources.INSTANCE.submitButtonIcon());

        App.injector.getSimpleEventBus().addHandler(ServerResultEvent.TYPE, mServerResultHandler);
        App.injector.getSimpleEventBus().addHandler(MenuEvent.TYPE, mMenuEventHandler);
        App.injector.getSimpleEventBus().addHandler(ProjectCreatedEvent.TYPE, mProjectCreatedHandler);

        mainPanel.setResizeListener(this);

        ifcFileHtml = new HTML("IFC File");
        ifcPanel.add(ifcFileHtml);
        instance = this;

    }

    /**
     * resizing the scrollable panels to always use the right height
     */
    private void resizeScrollPanel() {
        int height = RootLayoutPanel.get().getOffsetHeight() - ifcPanel.getAbsoluteTop();

        ifcPanel.getElement().getStyle().setHeight(height, Style.Unit.PX);


        if (treeContainer != null) {
            int h = southPanel.getAbsoluteTop() - center.getAbsoluteTop();
            //setting the padding
            treeContainer.setHeight(String.valueOf(h - 40) + "px");
        }
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
                //subtracting the padding from the size
                treeContainer.setWidth(String.valueOf(w - 40) + "px");
                treeContainer.setHeight(String.valueOf(h - 40) + "px");

                treeContainer.setStyleName(style.treeContainer());

                //setting the padding
                treeContainer.getElement().getStyle().setPadding(20, Style.Unit.PX);

                center.add(treeContainer);

                addMenu();

                //populateTree(treeContainer);

                //   refreshSpaces();


                //      addResultPanel(nu);

            }
        });

    }

    private void addMenu() {
        mMenue = MenuBuilder.getMenu();
        tabPanel1.add(mMenue);

        //finding the div that contains the menu, it's the last child
        Element menuParent = (Element) tabPanel1.getElement().getChild(tabPanel1.getElement().getChildCount() - 1);

        //tweaking the style to position it at the bottom
        menuParent.getStyle().setPosition(Style.Position.ABSOLUTE);
        menuParent.getStyle().setBottom(0, Style.Unit.PX);
    }

    private void addResultPanel(HTML southLabel) {
        int h = ifcPanel.getElement().getOffsetHeight();
        int w = ifcPanel.getElement().getOffsetWidth();


        resultContainer = new ScrollPanel();
        resultContainer.setWidth(String.valueOf(w) + "px");
        resultContainer.setHeight(String.valueOf(h) + "px");
        resultContainer.getElement().getStyle().setProperty("backgroundColor", "#99C");


        ifcPanel.add(resultContainer);

        resultContainer.add(southLabel);
    }

    @Override
    public void closePopupBase() {
        popup.removeFromParent();
    }

    private void populateTree(ScrollPanel panel) {
        Tree t = new Tree(App.Resources.INSTANCE);
        SpaceTreeItem c = new SpaceTreeItem();

        TreeItem root = new TreeItem(c);
        t.addItem(root);
        panel.add(t);
    }

    private void populateTree(ScrollPanel panel, IfcClientProject project) {
        Tree t = new Tree(App.Resources.INSTANCE);

        SpaceTreeItem c = new SpaceTreeItem();
        c.setName(project.getName());
        c.setNetArea((project.getMaxArea() + project.getMinArea()) / 2);
        TreeItem root = new TreeItem(c);
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
                ArrayList<EnvironmentTreeItem> cacheList = new ArrayList<EnvironmentTreeItem>();

                for (IfcClientSpaceType env : result) {
                    EnvironmentTreeItem b = new EnvironmentTreeItem(true, true, env);
                    cacheList.add(b);
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

                App.addToCache(SPACE_TYPE, cacheList);
            }
        });
    }

    @UiHandler("addButton")
    void onButtonClick(ClickEvent event) {
        int width = (Window.getClientWidth() / 2);
        popup.setWidth(width + "px");

        int height = (Window.getClientHeight() / 2);
        popup.setHeight(height + "px");

        popup.setGlassEnabled(true);


        popUpSpace.setHost(this);
        popup.setWidget(popUpSpace);

        popup.center();
    }

    public void removePopUp() {

    }

    @Override
    public void onResize() {
        resizeScrollPanel();
    }

    public void setProjectName(String selectedProject) {
        projectName = selectedProject;
        // projectNameHeader.setTitle(selectedProject);
        projectNameHeader.setInnerText(selectedProject);
    }


    interface HomeViewUiBinder extends UiBinder<ResizingSplitLayoutPanel, HomeView> {
    }

    interface MyStyle extends CssResource {
        String header();

        String menu();

        String treeNode();

        String treeContainer();

    }
}