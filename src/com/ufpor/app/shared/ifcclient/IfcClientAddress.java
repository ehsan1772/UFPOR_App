package com.ufpor.app.shared.ifcclient;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
public class IfcClientAddress implements Serializable{
    public final static String TAG = IfcClientAddress.class.getSimpleName();

    protected IfcClientAddress() {
    }

    public void setUserDefinedPurpose(IfcClientLabel userDefinedPurpose) {
        this.userDefinedPurpose = userDefinedPurpose;
        this.purpose = IfcClientAddressTypeEnum.USERDEFINED;
    }

    public void setPurpose(IfcClientAddressTypeEnum purpose) {
        if (purpose != IfcClientAddressTypeEnum.USERDEFINED) {
            this.purpose = purpose;
        } else {
            Logger logger = Logger.getLogger(TAG);
            logger.log(Level.SEVERE, "For user defined purposes set the type through setUserDefinedPurpose(IfcLabel userDefinedPurpose)");
        }
    }

    protected IfcClientAddress(IfcClientLabel userDefinedPurpose) {
        this.userDefinedPurpose = userDefinedPurpose;
        purpose = IfcClientAddressTypeEnum.USERDEFINED;
    }

    private IfcClientAddressTypeEnum purpose;
    private IfcClientText description;
    private IfcClientLabel userDefinedPurpose;
}
