package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_feats", schema = "pathfinderdb", catalog = "")
@IdClass(SheetFeatPK.class)
public class SheetFeat {
    private int sheetId;
    private int featId;
    private String featName;
    private String featDescription;
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
    @Column(name = "feat_id", nullable = false)
    public int getFeatId() {
        return featId;
    }

    public void setFeatId(int featId) {
        this.featId = featId;
    }

    @Basic
    @Column(name = "feat_name", nullable = false, length = 50)
    public String getFeatName() {
        return featName;
    }

    public void setFeatName(String featName) {
        this.featName = featName;
    }

    @Basic
    @Column(name = "feat_description", nullable = true, length = -1)
    public String getFeatDescription() {
        return featDescription;
    }

    public void setFeatDescription(String featDescription) {
        this.featDescription = featDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetFeat sheetFeat = (SheetFeat) o;

        if (sheetId != sheetFeat.sheetId) return false;
        if (featId != sheetFeat.featId) return false;
        if (featName != null ? !featName.equals(sheetFeat.featName) : sheetFeat.featName != null) return false;
        if (featDescription != null ? !featDescription.equals(sheetFeat.featDescription) : sheetFeat.featDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + featId;
        result = 31 * result + (featName != null ? featName.hashCode() : 0);
        result = 31 * result + (featDescription != null ? featDescription.hashCode() : 0);
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
