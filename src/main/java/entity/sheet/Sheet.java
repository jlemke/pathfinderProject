package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import entity.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Sort;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import static org.hibernate.engine.spi.CascadeStyles.DELETE_ORPHAN;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_main", schema = "pathfinderdb", catalog = "")
public class Sheet implements Serializable {
    private int sheetId;
    private User owner;
    private String ownerUsername;
    private String characterName = "";
    private String characterRace = "";
    private Timestamp dateCreated;
    private Timestamp lastAccessed;
    private String campaign = "";
    private List<SheetClass> sheetClasses = new ArrayList<>();
    private SheetDescription sheetDescription;
    private SheetGeneral sheetGeneral;
    private List<SheetArmor> sheetArmors = new ArrayList<>();
    private List<SheetSkill> sheetSkills = new ArrayList<>();
    private SheetSpeeds sheetSpeeds;
    private List<SheetAbility> sheetAbilities = new ArrayList<>();
    private List<SheetFeat> sheetFeats = new ArrayList<>();
    private List<SheetItem> sheetItems = new ArrayList<>();
    private SheetMoney sheetMoney;
    private List<SheetRacialTrait> sheetRacialTraits = new ArrayList<>();
    private List<SheetWeapon> sheetWeapons = new ArrayList<>();
    private List<SheetAbilityScoreColumn> sheetAbilityScoreColumns = new ArrayList<>();

    @Id
    @GeneratedValue
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "username", insertable = false, updatable = false)
    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

    @Basic
    @Column(name = "owner", nullable = false)
    public String getOwnerUsername() { return ownerUsername; }

    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    @Basic
    @Column(name = "character_name", nullable = true, length = 50)
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Basic
    @Column(name = "character_race", nullable = true, length = 50)
    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }

    @Basic
    @Column(name = "date_created", nullable = true)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "last_accessed", nullable = true)
    public Timestamp getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Basic
    @Column(name = "campaign", nullable = true, length = 50)
    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sheet sheet = (Sheet) o;

        if (sheetId != sheet.sheetId) return false;
        if (owner != null ? !owner.equals(sheet.owner) : sheet.owner != null) return false;
        if (characterName != null ? !characterName.equals(sheet.characterName) : sheet.characterName != null)
            return false;
        if (characterRace != null ? !characterRace.equals(sheet.characterRace) : sheet.characterRace != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(sheet.dateCreated) : sheet.dateCreated != null) return false;
        if (lastAccessed != null ? !lastAccessed.equals(sheet.lastAccessed) : sheet.lastAccessed != null) return false;
        if (campaign != null ? !campaign.equals(sheet.campaign) : sheet.campaign != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (characterName != null ? characterName.hashCode() : 0);
        result = 31 * result + (characterRace != null ? characterRace.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (lastAccessed != null ? lastAccessed.hashCode() : 0);
        result = 31 * result + (campaign != null ? campaign.hashCode() : 0);
        return result;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("level DESC")
    public List<SheetClass> getSheetClasses() {
        return sheetClasses;
    }

    public void setSheetClasses(List<SheetClass> sheetClasses) {
        this.sheetClasses = sheetClasses;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetDescription getSheetDescription() {
        return sheetDescription;
    }

    public void setSheetDescription(SheetDescription sheetDescription) {
        this.sheetDescription = sheetDescription;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetGeneral getSheetGeneral() {
        return sheetGeneral;
    }

    public void setSheetGeneral(SheetGeneral sheetGeneral) {
        this.sheetGeneral = sheetGeneral;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<SheetArmor> getSheetArmors() {
        return sheetArmors;
    }

    public void setSheetArmors(List<SheetArmor> sheetArmors) {
        this.sheetArmors = sheetArmors;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("order ASC")
    public List<SheetSkill> getSheetSkills() {
        return sheetSkills;
    }

    public void setSheetSkills(List<SheetSkill> sheetSkills) {
        this.sheetSkills = sheetSkills;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetSpeeds getSheetSpeeds() {
        return sheetSpeeds;
    }

    public void setSheetSpeeds(SheetSpeeds sheetSpeeds) {
        this.sheetSpeeds = sheetSpeeds;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<SheetAbility> getSheetAbilities() {
        return sheetAbilities;
    }

    public void setSheetAbilities(List<SheetAbility> sheetAbilities) {
        this.sheetAbilities = sheetAbilities;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<SheetFeat> getSheetFeats() {
        return sheetFeats;
    }

    public void setSheetFeats(List<SheetFeat> sheetFeats) {
        this.sheetFeats = sheetFeats;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<SheetItem> getSheetItems() {
        return sheetItems;
    }

    public void setSheetItems(List<SheetItem> sheetItems) {
        this.sheetItems = sheetItems;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetMoney getSheetMoney() {
        return sheetMoney;
    }

    public void setSheetMoney(SheetMoney sheetMoney) {
        this.sheetMoney = sheetMoney;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<SheetRacialTrait> getSheetRacialTraits() {
        return sheetRacialTraits;
    }

    public void setSheetRacialTraits(List<SheetRacialTrait> sheetRacialTraits) {
        this.sheetRacialTraits = sheetRacialTraits;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<SheetWeapon> getSheetWeapons() {
        return sheetWeapons;
    }

    public void setSheetWeapons(List<SheetWeapon> sheetWeapons) {
        this.sheetWeapons = sheetWeapons;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("columnId ASC")
    public List<SheetAbilityScoreColumn> getSheetAbilityScoreColumns() {
        return sheetAbilityScoreColumns;
    }

    public void setSheetAbilityScoreColumns(List<SheetAbilityScoreColumn> sheetAbilityScoreColumns) {
        this.sheetAbilityScoreColumns = sheetAbilityScoreColumns;
    }

}
