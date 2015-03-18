package com.ufpor.app.server.ifcphysical;

import java.util.ArrayList;

/**
 * An interface that all the objects who get published in the IFC step file should implement
 *
 * Created by Ehsan Barekati on 12/9/14.
 */
public interface IfcFileObject {
    void setNumber(int number);
    int getNumber();
    ArrayList<IfcFileObject> getRelatedObjects();
    String getObjectString(IfcFileManagerI fileManager);
}
