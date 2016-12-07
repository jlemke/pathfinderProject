/**
 * Created by Joe on 12/5/2016.
 */

var sheet;
var saveInProgress = false;

var app = angular.module('sheetApp', []);

app.controller('SheetController', function($scope) {
    sheet = this;
    sheet.data = {};

    this.loadSheet = function () {
        var url = window.location.href;
        var id = url.substring(url.lastIndexOf("=") + 1);
        console.log("/sheet?id=" + id);
        $.get("/sheet", {"id": id}, function (response) {
            console.log("data received");
            $scope.$apply(function () {
                sheet.data = response;
            });
            console.log(sheet.data);
            //initialize fields in fieldObjects.js, then create sheet
            //initializeFields();
            //createSheet();
        }, "json");
    }
});

$(document).ready(function() {
    sheet.loadSheet();
});

function saveSheet() {
    console.log("saving sheet " + sheet.data);
    saveInProgress = true;
    $.ajax({
        type: "POST",
        url: "/saveSheet",
        //contentType: "application/json",
        data: {"sheet" : JSON.stringify(sheet.data)},
        success: function(response) {
            saveInProgress = false;
            var message = $('<div id="alert">');
            if (response == "success")
                message.text("Sheet data saved");
            else
                message.text("Error: sheet data not saved");
            $('#messages').append(message);
            message.delay(1000).fadeOut(700);
        }
    });
}


function createSheet() {
    var sheetPage = $("<div class='sheet-page' id='sheetPage'>");
    console.log("created sheetPage");
    sheetPage.append(createMainInfo());
    sheetPage.prepend(createNavbar());
    sheetPage.append("<div>" + JSON.stringify(sheet) + "</div>");
    $("#content").append(sheetPage);
}

function createNavbar() {
    var navbar = $("<div class='fixedElement' id='navbar'>");

    var saveButton = $("<input type='button' value='Save' />");
    saveButton.click(function() {

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