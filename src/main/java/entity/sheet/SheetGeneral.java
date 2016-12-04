package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_general", schema = "pathfinderdb", catalog = "")
public class SheetGeneral {
    private int sheetId;
    private Integer hpCurrent;
    private Integer initMisc;
    private Integer fortMisc;
    private Integer refMisc;
    private Integer willMisc;
    private Sheet sheetBySheetId;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Basic
    @Column(name = "hp_current", nullable = true)
    public Integer getHpCurrent() {
        return hpCurrent;
    }

    public void setHpCurrent(Integer hpCurrent) {
        this.hpCurrent = hpCurrent;
    }

    @Basic
    @Column(name = "init_misc", nullable = true)
    public Integer getInitMisc() {
        return initMisc;
    }

    public void setInitMisc(Integer initMisc) {
        this.initMisc = initMisc;
    }

    @Basic
    @Column(name = "fort_misc", nullable = true)
    public Integer getFortMisc() {
        return fortMisc;
    }

    public void setFortMisc(Integer fortMisc) {
        this.fortMisc = fortMisc;
    }

    @Basic
    @Column(name = "ref_misc", nullable = true)
    public Integer getRefMisc() {
        return refMisc;
    }

    public void setRefMisc(Integer refMisc) {
        this.refMisc = refMisc;
    }

    @Basic
    @Column(name = "will_misc", nullable = true)
    public Integer getWillMisc() {
        return willMisc;
    }

    public void setWillMisc(Integer willMisc) {
        this.willMisc = willMisc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetGeneral that = (SheetGeneral) o;

        if (sheetId != that.sheetId) return false;
        if (hpCurrent != null ? !hpCurrent.equals(that.hpCurrent) : that.hpCurrent != null) return false;
        if (initMisc != null ? !initMisc.equals(that.initMisc) : that.initMisc != null) return false;
        if (fortMisc != null ? !fortMisc.equals(that.fortMisc) : that.fortMisc != null) return false;
        if (refMisc != null ? !refMisc.equals(that.refMisc) : that.refMisc != null) return false;
        if (willMisc != null ? !willMisc.equals(that.willMisc) : that.willMisc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (hpCurrent != null ? hpCurrent.hashCode() : 0);
        result = 31 * result + (initMisc != null ? initMisc.hashCode() : 0);
        result = 31 * result + (fortMisc != null ? fortMisc.hashCode() : 0);
        result = 31 * result + (refMisc != null ? refMisc.hashCode() : 0);
        result = 31 * result + (willMisc != null ? willMisc.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", nullable = false)
    public Sheet getSheetBySheetId() {
        return sheetBySheetId;
    }

    public void setSheetBySheetId(Sheet sheetBySheetId) {
        this.sheetBySheetId = sheetBySheetId;
    }
}
