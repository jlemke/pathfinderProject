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
    private int hpCurrent = 0;
    private int initMisc = 0;
    private int fortMisc = 0;
    private int refMisc = 0;
    private int willMisc = 0;
    private int favoredClassId = 0;
    private int fcHitPoints = 0;
    private int fcSkillRanks = 0;
    private int fcAlternateBonus = 0;
    private String fcAlternateBonusDesc = "None";
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

    @Basic
    @Column(name = "favored_class_id", nullable = false)
    public int getFavoredClassId() {
        return favoredClassId;
    }

    public void setFavoredClassId(int favoredClassId) {
        this.favoredClassId = favoredClassId;
    }

    @Basic
    @Column(name = "fc_hit_points", nullable = false)
    public int getFcHitPoints() {
        return fcHitPoints;
    }

    public void setFcHitPoints(int fcHitPoints) {
        this.fcHitPoints = fcHitPoints;
    }

    @Basic
    @Column(name = "fc_skill_ranks", nullable = false)
    public int getFcSkillRanks() {
        return fcSkillRanks;
    }

    public void setFcSkillRanks(int fcSkillRanks) {
        this.fcSkillRanks = fcSkillRanks;
    }

    @Basic
    @Column(name = "fc_alternate_bonus", nullable = false)
    public int getFcAlternateBonus() {
        return fcAlternateBonus;
    }

    public void setFcAlternateBonus(int fcAlternateBonus) {
        this.fcAlternateBonus = fcAlternateBonus;
    }

    @Basic
    @Column(name = "fc_alternate_bonus_desc", nullable = false)
    public String getFcAlternateBonusDesc() {
        return fcAlternateBonusDesc;
    }

    public void setFcAlternateBonusDesc(String fcAlternateBonusDesc) {
        this.fcAlternateBonusDesc = fcAlternateBonusDesc;
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
