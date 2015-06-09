package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.datastore.PostLoadContext;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecElementQuantity;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySet;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecPropertySetDefinition;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ehsan Barekati on 12/16/14.
 */

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class IfcDecRelDefinesByProperties<T extends IfcFileObject> extends IfcDecRelDefines {
    private IfcDecObject owner;

    private IfcDecPropertySetDefinition relatingObject;
    @Persistent(defaultFetchGroup = "true")
    private IfcDecPropertySet propertySet;
    @Persistent(defaultFetchGroup = "true")
    private IfcDecElementQuantity elementQuantity;

    @Override
    public IfcDecRoot getOwner() {
        return owner;
    }

    @Override
    public List getList() {
        return null;
    }

    public IfcDecRelDefinesByProperties(String GUID, User user, IfcDecRoot owner) {
        super(GUID, user, owner);
    }

    public IfcDecRelDefinesByProperties() {
    }

    @Override
    protected void initialize(IfcDecRoot owner) {

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

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }


    @Override
    public void prepareDataForClient(PostLoadContext context) {
        super.prepareDataForClient(context);
        if (elementQuantity != null) {
            relatingObject = elementQuantity;
        }
        if (propertySet != null) {
            relatingObject = propertySet;
        }
    }

    @Override
    public void prepareDataForStoreIfcDecContext(PutContext context) {
        super.prepareDataForStoreIfcDecContext(context);
            if (relatingObject instanceof IfcDecElementQuantity) {
                elementQuantity = (IfcDecElementQuantity) relatingObject;
            }
            if (relatingObject instanceof IfcDecPropertySet) {
                propertySet = (IfcDecPropertySet) relatingObject;
            }

    }
}
