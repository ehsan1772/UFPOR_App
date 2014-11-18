package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.shared.ifcdeckernel.property.IfcDecText;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public abstract class IfcDecAddress {
    public final static String TAG = IfcDecAddress.class.getSimpleName();

    public void setUserDefinedPurpose(IfcDecLabel userDefinedPurpose) {
        this.userDefinedPurpose = userDefinedPurpose;
        this.purpose = IfcDecAddressTypeEnum.USERDEFINED;
    }

    public void setPurpose(IfcDecAddressTypeEnum purpose) {
        if (purpose != IfcDecAddressTypeEnum.USERDEFINED) {
            this.purpose = purpose;
        } else {
            Logger logger = Logger.getLogger(TAG);
            logger.log(Level.SEVERE, "For user defined purposes set the type through setUserDefinedPurpose(IfcLabel userDefinedPurpose)");
        }
    }

    protected IfcDecAddress(IfcDecLabel userDefinedPurpose) {
        this.userDefinedPurpose = userDefinedPurpose;
        purpose = IfcDecAddressTypeEnum.USERDEFINED;
    }

    private IfcDecAddressTypeEnum purpose;
    private IfcDecText description;
    private IfcDecLabel userDefinedPurpose;
}
