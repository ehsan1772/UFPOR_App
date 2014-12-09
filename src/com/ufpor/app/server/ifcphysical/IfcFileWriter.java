package com.ufpor.app.server.ifcphysical;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 12/9/14.
 */
public class IfcFileWriter implements IfcFileWriterI {
    private ArrayList<String> lines;
    private String header;
    private String footer;

    public IfcFileWriter() {
        lines = new ArrayList<>();
    }

    @Override
    public int addObject(String ifcObject) {
        return 0;
    }

    @Override
    public void addObject(String ifcObject, int number) {

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
        for (String line : lines) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        stringBuilder.append(footer);

        return stringBuilder.toString();
    }

    @Override
    public void addHeader(String header) {

    }

    @Override
    public boolean isFileReady() {
        return false;
    }
}
