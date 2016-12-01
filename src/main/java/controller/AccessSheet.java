package controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
/**
 * Created by Joe on 10/26/2016.
 *
 */

@WebServlet(
        urlPatterns = {"/accessSheet"}
)

public class AccessSheet extends HttpServlet {
    /*
     *  servlet for accessing a sheet when they click on one in the sheet-list page
     */


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

}
