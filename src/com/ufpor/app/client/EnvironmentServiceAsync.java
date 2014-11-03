package com.ufpor.app.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;

import java.util.List;

/**
 * Created by ovenbits on 10/8/14.
 */
public interface EnvironmentServiceAsync {
    void addEnvironment(String name, String area, AsyncCallback<Void> async);

    void removeEnvironment(String symbol, AsyncCallback<Void> async);

    void getEnvironmentNames(AsyncCallback<String[]> async);

    void getEnvironments(AsyncCallback<List<EnvironmentDM>> async);

    void addIfcDecSpace(IfcDecSpace space, AsyncCallback<Void> async);
}
