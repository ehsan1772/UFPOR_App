package com.ufpor.app.server.ifcphysical;

import com.ufpor.app.server.GuidCompressor;
import com.ufpor.app.shared.ifcdeckernel.IfcDecLabel;
import com.ufpor.app.shared.ifcdeckernel.IfcDecOwnerHistory;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.property.*;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ehsan Barekati on 11/19/14.
 */
public class Constants {

    private static Constants instance;

    public final static String HEADER = "ISO-10303-21;\n" +
            "HEADER;\n" +
            "FILE_DESCRIPTION (\n" +
            "        ('%1s'),\n" +
            "        '2;1');\n" +
            "FILE_NAME (\n" +
            "        '%2s.ifc',\n" +
            "        '%3s',\n" +
            "        ('%4s'),\n" +
            "        ('%5s'),\n" +
            "        'UFPOR APP 0.0.1',\n" +
            "        'UFPOR DEMO beta',\n" +
            "        '%6s');\n" +
            "FILE_SCHEMA (('IFC4RC4'));\n" +
            "ENDSEC;\n" +
            "DATA;\n";

    public final static String FOOTER = "ENDSEC;\n" +
            "END-ISO-10303-21;";

    public final static String PROJECT = "IFCPROJECT ('%1s', %2s, %3s, %4s, %5s, %6s, %7s, %8s, %9s);";
    public final static String IFCUNITASSIGNMENT = "IFCUNITASSIGNMENT((%1s));";
    public final static String IFCSIUNIT = "IFCSIUNIT(%1s, %2s, %3s, %4s);";
    public final static String IFCPROPERTYSINGLEVALUE = "IFCPROPERTYSINGLEVALUE(%1s, %2s, %3s, %4s);";
    public final static String IFCELEMENTQUANTITY = "IFCELEMENTQUANTITY(%1s, %2s, %3s, %4s, %5s, %6s);";
    public final static String IFCQUANTITYAREA = "IFCQUANTITYAREA(%1s, %2s, %3s, %4s, %5s);";
    public final static String IFCRELDEFINESBYPROPERTIES =  "IFCRELDEFINESBYPROPERTIES(%1s, %2s, %3s, %4s, %5s, %6s);";
    public final static String IFCMETRIC = "IFCMETRIC ('%1s', %2s, %3s, %4s, %5s, %6s, %7s, %8s, %9s, %10s, %11s);";
    public static final String IFCRESOURCECONSTRAINTRELATIONSHIP = "IFCRESOURCECONSTRAINTRELATIONSHIP(%1s, %2s, %3s, %4s);";

    private final static IFCComparator COMPARATOR = new IFCComparator();
    private ArrayList<String> mFile;

    public static String getIfcFile(String header, ArrayList<String> lines) {
        Collections.sort(lines, COMPARATOR);
        String result = header;
        for (String line : lines) {
            result = result.concat(line);
            result = result.concat("\n");
        }
        result = result.concat(FOOTER);
        return result;
    }

    private int mItemCounter;
    private String nextNumber;

    private Constants() {
        this.mItemCounter = 0;
    }

    public void reset() {
        this.mItemCounter = 0;
        this.mFile = new ArrayList<String>();
    }

    public static String getHeader(IfcDecProject project) {
        String fileDescription = project.getLongName().getValue();
        String fileName = project.getName().getValue();
        String author = project.getUser().getNickname();
        String organization = project.getUser().getEmail();
        String authorization = project.getUser().getAuthDomain();

        return getHeader(fileDescription,
                fileName,
                author,
                organization,
                authorization);
    }


    public static String getHeader(String fileDescription,
                                   String fileName,
                                   String author,
                                   String organization,
                                   String authorization) {
        return String.format(HEADER, fileDescription, fileName, getCurrentDate(), author, organization, authorization);
    }

