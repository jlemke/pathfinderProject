package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_abilities", schema = "pathfinderdb", catalog = "")
@IdClass(SheetAbilityPK.class)
public class SheetAbility {
    private int sheetId;
    private int abilityId;
    private String abilityName;
    private String abilityDescription;
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
    @Column(name = "ability_id", nullable = false)
    public int getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(int abilityId) {
        this.abilityId = abilityId;
    }

    @Basic
    @Column(name = "ability_name", nullable = true, length = 50)
    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    @Basic
    @Column(name = "ability_description", nullable = true, length = -1)
    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetAbility that = (SheetAbility) o;

        if (sheetId != that.sheetId) return false;
        if (abilityId != that.abilityId) return false;
        if (abilityName != null ? !abilityName.equals(that.abilityName) : that.abilityName != null) return false;
        if (abilityDescription != null ? !abilityDescription.equals(that.abilityDescription) : that.abilityDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + abilityId;
        result = 31 * result + (abilityName != null ? abilityName.hashCode() : 0);
        result = 31 * result + (abilityDescription != null ? abilityDescription.hashCode() : 0);
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
