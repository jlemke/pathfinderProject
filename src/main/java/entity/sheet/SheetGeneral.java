package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_general", schema = "pathfinderdb", catalog = "")
public class SheetGeneral {
    private int sheetId;
    private int hpCurrent;
    private int initMisc;
    private int fortMisc;
    private int refMisc;
    private int willMisc;
    private Sheet sheet;

    @Id
    @Column(name = "sheet_id", nullable = false)
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "sheet"))
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Basic
    @Column(name = "hp_current", nullable = false)
    public int getHpCurrent() {
        return hpCurrent;
    }

    public void setHpCurrent(int hpCurrent) {
        this.hpCurrent = hpCurrent;
    }

    @Basic
    @Column(name = "init_misc", nullable = false)
    public int getInitMisc() {
        return initMisc;
    }

    public void setInitMisc(int initMisc) {
        this.initMisc = initMisc;
    }

    @Basic
    @Column(name = "fort_misc", nullable = false)
    public int getFortMisc() {
        return fortMisc;
    }

    public void setFortMisc(int fortMisc) {
        this.fortMisc = fortMisc;
    }

    @Basic
    @Column(name = "ref_misc", nullable = false)
    public int getRefMisc() {
        return refMisc;
    }

    public void setRefMisc(int refMisc) {
        this.refMisc = refMisc;
    }

    @Basic
    @Column(name = "will_misc", nullable = false)
    public int getWillMisc() {
        return willMisc;
    }

    public void setWillMisc(int willMisc) {
        this.willMisc = willMisc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetGeneral that = (SheetGeneral) o;

        if (sheetId != that.sheetId) return false;
        if (hpCurrent != that.hpCurrent) return false;
        if (initMisc != that.initMisc) return false;
        if (fortMisc != that.fortMisc) return false;
        if (refMisc != that.refMisc) return false;
        if (willMisc != that.willMisc) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + hpCurrent;
        result = 31 * result + initMisc;
        result = 31 * result + fortMisc;
        result = 31 * result + refMisc;
        result = 31 * result + willMisc;
        return result;
    }

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

}
