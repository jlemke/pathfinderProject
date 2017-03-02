package controller;

import persistence.SheetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Joe on 2/27/2017.
 */
@WebServlet(
        urlPatterns = {"/newSheet"}
)
public class newSheet extends HttpServlet {

    private final Logger logger = Logger.getLogger("logger");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //TODO replace with j_security_check
        String username = request.getUserPrincipal().getName();

        if (!username.equals("")) {
            SheetDao dao = new SheetDao();

            dao.createBlankSheet(username);
            response.setStatus(200);
            response.sendRedirect("sheets");
        } else
            response.setStatus(403);
    }
}