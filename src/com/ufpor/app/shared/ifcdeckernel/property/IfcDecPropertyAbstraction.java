package com.ufpor.app.shared.ifcdeckernel.property;

import javax.jdo.annotations.*;
import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecPropertyAbstraction implements Serializable {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    protected com.google.appengine.api.datastore.Key key;
}
