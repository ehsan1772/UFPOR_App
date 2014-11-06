package com.ufpor.app.client.data;

import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;
import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;

/**
 * Created by Ehsan Barekati on 11/6/14.
 */
public interface SpaceBuilderI {
    IfcClientSpace build();
    String setName(String name);
    String setLongName(String longName);
    String setDescription(String description);
    String setCompositionType(IfcElementCompositionEnum type);
    IfcClientSpace getSpace();
    void resetBuilder();
}
