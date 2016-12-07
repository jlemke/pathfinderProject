/**
 * Created by Joe on 12/5/2016.
 */

var sheet;
var saveInProgress = false;

$(document).ready(function() {
    var url = window.location.href;
    var id = url.substring(url.lastIndexOf("=") + 1);
    console.log("/sheet?id=" + id);
    $.get("/sheet", { "id" : id }, function(data) {
        console.log("data received");
        sheet = data;
        console.log(sheet);
        createSheet();
    }, "json");
});

function saveSheet() {

}


function createSheet() {
    var sheetPage = $("<div class='sheet-page' id='sheetPage'>");
    console.log("created sheetPage");
    sheetPage.append(createMainInfo());
    sheetPage.prepend(createNavbar());
    $("#content").append(sheetPage);
}

function createNavbar() {
    var navbar = $("<div class='fixedElement' id='navbar'>");

    var saveButton = $("<input type='button' value='Save' />");
    saveButton.click(function() {
        console.log("saving sheet " + sheet);
        saveInProgress = true;
        $.post("/saveSheet", sheet, function(success) {
            saveInProgress = false;
            if (success == "success")
                console.log("sheet saved");
            else
                console.log("error occurred on sheet save");
        });
    });

    navbar.append(saveButton);
    return navbar;
}

function createMainInfo() {
    var mainInfo = $("<div class='main-info' id='mainInfo'>");

    console.log("adding " + sheet.characterName);
    var temp = $("<input class='centered string-field character-name' id='characterName' spellcheck='false'>").val(sheet.characterName);
    mainInfo.append(temp);

    temp = $("<input class='centered string-field character-name' id='characterRace' spellcheck='false'>").val(sheet.characterRace);
    mainInfo.append(temp);

    console.log("returning mainInfo");
    return mainInfo;
}