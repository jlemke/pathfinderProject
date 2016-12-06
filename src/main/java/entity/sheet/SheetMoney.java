package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_money", schema = "pathfinderdb", catalog = "")
public class SheetMoney {
    private int sheetId;
    private int pp;
    private int gp;
    private int sp;
    private int cp;
    private Sheet sheet;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Basic
    @Column(name = "pp", nullable = false)
    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    @Basic
    @Column(name = "gp", nullable = false)
    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    @Basic
    @Column(name = "sp", nullable = false)
    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    @Basic
    @Column(name = "cp", nullable = false)
    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetMoney that = (SheetMoney) o;

        if (sheetId != that.sheetId) return false;
        if (pp != that.pp) return false;
        if (gp != that.gp) return false;
        if (sp != that.sp) return false;
        if (cp != that.cp) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + pp;
        result = 31 * result + gp;
        result = 31 * result + sp;
        result = 31 * result + cp;
        return result;
    }

    @OneToOne
    @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

}
