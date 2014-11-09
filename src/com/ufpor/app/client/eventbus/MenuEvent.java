package com.ufpor.app.client.eventbus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Ehsan Barekati on 11/8/14.
 */
public class MenuEvent extends GwtEvent<MenuEvent.MenuEventHandler>{
    public final static Type<MenuEventHandler> TYPE = new Type<MenuEventHandler>();;
    @Override
    public Type<MenuEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(MenuEventHandler handler) {
        handler.onMenuEvent(this);
    }

    public static interface MenuEventHandler extends EventHandler {
        void onMenuEvent(MenuEvent event);
    }
}
