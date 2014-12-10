package com.ufpor.app.server.ifcphysical;

import com.google.gwt.thirdparty.guava.common.collect.BiMap;
import com.google.gwt.thirdparty.guava.common.collect.HashBiMap;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ehsan Barekati on 12/9/14.
 */
public class IfcFileManager implements IfcFileManagerI {
    private static IfcFileManager ourInstance = new IfcFileManager();
    private BiMap<Integer, IfcFileObject> objectsBiMap;
    private Integer counter;
    private IfcDecProject project;
    private IfcFileWriterI fileWriter;

    private IfcFileManager() {
        objectsBiMap = HashBiMap.create();
        counter = 0;
    }

    public static IfcFileManager getInstance() {
        return ourInstance;
    }

    @Override
    public Integer addObject(IfcFileObject ifcObject) {
        if (objectsBiMap.values().contains(ifcObject)) {
            return null;
        }
        Integer number = getNextNumber();
        objectsBiMap.put(number, ifcObject);
        ifcObject.setNumber(number);
        return number;
    }

    @Override
    public BiMap<Integer, IfcFileObject> getObjectsMap() {
        return objectsBiMap;
    }

    @Override
    public Integer getNumber(IfcDecObject object) {
        return objectsBiMap.inverse().get(object);

    }

    @Override
    public int getNextNumber() {
        counter++;
        return counter;
    }

    @Override
    public IfcFileObject getObject(int number) {
        return objectsBiMap.get(number);
    }

    @Override
    public void setProject(IfcDecProject project) {
        this.project = project;
    }

    @Override
    public void reset() {
        objectsBiMap = HashBiMap.create();
        counter = 0;
    }

    @Override
    public String getHeader() {
        return project.getHeader();
    }

    @Override
    public String getStepFile() {
        if (fileWriter.isFileReady()) {
            return fileWriter.getStepFile();
        }

        return null;
    }

    @Override
    public ArrayList<String> getStepObjects() {
        return null;
    }

    @Override
    public void GenerateTheFile() {
        //getting all the objects in the project
        Set<IfcFileObject> objects = new HashSet<>(getAllObjects(project));

        //adding them to the map and assigning a number to them
        for (IfcFileObject object : objects) {
            addObject(object);
        }

        fileWriter.addHeader(project.getHeader());

        for (IfcFileObject object : objectsBiMap.values()) {
            fileWriter.addObject(object.getObjectString(this));
        }

    }

    /**
     *
     * @param object
     * @return
     */
    private ArrayList<IfcFileObject> getAllObjects(IfcFileObject object) {
        ArrayList<IfcFileObject> results = new ArrayList<>();
        results.add(object);
        for (IfcFileObject childObject : object.getRelatedObjects()) {
            results.addAll(getAllObjects(childObject));
        }
        return results;
    }
}
