package com.ufpor.app.shared.ifcdeckernel.type;

import com.google.appengine.api.users.User;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created by Ehsan Barekati on 11/24/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class IfcDecTypeProduct extends IfcDecTypeObject {
    public IfcDecTypeProduct(String guid, User user) {
        super(guid, user);
    }
}
