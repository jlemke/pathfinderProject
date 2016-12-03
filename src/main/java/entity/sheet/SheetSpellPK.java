package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/3/2016.
 */
public class SheetSpellPK implements Serializable {
    private int sheetId;
    private String className;
    private int spellId;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "class_name", nullable = false, length = 25)
    @Id
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "spell_id", nullable = false)
    @Id
    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSpellPK that = (SheetSpellPK) o;

        if (sheetId != that.sheetId) return false;
        if (spellId != that.spellId) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + spellId;
        return result;
    }
}
