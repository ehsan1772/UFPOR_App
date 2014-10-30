package com.ufpor.app.server.ifcKernel;

import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcRelNests extends IfcRelDecomposes {
    public final static String TAG = IfcRelNests.class.getSimpleName();
    private IfcObjectDefinition relatingObject;

    public IfcRelNests() {
    }

    public IfcRelNests(IfcObjectDefinition relatingObject, List<IfcObjectDefinition> relatedObjects) {
        this.relatingObject = relatingObject;
        this.relatedObjects = relatedObjects;
    }

    private List<IfcObjectDefinition> relatedObjects;
}
