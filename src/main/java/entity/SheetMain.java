package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Joe on 10/26/2016.
 */

@Entity
@Table(name = "sheet_main")
public class SheetMain {

    @Id
    @Column

    private int sheetId;

    @Column
    private String username;

    @Column
    private String characterName;

    @Column
    private String characterRace;

    @Column(name = "date_created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_accessed", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessed;

    @Column
    private String campaign;


    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

}
