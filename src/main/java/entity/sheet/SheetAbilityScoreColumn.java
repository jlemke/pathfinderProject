package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_ability_score_columns", schema = "pathfinderdb", catalog = "")
@IdClass(SheetAbilityScoreColumnPK.class)
public class SheetAbilityScoreColumn {
    private int sheetId;
    private int columnId;
    private String columnName;
    private Integer strColumn;
    private Integer dexColumn;
    private Integer conColumn;
    private Integer intColumn;
    private Integer wisColumn;
    private Integer chaColumn;
    private Sheet sheetMainBySheetId;

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
    @Column(name = "str_column", nullable = true)
    public Integer getStrColumn() {
        return strColumn;
    }

    public void setStrColumn(Integer strColumn) {
        this.strColumn = strColumn;
    }

    @Basic
    @Column(name = "dex_column", nullable = true)
    public Integer getDexColumn() {
        return dexColumn;
    }

    public void setDexColumn(Integer dexColumn) {
        this.dexColumn = dexColumn;
    }

    @Basic
    @Column(name = "con_column", nullable = true)
    public Integer getConColumn() {
        return conColumn;
    }

    public void setConColumn(Integer conColumn) {
        this.conColumn = conColumn;
    }

    @Basic
    @Column(name = "int_column", nullable = true)
    public Integer getIntColumn() {
        return intColumn;
    }

    public void setIntColumn(Integer intColumn) {
        this.intColumn = intColumn;
    }

    @Basic
    @Column(name = "wis_column", nullable = true)
    public Integer getWisColumn() {
        return wisColumn;
    }

    public void setWisColumn(Integer wisColumn) {
        this.wisColumn = wisColumn;
    }

    @Basic
    @Column(name = "cha_column", nullable = true)
    public Integer getChaColumn() {
        return chaColumn;
    }

    public void setChaColumn(Integer chaColumn) {
        this.chaColumn = chaColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetAbilityScoreColumn that = (SheetAbilityScoreColumn) o;

        if (sheetId != that.sheetId) return false;
        if (columnId != that.columnId) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (strColumn != null ? !strColumn.equals(that.strColumn) : that.strColumn != null) return false;
        if (dexColumn != null ? !dexColumn.equals(that.dexColumn) : that.dexColumn != null) return false;
        if (conColumn != null ? !conColumn.equals(that.conColumn) : that.conColumn != null) return false;
        if (intColumn != null ? !intColumn.equals(that.intColumn) : that.intColumn != null) return false;
        if (wisColumn != null ? !wisColumn.equals(that.wisColumn) : that.wisColumn != null) return false;
        if (chaColumn != null ? !chaColumn.equals(that.chaColumn) : that.chaColumn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + columnId;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (strColumn != null ? strColumn.hashCode() : 0);
        result = 31 * result + (dexColumn != null ? dexColumn.hashCode() : 0);
        result = 31 * result + (conColumn != null ? conColumn.hashCode() : 0);
        result = 31 * result + (intColumn != null ? intColumn.hashCode() : 0);
        result = 31 * result + (wisColumn != null ? wisColumn.hashCode() : 0);
        result = 31 * result + (chaColumn != null ? chaColumn.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", nullable = false)
    public Sheet getSheetMainBySheetId() {
        return sheetMainBySheetId;
    }

    public void setSheetMainBySheetId(Sheet sheetMainBySheetId) {
        this.sheetMainBySheetId = sheetMainBySheetId;
    }
}
