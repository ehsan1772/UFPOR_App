package com.ufpor.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class App implements EntryPoint {
  //  private LoginInfo loginInfo = null;
 //   private static EnvironmentServiceAsync envService;
    private VerticalPanel loginPanel = new VerticalPanel();
    private Label loginLabel = new Label(
            "Please sign in to your Google Account to access the UFPOR application.");
    private Anchor signInLink = new Anchor("Sign In");
    private LoginInfo loginInfo;

    @Override
    public void onModuleLoad() {
        // Check login status using login service.
        LoginServiceAsync loginService = LoginService.App.getInstance();
    //    envService = EnvironmentService.App.getInstance();
        loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
            public void onFailure(Throwable error) {
                int i = 0;
            }

            public void onSuccess(LoginInfo result) {
                loginInfo = result;
                if(loginInfo.isLoggedIn()) {
                    loadApplication(loginInfo);
                } else {
                    loadLogin();
                }
            }
        });

    }

    private void loadApplication(LoginInfo loginInfo) {
        int i = 0;
        testEnv();
    //    Composite view = new Designertest(loginInfo);
     //   RootLayoutPanel.get().add(view);
    }

    private void testEnv() {
        EnvironmentService.App.getInstance().addEnvironment("Test3", "1000", new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                int i = 0;
            }

            @Override
            public void onSuccess(Void result) {
                int i = 0;
            }
        });
    }

    private void loadLogin() {
        // Assemble login panel.
        signInLink.setHref(loginInfo.getLoginUrl());
        loginPanel.add(loginLabel);
        loginPanel.add(signInLink);
        RootPanel.get().clear();
        RootPanel.get().add(loginPanel);
    }

//    public static EnvironmentServiceAsync getEnvService() {
//        return envService;
//    }
}
