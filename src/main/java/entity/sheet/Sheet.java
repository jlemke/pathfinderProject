package entity.sheet;

import entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_main", schema = "pathfinderdb", catalog = "")
public class Sheet {
    private int sheetId;
    private User owner;
    private String characterName;
    private String characterRace;
    private Timestamp dateCreated;
    private Timestamp lastAccessed;
    private String campaign;
    private Collection<SheetClass> sheetClasses;
    private SheetDescription sheetDescription;
    private SheetGeneral sheetGeneral;
    private Collection<SheetArmor> sheetArmors;
    private Collection<SheetSkill> sheetSkills;
    private SheetSpeeds sheetSpeeds;
    private Collection<SheetAbility> sheetAbilities;
    private Collection<SheetFeat> sheetFeats;
    private Collection<SheetItem> sheetItems;
    private SheetMoney sheetMoney;
    private Collection<SheetRacialTrait> sheetRacialTraits;
    private Collection<SheetWeapon> sheetWeapons;
    private Collection<SheetAbilityScoreColumn> sheetAbilityScoreColumns;
    private Collection<SheetSpecializedSkill> sheetSpecializedSkills;


    public Sheet() {
        sheetClasses = new ArrayList<>();
        sheetArmors = new ArrayList<>();
        sheetSkills = new ArrayList<>();
        sheetAbilities = new ArrayList<>();
        sheetFeats = new ArrayList<>();
        sheetItems = new ArrayList<>();
        sheetRacialTraits = new ArrayList<>();
        sheetWeapons = new ArrayList<>();
        sheetAbilityScoreColumns = new ArrayList<>();
        sheetSpecializedSkills = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

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

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetClass> getSheetClasses() {
        return sheetClasses;
    }

    public void setSheetClasses(Collection<SheetClass> sheetClasses) {
        this.sheetClasses = sheetClasses;
    }

    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetDescription getSheetDescription() {
        return sheetDescription;
    }

    public void setSheetDescription(SheetDescription sheetDescription) {
        this.sheetDescription = sheetDescription;
    }

    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetGeneral getSheetGeneral() {
        return sheetGeneral;
    }

    public void setSheetGeneral(SheetGeneral sheetGeneral) {
        this.sheetGeneral = sheetGeneral;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetArmor> getSheetArmors() {
        return sheetArmors;
    }

    public void setSheetArmors(Collection<SheetArmor> sheetArmors) {
        this.sheetArmors = sheetArmors;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetSkill> getSheetSkills() {
        return sheetSkills;
    }

    public void setSheetSkills(Collection<SheetSkill> sheetSkills) {
        this.sheetSkills = sheetSkills;
    }

    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetSpeeds getSheetSpeeds() {
        return sheetSpeeds;
    }

    public void setSheetSpeeds(SheetSpeeds sheetSpeeds) {
        this.sheetSpeeds = sheetSpeeds;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetAbility> getSheetAbilities() {
        return sheetAbilities;
    }

    public void setSheetAbilities(Collection<SheetAbility> sheetAbilities) {
        this.sheetAbilities = sheetAbilities;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetFeat> getSheetFeats() {
        return sheetFeats;
    }

    public void setSheetFeats(Collection<SheetFeat> sheetFeats) {
        this.sheetFeats = sheetFeats;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetItem> getSheetItems() {
        return sheetItems;
    }

    public void setSheetItems(Collection<SheetItem> sheetItems) {
        this.sheetItems = sheetItems;
    }

    @OneToOne(mappedBy = "sheet", cascade = CascadeType.ALL)
    public SheetMoney getSheetMoney() {
        return sheetMoney;
    }

    public void setSheetMoney(SheetMoney sheetMoney) {
        this.sheetMoney = sheetMoney;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetRacialTrait> getSheetRacialTraits() {
        return sheetRacialTraits;
    }

    public void setSheetRacialTraits(Collection<SheetRacialTrait> sheetRacialTraits) {
        this.sheetRacialTraits = sheetRacialTraits;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetWeapon> getSheetWeapons() {
        return sheetWeapons;
    }

    public void setSheetWeapons(Collection<SheetWeapon> sheetWeapons) {
        this.sheetWeapons = sheetWeapons;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetAbilityScoreColumn> getSheetAbilityScoreColumns() {
        return sheetAbilityScoreColumns;
    }

    public void setSheetAbilityScoreColumns(Collection<SheetAbilityScoreColumn> sheetAbilityScoreColumns) {
        this.sheetAbilityScoreColumns = sheetAbilityScoreColumns;
    }

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SheetSpecializedSkill> getSheetSpecializedSkills() {
        return sheetSpecializedSkills;
    }

    public void setSheetSpecializedSkills(Collection<SheetSpecializedSkill> sheetSpecializedSkills) {
        this.sheetSpecializedSkills = sheetSpecializedSkills;
    }
}
