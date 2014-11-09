package com.ufpor.app.shared.ifcclient;


import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */


public class IfcClientApplication implements Serializable {

    private IfcClientOrganization applicationDeveloper;

    private IfcClientLabel version;

    private IfcClientLabel applicationFullName;

    private IfcClientIdentifier applicationIdentifier;

    /**
     *
     * @param applicationDeveloper
     * @param version (should be unique)
     * @param applicationFullName (should be unique)
     * @param applicationIdentifier (should be unique)
     */
    public IfcClientApplication(IfcClientOrganization applicationDeveloper, IfcClientLabel version, IfcClientLabel applicationFullName, IfcClientIdentifier applicationIdentifier) {
        this.applicationDeveloper = applicationDeveloper;
        this.version = version;
        this.applicationFullName = applicationFullName;
        this.applicationIdentifier = applicationIdentifier;
    }

    private IfcClientApplication() {
    }

    public IfcClientOrganization getApplicationDeveloper() {
        return applicationDeveloper;
    }

    public IfcClientLabel getVersion() {
        return version;
    }

    public IfcClientLabel getApplicationFullName() {
        return applicationFullName;
    }

    public IfcClientIdentifier getApplicationIdentifier() {
        return applicationIdentifier;
    }
}
