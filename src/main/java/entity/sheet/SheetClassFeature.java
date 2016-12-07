package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_class_features", schema = "pathfinderdb", catalog = "")
public class SheetClassFeature {
    private int classId;
    private int featureId;
    private String featureName;
    private String featureDescription;
    private SheetClass sheetClass;

    @Column(name = "class_id", nullable = false, length = 25)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Id
    @GeneratedValue
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

        if (featureId != that.featureId) return false;
        if (classId != that.classId) return false;
        if (featureName != null ? !featureName.equals(that.featureName) : that.featureName != null) return false;
        if (featureDescription != null ? !featureDescription.equals(that.featureDescription) : that.featureDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + featureId;
        result = 31 * result + (featureName != null ? featureName.hashCode() : 0);
        result = 31 * result + (featureDescription != null ? featureDescription.hashCode() : 0);
        return result;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false, insertable = false, updatable = false)
    public SheetClass getSheetClass() { return sheetClass; }

    public void setSheetClass(SheetClass sheetClass) { this.sheetClass = sheetClass; }

}
