package com.ufpor.app.server.ifcphysical;

import com.ufpor.app.shared.ifcdeckernel.IfcDecOwnerHistory;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecSIUnit;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecUnit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Ehsan Barekati on 11/19/14.
 */
public class Constants {
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

    public static String getIfcFile(String header, ArrayList<String> lines) {
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

    public Constants() {
        this.mItemCounter = 0;
    }

    public static String getHeader(String fileDescription,
                                   String fileName,
                                   String author,
                                   String organization,
                                   String authorization) {
        return String.format(HEADER, fileDescription, fileName, getCurrentDate(), author, organization, authorization);
    }

    public String getProject(IfcDecProject project, ArrayList<String> file) {
        String number = getNextNumber();

        //ROOT
        String GUI = project.getGlobalId().getValue();
        String ownerHistory = project.getOwnerHistory() == null ? "$" : getOwnerHistory(project.getOwnerHistory(),file);
        String name = (project.getName().getValue() == null || project.getName().getValue().isEmpty()) ? "$" : project.getName().getValue();
        String description = (project.getDescription() == null || project.getDescription().getValue().isEmpty()) ? "$" : project.getDescription().getValue();

        //IfcContext
        String objectType = (project.getObjectType() == null || project.getObjectType().getValue().isEmpty()) ? "$" : project.getObjectType().getValue();
        String longName = (project.getLongName() == null || project.getLongName().getValue().isEmpty()) ? "$" : project.getLongName().getValue();
        String phase = (project.getPhase() == null || project.getPhase().getValue().isEmpty()) ? "$" : project.getPhase().getValue();
        String representationContexts = "$";
        String unitsInContext = project.getUnitsInContext() == null ? "$" : getUnitsInContext(project, file);

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

    private String getUnit(IfcDecUnit unit, ArrayList<String> file) {
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
}
