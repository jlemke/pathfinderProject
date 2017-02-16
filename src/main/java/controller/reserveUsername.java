package controller;

import entity.ReservedUser;
import persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

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

        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");

        boolean[] errors = {false, false, false, false, false};

        UserDao dao = new UserDao();

        //error index 0 : username must contain 6 characters
        if (username.length() < 6)
            errors[0] = true;

        //error index 1 : username is taken
        if (!dao.isUsernameAvailable(username))
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

        if (noErrors(errors)) {
            ReservedUser newUser = new ReservedUser();
            newUser.setUsername(username);
            newUser.setPassword(password1);
            newUser.setEmail(email);
            newUser.setActivationCode("");

            //send activation link to email!
            /*
                something like:

                subject: Account Activation

                body:
                    Please click this link to activate your account:
                    thewebsite.com/activateAccount?activationCode=blahblahblahblah
             */

            response.sendRedirect("signup-success.html");
        } else {
            //return the error list
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(errors);
        }

    }

    private boolean noErrors(boolean[] errors) {
        for (boolean b : errors) if (b) return false;
        return true;
    }
}
