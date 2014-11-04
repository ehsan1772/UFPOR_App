package com.ufpor.app.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufpor.app.client.LoginInfo;
import com.ufpor.app.client.LoginService;

/**
 * Created by ovenbits on 10/8/14.
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
    @Override
    public LoginInfo login(String requestUri) {
        UserService userService = UserServiceFactory.getUserService();

        User user = null;

        if (userService.isUserLoggedIn()) {
            user = userService.getCurrentUser();
        }

        LoginInfo loginInfo = new LoginInfo();
        if (user != null) {
            loginInfo.setLoggedIn(true);
            loginInfo.setEmailAddress(user.getEmail());
            loginInfo.setNickname(user.getNickname());
            loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
        } else {
            loginInfo.setLoggedIn(false);
            loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
        }
        return loginInfo;
    }
}