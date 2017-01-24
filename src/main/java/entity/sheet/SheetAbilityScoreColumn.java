package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_ability_score_columns", schema = "pathfinderdb", catalog = "")
public class SheetAbilityScoreColumn implements Comparable<SheetAbilityScoreColumn> {
    private int sheetId;
    private int columnId;
    private String columnName = "";
    private int strRow = 0;
    private int dexRow = 0;
    private int conRow = 0;
    private int intRow = 0;
    private int wisRow = 0;
    private int chaRow = 0;
    private Sheet sheet;


    @Column(name = "sheet_id", nullable = false)
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "sheet"))
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }


    @Id
    @GeneratedValue
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
    @Column(name = "str_row", nullable = false)
    public int getStrRow() {
        return strRow;
    }

    public void setStrRow(int strRow) {
        this.strRow = strRow;
    }

    @Basic
    @Column(name = "dex_row", nullable = false)
    public int getDexRow() {
        return dexRow;
    }

    public void setDexRow(int dexRow) {
        this.dexRow = dexRow;
    }

    @Basic
    @Column(name = "con_row", nullable = false)
    public int getConRow() {
        return conRow;
    }

    public void setConRow(int conRow) {
        this.conRow = conRow;
    }

    @Basic
    @Column(name = "int_row", nullable = false)
    public int getIntRow() {
        return intRow;
    }

    public void setIntRow(int intRow) {
        this.intRow = intRow;
    }

    @Basic
    @Column(name = "wis_row", nullable = false)
    public int getWisRow() {
        return wisRow;
    }

    public void setWisRow(int wisRow) {
        this.wisRow = wisRow;
    }

    @Basic
    @Column(name = "cha_row", nullable = false)
    public int getChaRow() {
        return chaRow;
    }

    public void setChaRow(int chaRow) {
        this.chaRow = chaRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetAbilityScoreColumn that = (SheetAbilityScoreColumn) o;

        if (sheetId != that.sheetId) return false;
        if (columnId != that.columnId) return false;
        if (strRow != that.strRow) return false;
        if (dexRow != that.dexRow) return false;
        if (conRow != that.conRow) return false;
        if (intRow != that.intRow) return false;
        if (wisRow != that.wisRow) return false;
        if (chaRow != that.chaRow) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + columnId;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + strRow;
        result = 31 * result + dexRow;
        result = 31 * result + conRow;
        result = 31 * result + intRow;
        result = 31 * result + wisRow;
        result = 31 * result + chaRow;
        return result;
    }

    @Override
    public int compareTo(SheetAbilityScoreColumn other) {
        return this.columnId - other.columnId;
    }

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "sheet_id", insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

}
