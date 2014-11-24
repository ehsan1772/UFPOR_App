package com.ufpor.app.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.EnvironmentDM;
import com.ufpor.app.server.ifcphysical.Constants;
import com.ufpor.app.shared.ifcclient.IfcClientProject;
import com.ufpor.app.shared.ifcclient.product.IfcClientSpace;
import com.ufpor.app.shared.ifcdeckernel.IfcDecProject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecUnitAssignment;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecElementQuantity;
import com.ufpor.app.shared.ifcdeckernel.property.IfcDecUnit;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecConstraint;
import com.ufpor.app.shared.ifcdeckernel.property.constraint.IfcDecMetric;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.List;
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


        ArrayList<String> finalResult = new ArrayList<String>();
        String header = Constants.getHeader(pr.getLongName().getValue(), pr.getName().getValue(), pr.getUser().getNickname(),pr.getUser().getEmail(), pr.getUser().getAuthDomain());
        LOG.log(Level.INFO, "Header is: " + header);


        Constants costant = Constants.getInstance();
        costant.getProject(pr, finalResult);

        ifcFile = Constants.getIfcFile(header, finalResult);
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

            IfcDecMetric maxConstraint = (IfcDecMetric) defin.getConstraints().get(0);
            IfcDecMetric minConstraint = (IfcDecMetric) defin.getConstraints().get(1);
       //     IfcDecPropertySingleValue prop = (IfcDecMetric) maxConstraint defin.getConstraints().get(0);
       //     constratints = prop.getConstraints();

            IfcDecUnitAssignment assignment = finalOutCome.getUnitsInContext();

//            units.addAll(assignment.getUnits());
//            unit = units.get(0);

            pr = null;
            header = null;
            costant = null;

            Constants costant2 = Constants.getInstance();
            costant2.reset();
            ArrayList<String> finalResult2 = new ArrayList<String>();
            String header2 = Constants.getHeader(finalOutCome);
            LOG.log(Level.INFO, "Header is: \n" + header2);

            costant2.getProject(finalOutCome, finalResult2);
            String ifcFile2 = Constants.getIfcFile(header2, finalResult2);
            LOG.log(Level.INFO, "The file is:\n " + ifcFile2);

        } catch (Exception exception) {
            LOG.log(Level.SEVERE, exception.getMessage());
        }
        finally {
            pm2.close();
        }

        ArrayList<String> retunrvalue = new ArrayList<String>();
        retunrvalue.add(ifcFile);
        return retunrvalue;
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

    private PersistenceManager getPersistenceManager() {
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