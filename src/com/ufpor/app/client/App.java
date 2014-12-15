package com.ufpor.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.ufpor.app.client.dependency.UFPORGinjector;
import com.ufpor.app.client.eventbus.MenuEvent;
import com.ufpor.app.client.service.LoginService;
import com.ufpor.app.client.service.LoginServiceAsync;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.client.view.project.PopupGeneral;
import com.ufpor.app.client.view.project.PopupOpenProject;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class App implements EntryPoint, PopupBase.PopupBaseHost {
  //  public static UFPORGinjector injector;
    public final static UFPORGinjector injector = GWT.create(UFPORGinjector.class);
    private VerticalPanel loginPanel;
    private Label loginLabel;
    private Anchor signInLink;
    private LoginInfo loginInfo;
    @Inject
    private PopupPanel popup;
    @Inject
    private PopupBase popUpBase;
    @Inject
    private PopupGeneral popUpGeneral;


    private void initializeTheLoginPanel() {
        loginPanel = new VerticalPanel();
        loginLabel = new Label(
                "Please sign in to your Google Account to access the UFPOR application.");
        signInLink = new Anchor("Sign In");
    }

    @Override
    public void onModuleLoad() {
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
                    initializeTheLoginPanel();
                    loadLogin();
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
        Composite view = injector.getHomeView();
        RootLayoutPanel.get().add(view);
    }

    private void loadLogin() {
        // Assemble login panel.
        signInLink.setHref(loginInfo.getLoginUrl());
        loginPanel.add(loginLabel);
        loginPanel.add(signInLink);
        RootPanel.get().clear();
        RootPanel.get().add(loginPanel);
    }

    public static UFPORGinjector getInjector() {
        return injector;
    }

    @Override
    public void closePopupBase() {
        popup.removeFromParent();
    }
}
