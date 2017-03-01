package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.TreeSet;
import java.util.Set;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_classes", schema = "pathfinderdb", catalog = "")
public class SheetClass implements Comparable<SheetClass> {
    private int sheetId;
    private int classId;
    private String className;
    private String archetype = "";
    private int level = 1;
    private int hitPoints = 6;
    private int hitDie = 6;
    private String babProgression = "full";
    private String fortProgression = "slow";
    private String refProgression = "slow";
    private int skillsPerLevel = 0;
    private String willProgression = "slow'";
    private String casterAbility = "---";
    private String spellCap = "None";
    private String castingType = "None";
    private boolean preparedCaster = false;
    private int casterBonusMisc = 0;
    private String description;
    private Set<SheetSpell> sheetSpells = new TreeSet<>();
    private Set<SheetClassFeature> sheetClassFeatures = new TreeSet<>();
    private Sheet sheet;

    @Column(name = "sheet_id")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "sheetClass"))
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @GeneratedValue
    @Column(name = "class_id", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "class_name", nullable = true, length = 25)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "archetype", nullable = false, length = 30)
    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "hit_points", nullable = false)
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Basic
    @Column(name = "hit_die", nullable = false)
    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    @Basic
    @Column(name = "bab_progression", nullable = false)
    public String getBabProgression() {
        return babProgression;
    }

    public void setBabProgression(String babProgression) {
        this.babProgression = babProgression;
    }

    @Basic
    @Column(name = "fort_progression", nullable = false)
    public String getFortProgression() {
        return fortProgression;
    }

    public void setFortProgression(String fortProgression) {
        this.fortProgression = fortProgression;
    }

    @Basic
    @Column(name = "ref_progression", nullable = false)
    public String getRefProgression() {
        return refProgression;
    }

    public void setRefProgression(String refProgression) {
        this.refProgression = refProgression;
    }

    @Basic
    @Column(name = "skills_per_level", nullable = false)
    public int getSkillsPerLevel() {
        return skillsPerLevel;
    }

    public void setSkillsPerLevel(int skillsPerLevel) {
        this.skillsPerLevel = skillsPerLevel;
    }

    @Basic
    @Column(name = "will_progression", nullable = false)
    public String getWillProgression() {
        return willProgression;
    }

    public void setWillProgression(String willProgression) {
        this.willProgression = willProgression;
    }

    @Basic
    @Column(name = "caster_ability", nullable = false)
    public String getCasterAbility() {
        return casterAbility;
    }

    public void setCasterAbility(String casterAbility) {
        this.casterAbility = casterAbility;
    }

    @Basic
    @Column(name = "spell_cap", nullable = false)
    public String getSpellCap() {
        return spellCap;
    }

    public void setSpellCap(String spellCap) {
        this.spellCap = spellCap;
    }

    @Basic
    @Column(name = "casting_type", nullable = false)
    public String getCastingType() {
        return castingType;
    }

    public void setCastingType(String castingType) {
        this.castingType = castingType;
    }

    @Basic
    @Column(name = "prepared_caster", nullable = false)
    public boolean isPreparedCaster() {
        return preparedCaster;
    }

    public void setPreparedCaster(boolean preparedCaster) {
        this.preparedCaster = preparedCaster;
    }

    @Basic
    @Column(name = "caster_bonus_misc", nullable = false)
    public int getCasterBonusMisc() {
        return casterBonusMisc;
    }

    public void setCasterBonusMisc(int casterBonusMisc) {
        this.casterBonusMisc = casterBonusMisc;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetClass that = (SheetClass) o;

        if (sheetId != that.sheetId) return false;
        if (classId != that.classId) return false;
        if (level != that.level) return false;
        if (hitPoints != that.hitPoints) return false;
        if (hitDie != that.hitDie) return false;
        if (skillsPerLevel != that.skillsPerLevel) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (archetype != null ? !archetype.equals(that.archetype) : that.archetype != null) return false;
        if (babProgression != null ? !babProgression.equals(that.babProgression) : that.babProgression != null)
            return false;
        if (fortProgression != null ? !fortProgression.equals(that.fortProgression) : that.fortProgression != null)
            return false;
        if (refProgression != null ? !refProgression.equals(that.refProgression) : that.refProgression != null)
            return false;
        if (willProgression != null ? !willProgression.equals(that.willProgression) : that.willProgression != null)
            return false;
        if (casterAbility != null ? !casterAbility.equals(that.casterAbility) : that.casterAbility != null)
            return false;
        if (spellCap != null ? !spellCap.equals(that.spellCap) : that.spellCap != null)
            return false;
        if (castingType != null ? !castingType.equals(that.castingType) : that.castingType != null)
            return false;
        if (preparedCaster != that.preparedCaster) return false;
        if (casterBonusMisc != that.casterBonusMisc) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + sheetId;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (archetype != null ? archetype.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + hitPoints;
        result = 31 * result + hitDie;
        result = 31 * result + (babProgression != null ? babProgression.hashCode() : 0);
        result = 31 * result + (fortProgression != null ? fortProgression.hashCode() : 0);
        result = 31 * result + (refProgression != null ? refProgression.hashCode() : 0);
        result = 31 * result + skillsPerLevel;
        result = 31 * result + (willProgression != null ? willProgression.hashCode() : 0);
        result = 31 * result + (casterAbility != null ? casterAbility.hashCode() : 0);
        result = 31 * result + (spellCap != null ? spellCap.hashCode() : 0);
        result = 31 * result + (castingType != null ? castingType.hashCode() : 0);
        result = 31 * result + (preparedCaster ? 1 : 0);
        result = 31 * result + casterBonusMisc;
        result = 31 * result + (description != null ? className.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(SheetClass other) {
        return other.level - this.level;
    }


    @JsonManagedReference
    @OneToMany(mappedBy = "sheetClass", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<SheetSpell> getSheetSpells() {
        return sheetSpells;
    }

    public void setSheetSpells(Set<SheetSpell> sheetSpells) {
        this.sheetSpells = sheetSpells;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "sheetClass", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<SheetClassFeature> getSheetClassFeatures() {
        return sheetClassFeatures;
    }

    public void setSheetClassFeatures(Set<SheetClassFeature> sheetClassFeatures) {
        this.sheetClassFeatures = sheetClassFeatures;
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
