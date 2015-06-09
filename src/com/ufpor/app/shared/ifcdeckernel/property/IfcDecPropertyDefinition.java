package com.ufpor.app.shared.ifcdeckernel.property;

import com.google.appengine.api.users.User;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecDefinitionSelect;
import com.ufpor.app.shared.ifcdeckernel.relationship.IfcDecRelDefinesByProperties;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecPropertyDefinition extends IfcDecRoot implements IfcDecDefinitionSelect {
    @Persistent(serialized = "true")
    protected ArrayList<IfcDecConstraint> constraints = new ArrayList<IfcDecConstraint>();

    @NotPersistent
    protected IfcDecRelDefinesByProperties<IfcFileObject> relatedObjectsProp;

    public IfcDecPropertyDefinition(String GUID, User user, IfcFileObject... relatedObjects) {
        super(GUID, user);

        this.relatedObjectsProp = new IfcDecRelDefinesByProperties<IfcFileObject>(GUID, getUser(), this);

        this.relatedObjectsProp.addAll(Arrays.asList(relatedObjects));
    }

    protected IfcDecPropertyDefinition() {

    }

    public IfcDecRelDefinesByProperties<IfcFileObject> getRelatedObjectsProp() {
        return relatedObjectsProp;
    }

    public void setRelatedObjectsProp(IfcDecRelDefinesByProperties<IfcFileObject> relatedObjectsProp) {
        this.relatedObjectsProp = relatedObjectsProp;
    }

    public void addRelatedObjectsProp(IfcFileObject... relatedObjects) {
        if (this.relatedObjectsProp == null) {
            String guid = GuidCompressor.getNewIfcGloballyUniqueId();
            this.relatedObjectsProp = new IfcDecRelDefinesByProperties<IfcFileObject>(guid, getUser(), this);
        }
        this.relatedObjectsProp.addAll(Arrays.asList(relatedObjects));
    }

    public ArrayList<IfcDecConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<IfcDecConstraint> constraints) {
        this.constraints = constraints;
    }
}
