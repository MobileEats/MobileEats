"use strict";

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

    map.on('load', event => {
        console.log(event);
        map.resize()
    })

//*********** FIND LAT LONG FROM ADDRESS SAVED ON DATABASE **************
$(document).ready(function () {
    var pathname = window.location.pathname; // Returns path only (/path/example.html)
    if(pathname == "/vendors"){
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
                map.flyTo({center: lngLat, zoom: 10, duration: 5000})
                marker = new mapboxgl.Marker({color: "blue", draggable: true})
                    .setLngLat(lngLat)
                    .addTo(map);
                marker.on('dragend', onDragEnd);

                // Display all vendors.
                //**************  PLOTS INDIVIDUAL POINTS ON MAP ***************************
                let nameArray = [];
                let imgArray = [];
                $(".truck-name").each(function (index, val){
                    nameArray[index] = $(val).html();
                });
                console.log(nameArray);
                $(".vendorImg").each(function (index, val){
                    imgArray[index] = $(val).html();
                });
                $(".vendor-location").each(function(index, val){
                    let image = "<img id='popupImg'src='https://images.unsplash.com/photo-1565123409695-7b5ef63a2efb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1351&q=80'><h6>If you love beef this is the place for you!</h6>"
                    let vendor = {
                        address: $(val).html(),
                        img: "<img id='popupImg'src='" + imgArray[index] + "'><h6>" + nameArray[index] + "</h6>"
                    }

                    geocode($(val).html(), mapboxToken).then(function (results) {
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
            function error(err) {
                console.warn(`ERROR(${err.code}): ${err.message}`);
            }
            navigator.geolocation.getCurrentPosition(success, error, options);

    }
    else {
        geocode($('.address').html(), mapboxToken).then(function (results) {
            mapOptions.center = results;
            marker.remove();
            map.flyTo({center: results, zoom: 15, duration: 9000});
            marker = new mapboxgl.Marker({color: "red", draggable: true})
                .setLngLat(results)
                .addTo(map);
            marker.on('dragend', onDragEnd);
        });
    }


    });


// ************* GET GEOLOCATION  ON BUTTON CLICK ******************
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
            map.flyTo({center: lngLat, zoom: 18, duration: 5000})
            marker = new mapboxgl.Marker({color: "red", draggable: true})
                .setLngLat(lngLat)
                .addTo(map);
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
        var lngLat = coord;
        map.flyTo({center: lngLat, zoom: 15, duration: 1000});

    }

//*************  SAVE CURRENT LOCATION TO DATABASE **********************
$('#updateCurrent').click(function(){
    var coord = marker.getLngLat();
    reverseGeocode({lat: coord.lat, lng: coord.lng}, mapboxToken).then(function(results) {
        $('.address').html(results.features[0].place_name);
        openLocation();//calls function on vendors-profile.js and post to vendor controller
    });


});
