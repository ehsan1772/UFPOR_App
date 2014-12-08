package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.ufpor.app.client.App;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.eventbus.ServerResultEvent;
import com.ufpor.app.client.presenter.SpaceTypePresenter;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.HomeView;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

import java.util.List;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */
//public class PopupGeneral extends PopupBase implements ProjectPresenter.Display {
public class PopupSpaceType extends PopupBase  {
    private static PopupSpaceTypeUiBinder uiBinder = GWT.create(PopupSpaceTypeUiBinder.class);
    private IfcClientSpaceType spaceType;
    private SpaceTypePresenter spaceTypePresenter;

    @Inject
    public PopupSpaceType(LoginInfo loginInfo) {
        super(loginInfo);
    }

    @Override
    protected void initWidget(Widget widget) {
        initWidgetSuper(widget);
        spaceType = new IfcClientSpaceType();
        spaceTypePresenter = new SpaceTypePresenter(spaceType);

        for(String title : spaceTypePresenter.getViews().keySet()) {
            panel.add(spaceTypePresenter.getViews().get(title) , title);
        }
    }

    @UiHandler("save")
    public void handleClick1(ClickEvent e) {
        EnvironmentService.App.getInstance().addSpaceType(spaceTypePresenter.getSpaceType(), HomeView.projectName, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                int i = 0;
            }

            @Override
            public void onSuccess(List<String> result) {
                ServerResultEvent event = new ServerResultEvent(result.get(0));
                App.getInjector().getSimpleEventBus().fireEvent(event);
                host.closePopupBase();
            }
        });

    }


    interface PopupSpaceTypeUiBinder extends UiBinder<Widget, PopupSpaceType> {
    }
}
