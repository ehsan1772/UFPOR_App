package com.ufpor.app.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.com.google.common.base.Strings;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.EnvironmentDM;
import com.ufpor.app.server.ifcphysical.IfcFileManager;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecUnit;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;
import org.mortbay.log.Log;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ovenbits on 10/8/14.
 */
public class EnvironmentServiceImpl extends RemoteServiceServlet implements EnvironmentService {
    private static final Logger LOG = Logger.getLogger(EnvironmentServiceImpl.class.getName());
    public static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    private static final String TAG = EnvironmentServiceImpl.class.getSimpleName();
    private boolean isTest = false;
    private final static User TestUser = new User("test@test.com", "test.com", "test", "com");


    @Override
    public void addEnvironment(String name, String area) throws NotLoggedInException {
        checkLoggedIn();
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(new Environment(getUser(), name, area));
        } finally {
            pm.close();
        }
    }

    @Override
    public void addIfcDecSpace(IfcClientSpace space) throws NotLoggedInException {
        IfcDecSpace result = IfcDecSpace.getInstance(space);
        checkLoggedIn();
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(result);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm.close();
        }
    }

    @Override
    public void removeEnvironment(String symbol) throws NotLoggedInException {
        checkLoggedIn();
        PersistenceManager pm = getPersistenceManager();
        try {
            long deleteCount = 0;
            Query q = pm.newQuery(Environment.class, "user == u");
            q.declareParameters("com.google.appengine.api.users.User u");
            List<Environment> environments = (List<Environment>) q
                    .execute(getUser());
            for (Environment stock : environments) {
                if (symbol.equals(stock.getName())) {
                    deleteCount++;
                    pm.deletePersistent(stock);
                }
            }
            if (deleteCount != 1) {
          //      LOG.log(Level.WARNING, "removeStock deleted " + deleteCount
           //             + " Stocks");
            }
        } finally {
            pm.close();
        }
    }

    @Override
    public List<EnvironmentDM> getEnvironments() throws NotLoggedInException {
        checkLoggedIn();
        PersistenceManager pm = getPersistenceManager();
        List<EnvironmentDM> result = new ArrayList<EnvironmentDM>();
        try {
            Query q = pm.newQuery(Environment.class, "user == u");
            q.declareParameters("com.google.appengine.api.users.User u");
            q.setOrdering("createDate");
            List<Environment> environments = (List<Environment>) q.execute(getUser());
            for (Environment env : environments) {
                result.add(env.getEnvDM());
            }
        } finally {
            pm.close();
        }
        return result;

    }

    @Override
    public List<String> addProject(IfcClientProject ifcClientProject, boolean isTest) throws NotLoggedInException {
        this.isTest = isTest;
        if (!isTest) {
            checkLoggedIn();
        }

        IfcDecProject project;

        project = IfcDecProject.getInstance(ifcClientProject, getUser());

        String ifcFile = null;

        project.prepareDataForStore(null);
        project.prepareDataForStoreIfcDecContext(null);
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(project);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm.close();
        }

        String key = project.getKey();

        Log.debug("Key is: " + project.getKey());

        ArrayList<String> retunrvalue = new ArrayList<String>();
        retunrvalue.add(getProjectIfcString2(project.getName()));
        return retunrvalue;
    }

    @Override
    public String addProjectForId(IfcClientProject ifcClientProject, boolean isTest) throws NotLoggedInException {
        this.isTest = isTest;
        if (!isTest) {
            checkLoggedIn();
        }

        IfcDecProject project;

        project = IfcDecProject.getInstance(ifcClientProject, getUser());

        project.prepareDataForStore(null);
        project.prepareDataForStoreIfcDecContext(null);
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(project);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            return null;
        }
        finally {
            pm.close();
        }

        String key = project.getKey();

        return key;
    }

    @Override
    public String getIfcString(String projectId, boolean isTest) throws NotLoggedInException {
        PersistenceManager pm = getPersistenceManager();
        IfcDecProject project;
        String result = null;
        try {
            project = pm.getObjectById(IfcDecProject.class, projectId);

            project.prepareDataForClient(null);
            project.prepareDataForClientIfcDecContext(null);

            String id = project.getGlobalId().getValue();

            IfcFileManager.getInstance().setProject(project);
            IfcFileManager.getInstance().GenerateTheFile();
            result = IfcFileManager.getInstance().getStepFile();
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm.close();
        }
        return result;
    }

