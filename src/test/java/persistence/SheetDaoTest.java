package persistence;

import entity.sheet.Sheet;
import entity.sheet.SheetAbilityScoreColumn;
import entity.sheet.SheetInfo;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Joe on 12/4/2016.
 */
public class SheetDaoTest {

    private SheetDao dao;
    private UserDao userDao;
    private String USERNAME = "test_user";
    private final Logger logger = Logger.getLogger("logger");
    private Collection<Sheet> testSheets;

    @Before
    public void setUp() throws Exception {
        dao = new SheetDao();
        userDao = new UserDao();
        testSheets = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        for (Sheet testSheet : testSheets) {
            dao.deleteSheet(testSheet);
        }
    }


    @Test
    public void getListOfSheets() throws Exception {
        List<SheetInfo> sheets = dao.getListOfSheets(USERNAME);

        assertEquals("failed", "Fighter 1", sheets.get(0).getCharacterClassString());
    }

    @Test
    public void createBlankSheet() throws Exception {
        int newId = dao.createBlankSheet(USERNAME);
        List<SheetInfo> sheets = dao.getListOfSheets(USERNAME);

        SheetInfo temp;
        for (int i = 0; i < sheets.size(); i++) {
            temp = sheets.get(i);
            logger.info(temp.getSheetId());
            logger.info(temp.getCharacterClassString());
            logger.info(temp.getCharacterName());
            logger.info(temp.getCharacterRace());
            logger.info(temp.getOwner());
        }
        testSheets.add(dao.getSheet(newId));
        assertEquals("failed", newId, sheets.get(sheets.size() - 1).getSheetId());
    }

    @Test
    public void getSheet() throws Exception {
        int id = 3;
        Sheet sheet = dao.getSheet(id);

        assertEquals(2, sheet.getSheetClasses().size());
    }

    @Test
    public void saveSheet() throws Exception {
        int id = dao.createBlankSheet(USERNAME);
        Sheet sheet = new Sheet();
        sheet.setSheetId(id);
        sheet.setOwner(userDao.getUser(USERNAME));
        Set<SheetAbilityScoreColumn> columns = new TreeSet<>();
        SheetAbilityScoreColumn column;
        for (int i = 0; i < 4; i++) {
            column = new SheetAbilityScoreColumn();
            column.setSheet(sheet);
            column.setColumnName("Column " + i);
            columns.add(column);
        }
        sheet.setSheetAbilityScoreColumns(columns);
        dao.saveSheet(sheet);

        Sheet newSheet = dao.getSheet(id);
        testSheets.add(newSheet);
        assertEquals("fail", newSheet, sheet);
    }

}