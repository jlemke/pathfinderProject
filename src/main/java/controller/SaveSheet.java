package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.sheet.Sheet;
import entity.sheet.SheetInfo;
import org.apache.log4j.Logger;
import persistence.SheetDao;

import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
        //TODO, test if session expiring works
        //check that user is logged in
        String username = request.getUserPrincipal().getName();

        logger.info(username);

        if (username != null && !username.equals("")) {

            logger.info("parsing json into string");

            //parse json into string
            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String json = jb.toString();

            logger.info("json string created");
            logger.info("sheet value: " + json);

            ObjectMapper mapper = new ObjectMapper();
            //JSON to Sheet object
            Sheet sheet = mapper.readValue(json, Sheet.class);

            //check that user owns this sheet
            SheetDao dao = new SheetDao();

            //get owner of sheet from the database
            String ownerUsername = dao.getSheet(sheet.getSheetId()).getOwnerUsername();
            logger.info("Owner = " + ownerUsername);

            //if user both owns this sheet and is not changing the owner
            //TODO validate that the user isn't trying to change some values
            if (username.equals(ownerUsername) && username.equals(sheet.getOwnerUsername())) {
                logger.info("Saving : " + sheet.getCharacterName());
                Date date = new Date();
                sheet.setLastAccessed(new Timestamp(date.getTime()));

                try {
                    message = dao.saveSheet(sheet);
                    message = "success";
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "Error saving sheet using dao.";
                }
            } else {
                //user doesn't own this sheet, or is trying to change something
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                message = "owner_mismatch";
            }
        } else {
            //user isn't logged in
            logger.info("User attempted to save but isn't logged in.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            message = "logged_out";
        }

        //send response
        response.setContentType("text");
        PrintWriter out = response.getWriter();
        out.print(message);
    }
}
