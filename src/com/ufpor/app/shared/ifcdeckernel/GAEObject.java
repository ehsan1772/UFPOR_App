package com.ufpor.app.shared.ifcdeckernel;

import com.google.appengine.api.datastore.Key;
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
    protected Key key;
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

    public Key getKey() {
        return key;
    }
}
