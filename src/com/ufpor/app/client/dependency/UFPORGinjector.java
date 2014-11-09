package com.ufpor.app.client.dependency;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.ufpor.app.client.view.HomeView;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.data.SpaceBuilderI;

/**
 *
 * If you are lost look at:
 *
 * https://code.google.com/p/google-gin/wiki/GinTutorial#Step_3._Declare_bindings
 *
 * Created by Ehsan Barekati on 11/5/14.
 */
@GinModules(UFPORModule.class)
public interface UFPORGinjector extends Ginjector {
    HomeView getHomeView();
    LoginInfo getLoginInfo();
    SpaceBuilderI getSpaceBuilderI();
    SimpleEventBus getSimpleEventBus();
}
