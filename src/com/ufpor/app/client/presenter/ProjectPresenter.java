package com.ufpor.app.client.presenter;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.view.project.HalfPopUpView;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.IfcClientProject;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */

public class ProjectPresenter implements ProjectPresenterI {
    private IfcClientProject project;
    private HalfPopUpView projectView1;
    private double minArea;
    private double maxArea;
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


    public ProjectPresenter(IfcClientProject project) {
        this.project = project;
        projectView1 = new HalfPopUpView();
        initializeView(projectView1);

    }

    private void initializeView(HalfPopUpView projectView1) {
        projectView1.setFirstTextBoxTitleL("Name");
        projectView1.setSecondTextBoxTitleL("Max Area");
        projectView1.setThirdTextBoxTitleL("Min Area");
        projectView1.setLongTextBoxL("Long Name");

        projectView1.getFirstTextBoxL().addKeyUpHandler(nameChanged);
        projectView1.getSecondTextBoxL().addKeyUpHandler(maxAreaChanged);
        projectView1.getThirdTextBoxL().addKeyUpHandler(minAreaChanged);
        projectView1.getLongTextBoxL().addKeyUpHandler(longNameChanged);
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
    public Widget getView() {
        return projectView1;
    }

    public interface Display {
        HasKeyUpHandlers getNameHandler();
        HasKeyUpHandlers getAreaHandler();
        HasKeyUpHandlers getMinAreaHandler();
        HasKeyUpHandlers getMaxAreaHandler();
    }
}
