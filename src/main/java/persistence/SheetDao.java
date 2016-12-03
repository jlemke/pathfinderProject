package persistence;

import entity.sheet.Sheet;
import entity.sheet.SheetMain;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 11/23/2016.
 */
public class SheetDao {

    private final Logger logger = Logger.getLogger("logger");


    public SheetDao() {
        logger.info("dao created");
    }

    /**
     *
     * @param username the owner of the sheets
     * @return returns a list of SheetMain objects to be used to list a user's sheets
     *
     */
    public List<SheetMain> getListOfSheets(String username) {
        logger.info("in getListOfSheets");
        List<SheetMain> thisUsersSheets = new ArrayList<SheetMain>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        thisUsersSheets = (List<SheetMain>) session.createCriteria(SheetMain.class).add(Restrictions.eq("owner", username)).list();

        logger.info(thisUsersSheets);
        return thisUsersSheets;
    }



    /**
     *
     * @param sheetId the id of the sheet
     * @return returns the specified sheet
     */
    public Sheet getAllSheetInfo(int sheetId) {
        Sheet sheet = new Sheet();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        sheet = (Sheet) session.get(Sheet.class, sheetId);
        return sheet;
    }

    /**
     * Creates a new character sheet with default values in each field
     * @param owner username of the creator of this sheet
     */
    public void createBlankSheet(String owner) {

    }



}