//this is the old method!! without using the ifc file manager
//    private String getProjectIfcString(String name) {
//        PersistenceManager pm2 = getPersistenceManager();
//        List<IfcDecProject> projects = null;
//        IfcDecProject finalOutCome = null;
//        ArrayList<IfcDecUnit> units = null;
//        IfcDecUnit unit = null;
//        String ifcFile2 = null;
//        ArrayList<IfcDecConstraint> constratints = null;
//        try {
//            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
//            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
//            projects = (List<IfcDecProject>) q.execute(name, getUser());
//
//            finalOutCome = projects.get(projects.size() - 1);
//            finalOutCome.prepareDataForClient(null);
//            finalOutCome.prepareDataForClientIfcDecContext(null);
//            IfcDecElementQuantity defin = (IfcDecElementQuantity) finalOutCome.getIsDefinedBy().get(0);
//            //max area
//
//            IfcDecUnitAssignment assignment = finalOutCome.getUnitsInContext();
//
//            Constants costant2 = Constants.getInstance();
//            ArrayList<String> finalResult2 = new ArrayList<String>();
//            String header2 = Constants.getHeader(finalOutCome);
//            LOG.log(Level.INFO, "Header is: \n" + header2);
//
//            costant2.getProject(finalOutCome, finalResult2, this);
//            ifcFile2 = Constants.getIfcFile(header2, finalResult2);
//            LOG.log(Level.INFO, "The file is:\n " + ifcFile2);
//
//        } catch (Exception exception) {
//            LOG.log(Level.SEVERE, exception.getMessage());
//            exception.printStackTrace();
//        }
//        finally {
//            pm2.close();
//        }
//
//        return ifcFile2;
//    }

    private String getProjectIfcString2(String name) {
        PersistenceManager pm2 = getPersistenceManager();
        List<IfcDecProject> projects = null;
        IfcDecProject finalOutCome = null;
        ArrayList<IfcDecUnit> units = null;
        IfcDecUnit unit = null;
        String ifcFile2 = null;
        ArrayList<IfcDecConstraint> constratints = null;
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
            projects = (List<IfcDecProject>) q.execute(name, getUser());

            finalOutCome = projects.get(projects.size() - 1);
            finalOutCome.prepareDataForClient(null);
            finalOutCome.prepareDataForClientIfcDecContext(null);

            String id = finalOutCome.getGlobalId().getValue();

            IfcFileManager.getInstance().setProject(finalOutCome);
            IfcFileManager.getInstance().GenerateTheFile();
            ifcFile2 = IfcFileManager.getInstance().getStepFile();


        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        return ifcFile2;
    }

//    private void addSpaceTypeToTheProject(String spaceType, String projectName) {
//        PersistenceManager pm2 = getPersistenceManager();
//        try {
//            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
//            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
//            List<IfcDecProject> projects = (List<IfcDecProject>) q.execute( projectName, getUser());
//            IfcDecProject finalOutCome = projects.get(projects.size() - 1);
//       //     finalOutCome.removeAllTheSpaces();
//            finalOutCome.addSpaceType(spaceType);
//
//        } catch (Exception exception) {
//            LOG.log(Level.SEVERE, exception.getMessage());
//        }
//        finally {
//            pm2.close();
//        }
//    }

    private void addSpaceTypeToTheProject(String spaceType, String projectKey) {
        PersistenceManager pm = getPersistenceManager();
        try {
            IfcDecProject project = pm.getObjectById(IfcDecProject.class, projectKey);
            project.addSpaceType(spaceType);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm.close();
        }
    }

    @Override
    public List<String> addSpaceType(IfcClientSpaceType spaceType, String projectName) throws NotLoggedInException {
        checkLoggedIn();

        IfcDecSpaceType decSpaceType = IfcDecSpaceType.getInstance(spaceType);

        String ifcFile;

        decSpaceType.prepareDataForStoreIfcDecContext(null);
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(decSpaceType);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm.close();
        }

        String key = getProjectKeyByName(projectName);

        addSpaceTypeToTheProject(decSpaceType.getKey(), key);

