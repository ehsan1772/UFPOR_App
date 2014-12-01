package com.ufpor.app.client.eventbus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Ehsan Barekati on 11/8/14.
 */
public class MenuEvent extends GwtEvent<MenuEvent.MenuEventHandler> {
    public final static Type<MenuEventHandler> TYPE = new Type<MenuEventHandler>();
    private Event event;
    private Object value;

    public MenuEvent(Event event) {
        this.event = event;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Type<MenuEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(MenuEventHandler handler) {
        handler.onMenuEvent(this);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public enum Event {NEW, OPEN, OPEN_FILE}

    public static interface MenuEventHandler extends EventHandler {
        void onMenuEvent(MenuEvent event);
    }
}
