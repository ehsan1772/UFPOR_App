package com.ufpor.app.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.EnvironmentDM;
import com.ufpor.app.client.EnvironmentService;
import com.ufpor.app.client.NotLoggedInException;
import com.ufpor.app.shared.ifcdeckernel.decproduct.IfcDecSpace;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ovenbits on 10/8/14.
 */
public class EnvironmentServiceImpl extends RemoteServiceServlet implements EnvironmentService {
 //   private static final Logger LOG = Logger.getLogger(EnvironmentServiceImpl.class.getName());
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
    public void addIfcDecSpace(IfcDecSpace space) throws NotLoggedInException {
        checkLoggedIn();
        PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(space);
        } finally {
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