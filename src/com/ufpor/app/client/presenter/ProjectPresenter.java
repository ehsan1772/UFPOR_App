package com.ufpor.app.client.presenter;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.view.project.FullPopUpView;
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

public class ProjectPresenter implements ProjectPresenterI, FullPopUpView.Presenter {
    private FullPopUpView projectView3;
    private IfcClientProject project;
    private HalfPopUpView projectView1;
    private SpinnerPopUpView projectView2;
    private HashMap<String, Widget> views;
    private KeyUpHandler nameChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            project.setName(projectView1.getFirstTextBoxLText());
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
            if (projectView1.getSecondTextBoxLText().length() > 0) {
                project.setMaxArea(Double.valueOf(projectView1.getSecondTextBoxLText()));
            }
        }
    };

    private KeyUpHandler minAreaChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            if (projectView1.getThirdTextBoxLText().length() > 0) {
                project.setMinArea(Double.valueOf(projectView1.getThirdTextBoxLText()));
            }
        }
    };

    private ChangeHandler lengthUnitChanged = new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
            IfcClientSIUnit.IfcSIUnitName unitName = IfcClientSIUnit.IfcSIUnitName.valueOf(projectView2.getFirstListBoxLText());
            IfcClientSIUnit lengthUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.LENGTHUNIT, null, unitName);
            project.getUnitsInContext().addUnit(lengthUnit);
        }
    };

    private ChangeHandler areaUnitChanged = new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
            IfcClientSIUnit.IfcSIUnitName unitName = IfcClientSIUnit.IfcSIUnitName.valueOf(projectView2.getSecondListBoxLText());
            IfcClientSIUnit areaUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.AREAUNIT, null, unitName);
            project.getUnitsInContext().addUnit(areaUnit);
        }
    };

    private ChangeHandler volumeUnitChanged = new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
            IfcClientSIUnit.IfcSIUnitName unitName = IfcClientSIUnit.IfcSIUnitName.valueOf(projectView2.getThirdListBoxLText());
            IfcClientSIUnit volumeUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.VOLUMEUNIT, null, unitName);
            project.getUnitsInContext().addUnit(volumeUnit);
        }
    };
    private IfcClientProject testValues;

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

        for (IfcClientSIUnit.IfcSIUnitName val : IfcClientSIUnit.IfcSIUnitName.values()) {
            vals.add(val.name());
        }
        projectView2.setFirstListBoxL(vals.toArray(new String[0]));
        projectView2.setSecondListBoxL(vals.toArray(new String[0]));
        projectView2.setThirdListBoxL(vals.toArray(new String[0]));

        projectView2.getFirstListBoxL().addChangeHandler(lengthUnitChanged);
        projectView2.getSecondListBoxL().addChangeHandler(areaUnitChanged);
        projectView2.getThirdListBoxL().addChangeHandler(volumeUnitChanged);

        projectView2.getFirstListBoxL().setSelectedIndex(vals.indexOf(IfcClientSIUnit.IfcSIUnitName.METRE.name()));
        projectView2.getSecondListBoxL().setSelectedIndex(vals.indexOf(IfcClientSIUnit.IfcSIUnitName.SQUARE_METRE.name()));
        projectView2.getThirdListBoxL().setSelectedIndex(vals.indexOf(IfcClientSIUnit.IfcSIUnitName.CUBIC_METRE.name()));

        IfcClientSIUnit.IfcSIUnitName unitName1 = IfcClientSIUnit.IfcSIUnitName.valueOf(projectView2.getFirstListBoxLText());
        IfcClientSIUnit lengthUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.LENGTHUNIT, null, unitName1);
        project.getUnitsInContext().addUnit(lengthUnit);


        IfcClientSIUnit.IfcSIUnitName unitName2 = IfcClientSIUnit.IfcSIUnitName.valueOf(projectView2.getSecondListBoxLText());
        IfcClientSIUnit areaUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.AREAUNIT, null, unitName2);
        project.getUnitsInContext().addUnit(areaUnit);

        IfcClientSIUnit.IfcSIUnitName unitName3 = IfcClientSIUnit.IfcSIUnitName.valueOf(projectView2.getThirdListBoxLText());
        IfcClientSIUnit volumeUnit = new IfcClientSIUnit(IfcClientNamedUnit.IfcUnitEnum.VOLUMEUNIT, null, unitName3);
        project.getUnitsInContext().addUnit(volumeUnit);
       // setTestValues(project);

    }


    @Override
    public void setProjectName(String name) {
        project.setName(name);
    }

    @Override
    public void setProjectTotalArea(double area) {
        project.setTotalGrossArea(area);
    }

    @Override
    public void setProjectMaxArea(double area) {
      //  maxArea = area;
        //    project.setAreaBound(maxArea, minArea);
    }

    @Override
    public void setProjectMinArea(double area) {
       // minArea = area;
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
    public HashMap<String, Widget> getViews() {
        return views;
    }

    @Override
    public void listboxValueChanged(String value, Element as) {
        int i = 3;
    }

    public void setTestValues(IfcClientProject project) {
        int number = (int) (Math.random() * 100000);

        String name = "Test " + number;

        ((TextBox) projectView1.getFirstTextBoxL()).setText("Test " + number);
        ((TextBox) projectView1.getSecondTextBoxL()).setText("1200");
        ((TextBox) projectView1.getThirdTextBoxL()).setText("1000");
        ((TextArea) projectView1.getLongTextBoxL()).setText("Test Long " + number);

        project.setName(name);
        project.setMaxArea(1200);
        project.setMinArea(1000);
        project.setLongName(new IfcClientLabel(name + "Long"));

    }


    public interface Display {
        HasKeyUpHandlers getNameHandler();

        HasKeyUpHandlers getAreaHandler();

        HasKeyUpHandlers getMinAreaHandler();

        HasKeyUpHandlers getMaxAreaHandler();
    }
}
