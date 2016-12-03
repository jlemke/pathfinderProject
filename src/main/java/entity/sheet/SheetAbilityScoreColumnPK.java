package entity.sheet;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Joe on 12/3/2016.
 */
public class SheetAbilityScoreColumnPK implements Serializable {
    private int sheetId;
    private int columnId;

    @Column(name = "sheet_id", nullable = false)
    @Id
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Column(name = "column_id", nullable = false)
    @Id
    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetAbilityScoreColumnPK that = (SheetAbilityScoreColumnPK) o;

        if (sheetId != that.sheetId) return false;
        if (columnId != that.columnId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + columnId;
        return result;
    }
}
