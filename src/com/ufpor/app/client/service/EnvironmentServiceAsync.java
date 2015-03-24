package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.client.view.EnvironmentDM;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

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

    void addProject(IfcClientProject project, boolean isTest, AsyncCallback<List<String>> async);

    void addSpaceType(IfcClientSpaceType spaceType, String projectName, AsyncCallback<List<String>> async);

    void getProjectsNames(AsyncCallback<List<String>> async);

    void getSpaceTypes(String projectName, AsyncCallback<List<IfcClientSpaceType>> async);

    void getProjectString(String projectName, AsyncCallback<String> async);

    void deleteProjectByName(String name, AsyncCallback<Void> async);

}
