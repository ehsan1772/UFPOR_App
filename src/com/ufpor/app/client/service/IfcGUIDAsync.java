package com.ufpor.app.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Ehsan Barekati on 11/1/14.
 */
public interface IfcGUIDAsync {
    void getNewIfcGloballyUniqueId(AsyncCallback<String> async);
}
