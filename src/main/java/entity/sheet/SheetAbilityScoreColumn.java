package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_ability_score_columns", schema = "pathfinderdb", catalog = "")
@IdClass(SheetAbilityScoreColumnPK.class)
public class SheetAbilityScoreColumn {
    private int sheetId;
    private int columnId;
    private String columnName;
    private int strColumn;
    private int dexColumn;
    private int conColumn;
    private int intColumn;
    private int wisColumn;
    private int chaColumn;
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
    @Column(name = "column_id", nullable = false)
    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    @Basic
    @Column(name = "column_name", nullable = true, length = 30)
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Basic
    @Column(name = "str_column", nullable = false)
    public int getStrColumn() {
        return strColumn;
    }

    public void setStrColumn(int strColumn) {
        this.strColumn = strColumn;
    }

    @Basic
    @Column(name = "dex_column", nullable = false)
    public int getDexColumn() {
        return dexColumn;
    }

    public void setDexColumn(int dexColumn) {
        this.dexColumn = dexColumn;
    }

    @Basic
    @Column(name = "con_column", nullable = false)
    public int getConColumn() {
        return conColumn;
    }

    public void setConColumn(int conColumn) {
        this.conColumn = conColumn;
    }

    @Basic
    @Column(name = "int_column", nullable = false)
    public int getIntColumn() {
        return intColumn;
    }

    public void setIntColumn(int intColumn) {
        this.intColumn = intColumn;
    }

    @Basic
    @Column(name = "wis_column", nullable = false)
    public int getWisColumn() {
        return wisColumn;
    }

    public void setWisColumn(int wisColumn) {
        this.wisColumn = wisColumn;
    }

    @Basic
    @Column(name = "cha_column", nullable = false)
    public int getChaColumn() {
        return chaColumn;
    }

    public void setChaColumn(int chaColumn) {
        this.chaColumn = chaColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetAbilityScoreColumn that = (SheetAbilityScoreColumn) o;

        if (sheetId != that.sheetId) return false;
        if (columnId != that.columnId) return false;
        if (strColumn != that.strColumn) return false;
        if (dexColumn != that.dexColumn) return false;
        if (conColumn != that.conColumn) return false;
        if (intColumn != that.intColumn) return false;
        if (wisColumn != that.wisColumn) return false;
        if (chaColumn != that.chaColumn) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + columnId;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + strColumn;
        result = 31 * result + dexColumn;
        result = 31 * result + conColumn;
        result = 31 * result + intColumn;
        result = 31 * result + wisColumn;
        result = 31 * result + chaColumn;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

}
