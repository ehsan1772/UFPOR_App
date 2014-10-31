package com.ufpor.app.shared.ifc.ifcKernel;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcApplication {
    private IfcOrganization applicationDeveloper;
    private IfcLabel version;
    private IfcLabel applicationFullName;
    private IfcIdentifier applicationIdentifier;

    /**
     *
     * @param applicationDeveloper
     * @param version (should be unique)
     * @param applicationFullName (should be unique)
     * @param applicationIdentifier (should be unique)
     */
    public IfcApplication(IfcOrganization applicationDeveloper, IfcLabel version, IfcLabel applicationFullName, IfcIdentifier applicationIdentifier) {
        this.applicationDeveloper = applicationDeveloper;
        this.version = version;
        this.applicationFullName = applicationFullName;
        this.applicationIdentifier = applicationIdentifier;
    }

    public IfcOrganization getApplicationDeveloper() {
        return applicationDeveloper;
    }

    public IfcLabel getVersion() {
        return version;
    }

    public IfcLabel getApplicationFullName() {
        return applicationFullName;
    }

    public IfcIdentifier getApplicationIdentifier() {
        return applicationIdentifier;
    }
}
