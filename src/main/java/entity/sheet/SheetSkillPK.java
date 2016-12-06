package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/5/2016.
 */
public class SheetSkillPK implements Serializable {
    private int sheetId;
    private String skillName;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "skill_name", nullable = false, length = 30)
    @Id
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSkillPK that = (SheetSkillPK) o;

        if (sheetId != that.sheetId) return false;
        if (skillName != null ? !skillName.equals(that.skillName) : that.skillName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        return result;
    }
}
