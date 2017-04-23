package controller;

import persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Joe on 10/26/2016.
 */

/*
@WebServlet(
        urlPatterns = {"/home"}
)
*/

public class Index extends HttpServlet {

    /*
        we need to check if the username and password match a user in the database
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("loggedIn", false);
        //see if user is logged in, get username
        try {
            request.setAttribute("username", request.getUserPrincipal().getName());
            request.setAttribute("loggedIn", true);
        } catch (Exception e){

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

}
