package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_armors", schema = "pathfinderdb", catalog = "")
@IdClass(SheetArmorPK.class)
public class SheetArmor {
    private int sheetId;
    private int armorId;
    private String armorName;
    private Byte masterwork;
    private Integer acBonus;
    private Integer maxDexBonus;
    private Integer skillPenalty;
    private String type;
    private Integer spellFailureChance;
    private Integer weight;
    private Byte proficient;
    private Byte equipped;
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
    @Column(name = "armor_id", nullable = false)
    public int getArmorId() {
        return armorId;
    }

    public void setArmorId(int armorId) {
        this.armorId = armorId;
    }

    @Basic
    @Column(name = "armor_name", nullable = true, length = 50)
    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
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
    @Column(name = "ac_bonus", nullable = true)
    public Integer getAcBonus() {
        return acBonus;
    }

    public void setAcBonus(Integer acBonus) {
        this.acBonus = acBonus;
    }

    @Basic
    @Column(name = "max_dex_bonus", nullable = true)
    public Integer getMaxDexBonus() {
        return maxDexBonus;
    }

    public void setMaxDexBonus(Integer maxDexBonus) {
        this.maxDexBonus = maxDexBonus;
    }

    @Basic
    @Column(name = "skill_penalty", nullable = true)
    public Integer getSkillPenalty() {
        return skillPenalty;
    }

    public void setSkillPenalty(Integer skillPenalty) {
        this.skillPenalty = skillPenalty;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "spell_failure_chance", nullable = true)
    public Integer getSpellFailureChance() {
        return spellFailureChance;
    }

    public void setSpellFailureChance(Integer spellFailureChance) {
        this.spellFailureChance = spellFailureChance;
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
    @Column(name = "equipped", nullable = true)
    public Byte getEquipped() {
        return equipped;
    }

    public void setEquipped(Byte equipped) {
        this.equipped = equipped;
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

        SheetArmor that = (SheetArmor) o;

        if (sheetId != that.sheetId) return false;
        if (armorId != that.armorId) return false;
        if (armorName != null ? !armorName.equals(that.armorName) : that.armorName != null) return false;
        if (masterwork != null ? !masterwork.equals(that.masterwork) : that.masterwork != null) return false;
        if (acBonus != null ? !acBonus.equals(that.acBonus) : that.acBonus != null) return false;
        if (maxDexBonus != null ? !maxDexBonus.equals(that.maxDexBonus) : that.maxDexBonus != null) return false;
        if (skillPenalty != null ? !skillPenalty.equals(that.skillPenalty) : that.skillPenalty != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (spellFailureChance != null ? !spellFailureChance.equals(that.spellFailureChance) : that.spellFailureChance != null)
            return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (proficient != null ? !proficient.equals(that.proficient) : that.proficient != null) return false;
        if (equipped != null ? !equipped.equals(that.equipped) : that.equipped != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + armorId;
        result = 31 * result + (armorName != null ? armorName.hashCode() : 0);
        result = 31 * result + (masterwork != null ? masterwork.hashCode() : 0);
        result = 31 * result + (acBonus != null ? acBonus.hashCode() : 0);
        result = 31 * result + (maxDexBonus != null ? maxDexBonus.hashCode() : 0);
        result = 31 * result + (skillPenalty != null ? skillPenalty.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (spellFailureChance != null ? spellFailureChance.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (proficient != null ? proficient.hashCode() : 0);
        result = 31 * result + (equipped != null ? equipped.hashCode() : 0);
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
