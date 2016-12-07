package entity.sheet;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Joe on 12/3/2016.
 * This class is loaded by GetSheetList to display the desired information
 * without having to retrieve the entire Sheet object
 */
public class SheetInfo {

    private int sheetId;
    private String owner;
    private String characterName;
    private String characterRace;
    private String characterClassString;
    private Timestamp dateCreated;
    private Timestamp lastAccessed;
    private String campaign;

    public SheetInfo() {

    }

    public SheetInfo(int sheetId, String owner, String characterName, String characterRace,
                     String characterClassString, Timestamp dateCreated, Timestamp lastAccessed,
                     String campaign) {
        this.sheetId = sheetId;
        this.owner = owner;
        this.characterName = characterName;
        this.characterRace = characterRace;
        this.characterClassString = characterClassString;
        this.dateCreated = dateCreated;
        this.lastAccessed = lastAccessed;
        this.campaign = campaign;
    }

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }

    public String getCharacterClassString() {
        return characterClassString;
    }

    public void setCharacterClassString(String characterClassString) {
        this.characterClassString = characterClassString;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

}
