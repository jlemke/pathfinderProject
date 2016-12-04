package persistence;

import entity.sheet.SheetInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joe on 12/4/2016.
 */
public class SheetDaoTest {

    SheetDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new SheetDao();
    }

    @Test
    public void getListOfSheets() throws Exception {
        List<SheetInfo> sheets = dao.getListOfSheets("jlemke");

        assertEquals("failed", sheets.get(0).getCharacterClassString(), "Undead Lord Cleric 3/Fighter 1");
    }

}