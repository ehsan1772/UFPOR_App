package com.ufpor.app.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by ovenbits on 10/8/14.
 */
public interface EnvironmentServiceAsync {
    void addEnvironment(String name, String area, AsyncCallback<Void> async);

    void removeEnvironment(String symbol, AsyncCallback<Void> async);

    void getEnvironmentNames(AsyncCallback<String[]> async);

    void getEnvironments(AsyncCallback<List<EnvironmentDM>> async);
}
