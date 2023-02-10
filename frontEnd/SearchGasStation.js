let SideBarDisplay = true;
let isOption1 = true;
let isOption2 = false;
let myPosition_lat;
let myPosition_lng;
let searchOption = 1;
var map, marker;
var markerArr = [];
var innerHtml = ""; 


const SortByHH = document.getElementById("sort_HH");
const SortByLL = document.getElementById("sort_LL");
const SortByGG = document.getElementById("sort_GG");

SortByHH.addEventListener('click', sortHhPrice);
SortByLL.addEventListener('click', sortLlPrice);
SortByGG.addEventListener('click', sortGgPrice);

function sortHhPrice() {
	searchOption = 1;
    $('#sort_LL').css("opacity", "0.5");
    $('#sort_GG').css("opacity", "0.5");
    $('#sort_HH').css("opacity", "1");
}

function sortGgPrice() {
	searchOption = 2;
    $("#sort_LL").css("opacity", "0.5");
    $("#sort_GG").css("opacity", "1");
    $("#sort_HH").css("opacity", "0.5");
}

function sortLlPrice() {
	searchOption = 3;
    $("#sort_LL").css("opacity", "1");
    $("#sort_GG").css("opacity", "0.5");
    $("#sort_HH").css("opacity", "0.5");
}

navigator.geolocation.getCurrentPosition(initTmap);

const MainFrame = document.querySelector(".main__ForMap");

const SideBarButton = MainFrame.querySelector(".main__SearchBarHideButton");

SideBarButton.addEventListener('click', (e) => MoveSideBar(e));



function MoveSideBar(e){
    const HideSideBar = document.getElementsByClassName("main__SideSearchBar");
    const HideSideBarButton = e.target.closest(".main__SearchBarHideButton");
    if(SideBarDisplay){
        HideSideBar[0].style.marginLeft = "-22vw";
        SideBarDisplay = false;
        HideSideBarButton.innerHTML = "<img src='img/🦆 icon _chevron right_.svg'>";
    }
    else {
        HideSideBar[0].style.marginLeft = "0vw";
        SideBarDisplay = true;
        HideSideBarButton.innerHTML = "<img src='img/🦆 icon _chevron left_.svg'>";
    }
}

const SearchFromCurrentLocationButton = document.querySelector(".main__SearchFromCurrentLocation");
SearchFromCurrentLocationButton.addEventListener('click', (e) => SelectSearchOption1(e));

const SearchFromRouteButton = document.querySelector(".main__SearchFromRoute");
SearchFromRouteButton.addEventListener('click', (e) => SelectSearchOption2(e));

function SelectSearchOption1(e){
    const SearchFromCurLocButton = e.target.closest(".main__SearchFromCurrentLocation");
    if(isOption1){
        return;
    }
    else {
        const Option2button = document.querySelector(".main__SearchFromRoute");
        Option2button.style.height = "4.6vh";
        Option2button.style.width = "1.3vw";
        Option2button.style.backgroundColor = "white";
        Option2button.querySelector("#Option2").style.height = "14px";
        Option2button.querySelector("#Option2").style.width = "14px";
        SearchFromCurLocButton.style.height = "5vh";
        SearchFromCurLocButton.style.width = "3vh";
        SearchFromCurLocButton.style.backgroundColor = "#14BD7E";
        SearchFromCurLocButton.querySelector("#Option1").style.height = "30px";
        SearchFromCurLocButton.querySelector("#Option1").style.width = "30px";
        isOption1 = true;
        isOption2 = false;
    }
}

function SelectSearchOption2(e) {
    const SearchFromRouteButton = e.target.closest(".main__SearchFromRoute");
    if(isOption2){
        return;
    }
    else {
        const Option1button = document.querySelector(".main__SearchFromCurrentLocation");
        Option1button.style.height = "4.6vh";
        Option1button.style.width = "1.3vw";
        Option1button.style.backgroundColor = "white";
        Option1button.querySelector("#Option1").style.height = "14px";
        Option1button.querySelector("#Option1").style.width = "14px";
        SearchFromRouteButton.style.height = "5vh";
        SearchFromRouteButton.style.width = "3vh";
        SearchFromRouteButton.style.backgroundColor = "#14BD7E";
        SearchFromRouteButton.querySelector("#Option2").style.height = "30px";
        SearchFromRouteButton.querySelector("#Option2").style.width = "30px";
        isOption2 = true;
        isOption1 = false;
    }
}



function initTmap(position) {
	map = new Tmapv2.Map("map_div", {
		center : new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude),
		width : "100vw",
		height : "82vh",
		zoom : 17,
		zoomControl : true,
		scrollwheel : true
	});

    const SearchButton = document.getElementById("main__btn_select");
    FindAddressofSearchCoords(position.coords.longitude,position.coords.latitude);
    SearchButton.addEventListener('click', SearchNearGasStation);
}

