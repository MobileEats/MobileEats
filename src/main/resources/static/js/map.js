"use strict";
// $(document).ready(function () {


// ************* DISPLAY MAP ON LOAD - Default to San Antonio ******************
    var coord = [29.4241, -98.4936]; // lat[0] long[1] standard format

    mapboxgl.accessToken = mapboxToken;
    var mapOptions = {
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [coord[1], coord[0]], // San Antonio
        zoom: 9.7// captures all of 1604 Loop
    }
    var map = new mapboxgl.Map(mapOptions);
    var marker = new mapboxgl.Marker({color: "red", draggable: true})


//Find lat long from Address
$(document).ready(function () {
        geocode($('.address').html(), mapboxToken).then(function (results) {
            mapOptions.center = results;
            marker.remove();
            map.flyTo({center: results, zoom: 15, duration: 9000});
            marker = new mapboxgl.Marker({color: "red", draggable: true})
                .setLngLat(results)
                .addTo(map);
            marker.on('dragend', onDragEnd);
        });
    });


//     // ************* GET GEOLOCATION ******************
    $("#locate").click(function () {
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
            var coord = [crd.latitude,crd.longitude];
            map = new mapboxgl.Map(mapOptions);
            var lngLat = [coord[1], coord[0]];
            map.flyTo({center: lngLat, zoom: 9.7, duration: 5000})
            marker = new mapboxgl.Marker({color: "red", draggable: true})
                .setLngLat(lngLat)
                .addTo(map);
            console.log("geo coord", coord);
            console.log("geo lngLat", lngLat);
            marker.on('dragend', onDragEnd);

        }
        function error(err) {
            console.warn(`ERROR(${err.code}): ${err.message}`);
        }
        navigator.geolocation.getCurrentPosition(success, error, options);
    }


// ************* GET LOCATION FROM MARKER DRAG ******************
    function onDragEnd() {
        var coord = marker.getLngLat();
        console.log("drag coord", coord);
        var lngLat = coord;
        console.log("drag lngLat", lngLat);

        map.flyTo({center: lngLat, zoom: 9.7, duration: 1000});
        coord = [coord.lat, coord.lng];

    }

// });