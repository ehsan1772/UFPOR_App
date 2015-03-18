package com.ufpor.app.server;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.EnvironmentDM;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.server.ifcphysical.IfcFileManager;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecUnitAssignment;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.property.*;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;
import com.ufpor.app.shared.ifcdeckernel.type.IfcDecSpaceType;

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
    private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    private static final String TAG = EnvironmentServiceImpl.class.getSimpleName();

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
    public List<String> addProject(IfcClientProject project) throws NotLoggedInException {
        checkLoggedIn();

        IfcDecProject pr = IfcDecProject.getInstance(project);

        String ifcFile;
        Key key;

        pr.prepareDataForStore(null);
        pr.prepareDataForStoreIfcDecContext(null);
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(pr);
        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm.close();
        }

        String res = getProjectIfcString2(pr.getName());


        ArrayList<String> finalResult = new ArrayList<String>();
        String header = Constants.getHeader(pr.getLongName().getValue(), pr.getName(), pr.getUser().getNickname(),pr.getUser().getEmail(), pr.getUser().getAuthDomain());
        LOG.log(Level.INFO, "Header is: " + header);


        Constants costant = Constants.getInstance();
        costant.getProject(pr, finalResult, this);

        ifcFile = Constants.getIfcFile(header, finalResult, this);
        LOG.log(Level.INFO, "The file is:\n " + ifcFile);

        PersistenceManager pm2 = getPersistenceManager();
        List<IfcDecProject> projects = null;
        IfcDecProject finalOutCome = null;
        ArrayList<IfcDecUnit> units = null;
        IfcDecUnit unit = null;
        ArrayList<IfcDecConstraint> constratints = null;
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "user == u");
            q.declareParameters("com.google.appengine.api.users.User u");
            projects = (List<IfcDecProject>) q.execute(getUser());
            finalOutCome = projects.get(projects.size() - 1);
            finalOutCome.prepareDataForClient(null);
            finalOutCome.prepareDataForClientIfcDecContext(null);
            IfcDecElementQuantity defin = (IfcDecElementQuantity) finalOutCome.getIsDefinedBy().get(0);
            //max area

            IfcDecUnitAssignment assignment = finalOutCome.getUnitsInContext();

            key = finalOutCome.getKey();
            Constants costant2 = Constants.getInstance();
            costant2.reset();
            ArrayList<String> finalResult2 = new ArrayList<String>();
            String header2 = Constants.getHeader(finalOutCome);
            LOG.log(Level.INFO, "Header is: \n" + header2);

            costant2.getProject(finalOutCome, finalResult2, this);
            String ifcFile2 = Constants.getIfcFile(header2, finalResult2, this);
            LOG.log(Level.INFO, "The file is:\n " + ifcFile2);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        ArrayList<String> retunrvalue = new ArrayList<String>();
        retunrvalue.add(ifcFile);
        return retunrvalue;
    }

