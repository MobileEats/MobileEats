$( "#open" ).on( "click", function() {
    if( $( "input:checked" ).val() == "open"){
        console.log("We are open")
        $.ajax({
            type: 'POST',
            url: '/vendors/profile/{id}',
            data: {
                open: 1
            },
            success: function(text) {
                $(document.body).text('Response: ' + text);
            },
            error: function (jqXHR) {
                $(document.body).text('Error: ' + jqXHR.status);
            }
        });
    }
    else{
        console.log("Sorry we are closed")
    }

});