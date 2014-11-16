package com.ufpor.app.shared.ifcdeckernel.property;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecPropetySet extends IfcDecPropertySetDefinition {
}
