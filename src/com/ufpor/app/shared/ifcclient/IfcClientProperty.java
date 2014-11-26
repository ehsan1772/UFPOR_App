package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public abstract class IfcClientProperty extends IfcClientPropertyAbstraction {
    private static final String MIN_CLIENT = "min_area";
    private static final String MAX_CLIENT = "max_area";
    private String name;
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
