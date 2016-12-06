package persistence;

import entity.User;
import entity.sheet.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Joe on 11/23/2016.
 */
public class SheetDao {

    private final Logger logger = Logger.getLogger("logger");

    private final String[] DEFAULT_COLUMN_NAMES = {
            "BASE", "RACE", "MISC", "TEMP"
    };

    private final int BASE_COLUMN_VALUE = 10;

    private final String[][] DEFAULT_SKILLS = {
            {"Acrobatics","dex","f"},
            {"Appraise","int","f"},
            {"Bluff","cha","f"},
            {"Climb","str","f"},
            {"Craft",null,"f"},
            {"Diplomacy","int","f"},
            {"Disable Device","dex","t"},
            {"Disguise","cha","f"},
            {"Escape Artist","dex","f"},
            {"Handle Animal","cha","t"},
            {"Heal","wis","f"},
            {"Intimidate","cha","f"},
            {"Knowledge (Arcana)","int","t"},
            {"Knowledge (Dungeoneering)","int","t"},
            {"Knowledge (Engineering)","int","t"},
            {"Knowledge (Geography)","int","t"},
            {"Knowledge (History)","int","t"},
            {"Knowledge (Local)","int","t"},
            {"Knowledge (Nature)","int","t"},
            {"Knowledge (Nobility)","int","t"},
            {"Knowledge (Planes)","int","t"},
            {"Knowledge (Religion)","int","t"},
            {"Linguistics","int","t"},
            {"Perception","wis","f"},
            {"Perform",null,"f"},
            {"Profession",null,"t"},
            {"Ride","dex","f"},
            {"Sense Motive","wis","f"},
            {"Sleight of Hand","dex","t"},
            {"Spellcraft","int","t"},
            {"Stealth","dex","f"},
            {"Survival","wis","f"},
            {"Swim","str","f"},
            {"Use Magic Device","cha","t"}
    };

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
                "FROM sheet_main AS sm LEFT JOIN sheet_classes AS sc ON sm.sheet_id = sc.sheet_id " +
                "WHERE sm.owner = '" + username + "' GROUP BY sm.sheet_id ORDER BY sm.sheet_id ASC";
        Query query = session.createSQLQuery(sql);

        List<SheetInfo> thisUsersSheets = new ArrayList<>();
        SheetInfo temp;
        for(Object o : query.list()) {
            Object[] fields = (Object[]) o;
            for (int i = 0; i < fields.length; i++) {
                logger.info("before = " + fields[i]);
                if (fields[i] == null)
                    fields[i] = "";
                logger.info("after = " + fields[i]);
            }
            temp = new SheetInfo((int) fields[0], (String) fields[1], (String) fields[2], (String) fields[3],
                    (String) fields[4], (Timestamp) fields[5], (Timestamp) fields[6], (String) fields[7]);
            thisUsersSheets.add(temp);
        }

        session.close();
        return thisUsersSheets;
    }

    /**
     * Creates a new character sheet with default values in each field
     * @param username username of the creator of this sheet
     */
    public int createBlankSheet(String username) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User owner = (User) session.get(User.class, username);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        logger.info("creating sheet");
        Sheet sheet = new Sheet();
        sheet.setOwner(owner);
        sheet.setDateCreated(currentTime);
        sheet.setLastAccessed(currentTime);
        session.save(sheet);
        int id = sheet.getSheetId();

        logger.info("creating description");
        SheetDescription desc = new SheetDescription();
        desc.setSheet(sheet);
        sheet.setSheetDescription(desc);

        logger.info("creating general");
        SheetGeneral gen = new SheetGeneral();
        gen.setSheet(sheet);
        sheet.setSheetGeneral(gen);

        logger.info("creating ability columns");
        setBaseAbilityScoreColumns(sheet);

        logger.info("creating skills");
        setBaseSkills(sheet);

        SheetSpeeds speeds = new SheetSpeeds();
        speeds.setSheet(sheet);
        sheet.setSheetSpeeds(speeds);

        SheetMoney money = new SheetMoney();
        money.setSheet(sheet);
        sheet.setSheetMoney(money);

        session.update(sheet);
        transaction.commit();
        session.close();

        return id;
    }

    private void setBaseAbilityScoreColumns(Sheet sheet) {
        Set<SheetAbilityScoreColumn> columns = sheet.getSheetAbilityScoreColumns();
        SheetAbilityScoreColumn temp;
        for (int i = 0; i < DEFAULT_COLUMN_NAMES.length; i++) {
            temp = new SheetAbilityScoreColumn();
            logger.info("adding ability column " + DEFAULT_COLUMN_NAMES[i] + " with id = " + i);
            temp.setSheet(sheet);
            temp.setColumnName(DEFAULT_COLUMN_NAMES[i]);
            if (DEFAULT_COLUMN_NAMES[i].equals("BASE")) {
                temp.setStrColumn(BASE_COLUMN_VALUE);
                temp.setDexColumn(BASE_COLUMN_VALUE);
                temp.setConColumn(BASE_COLUMN_VALUE);
                temp.setIntColumn(BASE_COLUMN_VALUE);
                temp.setWisColumn(BASE_COLUMN_VALUE);
                temp.setChaColumn(BASE_COLUMN_VALUE);
            }
            columns.add(temp);
        }
    }

    private void setBaseSkills(Sheet sheet) {
        Set<SheetSkill> skills = sheet.getSheetSkills();
        SheetSkill temp;
        String[] defaultSkill;
        for (int i = 0; i < DEFAULT_SKILLS.length; i++) {
            defaultSkill = DEFAULT_SKILLS[i];
            temp = new SheetSkill();
            temp.setSheet(sheet);
            temp.setOrder(i + 1);
            temp.setSkillName(defaultSkill[0]);
            temp.setSkillAbility(defaultSkill[1]);
            temp.setSpecialization("");
            if (defaultSkill[2].equals("t"))
                temp.setReqTrained(true);
            else temp.setReqTrained(false);
            skills.add(temp);
        }
    }

    public Sheet getSheet(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        Sheet sheet = (Sheet) session.get(Sheet.class, id);

        session.close();
        return sheet;
    }


    public void saveSheet(Sheet sheet) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(sheet);
        transaction.commit();
        session.close();
    }

    public void deleteSheet(Sheet s) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(s);
        transaction.commit();
        session.close();
    }

    public void deleteSheet(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Sheet sheet = (Sheet) session.get(Sheet.class, id);

        session.delete(sheet);
        transaction.commit();
        session.close();
    }

}