function FindAddressofSearchCoords(lon, lat) {
    $.ajax({
        method : "GET",
        url : "https://apis.openapi.sk.com/tmap/geo/reversegeocoding?version=1&format=json&callback=result&appKey=l7xx7250af6176574c63a12302edf09d020c",
        async : false,
        data : {
            "coordType" : "WGS84GEO",
            "addressType" : "A10",
            "lon" : lon,
            "lat" : lat
        },
        success : function(response) {
            var arrResult = response.addressInfo;

            var lastLegal = arrResult.legalDong
                    .charAt(arrResult.legalDong.length - 1);

            var newRoadAddr = arrResult.city_do + ' '
                    + arrResult.gu_gun + ' ';

            if (arrResult.eup_myun == ''
                    && (lastLegal == "읍" || lastLegal == "면")) {
                newRoadAddr += arrResult.legalDong;
            } else {
                newRoadAddr += arrResult.eup_myun;
            }
            newRoadAddr += ' ' + arrResult.roadName + ' '
                    + arrResult.buildingIndex;

            if (arrResult.legalDong != ''
                    && (lastLegal != "읍" && lastLegal != "면")) {

                if (arrResult.buildingName != '') {
                    newRoadAddr += (' (' + arrResult.legalDong
                            + ', ' + arrResult.buildingName + ') ');
                } else {
                    newRoadAddr += (' (' + arrResult.legalDong + ')');
                }
            } else if (arrResult.buildingName != '') {
                newRoadAddr += (' (' + arrResult.buildingName + ') ');
            }

            var result = "<div>" + newRoadAddr + "</div>";
            $(".main__CurrentLocationAddress").html(result);
        },
        error : function(request, status, error) {
            console.log("code:" + request.status + "\n"
                    + "message:" + request.responseText + "\n"
                    + "error:" + error);
        }
    });
}


function SelectStIdLogo(stId, name){
    return `
        <div class='main__ResultList'> 
            <a href='./gasSTDView' data-link>
            <div class='main__ResultList__Title'>
        <img class='main__ResultList__Title_Logo' src = 'img/GasStation_Image/${stId}.png'>
            <span>${name}</span>
            </a>
        </div>
    `;
}

function ShowResult(ResultArray, positionBounds){
    for(var k in ResultArray){
        var noorLat = Number(ResultArray[k].noorLat);
        var noorLon = Number(ResultArray[k].noorLon);

        innerHtml += SelectStIdLogo(ResultArray[k].stId, ResultArray[k].name);
        var pointCng = new Tmapv2.Point(noorLon, noorLat);
        var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(pointCng);
        
        var lat = projectionCng._lat;
        var lon = projectionCng._lng;

        var markerPosition = new Tmapv2.LatLng(lat, lon);

        marker = new Tmapv2.Marker({
            position : markerPosition,
            icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_" + k + ".png",
            iconSize : new Tmapv2.Size(24, 38),
            title : ResultArray[k].name,
            map:map
        });

        if(ResultArray[k].hhPrice==0 || ResultArray[k].ggPrice==0){
            innerHtml += "<div class='main__ResultList__Contents'>"
                        + "유가 정보가 등록되지 않았습니다. </div> </div>";
        }

        else {
            innerHtml += "<div class='main__ResultList__Contents'> 휘발유: " + "<span>" + ResultArray[k].hhPrice + "</span>"
                     + "&nbsp;&nbsp;경유: " + "<span>" + ResultArray[k].ggPrice + "</span>"
                     + "&nbsp;&nbsp;LPG: <span>" + ResultArray[k].llPrice + " </span></div> </div>";
        }

        markerArr.push(marker);
        positionBounds.extend(markerPosition);	
    }
    $(".main__SearchResult").html(innerHtml);	
    map.panToBounds(positionBounds);	
    map.zoomOut();

}


function SearchNearGasStation(){
    $.ajax({
        method:"GET", // 요청 방식
        url:"https://apis.openapi.sk.com/tmap/pois/search/around?version=1&format=json&callback=result", 
        data:{
            "categories" : "주유소",
            "resCoordType" : "EPSG3857",
            "searchType" : "name",
            "searchtypCd" : "A",
            "radius" : 2,
            "reqCoordType" : "WGS84GEO",
            "centerLon" : map.getCenter()._lng,
            "centerLat" : map.getCenter()._lat,
            "appKey" : "l7xx7250af6176574c63a12302edf09d020c",
            "count" : 10
        },
        success:function(response){
            FindAddressofSearchCoords(map.getCenter()._lng,map.getCenter()._lat);

            if(response==null){
                const NoResult = "검색 결과가 존재하지 않습니다.";
                $("#searchResult").html(NoResult);
            }
            else {
                var resultpoisData = response.searchPoiInfo.pois.poi;
                var positionBounds = new Tmapv2.LatLngBounds();
                ResetMarkerArray();
                ShowResultByOption(searchOption, resultpoisData, positionBounds);
            }
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function ResetMarkerArray() {
    if(markerArr.length > 0){
        for(var i in markerArr){
            markerArr[i].setMap(null);
        }
        markerArr = [];
    }
}

function ShowResultByOption(option, resultpoisData, positionBounds){
    innerHtml = "";
    if(option==1){
        let HHsort = resultpoisData.sort(function(a, b) {
            return a.hhPrice - b.hhPrice;
        })
        
        ShowResult(HHsort, positionBounds);
    }
    else if(option==2){
        let GGsort = resultpoisData.sort(function(a, b) {
            return a.ggPrice - b.ggPrice;
        })
        ShowResult(GGsort, positionBounds);
    }
    else if(option==3){
        let LLsort = resultpoisData.sort(function(a, b) {
            return a.llPrice - b.llPrice;
        })
        ShowResult(LLsort, positionBounds);
    }
}