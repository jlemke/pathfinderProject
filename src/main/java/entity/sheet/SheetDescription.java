package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_description", schema = "pathfinderdb", catalog = "")
public class SheetDescription {
    private int sheetId;
    private String gender;
    private String alignment;
    private String deity;
    private Integer age;
    private String height;
    private String weight;
    private String visualDescription;
    private String biography;
    private String languages;
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
    @Column(name = "gender", nullable = true, length = 20)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "alignment", nullable = true, length = 10)
    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    @Basic
    @Column(name = "deity", nullable = true, length = 50)
    public String getDeity() {
        return deity;
    }

    public void setDeity(String deity) {
        this.deity = deity;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "height", nullable = true, length = 25)
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Basic
    @Column(name = "weight", nullable = true, length = 25)
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "visual_description", nullable = true, length = -1)
    public String getVisualDescription() {
        return visualDescription;
    }

    public void setVisualDescription(String visualDescription) {
        this.visualDescription = visualDescription;
    }

    @Basic
    @Column(name = "biography", nullable = true, length = -1)
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Basic
    @Column(name = "languages", nullable = true, length = 255)
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetDescription that = (SheetDescription) o;

        if (sheetId != that.sheetId) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (alignment != null ? !alignment.equals(that.alignment) : that.alignment != null) return false;
        if (deity != null ? !deity.equals(that.deity) : that.deity != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (visualDescription != null ? !visualDescription.equals(that.visualDescription) : that.visualDescription != null)
            return false;
        if (biography != null ? !biography.equals(that.biography) : that.biography != null) return false;
        if (languages != null ? !languages.equals(that.languages) : that.languages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (alignment != null ? alignment.hashCode() : 0);
        result = 31 * result + (deity != null ? deity.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (visualDescription != null ? visualDescription.hashCode() : 0);
        result = 31 * result + (biography != null ? biography.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
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
