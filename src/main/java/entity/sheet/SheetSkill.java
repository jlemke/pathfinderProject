package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_skills", schema = "pathfinderdb", catalog = "")
@IdClass(SheetSkillPK.class)
public class SheetSkill {
    private int sheetId;
    private String skillName;
    private String skillAbility;
    private int skillRanks;
    private boolean isClassSkill;
    private int skillMisc;
    private boolean reqTrained;
    private Collection<SheetSpecializedSkill> sheetSpecializedSkills;
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
    @Column(name = "skill_misc", nullable = false)
    public int getSkillMisc() {
        return skillMisc;
    }

    public void setSkillMisc(int skillMisc) {
        this.skillMisc = skillMisc;
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

        SheetSkill that = (SheetSkill) o;

        if (sheetId != that.sheetId) return false;
        if (skillRanks != that.skillRanks) return false;
        if (isClassSkill != that.isClassSkill) return false;
        if (skillMisc != that.skillMisc) return false;
        if (reqTrained != that.reqTrained) return false;
        if (skillName != null ? !skillName.equals(that.skillName) : that.skillName != null) return false;
        if (skillAbility != null ? !skillAbility.equals(that.skillAbility) : that.skillAbility != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        result = 31 * result + (skillAbility != null ? skillAbility.hashCode() : 0);
        result = 31 * result + skillRanks;
        result = 31 * result + (isClassSkill ? 1 : 0);
        result = 31 * result + skillMisc;
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

    @OneToMany(mappedBy = "sheetSkill")
    public Collection<SheetSpecializedSkill> getSheetSpecializedSkills() {
        return sheetSpecializedSkills;
    }

    public void setSheetSpecializedSkills(Collection<SheetSpecializedSkill> sheetSpecializedSkills) {
        this.sheetSpecializedSkills = sheetSpecializedSkills;
    }


}
