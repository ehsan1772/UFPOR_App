package com.ufpor.app.server.service;

import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.service.SpaceService;
import com.ufpor.app.server.EnvironmentServiceImpl;
import com.ufpor.app.shared.datatransfer.IfcSpace.Type;
import com.ufpor.app.shared.ifcdeckernel.IfcDecObjectDefinition;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.relationship.IfcDecRelAggregates;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ehsan Barekati on 4/3/15.
 */
public class SpaceServiceImpl extends RemoteServiceServlet implements SpaceService {
    private static final Logger LOG = Logger.getLogger(EnvironmentServiceImpl.class.getName());
    private static final PersistenceManagerFactory PMF = EnvironmentServiceImpl.PMF;
    private static final String TAG = SpaceServiceImpl.class.getSimpleName();
    private boolean isTest = false;
    private final static User TestUser = new User("test@test.com", "test.com", "test", "com");


    @Override
    public String addSpaceInstance(Type parentType, String parentSpaceKey, String spaceTypeKey) {
        PersistenceManager pm = PMF.getPersistenceManager();


        IfcDecSpaceType spaceType = pm.getObjectById(IfcDecSpaceType.class, spaceTypeKey);
        Type hierarchyType = getHierarchyType(spaceType.getElementType());

        IfcDecObjectDefinition parent = pm.getObjectById(IfcDecProject.class, parentSpaceKey);
        pm.close();

        pm = PMF.getPersistenceManager();
        IfcDecRelAggregates children = pm.getObjectById(IfcDecRelAggregates.class, parent.getChildSpaces().getKey());

        String spaceKey = null;
        IfcDecSpace space = null;

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
                space = IfcDecSpace.getInstance(spaceType);
                space.prepareDataForStoreIfcDecContext(null);
                children.add(space);
                children.prepareDataForStoreIfcDecContext(null);
            case SPACE_ELEMENT:
                break;
            case SPACE_PARTIAL:
                break;
        }

        pm.close();

        spaceKey = space.getKey();

        return spaceKey;
    }

    @Override
    public IfcDecSpace getSpaceByKey(String key) {
        PersistenceManager pm = PMF.getPersistenceManager();

        IfcDecSpace space = pm.getObjectById(IfcDecSpace.class, key);
        space.prepareDataForClient(null);

        pm.close();

        return space;
    }

    @Override
    public int getIfcRelAggregateByKey(String childrenKey) {
        PersistenceManager pm = PMF.getPersistenceManager();
        IfcDecRelAggregates children = pm.getObjectById(IfcDecRelAggregates.class, childrenKey);
        children.prepareDataForClient(null);
        return children.getList().size();
    }

    public static Type getHierarchyType(String typeString) {
        for (Type type : Type.values()) {
            if (type.toString().equals(typeString)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public IfcDecProject getProjectByKey(String key) {
        PersistenceManager pm2 = PMF.getPersistenceManager();
        IfcDecProject result = pm2.getObjectById(IfcDecProject.class, key);

        IfcDecRelAggregates children = result.getChildSpaces();
        children.prepareDataForClient(null);
        List l = children.getList();
        int i = l.size();

        IfcDecRelAggregates chl = pm2.getObjectById(IfcDecRelAggregates.class, result.getChildSpaces().getKey());
        chl.prepareDataForClient(null);
        result.setChildSpaces(chl);

        result.prepareDataForClient(null);
        pm2.close();
        return result;
    }
}