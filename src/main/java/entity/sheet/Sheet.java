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
    private Collection<SheetArmor> sheetArmorsesBySheetId;
    private Collection<SheetSkill> sheetSkillsesBySheetId;
    private SheetSpeeds sheetSpeedsBySheetId;
    private Collection<SheetAbility> sheetAbilitiesBySheetId;
    private Collection<SheetFeat> sheetFeatsesBySheetId;
    private Collection<SheetItem> sheetItemsesBySheetId;
    private SheetMoney sheetMoneyBySheetId;
    private Collection<SheetRacialTrait> sheetRacialTraitsesBySheetId;
    private Collection<SheetSpell> sheetSpellsesBySheetId;
    private Collection<SheetWeapon> sheetWeaponsesBySheetId;
    private Collection<SheetClassFeature> sheetClassFeaturesBySheetId;
    private Collection<SheetAbilityScoreColumn> sheetAbilityScoreColumnsesBySheetId;
    private Collection<SheetSpecializedSkill> sheetSpecializedSkillsesBySheetId;

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
    @JoinColumn(name = "owner", referencedColumnName = "username", nullable = false)
    public User getUsersByOwner() {
        return usersByOwner;
    }

    public void setUsersByOwner(User usersByOwner) {
        this.usersByOwner = usersByOwner;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetClass> getSheetClassesBySheetId() {
        return sheetClassesBySheetId;
    }

    public void setSheetClassesBySheetId(Collection<SheetClass> sheetClassesBySheetId) {
        this.sheetClassesBySheetId = sheetClassesBySheetId;
    }

    @OneToOne(mappedBy = "sheetMainBySheetId")
    public SheetDescription getSheetDescriptionBySheetId() {
        return sheetDescriptionBySheetId;
    }

    public void setSheetDescriptionBySheetId(SheetDescription sheetDescriptionBySheetId) {
        this.sheetDescriptionBySheetId = sheetDescriptionBySheetId;
    }

    @OneToOne(mappedBy = "sheetMainBySheetId")
    public SheetGeneral getSheetGeneralBySheetId() {
        return sheetGeneralBySheetId;
    }

    public void setSheetGeneralBySheetId(SheetGeneral sheetGeneralBySheetId) {
        this.sheetGeneralBySheetId = sheetGeneralBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetArmor> getSheetArmorsesBySheetId() {
        return sheetArmorsesBySheetId;
    }

    public void setSheetArmorsesBySheetId(Collection<SheetArmor> sheetArmorsesBySheetId) {
        this.sheetArmorsesBySheetId = sheetArmorsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetSkill> getSheetSkillsesBySheetId() {
        return sheetSkillsesBySheetId;
    }

    public void setSheetSkillsesBySheetId(Collection<SheetSkill> sheetSkillsesBySheetId) {
        this.sheetSkillsesBySheetId = sheetSkillsesBySheetId;
    }

    @OneToOne(mappedBy = "sheetMainBySheetId")
    public SheetSpeeds getSheetSpeedsBySheetId() {
        return sheetSpeedsBySheetId;
    }

    public void setSheetSpeedsBySheetId(SheetSpeeds sheetSpeedsBySheetId) {
        this.sheetSpeedsBySheetId = sheetSpeedsBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetAbility> getSheetAbilitiesBySheetId() {
        return sheetAbilitiesBySheetId;
    }

    public void setSheetAbilitiesBySheetId(Collection<SheetAbility> sheetAbilitiesBySheetId) {
        this.sheetAbilitiesBySheetId = sheetAbilitiesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetFeat> getSheetFeatsesBySheetId() {
        return sheetFeatsesBySheetId;
    }

    public void setSheetFeatsesBySheetId(Collection<SheetFeat> sheetFeatsesBySheetId) {
        this.sheetFeatsesBySheetId = sheetFeatsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetItem> getSheetItemsesBySheetId() {
        return sheetItemsesBySheetId;
    }

    public void setSheetItemsesBySheetId(Collection<SheetItem> sheetItemsesBySheetId) {
        this.sheetItemsesBySheetId = sheetItemsesBySheetId;
    }

    @OneToOne(mappedBy = "sheetMainBySheetId")
    public SheetMoney getSheetMoneyBySheetId() {
        return sheetMoneyBySheetId;
    }

    public void setSheetMoneyBySheetId(SheetMoney sheetMoneyBySheetId) {
        this.sheetMoneyBySheetId = sheetMoneyBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetRacialTrait> getSheetRacialTraitsesBySheetId() {
        return sheetRacialTraitsesBySheetId;
    }

    public void setSheetRacialTraitsesBySheetId(Collection<SheetRacialTrait> sheetRacialTraitsesBySheetId) {
        this.sheetRacialTraitsesBySheetId = sheetRacialTraitsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetSpell> getSheetSpellsesBySheetId() {
        return sheetSpellsesBySheetId;
    }

    public void setSheetSpellsesBySheetId(Collection<SheetSpell> sheetSpellsesBySheetId) {
        this.sheetSpellsesBySheetId = sheetSpellsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetWeapon> getSheetWeaponsesBySheetId() {
        return sheetWeaponsesBySheetId;
    }

    public void setSheetWeaponsesBySheetId(Collection<SheetWeapon> sheetWeaponsesBySheetId) {
        this.sheetWeaponsesBySheetId = sheetWeaponsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetClassFeature> getSheetClassFeaturesBySheetId() {
        return sheetClassFeaturesBySheetId;
    }

    public void setSheetClassFeaturesBySheetId(Collection<SheetClassFeature> sheetClassFeaturesBySheetId) {
        this.sheetClassFeaturesBySheetId = sheetClassFeaturesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetAbilityScoreColumn> getSheetAbilityScoreColumnsesBySheetId() {
        return sheetAbilityScoreColumnsesBySheetId;
    }

    public void setSheetAbilityScoreColumnsesBySheetId(Collection<SheetAbilityScoreColumn> sheetAbilityScoreColumnsesBySheetId) {
        this.sheetAbilityScoreColumnsesBySheetId = sheetAbilityScoreColumnsesBySheetId;
    }

    @OneToMany(mappedBy = "sheetMainBySheetId")
    public Collection<SheetSpecializedSkill> getSheetSpecializedSkillsesBySheetId() {
        return sheetSpecializedSkillsesBySheetId;
    }

    public void setSheetSpecializedSkillsesBySheetId(Collection<SheetSpecializedSkill> sheetSpecializedSkillsesBySheetId) {
        this.sheetSpecializedSkillsesBySheetId = sheetSpecializedSkillsesBySheetId;
    }
}