//    private String getProjectIfcString() {
//        PersistenceManager pm2 = getPersistenceManager();
//        List<IfcDecProject> projects = null;
//        IfcDecProject finalOutCome = null;
//        ArrayList<IfcDecUnit> units = null;
//        IfcDecUnit unit = null;
//        String ifcFile2 = null;
//        ArrayList<IfcDecConstraint> constratints = null;
//        try {
//            Query q = pm2.newQuery(IfcDecProject.class, "user == u");
//            q.declareParameters("com.google.appengine.api.users.User u");
//            projects = (List<IfcDecProject>) q.execute(getUser());
//            finalOutCome = projects.get(projects.size() - 1);
//            finalOutCome.prepareDataForClient(null);
//            finalOutCome.prepareDataForClientIfcDecContext(null);
//            IfcDecElementQuantity defin = (IfcDecElementQuantity) finalOutCome.getIsDefinedBy().get(0);
//            //max area
//
//            IfcDecUnitAssignment assignment = finalOutCome.getUnitsInContext();
//
//            Constants costant2 = Constants.getInstance();
//            costant2.reset();
//            ArrayList<String> finalResult2 = new ArrayList<String>();
//            String header2 = Constants.getHeader(finalOutCome);
//            LOG.log(Level.INFO, "Header is: \n" + header2);
//
//            costant2.getProject(finalOutCome, finalResult2, this);
//            ifcFile2 = Constants.getIfcFile(header2, finalResult2, this);
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

    private String getProjectIfcString(String name) {
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
            IfcDecElementQuantity defin = (IfcDecElementQuantity) finalOutCome.getIsDefinedBy().get(0);
            //max area

            IfcDecUnitAssignment assignment = finalOutCome.getUnitsInContext();

            Constants costant2 = Constants.getInstance();
            ArrayList<String> finalResult2 = new ArrayList<String>();
            String header2 = Constants.getHeader(finalOutCome);
            LOG.log(Level.INFO, "Header is: \n" + header2);

            costant2.getProject(finalOutCome, finalResult2, this);
            ifcFile2 = Constants.getIfcFile(header2, finalResult2, this);
            LOG.log(Level.INFO, "The file is:\n " + ifcFile2);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        return ifcFile2;
    }

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

    private void addSpaceTypeToTheProject(Key spaceType, String projectName) {
        PersistenceManager pm2 = getPersistenceManager();
        try {
            Query q = pm2.newQuery(IfcDecProject.class, "nameText == name && user == u");
            q.declareParameters("java.lang.String name, com.google.appengine.api.users.User u");
            List<IfcDecProject> projects = (List<IfcDecProject>) q.execute( projectName, getUser());
            IfcDecProject finalOutCome = projects.get(projects.size() - 1);
       //     finalOutCome.removeAllTheSpaces();
            finalOutCome.addSpaceType(spaceType);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm2.close();
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

        addSpaceTypeToTheProject(decSpaceType.getKey(), projectName);

        List<IfcDecSpaceType> spaceTypes = null;
        IfcDecSpaceType spaceTypeResult = null;
        PersistenceManager pm2 = getPersistenceManager();
        try {

            Query q = pm2.newQuery(IfcDecSpaceType.class);
            spaceTypes = (List<IfcDecSpaceType>) q.execute();
            spaceTypeResult = spaceTypes.get(spaceTypes.size() - 1);
            spaceTypeResult.prepareDataForClientIfcDecContext(null);

            IfcDecPropertySetDefinition prop = spaceTypeResult.getHasProperties().get(0);
            if (prop instanceof IfcDecPropertySet) {
                ArrayList<IfcDecProperty> pr = ((IfcDecPropertySet) prop).getProperties();
               // String n1 = pr.get(0).getName();
                String n2 = pr.get(1).getIfcString();
                String n3 = pr.get(2).getIfcString();
                String n4 = pr.get(3).getIfcString();
                int i = 0;
            }

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        ArrayList<String> reult = new ArrayList<String>();
        reult.add(getProjectIfcString(projectName));

        getProjectIfcString2(projectName);
        return reult;
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

            Set<Key> spaceKeys = projects.get(projects.size() - 1).getSpaceTypes();


            Query spaceTypeQuery = pm2.newQuery(IfcDecSpaceType.class, "key == spaceKey");
            spaceTypeQuery.declareParameters("com.google.appengine.api.datastore.Key spaceKey");

            if (spaceKeys == null) {
                return null;
            }

            for (Key key : spaceKeys) {
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


    public IfcDecSpaceType getSpaceTypeByKey(Key key) throws NotLoggedInException {
        PersistenceManager pm2 = getPersistenceManager();
        IfcDecSpaceType spaceTypeResult = null;
        try {
            spaceTypeResult = pm2.getObjectById(IfcDecSpaceType.class, key);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            pm2.close();
        }

        return spaceTypeResult;
    }

    public static ArrayList<IfcDecSpaceType> getSpaceTypeByKey(Set<Key> keys) throws NotLoggedInException {
        if (keys == null || keys.size() == 0) {
            return null;
        }
        PersistenceManager pm2 = getPersistenceManager();
        ArrayList<IfcDecSpaceType> spaceTypeResults = new ArrayList<IfcDecSpaceType>();
        try {
            for (Key key : keys) {
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
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser();
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