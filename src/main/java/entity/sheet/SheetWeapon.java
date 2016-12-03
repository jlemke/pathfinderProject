package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_weapons", schema = "pathfinderdb", catalog = "")
@IdClass(SheetWeaponPK.class)
public class SheetWeapon {
    private int sheetId;
    private int weaponId;
    private String weaponName;
    private Byte masterwork;
    private Integer enhancementBonus;
    private String damageRoll;
    private Integer criticalRange;
    private Integer criticalMultiplier;
    private String attackAbility;
    private String damageAbility;
    private Integer range;
    private Byte twoHand;
    private Byte bludgeoning;
    private Byte piercing;
    private Byte slashing;
    private Integer weight;
    private Byte proficient;
    private Integer value;
    private Sheet sheetMainBySheetId;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @Column(name = "weapon_id", nullable = false)
    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    @Basic
    @Column(name = "weapon_name", nullable = true, length = 50)
    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    @Basic
    @Column(name = "masterwork", nullable = true)
    public Byte getMasterwork() {
        return masterwork;
    }

    public void setMasterwork(Byte masterwork) {
        this.masterwork = masterwork;
    }

    @Basic
    @Column(name = "enhancement_bonus", nullable = true)
    public Integer getEnhancementBonus() {
        return enhancementBonus;
    }

    public void setEnhancementBonus(Integer enhancementBonus) {
        this.enhancementBonus = enhancementBonus;
    }

    @Basic
    @Column(name = "damage_roll", nullable = true, length = 10)
    public String getDamageRoll() {
        return damageRoll;
    }

    public void setDamageRoll(String damageRoll) {
        this.damageRoll = damageRoll;
    }

    @Basic
    @Column(name = "critical_range", nullable = true)
    public Integer getCriticalRange() {
        return criticalRange;
    }

    public void setCriticalRange(Integer criticalRange) {
        this.criticalRange = criticalRange;
    }

    @Basic
    @Column(name = "critical_multiplier", nullable = true)
    public Integer getCriticalMultiplier() {
        return criticalMultiplier;
    }

    public void setCriticalMultiplier(Integer criticalMultiplier) {
        this.criticalMultiplier = criticalMultiplier;
    }

    @Basic
    @Column(name = "attack_ability", nullable = true)
    public String getAttackAbility() {
        return attackAbility;
    }

    public void setAttackAbility(String attackAbility) {
        this.attackAbility = attackAbility;
    }

    @Basic
    @Column(name = "damage_ability", nullable = true)
    public String getDamageAbility() {
        return damageAbility;
    }

    public void setDamageAbility(String damageAbility) {
        this.damageAbility = damageAbility;
    }

    @Basic
    @Column(name = "range", nullable = true)
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Basic
    @Column(name = "two_hand", nullable = true)
    public Byte getTwoHand() {
        return twoHand;
    }

    public void setTwoHand(Byte twoHand) {
        this.twoHand = twoHand;
    }

    @Basic
    @Column(name = "bludgeoning", nullable = true)
    public Byte getBludgeoning() {
        return bludgeoning;
    }

    public void setBludgeoning(Byte bludgeoning) {
        this.bludgeoning = bludgeoning;
    }

    @Basic
    @Column(name = "piercing", nullable = true)
    public Byte getPiercing() {
        return piercing;
    }

    public void setPiercing(Byte piercing) {
        this.piercing = piercing;
    }

    @Basic
    @Column(name = "slashing", nullable = true)
    public Byte getSlashing() {
        return slashing;
    }

    public void setSlashing(Byte slashing) {
        this.slashing = slashing;
    }

    @Basic
    @Column(name = "weight", nullable = true)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "proficient", nullable = true)
    public Byte getProficient() {
        return proficient;
    }

    public void setProficient(Byte proficient) {
        this.proficient = proficient;
    }

    @Basic
    @Column(name = "value", nullable = true)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetWeapon that = (SheetWeapon) o;

        if (sheetId != that.sheetId) return false;
        if (weaponId != that.weaponId) return false;
        if (weaponName != null ? !weaponName.equals(that.weaponName) : that.weaponName != null) return false;
        if (masterwork != null ? !masterwork.equals(that.masterwork) : that.masterwork != null) return false;
        if (enhancementBonus != null ? !enhancementBonus.equals(that.enhancementBonus) : that.enhancementBonus != null)
            return false;
        if (damageRoll != null ? !damageRoll.equals(that.damageRoll) : that.damageRoll != null) return false;
        if (criticalRange != null ? !criticalRange.equals(that.criticalRange) : that.criticalRange != null)
            return false;
        if (criticalMultiplier != null ? !criticalMultiplier.equals(that.criticalMultiplier) : that.criticalMultiplier != null)
            return false;
        if (attackAbility != null ? !attackAbility.equals(that.attackAbility) : that.attackAbility != null)
            return false;
        if (damageAbility != null ? !damageAbility.equals(that.damageAbility) : that.damageAbility != null)
            return false;
        if (range != null ? !range.equals(that.range) : that.range != null) return false;
        if (twoHand != null ? !twoHand.equals(that.twoHand) : that.twoHand != null) return false;
        if (bludgeoning != null ? !bludgeoning.equals(that.bludgeoning) : that.bludgeoning != null) return false;
        if (piercing != null ? !piercing.equals(that.piercing) : that.piercing != null) return false;
        if (slashing != null ? !slashing.equals(that.slashing) : that.slashing != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (proficient != null ? !proficient.equals(that.proficient) : that.proficient != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + weaponId;
        result = 31 * result + (weaponName != null ? weaponName.hashCode() : 0);
        result = 31 * result + (masterwork != null ? masterwork.hashCode() : 0);
        result = 31 * result + (enhancementBonus != null ? enhancementBonus.hashCode() : 0);
        result = 31 * result + (damageRoll != null ? damageRoll.hashCode() : 0);
        result = 31 * result + (criticalRange != null ? criticalRange.hashCode() : 0);
        result = 31 * result + (criticalMultiplier != null ? criticalMultiplier.hashCode() : 0);
        result = 31 * result + (attackAbility != null ? attackAbility.hashCode() : 0);
        result = 31 * result + (damageAbility != null ? damageAbility.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (twoHand != null ? twoHand.hashCode() : 0);
        result = 31 * result + (bludgeoning != null ? bludgeoning.hashCode() : 0);
        result = 31 * result + (piercing != null ? piercing.hashCode() : 0);
        result = 31 * result + (slashing != null ? slashing.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (proficient != null ? proficient.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", nullable = false)
    public Sheet getSheetMainBySheetId() {
        return sheetMainBySheetId;
    }

    public void setSheetMainBySheetId(Sheet sheetMainBySheetId) {
        this.sheetMainBySheetId = sheetMainBySheetId;
    }
}
