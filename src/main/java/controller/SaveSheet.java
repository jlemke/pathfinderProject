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
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Joe on 12/5/2016.
 */

@WebServlet(
        urlPatterns = {"/saveSheet"}
)
public class SaveSheet extends HttpServlet {
    private final Logger logger = Logger.getLogger("logger");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



    }
}
