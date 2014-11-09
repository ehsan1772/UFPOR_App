package com.ufpor.app.client.service;

import com.google.appengine.api.users.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufpor.app.client.LoginInfo;

/**
 * Created by ovenbits on 10/8/14.
 */
public interface LoginServiceAsync {
    void login(String requestUri, AsyncCallback<LoginInfo> async);
}
