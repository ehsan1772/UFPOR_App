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
import com.ufpor.app.client.presenter.ProjectPresenter;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.shared.ifcclient.*;

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

        project.setName(new IfcClientLabel("Test Name"));
        project.setMaxArea(1000);
        project.setMinArea(990);
        project.setLongName(new IfcClientLabel("Long Test Name"));
        project.setDescription(new IfcClientText("Test Description"));

        IfcClientSIUnit.IfcClientSIUnitName unitName = IfcClientSIUnit.IfcClientSIUnitName.valueOf("METRE");
        IfcClientSIUnit lengthUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.LENGTHUNIT, null, unitName);
        project.getUnitsInContext().addUnit(lengthUnit);

        IfcClientSIUnit.IfcClientSIUnitName unitName2 = IfcClientSIUnit.IfcClientSIUnitName.valueOf("SQUARE_METRE");
        IfcClientSIUnit lengthUnit2 = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.AREAUNIT, null, unitName);
        project.getUnitsInContext().addUnit(lengthUnit2);

        IfcClientSIUnit.IfcClientSIUnitName unitName3 = IfcClientSIUnit.IfcClientSIUnitName.valueOf("CUBIC_METRE");
        IfcClientSIUnit lengthUnit3 = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.VOLUMEUNIT, null, unitName);
        project.getUnitsInContext().addUnit(lengthUnit3);




        EnvironmentService.App.getInstance().addProject(project, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<String> result) {
                ServerResultEvent event = new ServerResultEvent(result.get(0));
                App.getInjector().getSimpleEventBus().fireEvent(event);
                host.closePopupBase();
            }
        });

    }



    interface PopupGeneralUiBinder extends UiBinder<Widget, PopupGeneral> {
    }
}
