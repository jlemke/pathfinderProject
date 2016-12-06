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

        SheetDao dao = new SheetDao();
        int sheetId = Integer.parseInt(request.getParameter("id"));

        logger.info("getting sheet");
        Sheet sheet = dao.getSheet(sheetId);

        logger.info("mapping object");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());

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
    }
}