//        List<IfcDecSpaceType> spaceTypes = null;
//        IfcDecSpaceType spaceTypeResult = null;
//        PersistenceManager pm2 = getPersistenceManager();
//        try {
//
//            Query q = pm2.newQuery(IfcDecSpaceType.class);
//            spaceTypes = (List<IfcDecSpaceType>) q.execute();
//            spaceTypeResult = spaceTypes.get(spaceTypes.size() - 1);
//            spaceTypeResult.prepareDataForClientIfcDecContext(null);
//
//            IfcDecPropertySetDefinition prop = spaceTypeResult.getHasProperties().get(0);
//            if (prop instanceof IfcDecPropertySet) {
//                ArrayList<IfcDecProperty> pr = ((IfcDecPropertySet) prop).getProperties();
//               // String n1 = pr.get(0).getName();
//                String n2 = pr.get(1).getIfcString();
//                String n3 = pr.get(2).getIfcString();
//                String n4 = pr.get(3).getIfcString();
//                int i = 0;
//            }
//
//        } catch (Exception exception) {
//            LOG.log(Level.SEVERE, exception.getMessage());
//            exception.printStackTrace();
//        }
//        finally {
//            pm2.close();
//        }

        ArrayList<String> reult = new ArrayList<String>();
        reult.add(getProjectIfcString2(projectName));

        getProjectIfcString2(projectName);
        return reult;
    }

    @Override
    public String addSpaceType(IfcClientSpaceType spaceType, String projectKey, boolean isTest) throws NotLoggedInException {
        if (!isTest) {
            checkLoggedIn();
        }

        if (Strings.isNullOrEmpty(projectKey)) {
            return null;
        }

        IfcDecSpaceType decSpaceType = IfcDecSpaceType.getInstance(spaceType);

        decSpaceType.prepareDataForStoreIfcDecContext(null);
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(decSpaceType);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm.close();
        }

        addSpaceTypeToTheProject(decSpaceType.getKey(), projectKey);
        return decSpaceType.getKey();
    }

    @Override
    public List<String> getProjectsNames() throws NotLoggedInException {
        PersistenceManager pm2 = getPersistenceManager();
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "user == u");
            q.declareParameters("com.google.appengine.api.users.User u");
            List<IfcDecProject> projects = (List<IfcDecProject>) q.execute(getUser());
            List<String> result = new ArrayList<String>();
            for (IfcDecProject project : projects) {
                result.add(project.getName());
            }
            return result;

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }
        return null;
    }

    @Override
    public List<IfcClientSpaceType> getSpaceTypes(String projectName) {
        PersistenceManager pm2 = getPersistenceManager();
        List<IfcClientSpaceType> results = new ArrayList<IfcClientSpaceType>();
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
            List<IfcDecProject> projects = (List<IfcDecProject>) q.execute( projectName, getUser());

            if (projects.size() == 0) {
                return null;
            }

            Set<String> spaceKeys = projects.get(projects.size() - 1).getSpaceTypes();


            Query spaceTypeQuery = pm2.newQuery(IfcDecSpaceType.class, "key == spaceKey");
            spaceTypeQuery.declareParameters("com.google.appengine.api.datastore.Key spaceKey");

            if (spaceKeys == null) {
                return null;
            }

            for (String key : spaceKeys) {
                IfcDecSpaceType spaceType = ((List<IfcDecSpaceType>) spaceTypeQuery.execute(key)).get(0);
                spaceType.prepareDataForClient(null);
                spaceType.prepareDataForClientIfcDecContext(null);
                results.add(IfcDecSpaceType.getClientSpace(spaceType));
            }

            return results;

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }
        return null;
    }

    @Override
    public String getProjectString(String projectName) {
        return getProjectIfcString2(projectName);
    }


    @Override
    public void deleteProjectByName(String name) {
        PersistenceManager pm2 = getPersistenceManager();
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
            q.deletePersistentAll(name, getUser());


        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }
    }

    @Override
    public IfcDecProject getProjectByKey(String key) {
        PersistenceManager pm2 = getPersistenceManager();
        IfcDecProject result = pm2.getObjectById(IfcDecProject.class, key);
        result.prepareDataForClient(null);
        pm2.close();
        return result;
    }

    public String getProjectKeyByName(String projectName) {
        PersistenceManager pm2 = getPersistenceManager();
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
            List<IfcDecProject> projects = (List<IfcDecProject>) q.execute(projectName, getUser());

            if (projects.size() > 0) {
                return projects.get(0).getKey();
            }


        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        return null;
    }


    public IfcDecSpaceType getSpaceTypeByKey(String key) throws NotLoggedInException {
        PersistenceManager pm2 = getPersistenceManager();
        IfcDecSpaceType spaceTypeResult = null;
        try {
            spaceTypeResult = pm2.getObjectById(IfcDecSpaceType.class, key);
            spaceTypeResult.prepareDataForClient(null);
            spaceTypeResult.prepareDataForClientIfcDecContext(null);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        return spaceTypeResult;
    }

    public static ArrayList<IfcDecSpaceType> getSpaceTypeByKey(Set<String> keys) throws NotLoggedInException {
        if (keys == null || keys.size() == 0) {
            return null;
        }
        PersistenceManager pm2 = getPersistenceManager();
        ArrayList<IfcDecSpaceType> spaceTypeResults = new ArrayList<IfcDecSpaceType>();
        try {
            for (String key : keys) {
                IfcDecSpaceType spaceTypeResult = pm2.getObjectById(IfcDecSpaceType.class, key);
                spaceTypeResult.prepareDataForClientIfcDecContext(null);
                spaceTypeResults.add(spaceTypeResult);
            }

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        return spaceTypeResults;
    }

    private void checkLoggedIn() throws NotLoggedInException {
        if (getUser() == null) {
            throw new NotLoggedInException("Not logged in.");
        }
    }

    private User getUser() {
        if (!isTest) {
            UserService userService = UserServiceFactory.getUserService();
            return userService.getCurrentUser();
        }
        return TestUser;
    }

    private static PersistenceManager getPersistenceManager() {
        return PMF.getPersistenceManager();
    }

    @Override
    public String[] getEnvironmentNames() throws NotLoggedInException {
        checkLoggedIn();
        PersistenceManager pm = getPersistenceManager();
        List<String> names = new ArrayList<String>();
        try {
            Query q = pm.newQuery(Environment.class, "user == u");
            q.declareParameters("com.google.appengine.api.users.User u");
            q.setOrdering("createDate");
            List<Environment> environments = (List<Environment>) q.execute(getUser());
            for (Environment environment : environments) {
                names.add(environment.getName());
            }
        } finally {
            pm.close();
        }
        return (String[]) names.toArray(new String[0]);
    }

}