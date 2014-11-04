package com.ufpor.app.shared.ifcclient;

import java.util.Set;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcDecRelAssigns extends IfcDecRelationship {
    private Set<IfcDecObjectDefinition> relatedObjects;
    private IfcDecObjectTypeEnum relatedObjectsType;
}
