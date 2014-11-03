package com.ufpor.app.shared.ifckernel.product;

import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 11/1/14.
 */
public class IfcLocalPlacement<T> extends IfcObjectPlacement {
    public final static String TAG = IfcLocalPlacement.class.getSimpleName();
    private static Logger logger = Logger.getLogger(TAG);
    IfcObjectPlacement placementRelTo;
    T relativePlacement;

    public IfcLocalPlacement(Class<T> clazz) {
//        if (isTypeSupported(clazz)) {
//
//        }
    }

//    private boolean isTypeSupported(Class clazz) {
//        if (clazz == 	IfcAxis2Placement2D.class || clazz == 	IfcAxis2Placement3D.class)  {
//            return  true;
//        }
//        logger.log(Level.SEVERE, "IfcDefinitionSelect provides the option to either select an object or type object IfcObjectDefinition, or a property set template or property set, IfcPropertyDefinition.");
//        return false;
//    }
}