    public String getProject(IfcDecProject project, ArrayList<String> file) {
        mFile = file;
        String number = getNextNumber();

        //ROOT
        String GUI = project.getGlobalId().getValue();
        String ownerHistory = project.getOwnerHistory() == null ? "$" : getOwnerHistory(project.getOwnerHistory(),file);
        String name = (project.getName().getValue() == null || project.getName().getValue().isEmpty()) ? "$" : project.getName().getValue();
        String description = (project.getDescription() == null || project.getDescription().getValue() == null || project.getDescription().getValue().isEmpty()) ? "$" : project.getDescription().getValue();

        //IfcContext
        String objectType = (project.getObjectType() == null || project.getObjectType().getValue() == null || project.getObjectType().getValue().isEmpty()) ? "$" : project.getObjectType().getValue();
        String longName = (project.getLongName() == null || project.getLongName().getValue() == null || project.getLongName().getValue().isEmpty()) ? "$" : project.getLongName().getValue();
        String phase = (project.getPhase() == null || project.getPhase().getValue() == null || project.getPhase().getValue().isEmpty()) ? "$" : project.getPhase().getValue();
        String representationContexts = "$";
        String unitsInContext = project.getUnitsInContext() == null ? "$" : getUnitsInContext(project, file);

        //adding properties and their constraints
        addIsDefinedBy(project.getIsDefinedBy(), number, file);

        String ifcProject = String.format(PROJECT,
                GUI,
                ownerHistory,
                name,
                description,
                objectType,
                longName,
                phase,
                representationContexts,
                unitsInContext);

        file.add("#" + number + "= " + ifcProject);
        return "#" + number;
    }

    private void addIsDefinedBy(ArrayList<IfcDecPropertySetDefinitionSelect> isDefinedBy, String objectNumber, ArrayList<String> file) {
        //adding all the properties and collecting their numbers
        ArrayList<String> associatedPropertiesString = new ArrayList<String>();
        ArrayList<String> associatedElementQuantitiesString = new ArrayList<String>();

        ArrayList<IfcDecPropertySet> associatedProperties = new ArrayList<IfcDecPropertySet>();
        ArrayList<IfcDecElementQuantity> associatedElementQuantities = new ArrayList<IfcDecElementQuantity>();

        for (IfcDecPropertySetDefinitionSelect select : isDefinedBy) {
            if (select instanceof IfcDecPropertySetDefinition) {
                if (select instanceof IfcDecPropertySet) {
                    associatedPropertiesString.addAll(addIfcRelDefinesByPropertiesPropertSet((IfcDecPropertySet) select, file));
                    associatedProperties.add((IfcDecPropertySet) select);
                }
                if (select instanceof IfcDecElementQuantity) {
                    associatedElementQuantitiesString.addAll(addIfcRelDefinesByPropertiesElementQuantity((IfcDecElementQuantity) select, file));
                    associatedElementQuantities.add((IfcDecElementQuantity) select);
                }
            }
        }

        if (associatedElementQuantities.size() != 0) {
            addIfcElementQuantity(associatedElementQuantitiesString, associatedElementQuantities ,objectNumber, file);
        }
    }

    private void addIfcElementQuantity(ArrayList<String> associatedElementQuantities, ArrayList<IfcDecElementQuantity> elementQuantities, String objectNumber, ArrayList<String> file) {
        String number = getNextNumber();

        String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        String ownerHistory = "*";
        String name = "*";
        String description = "*";
        String methodOfMeasurement = "*";

        String quantities = getStringList(associatedElementQuantities);


        //adding IFCELEMENTQUANTITY
        file.add("#" + number + "= " + String.format(IFCELEMENTQUANTITY, guid, ownerHistory, name, description, methodOfMeasurement, quantities));

        //adding IFCRELDEFINESBYPROPERTIES
        addIfcRelDefinesByProperties(objectNumber, number, file);

        //adding constraints
        for (IfcDecElementQuantity quantity : elementQuantities) {
            if (quantity.getConstraints() != null && quantity.getConstraints().size() != 0) {
                addConstraint(quantity.getConstraints(),number, file);
            }
        }
    }


    private void addIfcRelDefinesByProperties(String objectNumber, String propertynumber, ArrayList<String> file) {
        //creating the IfcRelDefinesByProperties line

        String guid = GuidCompressor.getNewIfcGloballyUniqueId();
        String ownerHistory = "*";
        String name = "*";
        String description = "*";
        String relatedObjects = "(" + objectNumber + ")";
        String relatingProperty = propertynumber;

        String number = getNextNumber();
        file.add("#" + number + "= " + String.format(IFCRELDEFINESBYPROPERTIES, guid, ownerHistory, name, description, relatedObjects, relatingProperty));
    }

