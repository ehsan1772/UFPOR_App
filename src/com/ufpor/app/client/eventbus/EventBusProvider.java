package com.ufpor.app.client.eventbus;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Provider;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class EventBusProvider implements Provider<SimpleEventBus> {
    @Override
    public SimpleEventBus get() {
        return new SimpleEventBus();
    }
}
