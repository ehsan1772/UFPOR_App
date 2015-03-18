package com.ufpor.app.client.eventbus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.ufpor.app.shared.ifcclient.IfcClientProject;

/**
 * Created by Ehsan Barekati on 11/21/14.
 */
public class ProjectCreatedEvent extends GwtEvent<ProjectCreatedEvent.ProjectCreatedEventHandler> {
    public final static Type<ProjectCreatedEventHandler> TYPE = new Type<ProjectCreatedEventHandler>();

    public ProjectCreatedEvent(IfcClientProject project) {
        this.project = project;
    }


    private IfcClientProject project;
    @Override
    public Type<ProjectCreatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ProjectCreatedEventHandler handler) {
        handler.onProjectCreatedEvent(this);
    }

    public IfcClientProject getProject() {
        return project;
    }

    public void setProject(IfcClientProject project) {
        this.project = project;
    }

    public static interface ProjectCreatedEventHandler extends EventHandler {
        void onProjectCreatedEvent(ProjectCreatedEvent event);
    }
}
