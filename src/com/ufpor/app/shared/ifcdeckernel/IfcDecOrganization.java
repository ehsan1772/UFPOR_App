package com.ufpor.app.shared.ifcdeckernel;

import com.ufpor.app.server.ifcphysical.IfcFileManagerI;
import com.ufpor.app.server.ifcphysical.IfcFileObject;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
public class IfcDecOrganization implements Serializable, IfcFileObject {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private IfcDecIdentifier idendification;
    private IfcDecLabel name;
    private List<IfcDecActorRole> roles;
    private List<IfcDecAddress> addressed;
    private int number;

    public IfcDecOrganization(IfcDecLabel name) {

        this.name = name;
    }

    private IfcDecOrganization() {
    }

    public IfcDecIdentifier getIdendification() {
        return idendification;
    }

    public void setIdendification(IfcDecIdentifier idendification) {
        this.idendification = idendification;
    }

    public IfcDecLabel getName() {
        return name;
    }

    public List<IfcDecActorRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IfcDecActorRole> roles) {
        this.roles = roles;
    }

    public List<IfcDecAddress> getAddressed() {
        return addressed;
    }

    public void setAddressed(List<IfcDecAddress> addressed) {
        this.addressed = addressed;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> results = new ArrayList<>();

        results.addAll(addressed);
        results.addAll(roles);

        return results;
    }

    @Override
    public String getObjectString(IfcFileManagerI fileManager) {
        return null;
    }
}
