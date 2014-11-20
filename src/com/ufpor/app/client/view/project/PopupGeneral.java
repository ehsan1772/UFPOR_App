package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.presenter.ProjectPresenter;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.shared.ifcclient.IfcClientProject;

import java.util.List;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */
//public class PopupGeneral extends PopupBase implements ProjectPresenter.Display {
public class PopupGeneral extends PopupBase  {
    private static PopupGeneralUiBinder uiBinder = GWT.create(PopupGeneralUiBinder.class);
    private HalfPopUpView projectView1;
    private IfcClientProject project;

    public PopupGeneral(LoginInfo loginInfo) {
        super(loginInfo);
    }

    @Override
    protected void initWidget(Widget widget) {
        initWidgetSuper(widget);
        project = new IfcClientProject();
        ProjectPresenter pr = new ProjectPresenter(project);

        for(String title : pr.getView().keySet()) {
            panel.add(pr.getView().get(title) , title);
        }
    }

    @UiHandler("save")
    public void handleClick1(ClickEvent e) {
        EnvironmentService.App.getInstance().addProject(project, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<String> result) {

            }
        });
    }



    interface PopupGeneralUiBinder extends UiBinder<Widget, PopupGeneral> {
    }
}
