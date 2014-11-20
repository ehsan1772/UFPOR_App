package com.ufpor.app.client.presenter;

/**
 * Created by Ehsan Barekati on 11/13/14.
 */
public interface ProjectPresenterI extends PresenterI{

    //setters
    void setProjectName(String name);
    void setProjectTotalArea(double area);
    void setProjectMaxArea(double area);
    void setProjectMinArea(double area);

    //event listeners
    void totalAreaChangedListener(double area);
    void projectNameChangedListener(String name);
    void projectMinAreaChangedListener(double area);
    void projectMaxAreaChangedListener(double area);

}
