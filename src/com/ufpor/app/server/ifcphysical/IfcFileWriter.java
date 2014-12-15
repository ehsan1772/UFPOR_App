package com.ufpor.app.server.ifcphysical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Ehsan Barekati on 12/9/14.
 */
public class IfcFileWriter implements IfcFileWriterI {
    private ArrayList<String> lines;
    private String header;
    private String footer;

    /**
     * putting all the lines in the ifc file in order based on their number
     */
    private Comparator<String> ifcComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            Integer one = Integer.valueOf(o1.substring(1, o1.indexOf("=")));
            Integer two = Integer.valueOf(o2.substring(1, o2.indexOf("=")));
            return one - two;
        }
    };

    public IfcFileWriter() {
        lines = new ArrayList<>();
        footer = "ENDSEC;\n" +
                "END-ISO-10303-21;";
    }

    @Override
    public int addObject(String ifcObject) {
        return 0;
    }

    @Override
    public void addObject(String ifcObject, String number) {
        lines.add(number + "= " + ifcObject);
    }

    @Override
    public String getStepFile() {
        int length = 0;
        length += header.length();
        for (String line : lines) {
            length += line.length() + 1;
        }
        length += footer.length();

        StringBuilder stringBuilder = new StringBuilder(length);

        stringBuilder.append(header);

        Collections.sort(lines, ifcComparator);
        for (String line : lines) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        stringBuilder.append(footer);

        return stringBuilder.toString();
    }

    @Override
    public void addHeader(String header) {
            this.header = header;
    }

    @Override
    public boolean isFileReady() {
        return true;
    }
}
