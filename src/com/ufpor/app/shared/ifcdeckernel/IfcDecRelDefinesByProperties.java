package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;
import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;

/**
 * Created by Ehsan Barekati on 12/16/14.
 */
public class IfcDecRelDefinesByProperties<T extends IfcFileObject> extends IfcDecRelDefines {
    public IfcDecRelDefinesByProperties(String GUID, User user, IfcFileObject owner) {
        super(GUID, user, owner);
    }

    public IfcDecRelDefinesByProperties() {
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        String arrayObjects = fileManager.getNumberString(getList());
        String constraintNumber = fileManager.getNumber(getOwner());

        String guid = getGlobalId().getValue();
        String ownerHistory = "*";
        String name = "*";
        String description = "*";
        String relatedObjects = arrayObjects;
        String relatingProperty = fileManager.getNumber(getOwner());


        return String.format(Constants.IFCRELDEFINESBYPROPERTIES, guid, ownerHistory, name, description, relatedObjects, relatingProperty);

    }
}
