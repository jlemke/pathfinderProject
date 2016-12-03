package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/3/2016.
 */
public class SheetWeaponPK implements Serializable {
    private int sheetId;
    private int weaponId;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "weapon_id", nullable = false)
    @Id
    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetWeaponPK that = (SheetWeaponPK) o;

        if (sheetId != that.sheetId) return false;
        if (weaponId != that.weaponId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + weaponId;
        return result;
    }
}
