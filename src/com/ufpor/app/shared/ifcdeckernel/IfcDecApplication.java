package com.ufpor.app.shared.ifcdeckernel;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

@PersistenceCapable
public class IfcDecApplication implements Serializable {
    @Persistent
    private IfcDecOrganization applicationDeveloper;
    @Persistent
    private IfcDecLabel version;
    @Persistent
    private IfcDecLabel applicationFullName;
    @Persistent
    private IfcDecIdentifier applicationIdentifier;

    /**
     *
     * @param applicationDeveloper
     * @param version (should be unique)
     * @param applicationFullName (should be unique)
     * @param applicationIdentifier (should be unique)
     */
    public IfcDecApplication(IfcDecOrganization applicationDeveloper, IfcDecLabel version, IfcDecLabel applicationFullName, IfcDecIdentifier applicationIdentifier) {
        this.applicationDeveloper = applicationDeveloper;
        this.version = version;
        this.applicationFullName = applicationFullName;
        this.applicationIdentifier = applicationIdentifier;
    }

    private IfcDecApplication() {
    }

    public IfcDecOrganization getApplicationDeveloper() {
        return applicationDeveloper;
    }

    public IfcDecLabel getVersion() {
        return version;
    }

    public IfcDecLabel getApplicationFullName() {
        return applicationFullName;
    }

    public IfcDecIdentifier getApplicationIdentifier() {
        return applicationIdentifier;
    }
}
