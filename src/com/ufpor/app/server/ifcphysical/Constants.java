package com.ufpor.app.server.ifcphysical;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String getHeader(String fileDescription,
                                   String fileName,
                                   String author,
                                   String organization,
                                   String authorization) {
        return String.format(HEADER, fileDescription, fileName, getCurrentDate(), author, organization, authorization);
    }

    private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
