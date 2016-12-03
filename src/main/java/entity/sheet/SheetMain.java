package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Joe on 10/26/2016.
 */

@Entity
@Table(name = "sheet_main")
public class SheetMain {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="sheet_id")
    private int sheetId;

    @Column(name="owner")
    private String username;

    @Column(name="character_name")
    private String characterName;

    @Column(name="character_race")
    private String characterRace;

    @Column(name = "date_created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_accessed", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessed;

    @Column(name="campaign")
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
