package com.ufpor.app.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ehsan Barekati on 10/31/14.
 */
public class Test implements Serializable {
    private String hello;

    public Test() {
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getLongData() {
        return longData;
    }

    public void setLongData(long longData) {
        this.longData = longData;
    }

    private Date date;
    private long longData;
}
