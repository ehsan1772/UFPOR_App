package com.ufpor.app.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ufpor.app.shared.datatransfer.IfcSpace;

/**
 * Created by Ehsan Barekati on 4/3/15.
 */
@RemoteServiceRelativePath("SpaceService")
public interface SpaceService extends RemoteService {
    String addSpaceInstance(IfcSpace.Type parentType, String parentSpaceKey, String spaceTypeKey);

    /**
     * Utility/Convenience class.
     * Use SpaceService.App.getInstance() to access static instance of SpaceServiceAsync
     */
    public static class App {
        private static final SpaceServiceAsync ourInstance = (SpaceServiceAsync) GWT.create(SpaceService.class);

        public static SpaceServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
