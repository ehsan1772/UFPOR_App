package com.ufpor.app.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("appService")
public interface AppService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use appService.App.getInstance() to access static instance of appServiceAsync
     */
    public static class App {
        private static AppServiceAsync ourInstance = GWT.create(AppService.class);

        public static synchronized AppServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
