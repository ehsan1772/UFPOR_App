package com.ufpor.app.client.test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Created by Ehsan Barekati on 3/19/15.
 */
public class ProjectTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "com.ufpor.app.app";
    }

    public void testText() {
        assertEquals("BAZ", "BAZ");
    }
}
