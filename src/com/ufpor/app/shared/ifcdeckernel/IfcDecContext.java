package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.shared.ifcclient.select.IfcClientDefinitionSelect;
import com.ufpor.app.shared.ifcclient.select.IfcClientPropertySetDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinitionSelect;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecContext extends IfcDecObjectDefinition {
    protected ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy;
    protected ArrayList<IfcDecDefinitionSelect> declares;
    protected IfcDecLabel objectType;
    protected IfcDecLabel longName;
    protected IfcDecLabel phase;
}
