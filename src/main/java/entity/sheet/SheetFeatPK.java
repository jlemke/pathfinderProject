package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/3/2016.
 */
public class SheetFeatPK implements Serializable {
    private int sheetId;
    private int featId;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "feat_id", nullable = false)
    @Id
    public int getFeatId() {
        return featId;
    }

    public void setFeatId(int featId) {
        this.featId = featId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetFeatPK that = (SheetFeatPK) o;

        if (sheetId != that.sheetId) return false;
        if (featId != that.featId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + featId;
        return result;
    }
}
