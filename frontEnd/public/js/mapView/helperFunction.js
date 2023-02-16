function initTmap() {
	map = new Tmapv3.Map("map_div", {
		center : new Tmapv3.LatLng(37.56656541,126.98452047),
		width : "100vw",
		height : "82vh",
		zoom : 17,
		zoomControl : true,
		scrollwheel : true
	});

    map.on("ConfigLoad", function() {
        let longitude = localStorage.getItem("longitude");
        let latitude = localStorage.getItem("latitude");

        if(longitude && latitude) {
            FindAddressofSearchCoords(longitude, latitude);
        }
        else {
            navigator.geolocation.getCurrentPosition((position) => {
                FindAddressofSearchCoords(position.coords.longitude, position.coords.latitude);
            });
        }
    });

    const SearchButton = document.getElementById("main__btn_select");

    map.on("DragStart", function() {
        SearchButton.style.opacity = "0";
    });
    
    map.on("DragEnd", function() {
        SearchButton.style.opacity = "1";
    });

    SearchButton.addEventListener('click', SearchNearGasStation);
}

export { initTmap }