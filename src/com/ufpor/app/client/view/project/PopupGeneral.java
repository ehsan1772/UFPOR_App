package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.App;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.eventbus.ProjectCreatedEvent;
import com.ufpor.app.client.eventbus.ServerResultEvent;
import com.ufpor.app.client.presenter.ProjectPresenter;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.HomeView;
import com.ufpor.app.client.view.PopupBase;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.IfcClientSIUnit;

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
        setTitle("New Project");
        copyButton.setVisible(false);
        delete.setVisible(false);
    }

    @Override
    protected void initWidget(Widget widget) {
        initWidgetSuper(widget);
        project = new IfcClientProject();
        ProjectPresenter projectPresenter = new ProjectPresenter(project);

        for(String title : projectPresenter.getViews().keySet()) {
            panel.add(projectPresenter.getViews().get(title) , title);
        }
    }

    @UiHandler("save")
    public void handleClick1(ClickEvent e) {

        EnvironmentService.App.getInstance().addProject(project, false, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                host.closePopupBase();
            }

            @Override
            public void onSuccess(List<String> result) {
                ServerResultEvent event = new ServerResultEvent(result.get(0));
                ProjectCreatedEvent event2 = new ProjectCreatedEvent(project);

                App.getInjector().getSimpleEventBus().fireEvent(event);
                App.getInjector().getSimpleEventBus().fireEvent(event2);


                HomeView.projectName = project.getName();
                host.closePopupBase();
            }
        });



    }

    private void setProjectTestValues() {
        project.setName("Test Name");
        project.setMaxArea(1000);
        project.setMinArea(990);
        project.setLongName(new IfcClientLabel("Long Test Name"));
        project.setDescription("Test Description");

        IfcClientSIUnit.IfcSIUnitName unitName = IfcClientSIUnit.IfcSIUnitName.valueOf("METRE");
        IfcClientSIUnit lengthUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.LENGTHUNIT, null, unitName);
        project.getUnitsInContext().addUnit(lengthUnit);

        IfcClientSIUnit.IfcSIUnitName unitName2 = IfcClientSIUnit.IfcSIUnitName.valueOf("SQUARE_METRE");
        IfcClientSIUnit lengthUnit2 = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.AREAUNIT, null, unitName);
        project.getUnitsInContext().addUnit(lengthUnit2);

        IfcClientSIUnit.IfcSIUnitName unitName3 = IfcClientSIUnit.IfcSIUnitName.valueOf("CUBIC_METRE");
        IfcClientSIUnit lengthUnit3 = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.VOLUMEUNIT, null, unitName);
        project.getUnitsInContext().addUnit(lengthUnit3);
    }

//    private void setTestSpaceTpeValues() {
//        IfcClientSpaceType type = new IfcClientSpaceType();
//
//        //general attributes
//        type.setName("Test Type");
//        type.setLongName("Long test type");
//        type.setPredefinedType(IfcClientSpaceType.IfcSpaceTypeEnum.EXTERNAL);
//        type.setDescription("description");
//
//
//        //properties
//        SpaceCommonPropertySetBuilder pBuilder = new SpaceCommonPropertySetBuilder();
//        pBuilder.setGrossPlannedArea(1000);
//        pBuilder.setIsExternal(true);
//        pBuilder.setIsPublic(true);
//        pBuilder.setIsHandicapAccessible(false);
//        pBuilder.setNetPlannedArea(900);
//        pBuilder.setReferenceId("123");
//        pBuilder.setIsPubliclyAccessible(true);
//
//        //constraints
//        SpaceBaseQuantitiesBuilder constraintBuilder = new SpaceBaseQuantitiesBuilder();
//        constraintBuilder.setMaxGrossCeilingArea(1200);
//        constraintBuilder.setMaxGrossFloorArea(1240);
//        constraintBuilder.setMaxGrossWallArea(2050);
//
//        constraintBuilder.setMaxNetCeilingArea(2200);
//        constraintBuilder.setMaxNetFloorArea(1445);
//        constraintBuilder.setMaxNetWallArea(134);
//
//        constraintBuilder.setMaxFinishCeilingHeight(123);
//        constraintBuilder.setMaxFinishFloorHeight(234);
//        constraintBuilder.setMaxHeight(345);
//
//        constraintBuilder.setMinGrossCeilingArea(879);
//        constraintBuilder.setMinGrossFloorArea(34);
//        constraintBuilder.setMinGrossWallArea(3245);
//
//        constraintBuilder.setMinNetCeilingArea(1234);
//        constraintBuilder.setMinNetFloorArea(23);
//        constraintBuilder.setMinNetWallArea(453);
//
//        constraintBuilder.setMinFinishCeilingHeight(103);
//        constraintBuilder.setMinFinishFloorHeight(204);
//        constraintBuilder.setMinHeight(305);
//
//        type.addPropertySet(pBuilder.getProperties());
//        type.addPropertySet(constraintBuilder.getIfcClientElementQuantity());
//
//        EnvironmentService.App.getInstance().addSpaceType(type , new AsyncCallback<List<String>>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                int i = 0;
//            }
//
//            @Override
//            public void onSuccess(List<String> result) {
//                ServerResultEvent event = new ServerResultEvent(result.get(0));
//                App.getInjector().getSimpleEventBus().fireEvent(event);
//                host.closePopupBase();
//            }
//        });
//
//    }


    interface PopupGeneralUiBinder extends UiBinder<Widget, PopupGeneral> {
    }
}
