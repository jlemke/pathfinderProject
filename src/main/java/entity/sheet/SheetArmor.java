package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_armors", schema = "pathfinderdb", catalog = "")
public class SheetArmor {
    private int sheetId;
    private int armorId;
    private String armorName = "";
    private boolean masterwork = false;
    private int acBonus = 0;
    private int maxDexBonus = 0;
    private int skillPenalty = 0;
    private boolean equipped = false;
    private int spellFailureChance = 0;
    private int weight = 0;
    private boolean proficient = true;
    private String type = "";
    private int value = 0;
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
    @Column(name = "masterwork", nullable = false)
    public boolean isMasterwork() {
        return masterwork;
    }

    public void setMasterwork(boolean masterwork) {
        this.masterwork = masterwork;
    }

    @Basic
    @Column(name = "ac_bonus", nullable = false)
    public int getAcBonus() {
        return acBonus;
    }

    public void setAcBonus(int acBonus) {
        this.acBonus = acBonus;
    }

    @Basic
    @Column(name = "max_dex_bonus", nullable = false)
    public int getMaxDexBonus() {
        return maxDexBonus;
    }

    public void setMaxDexBonus(int maxDexBonus) {
        this.maxDexBonus = maxDexBonus;
    }

    @Basic
    @Column(name = "skill_penalty", nullable = false)
    public int getSkillPenalty() {
        return skillPenalty;
    }

    public void setSkillPenalty(int skillPenalty) {
        this.skillPenalty = skillPenalty;
    }

    @Basic
    @Column(name = "equipped", nullable = false)
    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    @Basic
    @Column(name = "spell_failure_chance", nullable = false)
    public int getSpellFailureChance() {
        return spellFailureChance;
    }

    public void setSpellFailureChance(int spellFailureChance) {
        this.spellFailureChance = spellFailureChance;
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
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "value", nullable = false)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetArmor that = (SheetArmor) o;

        if (sheetId != that.sheetId) return false;
        if (armorId != that.armorId) return false;
        if (masterwork != that.masterwork) return false;
        if (acBonus != that.acBonus) return false;
        if (skillPenalty != that.skillPenalty) return false;
        if (equipped != that.equipped) return false;
        if (spellFailureChance != that.spellFailureChance) return false;
        if (weight != that.weight) return false;
        if (proficient != that.proficient) return false;
        if (value != that.value) return false;
        if (armorName != null ? !armorName.equals(that.armorName) : that.armorName != null) return false;
        if (maxDexBonus != that.maxDexBonus) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + armorId;
        result = 31 * result + (armorName != null ? armorName.hashCode() : 0);
        result = 31 * result + (masterwork ? 1 : 0);
        result = 31 * result + acBonus;
        result = 31 * result + maxDexBonus;
        result = 31 * result + skillPenalty;
        result = 31 * result + (equipped ? 1 : 0);
        result = 31 * result + spellFailureChance;
        result = 31 * result + weight;
        result = 31 * result + (proficient ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + value;
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
