package com.ufpor.app.server.ifcphysical;

/**
 * This interface represents the IFC step file. the implementation
 * is not aware of the logic behind the IFC file and is only used to manage
 * the structure of the file
 *
 * Created by Ehsan Barekati on 12/9/14.
 */
public interface IfcFileWriterI {
    /**
     *
     * @param ifcObject the string representing the object to add to the file
     * @return the number of the added object
     */
    int addObject(String ifcObject);

    /**
     *
     * @param ifcObject
     * @param number the number correspondent to the object
     */
    void addObject(String ifcObject, int number);

    String getStepFile();

    /**
     *
     * @param header the header of the file
     */
    void addHeader(String header);

    boolean isFileReady();
}
