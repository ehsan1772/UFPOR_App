package com.ufpor.app.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ufpor.app.client.view.EnvironmentDM;
import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;

import java.util.List;

/**
 * Created by ovenbits on 10/8/14.
 */
@RemoteServiceRelativePath("EnvironmentService")
public interface EnvironmentService extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use EnvironmentService.App.getInstance() to access static instance of EnvironmentServiceAsync
     */
    public static class App {
        private static final EnvironmentServiceAsync ourInstance = (EnvironmentServiceAsync) GWT.create(EnvironmentService.class);

        public static EnvironmentServiceAsync getInstance() {
            return ourInstance;
        }
    }

    void addEnvironment(String name, String area) throws NotLoggedInException;

    void addIfcDecSpace(IfcClientSpace space) throws NotLoggedInException;

    public void removeEnvironment(String symbol) throws NotLoggedInException;

    public String[] getEnvironmentNames() throws NotLoggedInException;

    public List<EnvironmentDM> getEnvironments() throws NotLoggedInException;
}
