package com.ufpor.app.server.ifcphysical;

import com.google.gwt.thirdparty.guava.common.collect.BiMap;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;

import java.util.ArrayList;

;

/**
 * This interface should be implemented as a singleton to manage all the
 * IfcObjects in the project.
 *
 * Created by Ehsan Barekati on 12/9/14.
 */
public interface IfcFileManagerI {
    /**
     * All the IfcObjects should be registered through this method
     * to be added to the Ifc file
     *
     * @param ifcObject
     */
    Integer addObject(IfcFileObject ifcObject);


    /**
     * @return A map of unique values and unique IDs. Either one can be retrieved by the other one
     */
    BiMap<Integer, IfcFileObject> getObjectsMap();

    /**
     *
     * @param object
     * @return the number of an object
     */
    String getNumber(IfcFileObject object);


    /**
     *
     * @param objects
     * @return a string that represents all the object numbers
     */
    String getNumberString(ArrayList<IfcFileObject> objects);

    /**
     *
     * @return the next available number
     */
    int getNextNumber();

    /**
     * @param number
     * @return the object that corresponds to the number
     */
    IfcFileObject getObject(int number);

    /**
     *
     * @param project the project that this class assists
     */
    void setProject(IfcDecProject project);

    /**
     * can be called after setting the project or after changes in objects
     */
    void reset();

    /**
     *
     * @return the header of the STEP file
     */
    String getHeader();

    String getStepFile();

    /**
     *
     * @return the strings that are in the file
     */
    ArrayList<String> getStepObjects();

    void GenerateTheFile();

}
