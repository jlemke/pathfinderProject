package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_spells", schema = "pathfinderdb", catalog = "")
public class SheetSpell {
    private int classId;
    private int spellId;
    private int spellLevel;
    private String spellName;
    private String school;
    private String subschool;
    private String domain;
    private String subdomain;
    private String bloodline;
    private String patron;
    private String spellDescription;
    private String target;
    private String range;
    private String castingTime;
    private boolean verbal;
    private boolean somatic;
    private boolean material;
    private boolean focus;
    private boolean divineFocus;
    private boolean prepared;
    private SheetClass sheetClass;

    @Column(name = "class_id", nullable = false, length = 25)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "sheetClass"))
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Id
    @GeneratedValue
    @Column(name = "spell_id", nullable = false)
    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }

    @Basic
    @Column(name = "spell_level", nullable = false)
    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    @Basic
    @Column(name = "spell_name", nullable = true, length = 50)
    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    @Basic
    @Column(name = "school", nullable = true, length = 20)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "subschool", nullable = true, length = 25)
    public String getSubschool() {
        return subschool;
    }

    public void setSubschool(String subschool) {
        this.subschool = subschool;
    }

    @Basic
    @Column(name = "domain", nullable = true, length = 20)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "subdomain", nullable = true, length = 30)
    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    @Basic
    @Column(name = "bloodline", nullable = true, length = 20)
    public String getBloodline() {
        return bloodline;
    }

    public void setBloodline(String bloodline) {
        this.bloodline = bloodline;
    }

    @Basic
    @Column(name = "patron", nullable = true, length = 15)
    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    @Basic
    @Column(name = "spell_description", nullable = true, length = -1)
    public String getSpellDescription() {
        return spellDescription;
    }

    public void setSpellDescription(String spellDescription) {
        this.spellDescription = spellDescription;
    }

    @Basic
    @Column(name = "target", nullable = true, length = 255)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "spell_range", nullable = true, length = 60)
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Basic
    @Column(name = "casting_time", nullable = true, length = 25)
    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    @Basic
    @Column(name = "verbal", nullable = false)
    public boolean isVerbal() {
        return verbal;
    }

    public void setVerbal(boolean verbal) {
        this.verbal = verbal;
    }

    @Basic
    @Column(name = "somatic", nullable = false)
    public boolean isSomatic() {
        return somatic;
    }

    public void setSomatic(boolean somatic) {
        this.somatic = somatic;
    }

    @Basic
    @Column(name = "material", nullable = false)
    public boolean isMaterial() {
        return material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    @Basic
    @Column(name = "focus", nullable = false)
    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    @Basic
    @Column(name = "divine_focus", nullable = false)
    public boolean isDivineFocus() {
        return divineFocus;
    }

    public void setDivineFocus(boolean divineFocus) {
        this.divineFocus = divineFocus;
    }

    @Basic
    @Column(name = "prepared", nullable = false)
    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSpell that = (SheetSpell) o;

        if (spellId != that.spellId) return false;
        if (spellLevel != that.spellLevel) return false;
        if (verbal != that.verbal) return false;
        if (somatic != that.somatic) return false;
        if (material != that.material) return false;
        if (focus != that.focus) return false;
        if (divineFocus != that.divineFocus) return false;
        if (prepared != that.prepared) return false;
        if (classId != that.classId) return false;
        if (spellName != null ? !spellName.equals(that.spellName) : that.spellName != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (subschool != null ? !subschool.equals(that.subschool) : that.subschool != null) return false;
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        if (subdomain != null ? !subdomain.equals(that.subdomain) : that.subdomain != null) return false;
        if (bloodline != null ? !bloodline.equals(that.bloodline) : that.bloodline != null) return false;
        if (patron != null ? !patron.equals(that.patron) : that.patron != null) return false;
        if (spellDescription != null ? !spellDescription.equals(that.spellDescription) : that.spellDescription != null)
            return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;
        if (range != null ? !range.equals(that.range) : that.range != null) return false;
        if (castingTime != null ? !castingTime.equals(that.castingTime) : that.castingTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + spellId;
        result = 31 * result + spellLevel;
        result = 31 * result + (spellName != null ? spellName.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (subschool != null ? subschool.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (subdomain != null ? subdomain.hashCode() : 0);
        result = 31 * result + (bloodline != null ? bloodline.hashCode() : 0);
        result = 31 * result + (patron != null ? patron.hashCode() : 0);
        result = 31 * result + (spellDescription != null ? spellDescription.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (castingTime != null ? castingTime.hashCode() : 0);
        result = 31 * result + (verbal ? 1 : 0);
        result = 31 * result + (somatic ? 1 : 0);
        result = 31 * result + (material ? 1 : 0);
        result = 31 * result + (focus ? 1 : 0);
        result = 31 * result + (divineFocus ? 1 : 0);
        result = 31 * result + (prepared ? 1 : 0);
        return result;
    }


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false, insertable = false, updatable = false)

    public SheetClass getSheetClass() { return sheetClass; }

    public void setSheetClass(SheetClass sheetClass) { this.sheetClass = sheetClass; }

}
