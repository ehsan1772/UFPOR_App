package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.client.presenter.ProjectPresenter;
import com.ufpor.app.shared.ifcclient.IfcClientProject;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */
//public class PopupGeneral extends PopupBase implements ProjectPresenter.Display {
public class PopupGeneral extends PopupBase  {
    private static PopupGeneralUiBinder uiBinder = GWT.create(PopupGeneralUiBinder.class);
    private HalfPopUpView projectView1;

    public PopupGeneral(LoginInfo loginInfo) {
        super(loginInfo);
    }

    @Override
    protected void initWidget(Widget widget) {
        initWidgetSuper(widget);
        IfcClientProject project = new IfcClientProject();
        ProjectPresenter pr = new ProjectPresenter(project);
        panel.add(pr.getView(), "General2");
    }

    @UiHandler("save")
    public void handleClick1(ClickEvent e) {
        int i = 0;
    }



    interface PopupGeneralUiBinder extends UiBinder<Widget, PopupGeneral> {
    }
}
