package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_speeds", schema = "pathfinderdb", catalog = "")
public class SheetSpeeds {
    private int sheetId;
    private int speedBase;
    private int speedFly;
    private int speedSwim;
    private int speedClimb;
    private int speedBurrow;
    private String calculateBy;
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
    @Column(name = "speed_base", nullable = false)
    public int getSpeedBase() {
        return speedBase;
    }

    public void setSpeedBase(int speedBase) {
        this.speedBase = speedBase;
    }

    @Basic
    @Column(name = "speed_fly", nullable = false)
    public int getSpeedFly() {
        return speedFly;
    }

    public void setSpeedFly(int speedFly) {
        this.speedFly = speedFly;
    }

    @Basic
    @Column(name = "speed_swim", nullable = false)
    public int getSpeedSwim() {
        return speedSwim;
    }

    public void setSpeedSwim(int speedSwim) {
        this.speedSwim = speedSwim;
    }

    @Basic
    @Column(name = "speed_climb", nullable = false)
    public int getSpeedClimb() {
        return speedClimb;
    }

    public void setSpeedClimb(int speedClimb) {
        this.speedClimb = speedClimb;
    }

    @Basic
    @Column(name = "speed_burrow", nullable = false)
    public int getSpeedBurrow() {
        return speedBurrow;
    }

    public void setSpeedBurrow(int speedBurrow) {
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
        if (speedBase != that.speedBase) return false;
        if (speedFly != that.speedFly) return false;
        if (speedSwim != that.speedSwim) return false;
        if (speedClimb != that.speedClimb) return false;
        if (speedBurrow != that.speedBurrow) return false;
        if (calculateBy != null ? !calculateBy.equals(that.calculateBy) : that.calculateBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + speedBase;
        result = 31 * result + speedFly;
        result = 31 * result + speedSwim;
        result = 31 * result + speedClimb;
        result = 31 * result + speedBurrow;
        result = 31 * result + (calculateBy != null ? calculateBy.hashCode() : 0);
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
