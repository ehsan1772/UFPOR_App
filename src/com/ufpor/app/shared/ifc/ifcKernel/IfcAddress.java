package com.ufpor.app.shared.ifc.ifcKernel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcAddress {
    public final static String TAG = IfcAddress.class.getSimpleName();

    public void setUserDefinedPurpose(IfcLabel userDefinedPurpose) {
        this.userDefinedPurpose = userDefinedPurpose;
        this.purpose = IfcAddressTypeEnum.USERDEFINED;
    }

    public void setPurpose(IfcAddressTypeEnum purpose) {
        if (purpose != IfcAddressTypeEnum.USERDEFINED) {
            this.purpose = purpose;
        } else {
            Logger logger = Logger.getLogger(TAG);
            logger.log(Level.SEVERE, "For user defined purposes set the type through setUserDefinedPurpose(IfcLabel userDefinedPurpose)");
        }
    }

    protected IfcAddress(IfcLabel userDefinedPurpose) {
        this.userDefinedPurpose = userDefinedPurpose;
        purpose = IfcAddressTypeEnum.USERDEFINED;
    }

    private IfcAddressTypeEnum purpose;
    private IfcText description;
    private IfcLabel userDefinedPurpose;
}
