package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.client.view.EnvironmentDM;
import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;

import java.util.List;

/**
 * Created by ovenbits on 10/8/14.
 */
public interface EnvironmentServiceAsync {
    void addEnvironment(String name, String area, AsyncCallback<Void> async);

    void removeEnvironment(String symbol, AsyncCallback<Void> async);

    void getEnvironmentNames(AsyncCallback<String[]> async);

    void getEnvironments(AsyncCallback<List<EnvironmentDM>> async);

    void addIfcDecSpace(IfcClientSpace space, AsyncCallback<Void> async);
}
