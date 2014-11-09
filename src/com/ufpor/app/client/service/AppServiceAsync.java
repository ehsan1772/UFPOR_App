package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
