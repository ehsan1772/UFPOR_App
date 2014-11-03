package com.ufpor.app.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.IfcGUID;

/**
 * Created by Ehsan Barekati on 11/1/14.
 */
public class IfcGUIDImpl extends RemoteServiceServlet implements IfcGUID {
    @Override
    public String getNewIfcGloballyUniqueId() {
        return GuidCompressor.getNewIfcGloballyUniqueId();
    }
}