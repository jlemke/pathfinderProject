package entity.sheet;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_racial_traits", schema = "pathfinderdb", catalog = "")
@IdClass(SheetRacialTraitPK.class)
public class SheetRacialTrait {
    private int sheetId;
    private int traitId;
    private String traitName;
    private String traitDescription;
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
    @Column(name = "trait_id", nullable = false)
    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    @Basic
    @Column(name = "trait_name", nullable = true, length = 50)
    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    @Basic
    @Column(name = "trait_description", nullable = true, length = -1)
    public String getTraitDescription() {
        return traitDescription;
    }

    public void setTraitDescription(String traitDescription) {
        this.traitDescription = traitDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetRacialTrait that = (SheetRacialTrait) o;

        if (sheetId != that.sheetId) return false;
        if (traitId != that.traitId) return false;
        if (traitName != null ? !traitName.equals(that.traitName) : that.traitName != null) return false;
        if (traitDescription != null ? !traitDescription.equals(that.traitDescription) : that.traitDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + traitId;
        result = 31 * result + (traitName != null ? traitName.hashCode() : 0);
        result = 31 * result + (traitDescription != null ? traitDescription.hashCode() : 0);
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
