package com.ufpor.app.client.eventbus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Ehsan Barekati on 11/21/14.
 */
public class ServerResultEvent extends GwtEvent<ServerResultEvent.ServerResultEventHandler> {
    public final static GwtEvent.Type<ServerResultEventHandler> TYPE = new GwtEvent.Type<ServerResultEventHandler>();

    public ServerResultEvent(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result;
    @Override
    public Type<ServerResultEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ServerResultEventHandler handler) {
        handler.onServerResultEvent(this);
    }

    public static interface ServerResultEventHandler extends EventHandler {
        void onServerResultEvent(ServerResultEvent event);
    }
}
