package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_class_features", schema = "pathfinderdb", catalog = "")
@IdClass(SheetClassFeaturePK.class)
public class SheetClassFeature {
    private int sheetId;
    private String className;
    private int featureId;
    private String featureName;
    private String featureDescription;
    private SheetClass sheetClasses;
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
    @Column(name = "class_name", nullable = false, length = 25)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Id
    @Column(name = "feature_id", nullable = false)
    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    @Basic
    @Column(name = "feature_name", nullable = true, length = 30)
    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Basic
    @Column(name = "feature_description", nullable = true, length = -1)
    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetClassFeature that = (SheetClassFeature) o;

        if (sheetId != that.sheetId) return false;
        if (featureId != that.featureId) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (featureName != null ? !featureName.equals(that.featureName) : that.featureName != null) return false;
        if (featureDescription != null ? !featureDescription.equals(that.featureDescription) : that.featureDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + featureId;
        result = 31 * result + (featureName != null ? featureName.hashCode() : 0);
        result = 31 * result + (featureDescription != null ? featureDescription.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", nullable = false), @JoinColumn(name = "class_name", referencedColumnName = "class_name", nullable = false)})
    public SheetClass getSheetClasses() {
        return sheetClasses;
    }

    public void setSheetClasses(SheetClass sheetClasses) {
        this.sheetClasses = sheetClasses;
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
