package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.App;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.eventbus.ServerResultEvent;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.HomeView;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.shared.ifcclient.IfcClientProject;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */
//public class PopupGeneral extends PopupBase implements ProjectPresenter.Display {
public class PopupOpenProject extends PopupBase {
    private static PopupOpenProjectUiBinder uiBinder = GWT.create(PopupOpenProjectUiBinder.class);
    private HalfPopUpView projectView1;
    private IfcClientProject project;
    private OpenProjectPresenter openProjectPresenter;

    public PopupOpenProject(LoginInfo loginInfo) {
        super(loginInfo);
    }

    @Override
    protected void initWidget(Widget widget) {
        initWidgetSuper(widget);
        openProjectPresenter = new OpenProjectPresenter();
        panel.add(openProjectPresenter.getView(), "Select Project");

    }

    @UiHandler("delete")
    public void handleClick2(ClickEvent e) {
        String selectedProject = openProjectPresenter.getSelectedProjectName();
        EnvironmentService.App.getInstance().deleteProjectByName(selectedProject, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(Void result) {
                openProjectPresenter.refresh();
            }
        });
    }

    @UiHandler("save")
    public void handleClick1(ClickEvent e) {
        String selectedProject = openProjectPresenter.getSelectedProjectName();

//        MenuEvent event = new MenuEvent(MenuEvent.Event.OPEN_FILE);
//        event.setValue(selectedProject);
//        App.getInjector().getSimpleEventBus().fireEvent(event);
        HomeView.projectName = selectedProject;
        host.closePopupBase();

        EnvironmentService.App.getInstance().getProjectString(selectedProject, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(String result) {
                ServerResultEvent event = new ServerResultEvent(result);
                App.getInjector().getSimpleEventBus().fireEvent(event);
                host.closePopupBase();
            }
        });

//        EnvironmentService.App.getInstance().addProject(project, new AsyncCallback<List<String>>() {
//            @Override
//            public void onFailure(Throwable caught) {
//
//            }
//
//            @Override
//            public void onSuccess(List<String> result) {
//                ServerResultEvent event = new ServerResultEvent(result.get(0));
//                App.getInjector().getSimpleEventBus().fireEvent(event);
//                host.closePopupBase();
//
//            }
//        });

    }

    interface PopupOpenProjectUiBinder extends UiBinder<Widget, PopupOpenProject> {
    }
}
