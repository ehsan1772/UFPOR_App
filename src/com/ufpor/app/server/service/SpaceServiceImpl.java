package com.ufpor.app.server.service;

import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.service.SpaceService;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.datatransfer.IfcSpace.Type;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 4/3/15.
 */
public class SpaceServiceImpl extends RemoteServiceServlet implements SpaceService {
    private static final Logger LOG = Logger.getLogger(EnvironmentServiceImpl.class.getName());
    private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    private static final String TAG = SpaceServiceImpl.class.getSimpleName();
    private boolean isTest = false;
    private final static User TestUser = new User("test@test.com", "test.com", "test", "com");


    @Override
    public String addSpaceInstance(Type parentType, String parentSpaceKey, String spaceTypeKey) {
        PersistenceManager pm = PMF.getPersistenceManager();

        IfcDecSpaceType spaceType = pm.getObjectById(IfcDecSpaceType.class, spaceTypeKey);
        Type hierarchyType = getHierarchyType(spaceType.getElementType());


        IfcDecObjectDefinition parent = null;
        String spaceKey = null;

        switch (hierarchyType) {
            case PROJECT:

                break;
            case SITE:
             //   IfcDecSite.getInstance();
                break;
            case BUILDING:
                //   IfcDecBuilding.getInstance();
                break;
            case BUILDING_STORY:
                //   IfcDecStory.getInstance();
                break;
            case SPACE_COMPLEX:
                IfcDecSpace space = IfcDecSpace.getInstance(spaceType);
                pm.makePersistent(space);
                parent.addChildSpace(space);
                spaceKey = space.getKey();
            case SPACE_ELEMENT:
                break;
            case SPACE_PARTIAL:
                break;
        }


        return spaceKey;
    }

    public static Type getHierarchyType(String typeString) {
        for (Type type : Type.values()) {
            if (type.toString().equals(typeString)) {
                return type;
            }
        }
        return null;
    }
}