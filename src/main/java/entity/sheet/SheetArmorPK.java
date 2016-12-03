package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/3/2016.
 */
public class SheetArmorPK implements Serializable {
    private int sheetId;
    private int armorId;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "armor_id", nullable = false)
    @Id
    public int getArmorId() {
        return armorId;
    }

    public void setArmorId(int armorId) {
        this.armorId = armorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetArmorPK that = (SheetArmorPK) o;

        if (sheetId != that.sheetId) return false;
        if (armorId != that.armorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + armorId;
        return result;
    }
}
