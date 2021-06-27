$( "#open" ).on( "click", function() {
    if( $( "input:checked" ).val() == "open"){
        console.log("We are open")
    }
    else{
        console.log("Sorry we are closed")
    }

});