package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_main", schema = "pathfinderdb", catalog = "")
public class Sheet implements Serializable {
    private int sheetId;
    private User owner;
    private String characterName = "";
    private String characterRace = "";
    private Timestamp dateCreated;
    private Timestamp lastAccessed;
    private String campaign = "";
    private Set<SheetClass> sheetClasses = new TreeSet<>();
    private SheetDescription sheetDescription;
    private SheetGeneral sheetGeneral;
    private Set<SheetArmor> sheetArmors = new TreeSet<>();
    private Set<SheetSkill> sheetSkills = new TreeSet<>();
    private SheetSpeeds sheetSpeeds;
    private Set<SheetAbility> sheetAbilities = new TreeSet<>();
    private Set<SheetFeat> sheetFeats = new TreeSet<>();
    private Set<SheetItem> sheetItems = new TreeSet<>();
    private SheetMoney sheetMoney;
    private Set<SheetRacialTrait> sheetRacialTraits = new TreeSet<>();
    private Set<SheetWeapon> sheetWeapons = new TreeSet<>();
    private Set<SheetAbilityScoreColumn> sheetAbilityScoreColumns = new TreeSet<>();

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
    @JoinColumn(name = "owner")
    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

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
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetClass> getSheetClasses() {
        return sheetClasses;
    }

    public void setSheetClasses(Set<SheetClass> sheetClasses) {
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
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetArmor> getSheetArmors() {
        return sheetArmors;
    }

    public void setSheetArmors(Set<SheetArmor> sheetArmors) {
        this.sheetArmors = sheetArmors;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetSkill> getSheetSkills() {
        return sheetSkills;
    }

    public void setSheetSkills(Set<SheetSkill> sheetSkills) {
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
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetAbility> getSheetAbilities() {
        return sheetAbilities;
    }

    public void setSheetAbilities(Set<SheetAbility> sheetAbilities) {
        this.sheetAbilities = sheetAbilities;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetFeat> getSheetFeats() {
        return sheetFeats;
    }

    public void setSheetFeats(Set<SheetFeat> sheetFeats) {
        this.sheetFeats = sheetFeats;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetItem> getSheetItems() {
        return sheetItems;
    }

    public void setSheetItems(Set<SheetItem> sheetItems) {
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
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetRacialTrait> getSheetRacialTraits() {
        return sheetRacialTraits;
    }

    public void setSheetRacialTraits(Set<SheetRacialTrait> sheetRacialTraits) {
        this.sheetRacialTraits = sheetRacialTraits;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetWeapon> getSheetWeapons() {
        return sheetWeapons;
    }

    public void setSheetWeapons(Set<SheetWeapon> sheetWeapons) {
        this.sheetWeapons = sheetWeapons;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<SheetAbilityScoreColumn> getSheetAbilityScoreColumns() {
        return sheetAbilityScoreColumns;
    }

    public void setSheetAbilityScoreColumns(Set<SheetAbilityScoreColumn> sheetAbilityScoreColumns) {
        this.sheetAbilityScoreColumns = sheetAbilityScoreColumns;
    }

}
