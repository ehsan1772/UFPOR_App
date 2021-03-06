package com.ufpor.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.ufpor.app.client.dependency.UFPORGinjector;
import com.ufpor.app.client.eventbus.MenuEvent;
import com.ufpor.app.client.service.LoginService;
import com.ufpor.app.client.service.LoginServiceAsync;
import com.ufpor.app.client.view.HomeView;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.client.view.Welcome;
import com.ufpor.app.client.view.project.PopupGeneral;
import com.ufpor.app.client.view.project.PopupOpenProject;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class App implements EntryPoint, PopupBase.PopupBaseHost {
    //  public static UFPORGinjector injector;
    public final static UFPORGinjector injector = GWT.create(UFPORGinjector.class);
    //a memory cache
    private static Map<String, Object> cache;
    private VerticalPanel loginPanel;
    private Label loginLabel;
    private Anchor signInLink;
    private LoginInfo loginInfo;
    public static App app;

    public PopupPanel getPopup() {
        return popup;
    }

    @Inject
    private PopupPanel popup;
    @Inject
    private PopupBase popUpBase;
    @Inject
    private PopupGeneral popUpGeneral;
    private HomeView mHomeView;

    public static Object getCache(String key) {
        return cache.get(key);
    }

    public static void addToCache(String key, Object object) {
        cache.put(key, object);
    }

    public static UFPORGinjector getInjector() {
        return injector;
    }

    public HomeView getmHomeView() {
        return mHomeView;
    }

    private void initializeTheLoginPanel() {
        loginPanel = new VerticalPanel();
        loginLabel = new Label(
                "Please sign in to your Google Account to access the UFPOR application.");
        signInLink = new Anchor("Sign In");
    }

    @Override
    public void onModuleLoad() {
        app = this;
        //   Resources.INSTANCE.ensureInjected();
        loginInfo = injector.getLoginInfo();
        // Check login status using login service.
        LoginServiceAsync loginService = LoginService.App.getInstance();
        //    envService = EnvironmentService.App.getInstance();
        loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
            public void onFailure(Throwable error) {
                int i = 0;
            }

            public void onSuccess(LoginInfo result) {
                loginInfo.updateValues(result);
                if (loginInfo.isLoggedIn()) {
                    loadApplication(loginInfo);
                } else {
//                    initializeTheLoginPanel();
//                    loadLogin();
                    Welcome welcome = new Welcome(loginInfo.getLoginUrl());
                    RootPanel.get().clear();
                    RootPanel.get().add(welcome);

                }
            }
        });

        injector.getSimpleEventBus().addHandler(MenuEvent.TYPE, new MenuEvent.MenuEventHandler() {
            @Override
            public void onMenuEvent(MenuEvent event) {
                switch (event.getEvent()) {
                    case NEW:
                        startNewProject();
                        break;
                    case OPEN:
                        startOpenProject();
                        break;
                    case OPEN_FILE:
                        openProject((String) event.getValue());
                        break;
                }

            }
        });

        cache = new HashMap<>();

    }

    private void openProject(String value) {

    }

    private void startNewProject() {
        popup = new PopupPanel();

        int width = (Window.getClientWidth() / 2);
        popup.setWidth(width + "px");

        int height = (Window.getClientHeight() / 2);
        popup.setHeight(height + "px");

        popup.setGlassEnabled(true);
        popUpGeneral = new PopupGeneral(loginInfo);
        popUpGeneral.setHost(this);
        popup.setWidget(popUpGeneral);

        popup.center();

    }

    private void startOpenProject() {
        popup = new PopupPanel();

        int width = (Window.getClientWidth() / 4);
        popup.setWidth(width + "px");

        int height = (Window.getClientHeight() / 2);
        popup.setHeight(height + "px");

        popup.setGlassEnabled(true);

        PopupOpenProject popUpOpenProject = new PopupOpenProject(loginInfo);
        popUpOpenProject.setHost(this);
        popup.setWidget(popUpOpenProject);

        popup.center();
    }

    private void loadApplication(LoginInfo loginInfo) {
        mHomeView = injector.getHomeView();
        RootLayoutPanel.get().add(mHomeView);
    }

    private void loadLogin() {
        // Assemble login panel.
        signInLink.setHref(loginInfo.getLoginUrl());
        loginPanel.add(loginLabel);
        loginPanel.add(signInLink);
        RootPanel.get().clear();
        RootPanel.get().add(loginPanel);
    }

    @Override
    public void closePopupBase() {
        popup.removeFromParent();
    }

    public interface Resources extends ClientBundle, Tree.Resources {

        public static final Resources INSTANCE = GWT.create(Resources.class);

        @Source("com/ufpor/app/public/image/logo.png")
        ImageResource submitButtonIcon();

        @Source("com/ufpor/app/public/image/close.png")
        ImageResource closeButtonIcon();

        @Source("com/ufpor/app/public/image/download.png")
        ImageResource downloadButtonIcon();

        @Source("com/ufpor/app/public/image/add.png")
        ImageResource addButtonIcon();

        @Source("com/ufpor/app/public/image/search.png")
        ImageResource searchButtonIcon();

        @Source("com/ufpor/app/public/image/ok.png")
        ImageResource okButtonIcon();

        @Source("com/ufpor/app/public/image/copy.png")
        ImageResource copyButtonIcon();

        @Source("com/ufpor/app/public/image/delete.png")
        ImageResource deleteButtonIcon();

        @Source("com/ufpor/app/public/image/badge.png")
        ImageResource badgeButtonIcon();

        @Source("com/ufpor/app/public/image/edit.png")
        ImageResource editButtonIcon();

        @Source("com/ufpor/app/public/image/less.png")
        ImageResource lessIcon();

        @Source("com/ufpor/app/public/image/less.png")
        ImageResource equalIcon();

        @Source("com/ufpor/app/public/image/more.png")
        ImageResource moreIcon();

        @Override
        @Source("com/ufpor/app/public/image/plus.png")
        ImageResource treeClosed();

        @Override
        @Source("com/ufpor/app/public/image/plus.png")
        ImageResource treeLeaf();

        @Override
        @Source("com/ufpor/app/public/image/minus.png")
        ImageResource treeOpen();
    }


}
