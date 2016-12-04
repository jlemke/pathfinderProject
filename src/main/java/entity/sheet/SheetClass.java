package entity.sheet;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_classes", schema = "pathfinderdb", catalog = "")
@IdClass(SheetClassPK.class)
public class SheetClass {
    private int sheetId;
    private String className;
    private String archetype;
    private Integer level;
    private Integer hitPoints;
    private Integer hitDie;
    private String babProgression;
    private String fortProgression;
    private String refProgression;
    private String willProgression;
    private Integer skillsPerLevel;
    private String casterAbility;
    private Sheet sheetBySheetId;
    private Collection<SheetSpell> sheetSpells;
    private Collection<SheetClassFeature> sheetClassFeatures;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @Column(name = "class_name", nullable = false, length = 25)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "archetype", nullable = true, length = 30)
    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "hit_points", nullable = true)
    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Basic
    @Column(name = "hit_die", nullable = true)
    public Integer getHitDie() {
        return hitDie;
    }

    public void setHitDie(Integer hitDie) {
        this.hitDie = hitDie;
    }

    @Basic
    @Column(name = "bab_progression", nullable = true)
    public String getBabProgression() {
        return babProgression;
    }

    public void setBabProgression(String babProgression) {
        this.babProgression = babProgression;
    }

    @Basic
    @Column(name = "fort_progression", nullable = true)
    public String getFortProgression() {
        return fortProgression;
    }

    public void setFortProgression(String fortProgression) {
        this.fortProgression = fortProgression;
    }

    @Basic
    @Column(name = "ref_progression", nullable = true)
    public String getRefProgression() {
        return refProgression;
    }

    public void setRefProgression(String refProgression) {
        this.refProgression = refProgression;
    }

    @Basic
    @Column(name = "will_progression", nullable = true)
    public String getWillProgression() {
        return willProgression;
    }

    public void setWillProgression(String willProgression) {
        this.willProgression = willProgression;
    }

    @Basic
    @Column(name = "skills_per_level", nullable = true)
    public Integer getSkillsPerLevel() {
        return skillsPerLevel;
    }

    public void setSkillsPerLevel(Integer skillsPerLevel) {
        this.skillsPerLevel = skillsPerLevel;
    }

    @Basic
    @Column(name = "caster_ability", nullable = true)
    public String getCasterAbility() {
        return casterAbility;
    }

    public void setCasterAbility(String casterAbility) {
        this.casterAbility = casterAbility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetClass that = (SheetClass) o;

        if (sheetId != that.sheetId) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (archetype != null ? !archetype.equals(that.archetype) : that.archetype != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (hitPoints != null ? !hitPoints.equals(that.hitPoints) : that.hitPoints != null) return false;
        if (hitDie != null ? !hitDie.equals(that.hitDie) : that.hitDie != null) return false;
        if (babProgression != null ? !babProgression.equals(that.babProgression) : that.babProgression != null)
            return false;
        if (fortProgression != null ? !fortProgression.equals(that.fortProgression) : that.fortProgression != null)
            return false;
        if (refProgression != null ? !refProgression.equals(that.refProgression) : that.refProgression != null)
            return false;
        if (willProgression != null ? !willProgression.equals(that.willProgression) : that.willProgression != null)
            return false;
        if (skillsPerLevel != null ? !skillsPerLevel.equals(that.skillsPerLevel) : that.skillsPerLevel != null)
            return false;
        if (casterAbility != null ? !casterAbility.equals(that.casterAbility) : that.casterAbility != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (archetype != null ? archetype.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (hitPoints != null ? hitPoints.hashCode() : 0);
        result = 31 * result + (hitDie != null ? hitDie.hashCode() : 0);
        result = 31 * result + (babProgression != null ? babProgression.hashCode() : 0);
        result = 31 * result + (fortProgression != null ? fortProgression.hashCode() : 0);
        result = 31 * result + (refProgression != null ? refProgression.hashCode() : 0);
        result = 31 * result + (willProgression != null ? willProgression.hashCode() : 0);
        result = 31 * result + (skillsPerLevel != null ? skillsPerLevel.hashCode() : 0);
        result = 31 * result + (casterAbility != null ? casterAbility.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", insertable = false, updatable = false)
    public Sheet getSheetBySheetId() {
        return sheetBySheetId;
    }

    public void setSheetBySheetId(Sheet sheetBySheetId) {
        this.sheetBySheetId = sheetBySheetId;
    }

    @OneToMany(mappedBy = "sheetClasses")
    public Collection<SheetSpell> getSheetSpells() {
        return sheetSpells;
    }

    public void setSheetSpells(Collection<SheetSpell> sheetSpells) {
        this.sheetSpells = sheetSpells;
    }

    @OneToMany(mappedBy = "sheetClasses")
    public Collection<SheetClassFeature> getSheetClassFeatures() {
        return sheetClassFeatures;
    }

    public void setSheetClassFeatures(Collection<SheetClassFeature> sheetClassFeatures) {
        this.sheetClassFeatures = sheetClassFeatures;
    }
}
