package com.ufpor.app.client.data;

import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/6/14.
 */
public interface SpaceDataManagerI {
    ArrayList<IfcClientSpace> getAllSpaces();
    String addSpace(IfcClientSpace space);
    String removeSpace(IfcClientSpace space);
    String editSpace();
}
