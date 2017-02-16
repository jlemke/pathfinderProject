package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_weapons", schema = "pathfinderdb", catalog = "")
public class SheetWeapon {
    private int sheetId;
    private int weaponId;
    private String weaponName;
    private boolean masterwork;
    private int enhancementBonus;
    private String damageRoll;
    private String criticalRange;
    private String criticalMultiplier;
    private String attackAbility;
    private String damageAbility;
    private int range;
    private boolean twoHand;
    private boolean bludgeoning;
    private boolean piercing;
    private boolean slashing;
    private int weight;
    private boolean proficient;
    private int worth;
    private Sheet sheet;

    @Column(name = "sheet_id", nullable = false)
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "sheet"))
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @GeneratedValue
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
    @Column(name = "masterwork", nullable = false)
    public boolean isMasterwork() {
        return masterwork;
    }

    public void setMasterwork(boolean masterwork) {
        this.masterwork = masterwork;
    }

    @Basic
    @Column(name = "enhancement_bonus", nullable = false)
    public int getEnhancementBonus() {
        return enhancementBonus;
    }

    public void setEnhancementBonus(int enhancementBonus) {
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
    @Column(name = "critical_range", nullable = false)
    public String getCriticalRange() {
        return criticalRange;
    }

    public void setCriticalRange(String criticalRange) {
        this.criticalRange = criticalRange;
    }

    @Basic
    @Column(name = "critical_multiplier", nullable = false)
    public String getCriticalMultiplier() {
        return criticalMultiplier;
    }

    public void setCriticalMultiplier(String criticalMultiplier) {
        this.criticalMultiplier = criticalMultiplier;
    }

    @Basic
    @Column(name = "attack_ability", nullable = false)
    public String getAttackAbility() {
        return attackAbility;
    }

    public void setAttackAbility(String attackAbility) {
        this.attackAbility = attackAbility;
    }

    @Basic
    @Column(name = "damage_ability", nullable = false)
    public String getDamageAbility() {
        return damageAbility;
    }

    public void setDamageAbility(String damageAbility) {
        this.damageAbility = damageAbility;
    }

    @Basic
    @Column(name = "weapon_range", nullable = false)
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Basic
    @Column(name = "two_hand", nullable = false)
    public boolean isTwoHand() {
        return twoHand;
    }

    public void setTwoHand(boolean twoHand) {
        this.twoHand = twoHand;
    }

    @Basic
    @Column(name = "bludgeoning", nullable = false)
    public boolean isBludgeoning() {
        return bludgeoning;
    }

    public void setBludgeoning(boolean bludgeoning) {
        this.bludgeoning = bludgeoning;
    }

    @Basic
    @Column(name = "piercing", nullable = false)
    public boolean isPiercing() {
        return piercing;
    }

    public void setPiercing(boolean piercing) {
        this.piercing = piercing;
    }

    @Basic
    @Column(name = "slashing", nullable = false)
    public boolean isSlashing() {
        return slashing;
    }

    public void setSlashing(boolean slashing) {
        this.slashing = slashing;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "proficient", nullable = false)
    public boolean isProficient() {
        return proficient;
    }

    public void setProficient(boolean proficient) {
        this.proficient = proficient;
    }

    @Basic
    @Column(name = "worth", nullable = false)
    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetWeapon that = (SheetWeapon) o;

        if (sheetId != that.sheetId) return false;
        if (weaponId != that.weaponId) return false;
        if (masterwork != that.masterwork) return false;
        if (enhancementBonus != that.enhancementBonus) return false;
        if (criticalRange != null ? !criticalRange.equals(that.criticalRange) : that.criticalRange != null) return false;
        if (criticalMultiplier != null ? !criticalMultiplier.equals(that.criticalMultiplier) : that.criticalMultiplier != null) return false;
        if (range != that.range) return false;
        if (twoHand != that.twoHand) return false;
        if (bludgeoning != that.bludgeoning) return false;
        if (piercing != that.piercing) return false;
        if (slashing != that.slashing) return false;
        if (weight != that.weight) return false;
        if (proficient != that.proficient) return false;
        if (worth != that.worth) return false;
        if (weaponName != null ? !weaponName.equals(that.weaponName) : that.weaponName != null) return false;
        if (damageRoll != null ? !damageRoll.equals(that.damageRoll) : that.damageRoll != null) return false;
        if (attackAbility != null ? !attackAbility.equals(that.attackAbility) : that.attackAbility != null)
            return false;
        if (damageAbility != null ? !damageAbility.equals(that.damageAbility) : that.damageAbility != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + weaponId;
        result = 31 * result + (weaponName != null ? weaponName.hashCode() : 0);
        result = 31 * result + (masterwork ? 1 : 0);
        result = 31 * result + enhancementBonus;
        result = 31 * result + (damageRoll != null ? damageRoll.hashCode() : 0);
        result = 31 * result + (criticalRange != null ? criticalRange.hashCode() : 0);
        result = 31 * result + (criticalMultiplier != null ? criticalMultiplier.hashCode() : 0);
        result = 31 * result + (attackAbility != null ? attackAbility.hashCode() : 0);
        result = 31 * result + (damageAbility != null ? damageAbility.hashCode() : 0);
        result = 31 * result + range;
        result = 31 * result + (twoHand ? 1 : 0);
        result = 31 * result + (bludgeoning ? 1 : 0);
        result = 31 * result + (piercing ? 1 : 0);
        result = 31 * result + (slashing ? 1 : 0);
        result = 31 * result + weight;
        result = 31 * result + (proficient ? 1 : 0);
        result = 31 * result + worth;
        return result;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

}
