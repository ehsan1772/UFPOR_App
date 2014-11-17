package com.ufpor.app.shared.ifcdeckernel.property;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecPropertySetDefinition extends IfcDecProperryDefinition implements IfcDecPropertySetDefinitionSelect {
}
