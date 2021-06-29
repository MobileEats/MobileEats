$("#open").on("click", function () {
    openLocation();

});


function openLocation() {
    let id = $('.vendorId').val();
    let location = $('.address').html();
    console.log(location);
    let urlTest = "/vendors/profile/" + id;
    if ($("input:checked").val() == "open") {
        let data = {
            "open": true,
            "location": location
        }
        $.ajax({
            type: 'POST',
            url: urlTest,///this url cannot have a concat
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (results) {
                console.log(results);
            },
            // error: function (jqXHR) {
            //     $(document.body).text('Error: ' + jqXHR.status);
            // }
        });
    } else {
        let data = {
            "open": false,
            "location": location
        }
        $.ajax({
            type: 'POST',
            url: urlTest,///this url cannot have a concat
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (results) {
                console.log(results);
            },
            // error: function (jqXHR) {
            //     $(document.body).text('Error: ' + jqXHR.status);
            // }
        });
    }
}

<!--ADDING FOLLOW BUTTON FUNCTIONALITY-->
// function followButton() {
//     if ($("#follow").html() === "+ Follow") {
//         $("#follow").html("&#10003; Following")
//     } else {
//         if (confirm("Unfollow " + $("#follow").attr("vendor") + "?")) {
//             $("#follow").html("+ Follow");
//         }
//     }
// }

// function followButton() {
    if ($("#follow").attr("value") === "true") {
        $("#follow").html("&#10003; Following")
    } else {
        if ($("#follow").attr("value") === "false") {
            $("#follow").html("+ Follow");
        }
    }
// }

// $("#follow").on("click", function () {
//     followButton();
// });

