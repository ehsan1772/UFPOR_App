package com.ufpor.app.shared.ifckernel;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
public class IfcLabel implements Serializable {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String value;

    public IfcLabel(String value) {
        this.value = value;
    }

    private IfcLabel() {
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
