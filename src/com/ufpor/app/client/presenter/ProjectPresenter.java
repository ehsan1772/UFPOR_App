package com.ufpor.app.client.presenter;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.view.project.HalfPopUpView;
import com.ufpor.app.client.view.project.SpinnerPopUpView;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientNamedUnit;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.IfcClientSIUnit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */

public class ProjectPresenter implements ProjectPresenterI {
    private IfcClientProject project;
    private HalfPopUpView projectView1;
    private SpinnerPopUpView projectView2;
    private double minArea;
    private double maxArea;
    private HashMap<String, Widget> views;
    private KeyUpHandler nameChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            project.setName(new IfcClientLabel(projectView1.getFirstTextBoxLText()));
        }
    };

    private KeyUpHandler longNameChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            project.setLongName(new IfcClientLabel(projectView1.getLongTextBoxLText()));
        }
    };

    private KeyUpHandler maxAreaChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            project.setMaxArea(Double.valueOf(projectView1.getSecondTextBoxLText()));
        }
    };

    private KeyUpHandler minAreaChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            project.setMinArea(Double.valueOf(projectView1.getThirdTextBoxLText()));
        }
    };

    private KeyUpHandler lengthUnitChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            IfcClientSIUnit.IfcClientSIUnitName unitName = IfcClientSIUnit.IfcClientSIUnitName.valueOf(projectView2.getFirstListBoxLText());
            IfcClientSIUnit lengthUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.LENGTHUNIT, null, unitName);
            project.getUnitsInContext().addUnit(lengthUnit);
        }
    };

    private KeyUpHandler areaUnitChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            IfcClientSIUnit.IfcClientSIUnitName unitName = IfcClientSIUnit.IfcClientSIUnitName.valueOf(projectView2.getSecondListBoxLText());
            IfcClientSIUnit areaUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.AREAUNIT, null, unitName);
            project.getUnitsInContext().addUnit(areaUnit);
        }
    };

    private KeyUpHandler volumeUnitChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            IfcClientSIUnit.IfcClientSIUnitName unitName = IfcClientSIUnit.IfcClientSIUnitName.valueOf(projectView2.getThirdListBoxLText());
            IfcClientSIUnit volumeUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcClientUnitEnum.VOLUMEUNIT, null, unitName);
            project.getUnitsInContext().addUnit(volumeUnit);
        }
    };

    public ProjectPresenter(IfcClientProject project) {
        this.project = project;
        views = new HashMap<String, Widget>();
        projectView1 = new HalfPopUpView();
        projectView2 = new SpinnerPopUpView();

        views.put("General", projectView1);
        views.put("Setting", projectView2);

        initializeView();
    }

    private void initializeView() {
        projectView1.setFirstTextBoxTitleL("Name");
        projectView1.setSecondTextBoxTitleL("Max Area");
        projectView1.setThirdTextBoxTitleL("Min Area");
        projectView1.setLongTextBoxL("Long Name");

        projectView1.getFirstTextBoxL().addKeyUpHandler(nameChanged);
        projectView1.getSecondTextBoxL().addKeyUpHandler(maxAreaChanged);
        projectView1.getThirdTextBoxL().addKeyUpHandler(minAreaChanged);
        projectView1.getLongTextBoxL().addKeyUpHandler(longNameChanged);

        projectView2.setFirstTextBoxTitleL("Length Unit");
        projectView2.setSecondTextBoxTitleL("Area Unit");
        projectView2.setThirdTextBoxTitleL("Volume Unit");

        ArrayList<String> vals = new ArrayList<String>();

        for (IfcClientSIUnit.IfcClientSIUnitName val :IfcClientSIUnit.IfcClientSIUnitName.values() ) {
            vals.add(val.name());
        }
        projectView2.setFirstListBoxL(vals.toArray(new String[0]));
        projectView2.setSecondListBoxL(vals.toArray(new String[0]));
        projectView2.setThirdListBoxL(vals.toArray(new String[0]));

        projectView2.getFirstListBoxL().addKeyUpHandler(lengthUnitChanged);
        projectView2.getSecondListBoxL().addKeyUpHandler(areaUnitChanged);
        projectView2.getThirdListBoxL().addKeyUpHandler(volumeUnitChanged);
    }

    @Override
    public void setProjectName(String name) {
        project.setName(new IfcClientLabel(name));
    }

    @Override
    public void setProjectTotalArea(double area) {
        project.setTotalGrossArea(area);
    }

    @Override
    public void setProjectMaxArea(double area) {
        maxArea = area;
        //    project.setAreaBound(maxArea, minArea);
    }

    @Override
    public void setProjectMinArea(double area) {
        minArea = area;
        //   project.setAreaBound(maxArea, minArea);
    }

    @Override
    public void totalAreaChangedListener(double area) {
        setProjectTotalArea(area);
    }

    @Override
    public void projectNameChangedListener(String name) {
        setProjectName(name);
    }

    @Override
    public void projectMinAreaChangedListener(double area) {
        setProjectMinArea(area);
    }

    @Override
    public void projectMaxAreaChangedListener(double area) {
        setProjectMaxArea(area);
    }

    @Override
    public HashMap<String, Widget> getView() {
        return views;
    }

//    @Override
//    public Widget getView() {
//        return projectView1;
//    }

    public interface Display {
        HasKeyUpHandlers getNameHandler();

        HasKeyUpHandlers getAreaHandler();

        HasKeyUpHandlers getMinAreaHandler();

        HasKeyUpHandlers getMaxAreaHandler();
    }
}
