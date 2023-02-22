import { isReleaseMode } from "../common/utils";
import { distanceOption } from "./event";
import { brandLogoMapper, ResetOptionButton, transStId, SelectStIdLogo, highhHsaleInfo, nohHsaleInfo, FillSTDetail, onlyhPrice, hnHhPrice} from "./ExtraFunctions";
import { AdjustLocationToCurLocation } from "./SearchFunction";
import { SetLPGmarker, SetMarkerofMapCenter, SetMarkerofResult, SetHGmarker, 
    SetHmarker, SetGmarker, ResetMarkerArray, marker, markerArr, bringMarkerToFront} from "./marker";
import { ShowDetailTab, appendNewChart, closeDetailTab, DetailTabDisplay, MoveSideBar, SideBarDisplay, ShowSideBar, HideSideBar} from "./DetailTab";
import { getChartData, ShowChart} from "./chart";

let map = "";
let searchOption = 0;
let resultpoisData=[];
let positionBounds;
let ResultHtml = "";

function initTmap() {
	map = new Tmapv3.Map("map_div", {
		center : new Tmapv3.LatLng(37.56656541,126.98452047),
		width : "100vw",
		height : "82vh",
		zoom : 17,
		zoomControl : true,
		scrollwheel : true,
        responsive : false
	});

    map.on("ConfigLoad", function() {
        MoveToUserCurLocation();
        
        AddEventToOptionButton();
        
        AddEventToSideBarButton();

        AddEventToDetailTabButton();
        
        AddEventToResetButton();

        AddEventToSearchButton();
    });
}

function AddEventToSearchButton() {
    const SearchButton = document.getElementById("main__btn_select");
    SearchButton.addEventListener('click', SearchNearGasStation);

    map.on("DragStart", function() {
        SearchButton.style.opacity = "0";
    });
    
    map.on("DragEnd", function() {
        SearchButton.style.opacity = "1";
    });  
}

function MoveMaptoSearchcoords(lon, lat){
    var mapCenterPosition = new Tmapv3.LatLng(lat, lon);
    map.setCenter(mapCenterPosition);
}

function FindAddressofSearchCoords(lon, lat) {
    MoveMaptoSearchcoords(lon, lat);
    AdjustLocationToCurLocation(lon, lat);
}

function SearchNearGasStation(){
    if(DetailTabDisplay == true && SideBarDisplay ==true){
        closeDetailTab();
    }
    searchOption=0;
    ResetOptionButton();
    $.ajax({
        method:"GET", // 요청 방식
        url:"https://apis.openapi.sk.com/tmap/pois/search/around?version=1&format=json&callback=result", 
        data:{
            "categories" : "주유소;가스충전소",
            "resCoordType" : "EPSG3857",
            "searchType" : "name",
            "searchtypCd" : "A",
            "radius" : distanceOption,
            "reqCoordType" : "WGS84GEO",
            "centerLon" : map.getCenter()._lng,
            "centerLat" : map.getCenter()._lat,
            "appKey" : "l7xx7250af6176574c63a12302edf09d020c",
            "count" : 100
        },
        success:function(response) {
            FindAddressofSearchCoords(map.getCenter()._lng,map.getCenter()._lat);
            if(response==null){
                const NoResult = "검색 결과가 존재하지 않습니다.";
                document.getElementById("searchResult").innerHTML = NoResult;
            }
            else {
                resultpoisData = response.searchPoiInfo.pois.poi;
                positionBounds = new Tmapv3.LatLngBounds();
                ShowResult(resultpoisData, positionBounds); // 거리순 결과 데이터출력
            }
        },
        error:function(request, error){
            
        }
    });
}



function ShowResult(ResultArray, positionBounds){
    ResetMarkerArray(markerArr);
    ResultHtml = "";
    const resultArea = document.querySelector(".main__SearchResult");
    resultArea.innerHTML = "";
    for(var k in ResultArray){
        if(ResultArray[k].hhPrice==0 && ResultArray[k].ggPrice==0 && ResultArray[k].llPrice==0){
            continue;
        }

        ResultHtml += SelectStIdLogo(ResultArray[k].stId, ResultArray[k].name, ResultArray[k].radius);

        var markerPosition = SetMarkerofResult(ResultArray[k]);
        SetMarkerofMapCenter();

        if(ResultArray[k].llPrice != 0){
            SetLPGmarker(markerPosition, ResultArray[k]);
        }
        else {
            SetHGmarker(markerPosition, ResultArray[k]);
        }

        if(ResultArray[k].highHhSale != 0){
            ResultHtml += highhHsaleInfo(ResultArray[k]);

        }
        else { 
            ResultHtml += nohHsaleInfo(ResultArray[k]);
        }
        positionBounds.extend(markerPosition);
    }

    addmouseOverEventtoMarker();
    addClickEventToMarker(ResultArray);
    resultArea.innerHTML = ResultHtml;
    addEventToResult(ResultArray);
    map.fitBounds(positionBounds);	
}


