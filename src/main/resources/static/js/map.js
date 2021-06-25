"use strict";
$(document).ready(function () {


// ************* DISPLAY MAP ON LOAD - Default to San Antonio ******************
//     var coord = [32.3863, -94.8758]; // lat[0] long[1] standard format
    var coord = [29.4241, -98.4936]; // lat[0] long[1] standard format
    var lngLat = coord;

    var city = "San Antonio, TX"//SA coordinates in Mapbox format
    mapboxgl.accessToken = mapboxToken;
    var mapOptions = {
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [coord[1], coord[0]], // San Antonio
        zoom: 9.7// captures all of 1604 Loop
    }
    var map = new mapboxgl.Map(mapOptions);
    var marker = new mapboxgl.Marker({color: "red", draggable: true})
        .setLngLat([coord[1], coord[0]])
        .addTo(map);
    // getWeather(coord);
    marker.on('dragend', onDragEnd);

//
//     // ************* GET GEOLOCATION ******************
    $(".geolocation").click(function () {

        geoLocation();

    });
    function geoLocation(){
        var options = {
            enableHighAccuracy: true,
            timeout: 5000,
            maximumAge: 0
        };

        function success(pos) {
            var crd = pos.coords;
            coord = [crd.latitude,crd.longitude];
            map = new mapboxgl.Map(mapOptions);
            lngLat = [coord[1], coord[0]];
            map.flyTo({center: lngLat, zoom: 9.7, duration: 5000})
            marker = new mapboxgl.Marker({color: "red", draggable: true})
                .setLngLat(lngLat)
                .addTo(map);
            console.log("geo coord", coord);
            console.log("geo lngLat", lngLat);
            marker.on('dragend', onDragEnd);
            getWeather(coord);
        }
        function error(err) {
            console.warn(`ERROR(${err.code}): ${err.message}`);
        }
        navigator.geolocation.getCurrentPosition(success, error, options);
    }

// // // ************* GET LOCATION FROM SEARCH BOX******************
//
//     $("#weatherBtn").click(search);
//     function search(e) {
//         e.preventDefault();
//         geocode($("#weatherLocation").val(), mapboxToken).then(function (results) {
//             mapOptions.center = results;
//             coord = [results[1], results[0]];// in open weather format for getWeather()
//             marker.remove();
//             map.flyTo({center: results, zoom: 9.7, duration: 5000});
//             marker = new mapboxgl.Marker({color: "red", draggable: true})
//                 .setLngLat(results)
//                 .addTo(map);
//             marker.on('dragend', onDragEnd);
//             getWeather(coord);
//         });
//     }
//
// ************* GET LOCATION FROM MARKER DRAG ******************
    function onDragEnd() {
        coord = marker.getLngLat();
        console.log("drag coord", coord);
        lngLat = coord;
        console.log("drag lngLat", lngLat);

        map.flyTo({center: lngLat, zoom: 9.7, duration: 1000});
        coord = [coord.lat, coord.lng];
        getWeather(coord);
    }

// // ************* MAP SETTINGS ******************
//
//     $("#settingBtn").click(function(){
//         coord = marker.getLngLat();
//         mapOptions.center = coord;
//         if($(".mapView").val() === "satellite"){
//             mapOptions.style = 'mapbox://styles/mapbox/satellite-streets-v11';
//             map = new mapboxgl.Map(mapOptions);
//             marker = new mapboxgl.Marker({color: "red", draggable: true})
//                 .setLngLat(mapOptions.center)
//                 .addTo(map);
//         }else{
//             mapOptions.style = 'mapbox://styles/mapbox/streets-v11';
//             map = new mapboxgl.Map(mapOptions);
//             marker = new mapboxgl.Marker({color: "red", draggable: true})
//                 .setLngLat(mapOptions.center)
//                 .addTo(map);
//         }
//         if($("#darkMode").prop("checked") === true){
//
//         }else{
//
//         }
//         if($("#alerts").prop("checked") !== true){
//             $("#mapOverlay").addClass("hidden");
//         }else{
//             $("#mapOverlay").removeClass("hidden");
//         }
//         marker.on('dragend', onDragEnd);
//     });


});