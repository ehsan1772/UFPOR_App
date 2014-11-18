package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientConstraintEnum;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public enum IfcDecConstraintEnum {
    HARD,
    SOFT,
    ADVISORY,
    USERDEFINED,
    NOTDEFINED;

    public static IfcDecConstraintEnum getInstance(IfcClientConstraintEnum client) {
        for(IfcDecConstraintEnum en : IfcDecConstraintEnum.values()) {
            if (en.name().equals(client.name())) {
                return en;
            }
        }
        return null;
    }

}