function addEventToResult(ResultArray){
    const GSTArray = document.getElementsByClassName("main__ResultList");
    for(var k=0;k<GSTArray.length;k++) {
        GSTArray[k].addEventListener('click', (e) => {
            ShowGSTDetail(e, ResultArray);
        });
    }
}

function ShowGSTDetail(event, ResultArray){
    map.setZoom(17);

    if(DetailTabDisplay==false) ShowDetailTab();
    appendNewChart();

    const ResultTitle = event.target.closest(".main__ResultList");
    const SelectedTitle = ResultTitle.querySelector("#stName");

    for(var k=0;k<ResultArray.length;k++){
        if(ResultArray[k].name == SelectedTitle.innerHTML){
            break;
        }
    }
    var markerPosition = SetMarkerofResult(ResultArray[k]);

    if(searchOption==0){
        ShowOnlyCurMarker(SelectedTitle.innerHTML);
    }
    else {
        ToggleCurMarker(SelectedTitle.innerHTML);
    }

    map.setCenter(markerPosition);

    map.zoomIn();

    FillSTDetail(ResultArray[k]);
    
    getChartData(ResultArray[k]);
}

function sortHhPrice() {
    if(searchOption==1){ // 선택되어있으면 그대로
        searchOption = 0;
        $("#sort_HH").css("opacity", "0.5");
    }
    else {
        searchOption = 1;
        $("#sort_GG").css("opacity", "0.5");
        $("#sort_HH").css("opacity", "1");
        $("#sort_LL").css("opacity", "0.5");
        let HHsort = resultpoisData.sort(function(a, b) {
            return a.hhPrice - b.hhPrice;
        })
        for(var k=0;k<HHsort.length;k++){
            if(HHsort[k].hhPrice != 0){
                break;
            }
        }
        ShowResultByOption(HHsort.slice(k, HHsort.length));
    }
}

function sortGgPrice() {
    if(searchOption==2){
        searchOption = 0;
        $("#sort_GG").css("opacity", "0.5");
    }
    else {
        searchOption = 2;
        $("#sort_GG").css("opacity", "1");
        $("#sort_HH").css("opacity", "0.5");
        $("#sort_LL").css("opacity", "0.5");
        let GGsort = resultpoisData.sort(function(a, b) {
            return a.ggPrice - b.ggPrice;
        })

        for(var k=0;k<GGsort.length;k++){
            if(GGsort[k].ggPrice != 0){
                break;
            }
        }
        ShowResultByOption(GGsort.slice(k, GGsort.length));
    }
}

function sortLlPrice() {
    if(searchOption==3){
        searchOption = 0;
        $("#sort_LL").css("opacity", "0.5");
    }
    else {
        searchOption = 3;
        $("#sort_GG").css("opacity", "0.5");
        $("#sort_HH").css("opacity", "0.5");
        $("#sort_LL").css("opacity", "1");

        let LLsort = resultpoisData.sort(function(a, b) {
            return a.llPrice - b.llPrice;
        })
        for(var k=0;k<LLsort.length;k++){
            if(LLsort[k].llPrice != 0){
                break;
            }
        }
        ShowResultByOption(LLsort.slice(k, LLsort.length));
    }
}

