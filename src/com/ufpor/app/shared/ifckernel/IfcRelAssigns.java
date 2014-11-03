package com.ufpor.app.shared.ifckernel;

import java.util.Set;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcRelAssigns extends IfcRelationship {
    private Set<IfcObjectDefinition> relatedObjects;
    private IfcObjectTypeEnum relatedObjectsType;
}
