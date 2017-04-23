package persistence;

import entity.sheet.Sheet;
import entity.sheet.SheetAbilityScoreColumn;
import entity.sheet.SheetInfo;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import org.apache.catalina.realm.RealmBase;

/**
 * Created by Joe on 12/4/2016.
 */
public class SheetDaoTest {

    private SheetDao dao;
    private String USERNAME = "testUser3";
    private final Logger logger = Logger.getLogger("logger");
    private Collection<Sheet> testSheets;

    @Before
    public void setUp() throws Exception {
        dao = new SheetDao();
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

        assertEquals("failed", 141, sheets.get(0).getSheetId());
    }


    //TODO edit sheet skill to use compare to or whatever
    @Test
    public void createBlankSheet() throws Exception {
        int newId = dao.createBlankSheet(USERNAME);
        Sheet tempSheet = dao.getSheet(newId);
        testSheets.add(tempSheet);
        assertEquals("failed", newId, tempSheet.getSheetId());
    }

    @Test
    public void getSheet() throws Exception {
        int id = 3;
        Sheet sheet = dao.getSheet(id);

        assertEquals(2, sheet.getSheetClasses().size());
    }

    @Test
    public void deleteSheet() throws Exception {

    }

    /*
    @Test
    public void convertPasswords() {

    }
    */

    @Test
    public void saveSheet() throws Exception {
        int id = dao.createBlankSheet(USERNAME);
        Sheet sheet = dao.getSheet(id);
        ArrayList<SheetAbilityScoreColumn> columns = new ArrayList<>();
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