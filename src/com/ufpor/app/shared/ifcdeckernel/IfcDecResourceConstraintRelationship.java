package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;

/**
 * Created by Ehsan Barekati on 12/16/14.
 */
public class IfcDecResourceConstraintRelationship<T extends IfcFileObject> extends IfcDecResourceLevelRelationship {


    public IfcDecResourceConstraintRelationship(T owner) {
        super(owner);
    }

    public IfcDecResourceConstraintRelationship() {
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        String arrayObjects = fileManager.getNumberString(getList());
        String constraintNumber = fileManager.getNumber(getOwner());
        return String.format(Constants.IFCRESOURCECONSTRAINTRELATIONSHIP, name, description, constraintNumber, arrayObjects);
    }
}
