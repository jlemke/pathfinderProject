package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_speeds", schema = "pathfinderdb", catalog = "")
public class SheetSpeeds {
    private int sheetId;
    private Integer speedBase;
    private Integer speedFly;
    private Integer speedSwim;
    private Integer speedClimb;
    private Integer speedBurrow;
    private String calculateBy;
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
    @Column(name = "speed_base", nullable = true)
    public Integer getSpeedBase() {
        return speedBase;
    }

    public void setSpeedBase(Integer speedBase) {
        this.speedBase = speedBase;
    }

    @Basic
    @Column(name = "speed_fly", nullable = true)
    public Integer getSpeedFly() {
        return speedFly;
    }

    public void setSpeedFly(Integer speedFly) {
        this.speedFly = speedFly;
    }

    @Basic
    @Column(name = "speed_swim", nullable = true)
    public Integer getSpeedSwim() {
        return speedSwim;
    }

    public void setSpeedSwim(Integer speedSwim) {
        this.speedSwim = speedSwim;
    }

    @Basic
    @Column(name = "speed_climb", nullable = true)
    public Integer getSpeedClimb() {
        return speedClimb;
    }

    public void setSpeedClimb(Integer speedClimb) {
        this.speedClimb = speedClimb;
    }

    @Basic
    @Column(name = "speed_burrow", nullable = true)
    public Integer getSpeedBurrow() {
        return speedBurrow;
    }

    public void setSpeedBurrow(Integer speedBurrow) {
        this.speedBurrow = speedBurrow;
    }

    @Basic
    @Column(name = "calculate_by", nullable = true, length = 50)
    public String getCalculateBy() {
        return calculateBy;
    }

    public void setCalculateBy(String calculateBy) {
        this.calculateBy = calculateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetSpeeds that = (SheetSpeeds) o;

        if (sheetId != that.sheetId) return false;
        if (speedBase != null ? !speedBase.equals(that.speedBase) : that.speedBase != null) return false;
        if (speedFly != null ? !speedFly.equals(that.speedFly) : that.speedFly != null) return false;
        if (speedSwim != null ? !speedSwim.equals(that.speedSwim) : that.speedSwim != null) return false;
        if (speedClimb != null ? !speedClimb.equals(that.speedClimb) : that.speedClimb != null) return false;
        if (speedBurrow != null ? !speedBurrow.equals(that.speedBurrow) : that.speedBurrow != null) return false;
        if (calculateBy != null ? !calculateBy.equals(that.calculateBy) : that.calculateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (speedBase != null ? speedBase.hashCode() : 0);
        result = 31 * result + (speedFly != null ? speedFly.hashCode() : 0);
        result = 31 * result + (speedSwim != null ? speedSwim.hashCode() : 0);
        result = 31 * result + (speedClimb != null ? speedClimb.hashCode() : 0);
        result = 31 * result + (speedBurrow != null ? speedBurrow.hashCode() : 0);
        result = 31 * result + (calculateBy != null ? calculateBy.hashCode() : 0);
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
