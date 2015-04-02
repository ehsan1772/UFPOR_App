package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.IfcFileObject;

/**
 * Created by Ehsan Barekati on 12/16/14.
 */
public abstract class IfcDecRelDefines<T extends IfcFileObject> extends IfcDecRelationship {
    public IfcDecRelDefines(String GUID, User user, IfcDecRoot owner) {
        super(GUID, user, owner);
    }

    protected IfcDecRelDefines() {
    }
}
