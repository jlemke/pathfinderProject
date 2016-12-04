package entity.sheet;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_skills", schema = "pathfinderdb", catalog = "")
public class SheetSkill {
    private int sheetId;
    private String skillName;
    private String skillAbility;
    private Integer skillRanks;
    private Integer skillMisc;
    private Byte isClassSkill;
    private Byte reqTrained;
    private Sheet sheetBySheetId;
    private Collection<SheetSpecializedSkill> sheetSpecializedSkills;

    @Basic
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @Column(name = "skill_name", nullable = false, length = 30)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Basic
    @Column(name = "skill_ability", nullable = true)
    public String getSkillAbility() {
        return skillAbility;
    }

    public void setSkillAbility(String skillAbility) {
        this.skillAbility = skillAbility;
    }

    @Basic
    @Column(name = "skill_ranks", nullable = true)
    public Integer getSkillRanks() {
        return skillRanks;
    }

    public void setSkillRanks(Integer skillRanks) {
        this.skillRanks = skillRanks;
    }

    @Basic
    @Column(name = "skill_misc", nullable = true)
    public Integer getSkillMisc() {
        return skillMisc;
    }

    public void setSkillMisc(Integer skillMisc) {
        this.skillMisc = skillMisc;
    }

    @Basic
    @Column(name = "is_class_skill", nullable = true)
    public Byte getIsClassSkill() {
        return isClassSkill;
    }

    public void setIsClassSkill(Byte isClassSkill) {
        this.isClassSkill = isClassSkill;
    }

    @Basic
    @Column(name = "req_trained", nullable = true)
    public Byte getReqTrained() {
        return reqTrained;
    }

    public void setReqTrained(Byte reqTrained) {
        this.reqTrained = reqTrained;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSkill that = (SheetSkill) o;

        if (sheetId != that.sheetId) return false;
        if (skillName != null ? !skillName.equals(that.skillName) : that.skillName != null) return false;
        if (skillAbility != null ? !skillAbility.equals(that.skillAbility) : that.skillAbility != null) return false;
        if (skillRanks != null ? !skillRanks.equals(that.skillRanks) : that.skillRanks != null) return false;
        if (skillMisc != null ? !skillMisc.equals(that.skillMisc) : that.skillMisc != null) return false;
        if (isClassSkill != null ? !isClassSkill.equals(that.isClassSkill) : that.isClassSkill != null) return false;
        if (reqTrained != null ? !reqTrained.equals(that.reqTrained) : that.reqTrained != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        result = 31 * result + (skillAbility != null ? skillAbility.hashCode() : 0);
        result = 31 * result + (skillRanks != null ? skillRanks.hashCode() : 0);
        result = 31 * result + (skillMisc != null ? skillMisc.hashCode() : 0);
        result = 31 * result + (isClassSkill != null ? isClassSkill.hashCode() : 0);
        result = 31 * result + (reqTrained != null ? reqTrained.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sheetSkill")
    public Collection<SheetSpecializedSkill> getSheetSpecializedSkills() {
        return sheetSpecializedSkills;
    }

    public void setSheetSpecializedSkills(Collection<SheetSpecializedSkill> sheetSpecializedSkills) {
        this.sheetSpecializedSkills = sheetSpecializedSkills;
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
