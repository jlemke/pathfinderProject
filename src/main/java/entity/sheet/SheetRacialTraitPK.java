package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/3/2016.
 */
public class SheetRacialTraitPK implements Serializable {
    private int sheetId;
    private int traitId;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "trait_id", nullable = false)
    @Id
    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetRacialTraitPK that = (SheetRacialTraitPK) o;

        if (sheetId != that.sheetId) return false;
        if (traitId != that.traitId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + traitId;
        return result;
    }
}
