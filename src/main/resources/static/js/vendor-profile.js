
$(window).on("load", function() {

    //on reload it needs to check value of isOpen

});

$("#open").on("click", function () {
    openLocation();
});

function openLocation(){
    let id = $('.vendorId').val();
    let location = $('.address').html();
    let urlTest = "/profile";
    if( $("#open").prop("checked") == true){
        let data = {
            "open":true,
            "location": location,
            "id": id
        }
        $.ajax({
            type: 'POST',
            url: urlTest,///this url cannot have a concat
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (results) {

            }
            // error: function (jqXHR) {
            //     $(document.body).text('Error: ' + jqXHR.status);
            // }
        });
    } else {
        let data = {
            "open":false,
            "location": location,
            "id": id
        }
        $.ajax({
            type: 'POST',
            url: urlTest,///this url cannot have a concat
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (results) {

            }
            // error: function (jqXHR) {
            //     $(document.body).text('Error: ' + jqXHR.status);
            // }
        });
    }
}