    public String getStringList(ArrayList<String> list) {
        String result = "(";
        for (String element : list) {
            result = result.concat(element);
            if (list.indexOf(element) != list.size() - 1) {
                result = result.concat(",");
            }
        }
        result = result.concat(")");

        return result;
    }

    private ArrayList<String> addIfcRelDefinesByPropertiesElementQuantity(IfcDecElementQuantity elementQuantity, ArrayList<String> file) {
        ArrayList<String> properties = new ArrayList<String>();
        String propertyNumber = null;
        for(IfcDecPhysicalQuantity property : elementQuantity.getQuantities()) {
            if (property instanceof IfcDecPhysicalSimpleQuantity) {
                propertyNumber = getNextNumber();
                file.add("#" + propertyNumber + "= " + property.getIfcString());
                properties.add(propertyNumber);
            }
        }

        return properties;
    }

    private void addConstraint(ArrayList<IfcDecConstraint> constraints, String relatedProperty, ArrayList<String> file) {
            for (IfcDecConstraint constraint : constraints) {
                String number = getNextNumber();
                file.add("#" + number + "= " + ((IfcDecMetric) constraint).getIfcString());
                String relNumber = getNextNumber();
                file.add("#" + relNumber + "= " + ((IfcDecMetric) constraint).getIfcStringConstraintRelationship(number, relatedProperty));
            }
    }

    private ArrayList<String> addIfcRelDefinesByPropertiesPropertSet(IfcDecPropertySet propertySet, ArrayList<String> file) {
        ArrayList<String> properties = new ArrayList<String>();
        for(IfcDecProperty property : propertySet.getProperties()) {
            String number = getNextNumber();
            file.add("#" + number + "= " + property.getIfcString());
            properties.add(number);
        }
        return properties;
    }

    private String getUnitsInContext(IfcDecProject project, ArrayList<String> file) {
        String number = getNextNumber();
        LinkedList<String> unitNumbers = new LinkedList<String>();
        for (IfcDecUnit unit : project.getUnitsInContext().getUnits()) {
            unitNumbers.add(getUnit(unit, file));
        }

        String link = "";
        do {
            link = link.concat(unitNumbers.poll());
            if (unitNumbers.size() != 0) {
                link = link.concat(",");
            }

        } while (unitNumbers.size() != 0);

        String ifcUnitAssignment = String.format(IFCUNITASSIGNMENT, link);
        file.add("#" + number + "= " + ifcUnitAssignment);
        return "#" + number;

    }

    public String getUnit(IfcDecUnit unit, ArrayList<String> file) {
        if (file == null) {
            file = mFile;
        }
        if (unit instanceof IfcDecSIUnit) {
            String number = getNextNumber();
            IfcDecSIUnit siUnit = (IfcDecSIUnit) unit;
            String unitType = "*";
            String prefix =  "." + siUnit.getUnitType().name();
            String name = siUnit.getPrefix() == null ? "$" : "." + siUnit.getPrefix().name();
            String dimensions =  "." + siUnit.getName().name();

            String ifcUnit = String.format(IFCSIUNIT,
                    unitType,
                    prefix,
                    name,
                    dimensions);

            //don't add it to the file if it already exists and return the existing number
            for(String line : mFile) {
                if (line.contains(ifcUnit)) {
                    decreaseNumber();
                    return "#" + line.substring(1, line.indexOf("="));
                }
            }

            file.add("#" + number + "= " + ifcUnit);
            return "#" + number;
        }
        return null;

    }


    private String getOwnerHistory(IfcDecOwnerHistory ownerHistory, ArrayList<String> file) {
        return null;
    }

    private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getNextNumber() {
        mItemCounter++;
        return String.valueOf(mItemCounter);
    }

    public void decreaseNumber() {
        mItemCounter--;
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    public static class IFCComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int one = Integer.valueOf(o1.substring(1, o1.indexOf("=")));
            int two = Integer.valueOf(o2.substring(1, o2.indexOf("=")));
            return one - two;
        }
    }

    public static String getStringFromLabel(IfcDecLabel text) {
        String result = (text != null && text.getValue() != null && text.getValue().isEmpty()) ? text.getValue() : "*";
        return result;
    }

    public static String getStringFromText(IfcDecText text) {
        String result = (text != null && text.getValue() != null && text.getValue().isEmpty()) ? text.getValue() : "*";
        return result;
    }
}
