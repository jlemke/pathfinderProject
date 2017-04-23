package controller;

import entity.User;
import persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.apache.catalina.realm.RealmBase;

/**
 * Created by Joe on 10/26/2016.
 */

@WebServlet(
        urlPatterns = {"/createAccount"}
)

public class reserveUsername extends HttpServlet {

    private final Logger logger = Logger.getLogger("logger");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("attempting to create account");
        String username = request.getParameter("username");
        username = username == null ? "" : username;
        String password1 = request.getParameter("password1");
        password1 = password1 == null ? "" : password1;
        String password2 = request.getParameter("password2");
        password2 = password2 == null ? "" : password2;
        String email = request.getParameter("email");
        email = email == null ? "" : email;

        logger.info("username : " + username);
        logger.info("password 1 : " + password1);
        logger.info("password 2 : " + password2);
        logger.info("email : " + email);

        boolean[] errors = {false, false, false, false, false};

        logger.info("creating userdao");
        UserDao dao = new UserDao();
        logger.info("userdao created");

        //error index 0 : username must contain 6 characters
        if (username.length() < 6 || username == null)
            errors[0] = true;
        else if (!dao.isUsernameAvailable(username)) //error index 1 : username is taken
            errors[1] = true;

        //error index 2 : password must contain 6 characters
        if (password1.length() < 6 || password2.length() < 6)
            errors[2] = true;

        //error index 3 : passwords must match
        if (!password1.equals(password2))
            errors[3] = true;

        //TODO maybe use regex for email, maybe get lazy
        //error index 4 : must enter a valid email address
        if (email.length() < 3)
            errors[4] = true;

        logger.info("errors + " + errorString(errors));

        PrintWriter out = response.getWriter();
        if (noErrors(errors)) {
            logger.info(errorString(errors));
            User newUser = new User();
            newUser.setUsername(username);

            String encryptedPassword = RealmBase.Digest(password1,"sha-256", "UTF-8");
            newUser.setPassword(encryptedPassword);
            newUser.setEmail(email);

            dao.createRegisteredUser(newUser);
            response.setStatus(200);
            out.print("success");
            //send activation link to email!
            /*
                ReservedUser newUser = new ReservedUser();
                newUser.setUsername(username);
                newUser.setPassword(password1);
                newUser.setEmail(email);
                newUser.setActivationCode("");
                something like:

                subject: Account Activation

                body:
                    Please click this link to activate your account:
                    thewebsite.com/activateAccount?activationCode=blahblahblahblah
             */

        } else {
            //return the error list
            response.setStatus(500);
            response.setContentType("application/json");
            logger.info(errorString(errors));
            out.print(errorString(errors));
        }

    }

    private boolean noErrors(boolean[] errors) {
        for (boolean b : errors) if (b) return false;
        return true;
    }

    private String errorString(boolean[] errors) {
        String s = "[";
        for (int i = 0; i < errors.length; i++) {
            if (errors[i])
                s += "1";
            else s += "0";

            if (i + 1 == errors.length)
                s += "]";
            else s += ", ";
        }
        return s;
    }
}
