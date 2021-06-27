$( "input" ).on( "click", function() {
    $( "#log" ).html( $( "input:checked" ).val() + " is checked!" );
});