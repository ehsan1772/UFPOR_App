package com.ufpor.app.shared.ifcclient;

import com.ufpor.app.client.LoginInfo;

import java.io.Serializable;

/**
 * Created by Ehsan Barekati on 11/3/14.
 */

public abstract class GAEObject implements Serializable {

    protected LoginInfo user;

    protected GAEObject(LoginInfo user) {
        this.user = user;
    }

    protected GAEObject() {
    }

    public LoginInfo getUser() {
        return user;
    }

}
