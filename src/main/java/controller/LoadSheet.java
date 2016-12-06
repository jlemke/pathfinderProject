package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        Sheet sheet = dao.getSheet(sheetId);

        ObjectMapper mapper = new ObjectMapper();

        String output = null;
        try {
            output = mapper.writeValueAsString(sheet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(output);
    }
}