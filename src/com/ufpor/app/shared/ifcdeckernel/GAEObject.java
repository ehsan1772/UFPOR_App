package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.users.User;

import javax.jdo.annotations.*;
import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/3/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class GAEObject implements Serializable {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    protected String key;

    @Persistent
    protected User user;

    protected GAEObject(User user) {
        this.user = user;
    }

    protected GAEObject() {
    }

    public User getUser() {
        return user;
    }

    public String getKey() {
        return key;
    }


    //this way items with the same key will be considered equal in the BiMap in IfcFileManager
    @Override
    public int hashCode() {
        if (key != null && !key.isEmpty()) {
            return key.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  GAEObject && key != null && !key.isEmpty()) {
            return key.equals(((GAEObject) obj).getKey());
        }
        return super.equals(obj);
    }
}
