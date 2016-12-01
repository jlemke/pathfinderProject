package persistence;

import entity.Sheet;
import entity.SheetMain;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 11/23/2016.
 */
public class SheetDao {

    /**
     *
     * @param username the owner of the sheets
     * @return returns a list of SheetMain objects to be used to list a user's sheets
     *
     */
    public List<SheetMain> getListOfSheets(String username) {
        List<SheetMain> thisUsersSheets = new ArrayList<SheetMain>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        thisUsersSheets = session.createCriteria(SheetMain.class).add(Restrictions.eq("owner", username)).list();

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

        sheet = session.get(Sheet.class, sheetId);
        return sheet;
    }

}
