package com.ufpor.app.client.data;

import com.ufpor.app.shared.ifcclient.IfcDecLabel;
import com.ufpor.app.shared.ifcclient.decproduct.IfcClientSpace;
import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;
import com.ufpor.app.shared.ifckernel.IfcText;

/**
 * Created by Ehsan Barekati on 11/6/14.
 */
public class SpaceBuilder implements SpaceBuilderI {
    private IfcClientSpace space;

    public SpaceBuilder() {
        space = new IfcClientSpace();
    }

    @Override
    public IfcClientSpace build() {
        return null;
    }

    @Override
    public String setName(String name) {
        IfcDecLabel label = new IfcDecLabel(name);
        space.setName(new IfcDecLabel(name));
        return label.getValue();
    }

    @Override
    public String setLongName(String longName) {
        IfcDecLabel label = new IfcDecLabel(longName);
        space.setLongName(label);
        return label.getValue();
    }

    @Override
    public String setDescription(String description) {
        IfcText text = new IfcText(description);
        space.setDescription(text);
        return text.getValue();
    }

    @Override
    public String setCompositionType(IfcElementCompositionEnum type) {
        space.setCompositionType(type);
        return type.toString();
    }

    @Override
    public IfcClientSpace getSpace() {
        return space;
    }

    @Override
    public void resetBuilder() {
        space = new IfcClientSpace();
    }
}
