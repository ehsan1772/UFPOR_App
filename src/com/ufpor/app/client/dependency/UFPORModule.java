package com.ufpor.app.client.dependency;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.ufpor.app.client.HomeView;
import com.ufpor.app.client.LoginInfo;

/**
 *
 * If you are lost look at:
 *
 * https://code.google.com/p/google-gin/wiki/GinTutorial#Step_3._Declare_bindings
 * Created by Ehsan Barekati on 11/5/14.
 */
public class UFPORModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(HomeView.class).in(Singleton.class);
        bind(LoginInfo.class).in(Singleton.class);
    }
}
