package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.shared.datatransfer.IfcSpace;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;

/**
 * Created by Ehsan Barekati on 4/3/15.
 */
public interface SpaceServiceAsync {
    void addSpaceInstance(IfcSpace.Type parentType, String parentSpaceKey, String spaceTypeKey, AsyncCallback<String> async);

    void getSpaceByKey(String key, AsyncCallback<IfcDecSpace> async);

    void getIfcRelAggregateByKey(String childrenKey, AsyncCallback<Integer> async);

    void getProjectByKey(String key, AsyncCallback<IfcDecProject> async);
}
