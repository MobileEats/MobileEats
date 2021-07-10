"use strict";

var modalAddress;

var options = {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 0
};

// ************* DISPLAY MAP ON LOAD - Default to San Antonio ******************
var coord = [29.4241, -98.4936]; // lat[0] long[1] standard format
mapboxgl.accessToken = MAPBOX_API_KEY;
var mapOptions = {
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11',
    center: [coord[1], coord[0]], // San Antonio
    zoom: 9.7// captures all of 1604 Loop
}
var map = new mapboxgl.Map(mapOptions);
var marker = new mapboxgl.Marker({color: "blue", draggable: true})
var markerLocation= new mapboxgl.Marker({color: "red", draggable: true});
map.on('load', event => {
    map.resize()
})

//*********** FIND LAT LONG FROM ADDRESS SAVED ON DATABASE OR SEARCHED **************
$(document).ready(function () {
    plotVendors();
    $("#modalAddress").on('click', function (){
        modalAddress = $('#address').val();
        searchAddress(modalAddress, 10);

    });
    $("#modalLocate").click(function () {
        geoLocation(10);
        getTravelTime(null);
    });
});

function searchAddress(address, zoom){// function will pull address class address and plot the point
    geocode(address, MAPBOX_API_KEY).then(function (results) {
        mapOptions.center = results;
        markerLocation.remove();
        map.flyTo({center: results, zoom: zoom, duration: 9000});
        markerLocation = new mapboxgl.Marker({color: "red", draggable: true})
            .setLngLat(results)
            .addTo(map);
        markerLocation.on('dragend', onDragEnd);
        console.log("in search");

        getTravelTime(results);
    });
}

function geoLocation(zoom) {
    function success(pos) {
        var crd = pos.coords;
        var coord = [crd.latitude, crd.longitude];
        var lngLat = [coord[1], coord[0]];
        markerLocation.remove();
        map.flyTo({center: lngLat, zoom: zoom, duration: 5000})
        markerLocation = new mapboxgl.Marker({color: "red", draggable: true})
            .setLngLat(lngLat)
            .addTo(map);
        markerLocation.on('dragend', onDragEnd);
        console.log("in geo");
    }
    function error(err) {
        console.warn(`ERROR(${err.code}): ${err.message}`);
    }
    navigator.geolocation.getCurrentPosition(success, error, options);
}

// ************* GET LOCATION FROM MARKER DRAG ******************
function onDragEnd() {
    var lngLat = markerLocation.getLngLat();
    console.log(lngLat);
    coord = [lngLat.lng, lngLat.lat];
    map.flyTo({center: lngLat, zoom: 10, duration: 1000});
    getTravelTime(coord);
}

function plotVendors(){
    //**************  PLOTS INDIVIDUAL VENDORS ON MAP ***************************
    let nameArray = [];
    let imgArray = [];
    $(".truck-name").each(function (index, val) {
        nameArray[index] = $(val).html();
    });

    $(".vendorImg").each(function (index, val) {
        imgArray[index] = $(val).html();
    });
    $(".vendor-location").each(function (index, val) {
        let image = "<img id='popupImg'src='https://images.unsplash.com/photo-1565123409695-7b5ef63a2efb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1351&q=80'><h6>If you love beef this is the place for you!</h6>"
        let vendor = {
            address: $(val).html(),
            img: "<img id='popupImg'src='" + imgArray[index] + "'><h6>" + nameArray[index] + "</h6>"
        }
        geocode($(val).html(), MAPBOX_API_KEY).then(function (results) {
            let el = document.createElement('div');
            el.className = 'marker';
            marker = new mapboxgl.Marker(el)
                .setLngLat(results)
                .addTo(map);
            let PopUp = new mapboxgl.Popup()
                .setHTML(vendor.img + vendor.address);
            marker.setPopup(PopUp);
        });
    });

}

function secToMin(secs){
    if(secs%60===0){
        return secs/60;
    }else{
        return Math.round(secs/60);
    }
}

function getTravelTime(lnglat){
    let crd;
    let coord = [];
    function success(pos) {
        if(lnglat == null){
            crd = pos.coords;
            coord = [crd.latitude, crd.longitude];
            console.log("if",coord)
        }
        else{
            coord = [lnglat[1],lnglat[0]]
            console.log("else",coord);
        }
        let duration;
        $(".vendor-location").each(function (index, val) {

            geocode($(val).html(), MAPBOX_API_KEY).then(function (results) {
                $.get("https://api.mapbox.com/directions/v5/mapbox/driving/" + coord[1] + "," + coord[0] + ";" + results[0] + "," + results[1] + "?access_token=" + MAPBOX_API_KEY).done(function (results){
                    duration = secToMin(results.routes[0].duration);
                    if (duration >= 60){
                        duration = duration /60;
                        duration = duration.toFixed(1);
                        $(".travel-time").eq(index).html("You are about " + duration + " hours away.");
                    }
                    else{
                        $(".travel-time").eq(index).html("You are about " + duration + " minutes away.");
                    }

                })
            })
        })
    }
    function error(err) {
        console.warn(`ERROR(${err.code}): ${err.message}`);
    }
    navigator.geolocation.getCurrentPosition(success, error, options);
}


// ************* GET GEOLOCATION ON BUTTON CLICK ON VENDOR PROFILE VIEW******************
$("#locate").click(function () {
    geoLocation(18);
});

//*************  SAVE CURRENT LOCATION TO DATABASE ON BUTTON CLICK**********************
$('#updateCurrent').click(function () {
    var coord = marker.getLngLat();
    reverseGeocode({lat: coord.lat, lng: coord.lng}, MAPBOX_API_KEY).then(function (results) {
        $('.address').html(results.features[0].place_name);
        openLocation();//calls function on vendors-profile.js and post to vendor controller
    });

});