package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_specialized_skills", schema = "pathfinderdb", catalog = "")
@IdClass(SheetSpecializedSkillPK.class)
public class SheetSpecializedSkill {
    private int sheetId;
    private int skillId;
    private String skillName;
    private String specialization;
    private String skillAbility;
    private int skillMisc;
    private int skillRanks;
    private boolean isClassSkill;
    private boolean reqTrained;
    private SheetSkill sheetSkill;
    private Sheet sheet;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @Column(name = "skill_id", nullable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "skill_name", nullable = false, length = 30)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Basic
    @Column(name = "specialization", nullable = true, length = 30)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Basic
    @Column(name = "skill_ability", nullable = false)
    public String getSkillAbility() {
        return skillAbility;
    }

    public void setSkillAbility(String skillAbility) {
        this.skillAbility = skillAbility;
    }

    @Basic
    @Column(name = "skill_misc", nullable = false)
    public int getSkillMisc() {
        return skillMisc;
    }

    public void setSkillMisc(int skillMisc) {
        this.skillMisc = skillMisc;
    }

    @Basic
    @Column(name = "skill_ranks", nullable = false)
    public int getSkillRanks() {
        return skillRanks;
    }

    public void setSkillRanks(int skillRanks) {
        this.skillRanks = skillRanks;
    }

    @Basic
    @Column(name = "is_class_skill", nullable = false)
    public boolean isClassSkill() {
        return isClassSkill;
    }

    public void setClassSkill(boolean classSkill) {
        isClassSkill = classSkill;
    }

    @Basic
    @Column(name = "req_trained", nullable = false)
    public boolean isReqTrained() {
        return reqTrained;
    }

    public void setReqTrained(boolean reqTrained) {
        this.reqTrained = reqTrained;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSpecializedSkill that = (SheetSpecializedSkill) o;

        if (sheetId != that.sheetId) return false;
        if (skillId != that.skillId) return false;
        if (skillMisc != that.skillMisc) return false;
        if (skillRanks != that.skillRanks) return false;
        if (isClassSkill != that.isClassSkill) return false;
        if (reqTrained != that.reqTrained) return false;
        if (skillName != null ? !skillName.equals(that.skillName) : that.skillName != null) return false;
        if (specialization != null ? !specialization.equals(that.specialization) : that.specialization != null)
            return false;
        if (skillAbility != null ? !skillAbility.equals(that.skillAbility) : that.skillAbility != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + skillId;
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        result = 31 * result + (skillAbility != null ? skillAbility.hashCode() : 0);
        result = 31 * result + skillMisc;
        result = 31 * result + skillRanks;
        result = 31 * result + (isClassSkill ? 1 : 0);
        result = 31 * result + (reqTrained ? 1 : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "skill_name", nullable = false, insertable = false, updatable = false)
    })
    public SheetSkill getSheetSkill() { return sheetSkill; }

    public void setSheetSkill(SheetSkill sheetSkill) { this.sheetSkill = sheetSkill; }

}
