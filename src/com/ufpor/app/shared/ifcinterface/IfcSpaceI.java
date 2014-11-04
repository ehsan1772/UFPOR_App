package com.ufpor.app.shared.ifcinterface;

import com.ufpor.app.shared.ifckernel.IfcElementCompositionEnum;
import com.ufpor.app.shared.ifckernel.IfcLabel;
import com.ufpor.app.shared.ifckernel.IfcLengthMeasure;
import com.ufpor.app.shared.ifckernel.IfcOwnerHistory;
import com.ufpor.app.shared.ifckernel.product.IfcObjectPlacement;
import com.ufpor.app.shared.ifckernel.product.IfcProductionRepresentation;
import com.ufpor.app.shared.ifckernel.product.IfcSpaceTypeEnum;

/**
 * Created by Ehsan Barekati on 11/4/14.
 */
public interface IfcSpaceI {
    String getGUID();
    IfcOwnerHistory getOwnerHistory();
    IfcLabel getName();
    IfcLabel getDescription();
    IfcLabel getObjectType();
    IfcObjectPlacement getObjectPlacement();
    IfcProductionRepresentation getRepresentation();
    IfcLabel getLongName();
    IfcElementCompositionEnum getCompositionType();
    IfcSpaceTypeEnum getPredefinedType();
    IfcLengthMeasure getElevationWithFlooring();
}
