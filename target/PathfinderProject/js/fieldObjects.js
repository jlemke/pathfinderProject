/**
 * Created by Joe on 12/6/2016.
 */
/**
 *
 * Might be good idea to learn angular js for this
 *
 */

/**
 * fVal : value of this field
 * elementId : this is the id of the element input or div it should change and access
 *
 */
var Field = function(fVal, elementId, dependentFields, calculate()) {
    this.fVal = fVal;
    this.elementId = elementId;
    this.dependentFields = [];
    this.calculate = calculate();
}

Field.prototype.cascade = function() {
    for (var dependentField in this.dependentFields) dependentField.calculate();
};

Field.prototype.updateText = function() {

};

//sets the value of the Field based on int
function validateIntField() {
    var x = this.element.val();
    if(Math.floor(x) == x && $.isNumeric(x)) {
        this.fVal = this.element.val();
        this.cascade();
    } else this.element.val(this.fVal);
}

//initialize field objects then return to sheet.js and create the page elements
function initializeFields() {
    var abiColSize = sheet.sheetAbilityScoreColumns.length;
    var index = 0;
    sheet.sheetAbilityScoreColumns.forEach(function() {
        strRow.push(new Field(column.strRow, $("<input class='abiRow' id='strRow" + index + "' />"), [strScore], function ()));
        strRow[index].element.on("change", strRow[index].calculate());
        dexRow.push(new Field(column.dexRow));
        conRow.push(new Field(column.conRow));
        intRow.push(new Field(column.intRow));
        wisRow.push(new Field(column.wisRow));
        chaRow.push(new Field(column.chaRow));
        index++;
    });

    //after done go back to creating sheet in sheet.js -->
}

function createAbiRow(abi, colVal, index) {
    var field;
    abiString = abi + "Row";
    colElement = $("<input class='ability-row' type='text' />");
    colElement.attr("id", abiString + index);
    colElement.on("change", abiCols[abi][index].calculate());
    field = new Field(colVal, colElement);
    return field;
}

//object consisting of arrays of Field objects
var abiCols = {
    "str" : [],
    "dex" : [],
    "con" : [],
    "int" : [],
    "wis" : [],
    "cha" : []
};

//object consisting of Field objects
var abiScores = {
    "str" : 0,
    "dex" : 0,
    "con" : 0,
    "int" : 0,
    "wis" : 0,
    "cha" : 0
};

//object consisting of Field objects
var abiMods = {
    "str" : 0,
    "dex" : 0,
    "con" : 0,
    "int" : 0,
    "wis" : 0,
    "cha" : 0
}

conMod = new Field(0, $("<input >"), [fortSave, maxHealth], function() {
    this.value = 0;
    for

    this.cascade();
});

var maxHealth = new Field([], function() {

});