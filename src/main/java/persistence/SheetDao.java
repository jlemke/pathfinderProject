package persistence;

import entity.User;
import entity.sheet.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.*;

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
            {"Craft","int","f"},
            {"Diplomacy","cha","f"},
            {"Disable Device","dex","t"},
            {"Disguise","cha","f"},
            {"Escape Artist","dex","f"},
            {"Fly", "dex", "f"},
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
            {"Perform","cha","f"},
            {"Profession","wis","t"},
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

        User owner = (User) session.get(User.class, username);
        List<Sheet> sheets = owner.getSheets();

        List<SheetInfo> thisUsersSheets = new ArrayList<>();
        SheetInfo temp;
        String classString;

        for (Sheet sheet : sheets) {
            temp = new SheetInfo();
            temp.setSheetId(sheet.getSheetId());
            temp.setCharacterName(sheet.getCharacterName());
            temp.setCharacterRace(sheet.getCharacterRace());
            temp.setOwner(username);
            temp.setCampaign(sheet.getCampaign());
            temp.setDateCreated(sheet.getDateCreated());
            temp.setLastAccessed(sheet.getLastAccessed());


            //Create a string of this sheet's classes
            //Format : Archetype1 Class1 Level1/Archetype2 Class2 Level2 ...
            classString = "";
            List<SheetClass> classes = new ArrayList<>(sheet.getSheetClasses());
            //sort classes by level
            Collections.sort(classes, Comparator.comparing(SheetClass::getLevel).reversed());
            //create the string, ignoring blank archetypes
            for (SheetClass sheetClass : classes) {
                if (sheetClass.getArchetype() != null && !sheetClass.getArchetype().equals(""))
                    classString += sheetClass.getArchetype() + " ";
                classString += sheetClass.getClassName() + " " + sheetClass.getLevel() + "/";
            }
            if (classString.length() != 0)
                classString = classString.substring(0, classString.length() - 1);
            else classString = "";
            temp.setCharacterClassString(classString);

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
        sheet.setOwnerUsername(username);
        sheet.setDateCreated(currentTime);
        sheet.setLastAccessed(currentTime);
        sheet.setCharacterName("New Sheet");
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
        ArrayList<SheetAbilityScoreColumn> columns = (ArrayList<SheetAbilityScoreColumn>) sheet.getSheetAbilityScoreColumns();
        SheetAbilityScoreColumn temp;
        for (int i = 0; i < DEFAULT_COLUMN_NAMES.length; i++) {
            temp = new SheetAbilityScoreColumn();
            logger.info("adding ability column " + DEFAULT_COLUMN_NAMES[i] + " with id = " + i);
            temp.setSheet(sheet);
            temp.setColumnName(DEFAULT_COLUMN_NAMES[i]);
            if (DEFAULT_COLUMN_NAMES[i].equals("BASE")) {
                temp.setStrRow(BASE_COLUMN_VALUE);
                temp.setDexRow(BASE_COLUMN_VALUE);
                temp.setConRow(BASE_COLUMN_VALUE);
                temp.setIntRow(BASE_COLUMN_VALUE);
                temp.setWisRow(BASE_COLUMN_VALUE);
                temp.setChaRow(BASE_COLUMN_VALUE);
            }
            columns.add(temp);
        }
    }

    private void setBaseSkills(Sheet sheet) {
        ArrayList<SheetSkill> skills = (ArrayList<SheetSkill>) sheet.getSheetSkills();
        SheetSkill temp;
        String[] defaultSkill;
        for (int i = 0; i < DEFAULT_SKILLS.length; i++) {
            defaultSkill = DEFAULT_SKILLS[i];
            temp = new SheetSkill();
            temp.setSheet(sheet);
            temp.setOrder(i + 1);
            temp.setSkillName(defaultSkill[0]);
            temp.setSkillAbility(defaultSkill[1]);
            if (defaultSkill[2].equals("t"))
                temp.setReqTrained(true);
            else temp.setReqTrained(false);
            skills.add(temp);
        }
    }

    public Sheet getSheet(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        //force session to fetch all collections by using .size()

        Sheet sheet = (Sheet) session.get(Sheet.class, id);
        sheet.getSheetAbilityScoreColumns().size();

        for (SheetAbilityScoreColumn col : sheet.getSheetAbilityScoreColumns()) {
            logger.info(col.getColumnId());
        }

        sheet.getSheetClasses().size();
        for (SheetClass sheetClass : sheet.getSheetClasses()) {
            logger.info(sheetClass.getLevel());
            sheetClass.getSheetSpells().size();
            sheetClass.getSheetClassFeatures().size();
        }
        sheet.getSheetAbilities().size();
        sheet.getSheetArmors().size();
        sheet.getSheetFeats().size();
        sheet.getSheetItems().size();
        sheet.getSheetRacialTraits().size();
        sheet.getSheetSkills().size();
        sheet.getSheetWeapons().size();

        session.close();
        return sheet;
    }


    public String saveSheet(Sheet sheet) {
        //TODO possibly implement error handling using string return type
        String message = "Sheet Saved?";
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        logger.info("Saving/updating sheet in hibernate...");

        //save or update all children
        //sheet = updateCollections(sheet, session);

        session.merge(sheet);
        transaction.commit();
        session.close();
        return message;
    }

    private Sheet updateCollections(Sheet sheet, Session session) {
        for (SheetAbility sheetAbility : sheet.getSheetAbilities()) {
            sheetAbility.setSheet(sheet);
            session.saveOrUpdate(sheetAbility);
        }

        for (SheetArmor sheetArmor : sheet.getSheetArmors()) {
            sheetArmor.setSheet(sheet);
            session.saveOrUpdate(sheetArmor);
        }

        //Save or update all class spells and features
        for (SheetClass sheetClass : sheet.getSheetClasses()) {
            sheetClass.setSheet(sheet);
            session.save(sheetClass);
            for (SheetClassFeature sheetClassFeature : sheetClass.getSheetClassFeatures()) {
                sheetClassFeature.setSheetClass(sheetClass);
                session.saveOrUpdate(sheetClassFeature);
            }
            for (SheetSpell sheetSpell : sheetClass.getSheetSpells()) {
                sheetSpell.setSheetClass(sheetClass);
                session.saveOrUpdate(sheetSpell);
            }
        }
        return sheet;
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
