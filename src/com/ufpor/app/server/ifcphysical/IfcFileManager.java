package com.ufpor.app.server.ifcphysical;


import com.google.appengine.repackaged.com.google.common.collect.BiMap;
import com.google.appengine.repackaged.com.google.common.collect.HashBiMap;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private ArrayList<IfcFileObject> results;

    private IfcFileManager() {
        objectsBiMap = HashBiMap.create();
        counter = 0;
        fileWriter = new IfcFileWriter();
        results = new ArrayList<>();
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
    public String getNumber(IfcFileObject object) {
        return "#" + objectsBiMap.inverse().get(object);

    }

    @Override
    public String getNumberString(List<IfcFileObject> objects) {
        StringBuilder result = new StringBuilder(objects.size() * 4);
        result.append("(");

        for (IfcFileObject object : objects) {
            result.append("#");
            result.append(objectsBiMap.inverse().get(object));
            if (objects.indexOf(object) != objects.size() - 1) {
                result.append(", ");
            }
        }

        result.append(")");

        return result.toString();
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
        reset();
        this.project = project;
    }

    @Override
    public void reset() {
        objectsBiMap = HashBiMap.create();
        counter = 0;
        fileWriter = new IfcFileWriter();
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
        getAllObjects(project, results);
        Set<IfcFileObject> objects = new HashSet<>(results);

        //adding them to the map and assigning a number to them
        for (IfcFileObject object : objects) {
            addObject(object);
        }

        fileWriter.addHeader(project.getHeader());

        for (IfcFileObject object : objectsBiMap.values()) {
            fileWriter.addObject(object.getObjectString(this), getNumber(object));
        }

    }

    /**
     *
     * @param object
     * @return
     */
    private void getAllObjects(IfcFileObject object, ArrayList<IfcFileObject> results) {
        if (object == null || results.contains(object)) {
            return;
        }

        results.add(object);
        ArrayList<IfcFileObject> relatedObjects = object.getRelatedObjects();
        if (relatedObjects!= null) {
            for (IfcFileObject childObject : relatedObjects) {
                if (childObject != null && !results.contains(childObject)) {
                    System.out.println(childObject.getClass().getSimpleName());
                    getAllObjects(childObject, results);
                }
            }
        }
        //return results;
    }
}
