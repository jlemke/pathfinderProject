/**
 * Created by Joe on 12/5/2016.
 */
$(document).ready(function() {

    //$(".sheet-row").attr("href", "/sheet?id=" + $(this).attr("sheet-id"));
    $(".sheet-row").on("click", function() {
        var url = window.location.href;
        var page = url.substring(0, url.lastIndexOf('/') + 1) + "sheet.html?id=" + $(this).attr("sheet-id");
        window.location = page;
    });
});