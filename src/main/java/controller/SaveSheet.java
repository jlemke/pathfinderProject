package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.sheet.Sheet;
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
import java.io.PrintWriter;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "success";

        logger.info("saving sheet...");

        String json = request.getAttribute("sheet").toString();
        logger.info("sheet value: " + json);

        ObjectMapper mapper = new ObjectMapper();
        //JSON to Sheet object
        Sheet sheet = mapper.readValue(json, Sheet.class);

        SheetDao dao = new SheetDao();
        try {
            dao.saveSheet(sheet);
        } catch (Exception e) {
            e.printStackTrace();
            message = "error";
        }

        response.setContentType("text");
        PrintWriter out = response.getWriter();
        out.print(message);
    }
}
