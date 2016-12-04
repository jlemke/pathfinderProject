package entity.sheet;

import entity.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_main", schema = "pathfinderdb", catalog = "")
public class Sheet {
    private int sheetId;
    private String owner;
    private String characterName;
    private String characterRace;
    private Timestamp dateCreated;
    private Timestamp lastAccessed;
    private String campaign;
    private User usersByOwner;
    private Collection<SheetClass> sheetClassesBySheetId;
    private SheetDescription sheetDescriptionBySheetId;
    private SheetGeneral sheetGeneralBySheetId;
    private Collection<SheetArmor> sheetArmorsBySheetId;
    private Collection<SheetSkill> sheetSkillsBySheetId;
    private SheetSpeeds sheetSpeedsBySheetId;
    private Collection<SheetAbility> sheetAbilitiesBySheetId;
    private Collection<SheetFeat> sheetFeatsBySheetId;
    private Collection<SheetItem> sheetItemsBySheetId;
    private SheetMoney sheetMoneyBySheetId;
    private Collection<SheetRacialTrait> sheetRacialTraitsBySheetId;
    private Collection<SheetSpell> sheetSpellsBySheetId;
    private Collection<SheetWeapon> sheetWeaponsBySheetId;
    private Collection<SheetClassFeature> sheetClassFeaturesBySheetId;
    private Collection<SheetAbilityScoreColumn> sheetAbilityScoreColumnsBySheetId;
    private Collection<SheetSpecializedSkill> sheetSpecializedSkillsBySheetId;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Basic
    @Column(name = "owner", nullable = false, length = 30)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

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
        if (dateCreated != null ? !dateCreated.equals(sheet.dateCreated) : sheet.dateCreated != null)
            return false;
        if (lastAccessed != null ? !lastAccessed.equals(sheet.lastAccessed) : sheet.lastAccessed != null)
            return false;
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

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "username", insertable = false, updatable = false)
    public User getUsersByOwner() {
        return usersByOwner;
    }

    public void setUsersByOwner(User usersByOwner) {
        this.usersByOwner = usersByOwner;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetClass> getSheetClassesBySheetId() {
        return sheetClassesBySheetId;
    }

    public void setSheetClassesBySheetId(Collection<SheetClass> sheetClassesBySheetId) {
        this.sheetClassesBySheetId = sheetClassesBySheetId;
    }

    @OneToOne(mappedBy = "sheetBySheetId")
    public SheetDescription getSheetDescriptionBySheetId() {
        return sheetDescriptionBySheetId;
    }

    public void setSheetDescriptionBySheetId(SheetDescription sheetDescriptionBySheetId) {
        this.sheetDescriptionBySheetId = sheetDescriptionBySheetId;
    }

    @OneToOne(mappedBy = "sheetBySheetId")
    public SheetGeneral getSheetGeneralBySheetId() {
        return sheetGeneralBySheetId;
    }

    public void setSheetGeneralBySheetId(SheetGeneral sheetGeneralBySheetId) {
        this.sheetGeneralBySheetId = sheetGeneralBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetArmor> getSheetArmorsBySheetId() {
        return sheetArmorsBySheetId;
    }

    public void setSheetArmorsBySheetId(Collection<SheetArmor> sheetArmorsesBySheetId) {
        this.sheetArmorsBySheetId = sheetArmorsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetSkill> getSheetSkillsBySheetId() {
        return sheetSkillsBySheetId;
    }

    public void setSheetSkillsBySheetId(Collection<SheetSkill> sheetSkillsesBySheetId) {
        this.sheetSkillsBySheetId = sheetSkillsesBySheetId;
    }

    @OneToOne(mappedBy = "sheetBySheetId")
    public SheetSpeeds getSheetSpeedsBySheetId() {
        return sheetSpeedsBySheetId;
    }

    public void setSheetSpeedsBySheetId(SheetSpeeds sheetSpeedsBySheetId) {
        this.sheetSpeedsBySheetId = sheetSpeedsBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetAbility> getSheetAbilitiesBySheetId() {
        return sheetAbilitiesBySheetId;
    }

    public void setSheetAbilitiesBySheetId(Collection<SheetAbility> sheetAbilitiesBySheetId) {
        this.sheetAbilitiesBySheetId = sheetAbilitiesBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetFeat> getSheetFeatsBySheetId() {
        return sheetFeatsBySheetId;
    }

    public void setSheetFeatsBySheetId(Collection<SheetFeat> sheetFeatsesBySheetId) {
        this.sheetFeatsBySheetId = sheetFeatsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetItem> getSheetItemsBySheetId() {
        return sheetItemsBySheetId;
    }

    public void setSheetItemsBySheetId(Collection<SheetItem> sheetItemsesBySheetId) {
        this.sheetItemsBySheetId = sheetItemsesBySheetId;
    }

    @OneToOne(mappedBy = "sheetBySheetId")
    public SheetMoney getSheetMoneyBySheetId() {
        return sheetMoneyBySheetId;
    }

    public void setSheetMoneyBySheetId(SheetMoney sheetMoneyBySheetId) {
        this.sheetMoneyBySheetId = sheetMoneyBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetRacialTrait> getSheetRacialTraitsBySheetId() {
        return sheetRacialTraitsBySheetId;
    }

    public void setSheetRacialTraitsBySheetId(Collection<SheetRacialTrait> sheetRacialTraitsesBySheetId) {
        this.sheetRacialTraitsBySheetId = sheetRacialTraitsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetSpell> getSheetSpellsBySheetId() {
        return sheetSpellsBySheetId;
    }

    public void setSheetSpellsBySheetId(Collection<SheetSpell> sheetSpellsBySheetId) {
        this.sheetSpellsBySheetId = sheetSpellsBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetWeapon> getSheetWeaponsBySheetId() {
        return sheetWeaponsBySheetId;
    }

    public void setSheetWeaponsBySheetId(Collection<SheetWeapon> sheetWeaponsBySheetId) {
        this.sheetWeaponsBySheetId = sheetWeaponsBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetClassFeature> getSheetClassFeaturesBySheetId() {
        return sheetClassFeaturesBySheetId;
    }

    public void setSheetClassFeaturesBySheetId(Collection<SheetClassFeature> sheetClassFeaturesBySheetId) {
        this.sheetClassFeaturesBySheetId = sheetClassFeaturesBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetAbilityScoreColumn> getSheetAbilityScoreColumnsBySheetId() {
        return sheetAbilityScoreColumnsBySheetId;
    }

    public void setSheetAbilityScoreColumnsBySheetId(Collection<SheetAbilityScoreColumn> sheetAbilityScoreColumnsBySheetId) {
        this.sheetAbilityScoreColumnsBySheetId = sheetAbilityScoreColumnsBySheetId;
    }

    @OneToMany(mappedBy = "sheetBySheetId")
    public Collection<SheetSpecializedSkill> getSheetSpecializedSkillsBySheetId() {
        return sheetSpecializedSkillsBySheetId;
    }

    public void setSheetSpecializedSkillsBySheetId(Collection<SheetSpecializedSkill> sheetSpecializedSkillsBySheetId) {
        this.sheetSpecializedSkillsBySheetId = sheetSpecializedSkillsBySheetId;
    }
}
