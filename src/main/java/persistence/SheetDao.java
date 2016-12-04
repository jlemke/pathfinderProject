package persistence;

import entity.sheet.Sheet;
import entity.sheet.SheetInfo;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

import java.sql.Timestamp;
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
     * @return returns a list of Sheet objects to be used to list a user's sheets
     *
     */
    public List<SheetInfo> getListOfSheets(String username) {
        logger.info("in getListOfSheets");
        Session session = SessionFactoryProvider.getSessionFactory().openSession();


        //TODO add 'as' statements and figure out how to access object
        String sql = "SELECT sm.sheet_id, sm.owner, sm.character_name, sm.character_race, " +
                "GROUP_CONCAT(TRIM(CONCAT(sc.archetype, CONCAT(' ', CONCAT(sc.class_name, " +
                "CONCAT(' ', CONCAT(sc.level)))))) SEPARATOR '/'), sm.date_created, sm.last_accessed, sm.campaign " +
                "FROM sheet_main AS sm JOIN sheet_classes AS sc ON sm.sheet_id = sc.sheet_id " +
                "WHERE sm.owner = '" + username + "' GROUP BY sc.sheet_id ORDER BY sc.level DESC";
        Query query = session.createSQLQuery(sql);

        List<SheetInfo> thisUsersSheets = new ArrayList<SheetInfo>();
        SheetInfo temp;
        for(Object o : query.list()) {
            Object[] fields = (Object[]) o;
            temp = new SheetInfo((String) fields[1], (String) fields[2], (String) fields[3],
                    (String) fields[4], (Timestamp) fields[5], (Timestamp) fields[6], (String) fields[7]);
            thisUsersSheets.add(temp);
        }

        logger.info(thisUsersSheets);
        return thisUsersSheets;
    }



    /**
     *
     * @param sheetId the id of the sheet
     * @return returns the specified sheet

    public Sheet getAllSheetInfo(int sheetId) {
        Sheet sheet = new Sheet();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        sheet = (Sheet) session.get(Sheet.class, sheetId);
        return sheet;
    }
    */

    /**
     * Creates a new character sheet with default values in each field
     * @param owner username of the creator of this sheet
     */
    public void createBlankSheet(String owner) {

    }



}
