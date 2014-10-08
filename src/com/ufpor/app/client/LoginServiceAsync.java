package com.ufpor.app.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by ovenbits on 10/8/14.
 */
public interface LoginServiceAsync {
    void login(String requestUri, AsyncCallback<LoginInfo> async);
}
