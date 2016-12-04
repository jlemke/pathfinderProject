package entity.sheet;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "sheet_items", schema = "pathfinderdb", catalog = "")
@IdClass(SheetItemPK.class)
public class SheetItem {
    private int sheetId;
    private int itemId;
    private String itemName;
    private String itemDescription;
    private Integer itemQuantity;
    private Integer unitWeight;
    private Integer unitValue;
    private Sheet sheetBySheetId;

    @Id
    @Column(name = "sheet_id", nullable = false)
    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    @Id
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
    @Column(name = "item_quantity", nullable = true)
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Basic
    @Column(name = "unit_weight", nullable = true)
    public Integer getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(Integer unitWeight) {
        this.unitWeight = unitWeight;
    }

    @Basic
    @Column(name = "unit_value", nullable = true)
    public Integer getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Integer unitValue) {
        this.unitValue = unitValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheetItem sheetItem = (SheetItem) o;

        if (sheetId != sheetItem.sheetId) return false;
        if (itemId != sheetItem.itemId) return false;
        if (itemName != null ? !itemName.equals(sheetItem.itemName) : sheetItem.itemName != null) return false;
        if (itemDescription != null ? !itemDescription.equals(sheetItem.itemDescription) : sheetItem.itemDescription != null)
            return false;
        if (itemQuantity != null ? !itemQuantity.equals(sheetItem.itemQuantity) : sheetItem.itemQuantity != null)
            return false;
        if (unitWeight != null ? !unitWeight.equals(sheetItem.unitWeight) : sheetItem.unitWeight != null) return false;
        if (unitValue != null ? !unitValue.equals(sheetItem.unitValue) : sheetItem.unitValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sheetId;
        result = 31 * result + itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemDescription != null ? itemDescription.hashCode() : 0);
        result = 31 * result + (itemQuantity != null ? itemQuantity.hashCode() : 0);
        result = 31 * result + (unitWeight != null ? unitWeight.hashCode() : 0);
        result = 31 * result + (unitValue != null ? unitValue.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", insertable = false, updatable = false)
    public Sheet getSheetBySheetId() {
        return sheetBySheetId;
    }

    public void setSheetBySheetId(Sheet sheetBySheetId) {
        this.sheetBySheetId = sheetBySheetId;
    }
}
