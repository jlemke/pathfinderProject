/**
 * Created by Joe on 12/5/2016.
 */

var sheet;

$(document).ready(function() {
    var url = window.location.href;
    var id = url.substring(url.lastIndexOf("=") + 1);
    console.log("/sheet?id=" + id);
    $.get("/sheet", { "id" : id }, function(data) {
        console.log("data received");
        sheet = data;
        console.log(sheet);
        loadSheet();
    }, "json");
});

function loadSheet() {
    var sheetPage = $("#sheet");
    sheetPage.text(sheetPage);
    //loadMainInfo(sheetPage);
    $("#content").add(sheetPage);
}

function loadMainInfo(page) {
    var temp = $("<div id='characterName'>").set(sheet.characterName);
    page.add(temp);
    temp = $("<div id='characterRace'>").set(sheet.characterRace);
    page.add(temp);
}