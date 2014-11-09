package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * Created by Ehsan Barekati on 11/1/14.
 */
@RemoteServiceRelativePath("IfcGUID")
public interface IfcGUID extends RemoteService {
    String getNewIfcGloballyUniqueId();


    /**
     * Utility/Convenience class.
     * Use IfcGUID.App.getInstance() to access static instance of IfcGUIDAsync
     */
    public static class App {
        private static final IfcGUIDAsync ourInstance = (IfcGUIDAsync) GWT.create(IfcGUID.class);

        public static IfcGUIDAsync getInstance() {
            return ourInstance;
        }
    }
}
