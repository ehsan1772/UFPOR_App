package com.ufpor.app.shared.ifcclient.actor;

import com.ufpor.app.shared.ifcclient.IfcClientAddress;
import com.ufpor.app.shared.ifcclient.IfcClientIdentifier;
import com.ufpor.app.shared.ifcclient.IfcClientLabel;
import com.ufpor.app.shared.ifckernel.IfcActorRole;

import java.util.ArrayList;

/**
 * Created by Ehsan Barekati on 11/11/14.
 */
public class IfcClientPerson implements IfcClientActorSelect {
    private IfcClientIdentifier idendification;
    private IfcClientLabel familyName;
    private IfcClientLabel givenName;
    private ArrayList<IfcClientLabel> middleNames;
    private ArrayList<IfcClientLabel> prefixTitles;
    private ArrayList<IfcClientLabel> suffixTitles;
    private ArrayList<IfcActorRole> roles;
    private ArrayList<IfcClientAddress> addresses;
}
