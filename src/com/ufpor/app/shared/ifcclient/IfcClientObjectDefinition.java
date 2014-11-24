package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.shared.ifcclient.select.IfcClientDefinitionSelect;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */

public abstract class IfcClientObjectDefinition extends IfcClientRoot implements IfcClientDefinitionSelect {
    //this property represents the inverse: HasContext	:	SET [0:1] OF IfcRelDeclares FOR RelatedDefinitions; relationship
    protected ArrayList<IfcClientContext> hasContext;

    protected IfcClientObjectDefinition(String GUID, LoginInfo user) {
        super(GUID, user);
    }

    public IfcClientObjectDefinition() {
    }

    public ArrayList<IfcClientContext> getHasContext() {
        return hasContext;
    }

    public void setHasContext(ArrayList<IfcClientContext> hasContext) {
        this.hasContext = hasContext;
    }
}
