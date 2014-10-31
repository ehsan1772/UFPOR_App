package com.ufpor.app.shared.ifc.ifcKernel;

import java.util.Set;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcRelAssigns extends IfcRelationship {
    private Set<IfcObjectDefinition> relatedObjects;
    private IfcObjectTypeEnum relatedObjectsType;
}
