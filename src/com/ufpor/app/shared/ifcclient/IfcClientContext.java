package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.shared.ifcclient.select.IfcClientDefinitionSelect;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public abstract class IfcClientContext extends IfcClientObjectDefinition {
    /*
    Set of property set definitions attached to this context. Those statically or dynamically defined properties contain alphanumeric information content that further defines the context.
     */
    protected ArrayList<IfcClientPropertySetDefinition> isDefinedBy;
    protected ArrayList<IfcClientDefinitionSelect> declares;
    protected IfcClientLabel objectType;
    protected IfcClientLabel longName;
    protected IfcClientLabel phase;

}