function ShowResultByOption(ResultArray) {
    ResultHtml = "";
    const resultArea = document.querySelector(".main__SearchResult");
    resultArea.innerHTML = "";
    ResetMarkerArray(markerArr);
    var positionBounds = new Tmapv3.LatLngBounds();
    var loopCounter = 10 > ResultArray.length ? ResultArray.length : 10;

    for(var k=0;k<loopCounter;k++){      
        ResultHtml += SelectStIdLogo(ResultArray[k].stId, ResultArray[k].name, ResultArray[k].radius);
        var markerPosition = SetMarkerofResult(ResultArray[k]);

        if(searchOption==1){
            if(ResultArray[k].highHhSale != 0){
                ResultHtml += hnHhPrice(ResultArray[k]);
            }
            else {
                ResultHtml += onlyhPrice(ResultArray[k]);
            }
            SetHmarker(markerPosition, ResultArray[k]);
        }
        else if(searchOption==2){
            ResultHtml += `<div class='main__ResultList__Contents'>
                        경유: <span> ${ResultArray[k].ggPrice == 0 ? '-' : ResultArray[k].ggPrice} </span>&nbsp;&nbsp;
                        </div></div></div>`;
            SetGmarker(markerPosition, ResultArray[k]);
        }
        else if(searchOption==3){
            ResultHtml += `<div class='main__ResultList__Contents'>
                        LPG: <span> ${ResultArray[k].llPrice == 0 ? '-' : ResultArray[k].llPrice} </span>&nbsp;&nbsp;
                        </div></div></div>`;
            SetLPGmarker(markerPosition, ResultArray[k]);
        }
        markerArr.push(marker);
        positionBounds.extend(markerPosition);
    }
    addmouseOverEventtoMarker();
    addClickEventToMarker(ResultArray);
    resultArea.innerHTML = ResultHtml;
    addEventToResult(ResultArray);
    map.fitBounds(positionBounds);	
}

function ShowOnlyCurMarker(SelectedTitle){
    const selectedmarker = document.getElementById(SelectedTitle);

    const HGmarkerARR = document.getElementsByClassName('Map_Marker_HG');
    for(var k=0;k<HGmarkerARR.length;k++){
        HGmarkerARR[k].style.display = 'none';
    }
    const LmarkerARR = document.getElementsByClassName('Map_Marker_LPG');
    for(var k=0;k<LmarkerARR.length;k++){
        LmarkerARR[k].style.display = 'none';
    }
    selectedmarker.style.display = "";
}

function ToggleCurMarker(SelectedTitle){
    const selectedmarker = document.getElementById(SelectedTitle);

    if(searchOption==0){
        const HGmarkerARR = document.getElementsByClassName('Map_Marker_HG');
        for(var k=0;k<HGmarkerARR.length;k++){
            HGmarkerARR[k].classList.remove('selected__Map_Marker_HG');
        }
        const LmarkerARR = document.getElementsByClassName('Map_Marker_LPG');
        for(var k=0;k<LmarkerARR.length;k++){
            LmarkerARR[k].classList.remove('selected__Map_Marker_LPG');
        }

        if(SelectedTitle.includes('주유소')){
            selectedmarker.classList.toggle('selected__Map_Marker_HG');
        }
        else if(SelectedTitle.includes('충전소')){
            selectedmarker.classList.toggle('selected__Map_Marker_LPG');
        }
    }
    else if(searchOption==1){
        const HmarkerARR = document.getElementsByClassName('Map_Marker_hprice');
        for(var k=0;k<HmarkerARR.length;k++){
            HmarkerARR[k].classList.remove('selected__Map_Marker_hprice');
        }
        selectedmarker.classList.toggle('selected__Map_Marker_hprice');
    }
    else if(searchOption==2){
        const GmarkerARR = document.getElementsByClassName('Map_Marker_gprice');
        for(var k=0;k<GmarkerARR.length;k++){
            GmarkerARR[k].classList.remove('selected__Map_Marker_gprice');
        }
        selectedmarker.classList.toggle('selected__Map_Marker_gprice');
    }
    else if(searchOption==3){
        const LmarkerARR = document.getElementsByClassName('Map_Marker_LPG');
        for(var k=0;k<LmarkerARR.length;k++){
            LmarkerARR[k].classList.remove('selected__Map_Marker_LPG');
        }
        selectedmarker.classList.toggle('selected__Map_Marker_LPG');
    }
}
function addmouseOverEventtoMarker() {
    const HGmapmarkerarr = document.getElementsByClassName('img_HG');
    const LPGmapmarkerarr = document.getElementsByClassName('img_LPG');
    const HnGmapmarkerarr = document.getElementsByClassName('img_HnG');
    for(var k=0;k<HGmapmarkerarr.length;k++){
        HGmapmarkerarr[k].addEventListener('mouseover', (e) => bringMarkerToFront(e));
    }
    for(var k=0;k<LPGmapmarkerarr.length;k++){
        LPGmapmarkerarr[k].addEventListener('mouseover', (e) => bringMarkerToFront(e));
    }
    for(var k=0;k<HnGmapmarkerarr.length;k++){
        HnGmapmarkerarr[k].addEventListener('mouseover', (e) => bringMarkerToFront(e));
    }
}

