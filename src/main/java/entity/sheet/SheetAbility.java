package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_abilities", schema = "pathfinderdb", catalog = "")
public class SheetAbility {
    private int sheetId;
    private int abilityId;
    private String abilityName = "";
    private String abilityDescription = "";
    private String type = "feat";
    private String evalText = "";
    private int evalPriority = 0;
    private int activeLevel = 1;
    private boolean enabled = false;
    private Sheet sheet;

    @Column(name = "sheet_id", nullable = false)
    @GeneratedValue(generator="gen")
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

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "eval_text", nullable = true, length = -1)
    public String getEvalText() {
        return evalText;
    }

    public void setEvalText(String evalText) {
        this.evalText = evalText;
    }

    @Basic
    @Column(name = "eval_priority", nullable = true)
    public int getEvalPriority() {
        return evalPriority;
    }

    public void setEvalPriority(int evalPriority) {
        this.evalPriority = evalPriority;
    }

    @Basic
    @Column(name = "active_level", nullable = true)
    public int getActiveLevel() {
        return activeLevel;
    }

    public void setActiveLevel(int activeLevel) {
        this.activeLevel = activeLevel;
    }

    @Basic
    @Column(name = "enabled", nullable = true)
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetAbility that = (SheetAbility) o;

        if (sheetId != that.sheetId) return false;
        if (abilityId != that.abilityId) return false;
        if (abilityName != null ? !abilityName.equals(that.abilityName) : that.abilityName != null) return false;
        if (abilityDescription != null ? !abilityDescription.equals(that.abilityDescription) : that.abilityDescription != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (evalPriority != that.evalPriority) return false;
        if (activeLevel != that.activeLevel) return false;
        if (enabled != that.enabled) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + abilityId;
        result = 31 * result + (abilityName != null ? abilityName.hashCode() : 0);
        result = 31 * result + (abilityDescription != null ? abilityDescription.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (evalText != null ? evalText.hashCode() : 0);
        result = 31 * result + evalPriority;
        result = 31 * result + activeLevel;
        result = 31 * result + (enabled ? 1 : 0);
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
