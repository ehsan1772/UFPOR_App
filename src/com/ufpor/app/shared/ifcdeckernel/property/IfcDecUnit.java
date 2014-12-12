package com.ufpor.app.shared.ifcdeckernel.property;

import com.ufpor.app.server.ifcphysical.IfcFileObject;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/15/14.
 */
public interface IfcDecUnit extends Serializable, IfcFileObject {
    String getIfcString();
}
