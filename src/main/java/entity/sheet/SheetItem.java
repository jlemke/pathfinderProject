package entity.sheet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "sheet_items", schema = "pathfinderdb", catalog = "")
public class SheetItem {
    private int sheetId;
    private int itemId;
    private String itemName;
    private String itemDescription;
    private int itemQuantity;
    private int unitWeight;
    private int unitValue;
    private Sheet sheet;

    @Column(name = "sheet_id", nullable = false)
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "sheet"))
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
    @GeneratedValue
    @Column(name = "item_id", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_name", nullable = true, length = 50)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "item_description", nullable = true, length = -1)
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Basic
    @Column(name = "item_quantity", nullable = false)
    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Basic
    @Column(name = "unit_weight", nullable = false)
    public int getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(int unitWeight) {
        this.unitWeight = unitWeight;
    }

    @Basic
    @Column(name = "unit_value", nullable = false)
    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetItem sheetItem = (SheetItem) o;

        if (sheetId != sheetItem.sheetId) return false;
        if (itemId != sheetItem.itemId) return false;
        if (itemQuantity != sheetItem.itemQuantity) return false;
        if (unitWeight != sheetItem.unitWeight) return false;
        if (unitValue != sheetItem.unitValue) return false;
        if (itemName != null ? !itemName.equals(sheetItem.itemName) : sheetItem.itemName != null) return false;
        if (itemDescription != null ? !itemDescription.equals(sheetItem.itemDescription) : sheetItem.itemDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemDescription != null ? itemDescription.hashCode() : 0);
        result = 31 * result + itemQuantity;
        result = 31 * result + unitWeight;
        result = 31 * result + unitValue;
        return result;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sheet_id", nullable = false, insertable = false, updatable = false)
    public Sheet getSheet() { return sheet; }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
        this.sheetId = sheet.getSheetId();
    }

}
