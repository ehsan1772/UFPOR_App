package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.shared.datatransfer.IfcSpace;

/**
 * Created by Ehsan Barekati on 4/3/15.
 */
public interface SpaceServiceAsync {
    void addSpaceInstance(IfcSpace.Type parentType, String parentSpaceKey, String spaceTypeKey, AsyncCallback<String> async);
}
