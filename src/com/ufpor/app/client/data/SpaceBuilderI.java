package com.ufpor.app.client.data;

import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;

/**
 * Created by Ehsan Barekati on 11/6/14.
 */
public interface SpaceBuilderI {
    IfcClientSpace build();
    String setName(String name);
    String setLongName(String longName);
    String setType(String type);
    String setDescription(String description);
    String setCompositionType(String type);
    IfcClientSpace getSpace();
    void resetBuilder();
}
