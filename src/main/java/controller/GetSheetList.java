package controller;

import entity.sheet.SheetInfo;
import persistence.SheetDao;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Joe on 10/26/2016.
 */

@WebServlet(
        urlPatterns = {"/sheets"}
)

public class GetSheetList extends HttpServlet {

    private final Logger logger = Logger.getLogger("logger");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get username from j_security_check
        //String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        logger.info("in getSheetList servlet");

        String username = request.getUserPrincipal().getName();

        //get sheets for this user
        List<SheetInfo> sheets;
        SheetDao dao = new SheetDao();
        sheets = dao.getListOfSheets(username);

        request.setAttribute("sheets", sheets);
        request.setAttribute("user", username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("sheets.jsp");
        dispatcher.forward(request, response);

    }

}
