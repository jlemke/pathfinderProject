package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import entity.sheet.Sheet;
import persistence.SheetDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Created by Joe on 12/5/2016.
 */

@WebServlet(
        urlPatterns = {"/sheet"}
)
public class LoadSheet extends HttpServlet {

    private final Logger logger = Logger.getLogger("logger");


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //TODO replace with j_security_check
        String username = "jlemke"; //request.getUserPrincipal().getName();

        SheetDao dao = new SheetDao();

        logger.info("getting id");
        int sheetId = Integer.parseInt(request.getParameter("id"));

        logger.info("sheet id requested: " + sheetId);
        logger.info("getting sheet");


        Sheet sheet = dao.getSheet(sheetId);

        logger.info("confirming ownership of sheet");

        if (username.equals(sheet.getOwner().getUsername())) {

        logger.info("mapping object");
        ObjectMapper mapper = new ObjectMapper();
        Hibernate4Module hbm = new Hibernate4Module();
        hbm.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        mapper.registerModule(hbm);

        String output = null;
        try {
            output = mapper.writeValueAsString(sheet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        logger.info("json output : " + output);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(output);
        } else {
            //need to send forbidden page!
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect("forbidden.html");
        }

    }
}
