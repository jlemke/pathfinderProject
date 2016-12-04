package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_spells", schema = "pathfinderdb", catalog = "")
@IdClass(SheetSpellPK.class)
public class SheetSpell {
    private int sheetId;
    private String className;
    private int spellId;
    private Integer spellLevel;
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
    private Byte verbal;
    private Byte somatic;
    private Byte material;
    private Byte focus;
    private Byte divineFocus;
    private Byte prepared;
    private SheetClass sheetClasses;
    private Sheet sheetBySheetId;

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

    @Id
    @Column(name = "spell_id", nullable = false)
    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }

    @Basic
    @Column(name = "spell_level", nullable = true)
    public Integer getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(Integer spellLevel) {
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
    @Column(name = "range", nullable = true, length = 60)
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
    @Column(name = "verbal", nullable = true)
    public Byte getVerbal() {
        return verbal;
    }

    public void setVerbal(Byte verbal) {
        this.verbal = verbal;
    }

    @Basic
    @Column(name = "somatic", nullable = true)
    public Byte getSomatic() {
        return somatic;
    }

    public void setSomatic(Byte somatic) {
        this.somatic = somatic;
    }

    @Basic
    @Column(name = "material", nullable = true)
    public Byte getMaterial() {
        return material;
    }

    public void setMaterial(Byte material) {
        this.material = material;
    }

    @Basic
    @Column(name = "focus", nullable = true)
    public Byte getFocus() {
        return focus;
    }

    public void setFocus(Byte focus) {
        this.focus = focus;
    }

    @Basic
    @Column(name = "divine_focus", nullable = true)
    public Byte getDivineFocus() {
        return divineFocus;
    }

    public void setDivineFocus(Byte divineFocus) {
        this.divineFocus = divineFocus;
    }

    @Basic
    @Column(name = "prepared", nullable = true)
    public Byte getPrepared() {
        return prepared;
    }

    public void setPrepared(Byte prepared) {
        this.prepared = prepared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSpell that = (SheetSpell) o;

        if (sheetId != that.sheetId) return false;
        if (spellId != that.spellId) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (spellLevel != null ? !spellLevel.equals(that.spellLevel) : that.spellLevel != null) return false;
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
        if (verbal != null ? !verbal.equals(that.verbal) : that.verbal != null) return false;
        if (somatic != null ? !somatic.equals(that.somatic) : that.somatic != null) return false;
        if (material != null ? !material.equals(that.material) : that.material != null) return false;
        if (focus != null ? !focus.equals(that.focus) : that.focus != null) return false;
        if (divineFocus != null ? !divineFocus.equals(that.divineFocus) : that.divineFocus != null) return false;
        if (prepared != null ? !prepared.equals(that.prepared) : that.prepared != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + spellId;
        result = 31 * result + (spellLevel != null ? spellLevel.hashCode() : 0);
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
        result = 31 * result + (verbal != null ? verbal.hashCode() : 0);
        result = 31 * result + (somatic != null ? somatic.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (focus != null ? focus.hashCode() : 0);
        result = 31 * result + (divineFocus != null ? divineFocus.hashCode() : 0);
        result = 31 * result + (prepared != null ? prepared.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", insertable = false, updatable = false), @JoinColumn(name = "class_name", referencedColumnName = "class_name", insertable = false, updatable = false)})
    public SheetClass getSheetClasses() {
        return sheetClasses;
    }

    public void setSheetClasses(SheetClass sheetClasses) {
        this.sheetClasses = sheetClasses;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", insertable = false, updatable = false)
    public Sheet getSheetBySheetId() {
        return sheetBySheetId;
    }

    public void setSheetBySheetId(Sheet sheetBySheetId) {
        this.sheetBySheetId = sheetBySheetId;
    }
}
