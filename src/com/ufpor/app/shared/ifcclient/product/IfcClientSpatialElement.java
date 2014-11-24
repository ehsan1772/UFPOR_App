package com.ufpor.app.shared.ifcclient.product;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */

public abstract class IfcClientSpatialElement extends IfcClientProduct {
    protected IfcClientLabel longName;
    //TODO this relationship should be represented by IfcRelAggregates
    protected ArrayList<IfcClientSpatialElement> children;
    protected IfcClientSpatialElement(String GUID, LoginInfo user) {
        super(GUID, user);
        children = new ArrayList<IfcClientSpatialElement>();
    }

    public IfcClientSpatialElement() {
    }

    public IfcClientLabel getLongName() {
        return longName;
    }

    public void setLongName(IfcClientLabel longName) {
        this.longName = longName;
    }

    public void addChild(IfcClientSpatialElement child) {
        //TODO make sure that the child is an instance of this class
        children.add(child);
    }
}
