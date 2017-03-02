/**
 * Created by Joe on 3/2/2017.
 */

var errors = [];

function signUp() {
    $.ajax({
        method : "POST",
        url : "createAccount",
        data : {
            "username" : $("#username").val(),
            "password1" : $("#password1").val(),
            "password2" : $("#password2").val(),
            "email" : $("#email").val()
        },
        success : function(response) {
            var url = $(location).attr("href");
            url = url.substring(0, url.lastIndexOf('/') + 1) + "signup-success.html";
            $(location).attr("href", url);
        },
        error : function(data) {
            errors = data.responseJSON;
            for (var i = 0; i < 4; i++)
                $("#errors" + i).html("");
            if (errors[0])
                $("#errors0").html("Username must be more than 5 characters.");
            else if (errors[1])
                $("#errors0").html("Username is taken.");
            if (errors[2])
                $("#errors1").html("Password must be more than 5 characters.");
            if (errors[3])
                $("#errors2").html("Passwords must match.");
            if (errors[4])
                $("#errors3").html("Email must be valid.");
        }
    });
}