package com.ufpor.app.server.ifcphysical;

import com.ufpor.app.shared.ifcdeckernel.property.IfcDecSIUnit;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecUnit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Ehsan Barekati on 11/19/14.
 */
public class Constants {

    private static Constants instance;

    public final static String HEADER = "ISO-10303-21;\n" +
            "HEADER;\n" +
            "FILE_DESCRIPTION (\n" +
            "        ('%1$s'),\n" +
            "        '2;1');\n" +
            "FILE_NAME (\n" +
            "        '%2$s.ifc',\n" +
            "        '%3$s',\n" +
            "        ('%4$s'),\n" +
            "        ('%5$s'),\n" +
            "        'UFPOR APP 0.0.1',\n" +
            "        'UFPOR DEMO beta',\n" +
            "        '%6$s');\n" +
            "FILE_SCHEMA (('IFC4RC4'));\n" +
            "ENDSEC;\n" +
            "DATA;\n";

    public final static String FOOTER = "ENDSEC;\n" +
            "END-ISO-10303-21;";

    public final static String PROJECT = "IFCPROJECT ('%1$s', %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s);";
    public final static String IFCUNITASSIGNMENT = "IFCUNITASSIGNMENT(%1$s);";
    public final static String IFCSIUNIT = "IFCSIUNIT(%1$s, %2$s, %3$s, %4$s);";
    public final static String IFCPROPERTYSINGLEVALUE = "IFCPROPERTYSINGLEVALUE(%1$s, %2$s, %3$s, %4$s);";
    public final static String IFCELEMENTQUANTITY = "IFCELEMENTQUANTITY(%1s, %2$s, %3$s, %4$s, %5$s, %6$s);";
    public final static String IFCPROPERTYSET = "IFCPROPERTYSET(%1$s, %2$s, %3$s, %4$s, %5$s);";
    public final static String IFCQUANTITYAREA = "IFCQUANTITYAREA(%1$s, %2$s, %3$s, %4$s, %5$s);";
    public final static String IFCQUANTITYLENGTH = "IFCQUANTITYLENGTH(%1$s, %2$s, %3$s, %4$s, %5$s);";
    public final static String IFCRELDEFINESBYPROPERTIES =  "IFCRELDEFINESBYPROPERTIES(%1$s, %2$s, %3$s, %4$s, %5$s, %6$s);";
    public final static String IFCSPACETYPE = "IFCSPACETYPE ('%1$s', %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s, %10$s, %11$s);";
    public final static String IFCMETRIC = "IFCMETRIC ('%1$s', %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s, %10$s, %11$s);";
    public final static String IFCOBJECTIVE = "IFCOBJECTIVE ('%1$s', %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s, %10$s, %11$s);";
    public static final String IFCRESOURCECONSTRAINTRELATIONSHIP = "IFCRESOURCECONSTRAINTRELATIONSHIP(%1$s, %2$s, %3$s, %4$s);";
    public final static String IFCRELAGGREGATES = "IFCRELAGGREGATES(%1$s, %2$s, '%3$s', '%4$s', %5$s, %6$s);";
    public final static String IFCRELDECLARES = "IFCRELDECLARES(%1$s, %2$s, '%3$s', '%4$s', %5$s, %6$s);";
    public final static String IFCSPACE = "IFCSPACE('%1$s', %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s, %10$s, %11$s);";

    private final static IFCComparator COMPARATOR = new IFCComparator();
    private ArrayList<String> mFile;


    private int mItemCounter;
    private String nextNumber;

    private Constants() {
        this.mItemCounter = 0;
    }

    public void reset() {
        this.mItemCounter = 0;
        this.mFile = new ArrayList<>();
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

    public static String getCurrentDate() {
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
        instance.reset();
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

}
