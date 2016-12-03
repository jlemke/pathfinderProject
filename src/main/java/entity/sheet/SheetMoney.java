package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_money", schema = "pathfinderdb", catalog = "")
public class SheetMoney {
    private int sheetId;
    private Integer pp;
    private Integer gp;
    private Integer sp;
    private Integer cp;
    private Sheet sheetMainBySheetId;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Basic
    @Column(name = "pp", nullable = true)
    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    @Basic
    @Column(name = "gp", nullable = true)
    public Integer getGp() {
        return gp;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
    }

    @Basic
    @Column(name = "sp", nullable = true)
    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }

    @Basic
    @Column(name = "cp", nullable = true)
    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetMoney that = (SheetMoney) o;

        if (sheetId != that.sheetId) return false;
        if (pp != null ? !pp.equals(that.pp) : that.pp != null) return false;
        if (gp != null ? !gp.equals(that.gp) : that.gp != null) return false;
        if (sp != null ? !sp.equals(that.sp) : that.sp != null) return false;
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (pp != null ? pp.hashCode() : 0);
        result = 31 * result + (gp != null ? gp.hashCode() : 0);
        result = 31 * result + (sp != null ? sp.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", nullable = false)
    public Sheet getSheetMainBySheetId() {
        return sheetMainBySheetId;
    }

    public void setSheetMainBySheetId(Sheet sheetMainBySheetId) {
        this.sheetMainBySheetId = sheetMainBySheetId;
    }
}