function addClickEventToMarker(ResultArray){
    if(searchOption==0){
        const HGmapmarkerarr = document.getElementsByClassName('img_HG');
        const LPGmapmarkerarr = document.getElementsByClassName('img_LPG');
        for(var k=0;k<HGmapmarkerarr.length;k++){
            HGmapmarkerarr[k].addEventListener('click', (e) => ShowGSTDetailByMarker(e, ResultArray));
        }
        for(var k=0;k<LPGmapmarkerarr.length;k++){
            LPGmapmarkerarr[k].addEventListener('click', (e) => ShowGSTDetailByMarker(e, ResultArray));
        }
    }
    else if(searchOption==1 || searchOption ==2){
        const HnGmapmarkerarr = document.getElementsByClassName('img_HnG');
        for(var k=0;k<HnGmapmarkerarr.length;k++){
            HnGmapmarkerarr[k].addEventListener('click', (e) => ShowGSTDetailByMarker(e, ResultArray));
        }
    }
    else if(searchOption==3){
        const LPGmapmarkerarr = document.getElementsByClassName('img_LPG');
        for(var k=0;k<LPGmapmarkerarr.length;k++){
            LPGmapmarkerarr[k].addEventListener('click', (e) => ShowGSTDetailByMarker(e, ResultArray));
        }
    }
}

function ShowGSTDetailByMarker(e, ResultArray){
    if(searchOption==0){
        const HGmarkerGSTname = e.target.closest('.Map_Marker_HG');
        const LPGmarkerGSTname = e.target.closest('.Map_Marker_LPG');
        if(HGmarkerGSTname){
            ShowDetail(ResultArray, HGmarkerGSTname);
        }
        else {
            ShowDetail(ResultArray, LPGmarkerGSTname);
        }
    }
    else if(searchOption==1){
        const Hmarkername = e.target.closest('.Map_Marker_hprice');
        ShowDetail(ResultArray, Hmarkername);
    }
    else if(searchOption==2){
        const Gmarkername = e.target.closest('.Map_Marker_gprice');
        ShowDetail(ResultArray, Gmarkername);
    }
    else if(searchOption==3){
        const Lmarkername = e.target.closest('.Map_Marker_LPG');
        ShowDetail(ResultArray, Lmarkername);
    }
}


function ShowDetail(ResultArray, markername){
    if(SideBarDisplay==false){
        ShowSideBar();
    }
    if(DetailTabDisplay==false){
        ShowDetailTab();
    }

    appendNewChart();
    
    for(var k=0;k<ResultArray.length;k++){
        if(ResultArray[k].name == markername.id){
            break;
        }
    }
    ToggleCurMarker(markername.id);
    FillSTDetail(ResultArray[k]);
    getChartData(ResultArray[k]);
}

function MoveToinitialPoint(){
    ResetMarkerArray(markerArr);
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
    const resultArea = document.querySelector(".main__SearchResult");
    resultArea.innerHTML = "";

    SetMarkerofMapCenter();
}


function MoveToUserCurLocation(){
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
}

function AddEventToOptionButton() {
    const SortByHH = document.getElementById("sort_HH");
    const SortByLL = document.getElementById("sort_LL");
    const SortByGG = document.getElementById("sort_GG");

    SortByHH.addEventListener('click', sortHhPrice);
    SortByGG.addEventListener('click', sortGgPrice);
    SortByLL.addEventListener('click', sortLlPrice);
}

function AddEventToSideBarButton(){
    const MainFrame = document.querySelector(".main__ForMap");
    const SideBarButton = MainFrame.querySelector(".main__SearchBarHideButton");
    SideBarButton.addEventListener('click', (e) => MoveSideBar(e));
}

function AddEventToDetailTabButton(){
    document.getElementsByClassName("main__GSTdetailCloseButton")[0].addEventListener('click', closeDetailTab);
}

function AddEventToResetButton(){
    const SearchFromRouteButton = document.querySelector(".main__SearchFromRoute");
    SearchFromRouteButton.addEventListener('click', (e) => MoveToinitialPoint(e));
}
export { initTmap,map};


