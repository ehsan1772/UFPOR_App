package com.ufpor.app.client.data;

import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpaceTypeEnum;
import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;

/**
 * Created by Ehsan Barekati on 11/6/14.
 */
public class SpaceBuilder implements SpaceBuilderI {
    private IfcClientSpace space;

    public SpaceBuilder() {
        space = new IfcClientSpace();
        space.setPredefinedType(IfcClientSpaceTypeEnum.SPACE);
        space.setCompositionType(IfcElementCompositionEnum.ELEMENT);
    }

    @Override
    public IfcClientSpace build() {
        return null;
    }

    @Override
    public String setName(String name) {
        space.setName(name);
        return name;
    }

    @Override
    public String setLongName(String longName) {
        IfcClientLabel label = new IfcClientLabel(longName);
        space.setLongName(label);
        return label.getValue();
    }

    @Override
    public String setType(String type) {
        for (IfcClientSpaceTypeEnum typeEnum : IfcClientSpaceTypeEnum.values()) {
            if(typeEnum.toString().equals(type)) {
                space.setPredefinedType(typeEnum);
                return typeEnum.toString();
            }
        }
        return null;
    }

    @Override
    public String setDescription(String description) {
        space.setDescription(description);
        return description;
    }

    @Override
    public String setCompositionType(String type) {
        for (IfcClientSpaceTypeEnum typeEnum : IfcClientSpaceTypeEnum.values()) {
            if(typeEnum.toString().equals(type)) {
                space.setPredefinedType(typeEnum);
                return typeEnum.toString();
            }
        }
        return null;
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
