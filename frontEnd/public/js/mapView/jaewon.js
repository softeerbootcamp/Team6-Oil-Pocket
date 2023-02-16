let SideBarDisplay = true;
let DetailTabDisplay = false;
let isOption1 = true;
let isOption2 = false;
let searchOption = 0;
var map; 
var marker, LPGmarker, HGmarker;
var LPGmarkerArr = [];
var HGmarkerArr = [];
var markerArr = [];
var ResultHtml = ""; 
var count=0;
var resultpoisData=[];
var positionBounds;

// const SortByHH = document.getElementById("sort_HH");
// const SortByLL = document.getElementById("sort_LL");
// const SortByGG = document.getElementById("sort_GG");

// SortByHH.addEventListener('click', sortHhPrice);
// SortByGG.addEventListener('click', sortGgPrice);
// SortByLL.addEventListener('click', sortLlPrice);

// SortByLL.addEventListener('click', SearchNearLPGStation);
// initTmap();

// const MainFrame = document.querySelector(".main__ForMap");

// const SideBarButton = MainFrame.querySelector(".main__SearchBarHideButton");

// SideBarButton.addEventListener('click', (e) => MoveSideBar(e));

// document.getElementsByClassName("main__GSTdetailCloseButton")[0].addEventListener('click', closeDetailTab);

function closeDetailTab() {
    const GSTDetailTab = document.getElementsByClassName("main__GSTDetailTab");
    GSTDetailTab[0].style.marginLeft = "-22vw";
    const SideSearchBar = document.getElementsByClassName("main__SideSearchBar");
    SideSearchBar[0].style.borderRadius = "0 5px 5px 0";
    DetailTabDisplay = false;
}

function MoveSideBar(e){
    const HideSideBar = document.getElementsByClassName("main__SearchBarnDetailTab");
    const HideSideBarButton = e.target.closest(".main__SearchBarHideButton");
    if(SideBarDisplay){
        if(DetailTabDisplay){
            HideSideBar[0].style.marginLeft = "-44vw";
        }
        else {
            HideSideBar[0].style.marginLeft = "-22vw";
        }
        SideBarDisplay = false;
        HideSideBarButton.innerHTML = "<img src='img/🦆 icon _chevron right_.svg'>";
        
    }
    else {
        HideSideBar[0].style.marginLeft = "0vw";
        SideBarDisplay = true;
        HideSideBarButton.innerHTML = "<img src='img/🦆 icon _chevron left_.svg'>";
    }
}

// const SearchFromCurrentLocationButton = document.querySelector(".main__SearchFromCurrentLocation");
// SearchFromCurrentLocationButton.addEventListener('click', (e) => SelectSearchOption1(e));

// const SearchFromRouteButton = document.querySelector(".main__SearchFromRoute");
// SearchFromRouteButton.addEventListener('click', (e) => SelectSearchOption2(e));

function SelectSearchOption1(e){
    const SearchFromCurLocButton = e.target.closest(".main__SearchFromCurrentLocation");
    if(isOption1){
        return;
    }
    else {
        const Option2button = document.querySelector(".main__SearchFromRoute");
        Option2button.style.height = "4.6vh";
        Option2button.style.width = "1.9vw";
        Option2button.style.backgroundColor = "white";
        Option2button.querySelector("#Option2").style.height = "14px";
        Option2button.querySelector("#Option2").style.width = "14px";
        SearchFromCurLocButton.style.height = "5vh";
        SearchFromCurLocButton.style.width = "3vw";
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
        Option1button.style.width = "1.9vw";
        Option1button.style.backgroundColor = "white";
        Option1button.querySelector("#Option1").style.height = "14px";
        Option1button.querySelector("#Option1").style.width = "14px";
        SearchFromRouteButton.style.height = "5vh";
        SearchFromRouteButton.style.width = "3vw";
        SearchFromRouteButton.style.backgroundColor = "#14BD7E";
        SearchFromRouteButton.querySelector("#Option2").style.height = "30px";
        SearchFromRouteButton.querySelector("#Option2").style.width = "30px";
        isOption2 = true;
        isOption1 = false;
    }
}

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

function FindAddressofSearchCoords(lon, lat) {
    var mapCenterPosition = new Tmapv3.LatLng(lat, lon);
    map.setCenter(mapCenterPosition);
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
            const curAddr = document.getElementsByClassName("main__CurrentLocationAddress");
            curAddr[0].innerHTML = result;
        },
        error : function(request, error) {
            console.log("code:" + request.status + "\n"
                    + "message:" + request.responseText + "\n"
                    + "error:" + error);
        }
    });
}

function SelectStIdLogo(stId, name, radius){
    return `
        <div class='main__ResultList'> 
            <div class='main__ResultList__Title'>
                <img class='main__ResultList__Title_Logo' src = 'img/GasStation_Image/${stId}.png'>
                <div class="main__TitleArea">
                    <div id="stName">${name}</div>
                    <div id="stRadius">${Math.round(radius*10)/10}km</div>
                </div>
            </div>
    `;
}

function ShowResult(ResultArray, positionBounds){
    ResetMarkerArray(HGmarkerArr);
    ResetMarkerArray(LPGmarkerArr);
    ResetMarkerArray(markerArr);
    ResultHtml = "";
    const resultArea = document.querySelector(".main__SearchResult");
    resultArea.innerHTML = "";
    for(var k in ResultArray){
        if(ResultArray[k].hhPrice==0 && ResultArray[k].ggPrice==0 && ResultArray[k].llPrice==0){
            continue;
        }
        var noorLat = Number(ResultArray[k].noorLat);
        var noorLon = Number(ResultArray[k].noorLon);

        ResultHtml += SelectStIdLogo(ResultArray[k].stId, ResultArray[k].name, ResultArray[k].radius);
        var pointCng = new Tmapv3.Point(noorLon, noorLat);
        var projectionCng = new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(pointCng);
        
        var lat = projectionCng._lat;
        var lon = projectionCng._lng;

        var markerPosition = new Tmapv3.LatLng(lat, lon);

        marker = new Tmapv3.Marker({
            position : map.getCenter(),
            icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_m.png",
            map:map
            });
        markerArr.push(marker);

        if(ResultArray[k].llPrice != 0){
            LPGmarker = new Tmapv3.Marker({
                position : markerPosition,
                iconHTML: `<div class='Map_Marker'><img src="../img/mapInfo.png"><span id="LL">LPG : ${ResultArray[k].llPrice}</span></div>`,
                iconSize : Tmapv3.Size(10, 20),
                map:map
            });
            LPGmarkerArr.push(LPGmarker);
            
        }
        else {
            HGmarker = new Tmapv3.Marker({
                position : markerPosition,
                iconHTML: `<div class='Map_Marker'><img src ='/public/img/mapInfo.png'><span id="HG">휘 : ${ResultArray[k].hhPrice} &nbsp; 경 : ${ResultArray[k].ggPrice}</span></div>`,
                iconSize : Tmapv3.Size(10, 20),
                map:map
            });
            HGmarkerArr.push(HGmarker);
        }
        if(ResultArray[k].highHhSale != 0){
            ResultHtml += `<div class='main__ResultList__Contents'>
                        휘발유: <span> ${ResultArray[k].hhPrice == 0 ? '-' : ResultArray[k].hhPrice} </span>
                        <i class="fa-solid fa-circle-check"></i>&nbsp;
                        경유: <span> ${ResultArray[k].ggPrice == 0 ? '-' : ResultArray[k].ggPrice} </span>&nbsp;&nbsp;
                        LPG: <span> ${ResultArray[k].llPrice == 0 ? '-' : ResultArray[k].llPrice} </span></div></div>`;

        }
        else {
            ResultHtml += `<div class='main__ResultList__Contents'>
                        휘발유: <span> ${ResultArray[k].hhPrice == 0 ? '-' : ResultArray[k].hhPrice} </span>&nbsp;&nbsp;
                        경유: <span> ${ResultArray[k].ggPrice == 0 ? '-' : ResultArray[k].ggPrice} </span>&nbsp;&nbsp;
                        LPG: <span> ${ResultArray[k].llPrice == 0 ? '-' : ResultArray[k].llPrice} </span></div></div>`;

        }
        positionBounds.extend(markerPosition);
    }
    // AddEventToMarker(LPGmarkerArr);
    // AddEventToMarker(HGmarkerArr);
    resultArea.innerHTML = ResultHtml;
    addEventToResult(ResultArray);
    map.fitBounds(positionBounds);	
}

function SearchNearGasStation(){
    if(DetailTabDisplay == true){
        closeDetailTab();
    }
    searchOption=0;
    $("#sort_GG").css("opacity", "0.5");
    $("#sort_HH").css("opacity", "0.5");
    $("#sort_LL").css("opacity", "0.5");
    $.ajax({
        method:"GET", // 요청 방식
        url:"https://apis.openapi.sk.com/tmap/pois/search/around?version=1&format=json&callback=result", 
        data:{
            "categories" : "주유소;가스충전소",
            "resCoordType" : "EPSG3857",
            "searchType" : "name",
            "searchtypCd" : "A",
            "radius" : 3,
            "reqCoordType" : "WGS84GEO",
            "centerLon" : map.getCenter()._lng,
            "centerLat" : map.getCenter()._lat,
            "appKey" : "l7xx7250af6176574c63a12302edf09d020c",
            "count" : 100
        },
        success:function(response) {
            console.log(response);
            FindAddressofSearchCoords(map.getCenter()._lng,map.getCenter()._lat);

            if(response==null){
                const NoResult = "검색 결과가 존재하지 않습니다.";
                document.getElementById("searchResult").innerHTML = NoResult;
            }
            else {
                resultpoisData = response.searchPoiInfo.pois.poi;
                positionBounds = new Tmapv3.LatLngBounds();
                ResetMarkerArray(LPGmarkerArr);
                ResetMarkerArray(HGmarkerArr);
                ShowResult(resultpoisData, positionBounds); // 거리순 결과 데이터출력
            }
        },
        error:function(request, error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function ResetMarkerArray(markerArr) {
    if(markerArr.length > 0){
        for(var i in markerArr){
            markerArr[i].setMap(null);
        }
        markerArr = [];
    }
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
    const GSTDetailTab = document.getElementsByClassName("main__GSTDetailTab");
    GSTDetailTab[0].style.marginLeft = "0vw";
    const SideSearchBar = document.getElementsByClassName("main__SideSearchBar");
    SideSearchBar[0].style.borderRadius = "0";
    DetailTabDisplay = true;
    const ResultTitle = event.target.closest(".main__ResultList");
    const SelectedTitle = ResultTitle.querySelector("#stName");

    for(var k=0;k<ResultArray.length;k++){
        if(ResultArray[k].name == SelectedTitle.innerHTML){
            break;
        }
    }
    var noorLat = Number(ResultArray[k].noorLat);
    var noorLon = Number(ResultArray[k].noorLon);

    var pointCng = new Tmapv3.Point(noorLon, noorLat);
    var projectionCng = new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(pointCng);
    
    var lat = projectionCng._lat;
    var lon = projectionCng._lng;

    var markerPosition = new Tmapv3.LatLng(lat, lon);

    map.setCenter(markerPosition);
    map.zoomIn();

    FillSTDetail(ResultArray[k]);
    //query 문 작성
    $.ajax({
        method:"GET", // 요청 방식
        url:"http://localhost:8080/api/v1/gas-station/" + ResultArray[k].name + "/" + ResultArray[k].roadName + "/" 
        + ResultArray[k].buildingNo1 + "/" + transStId(ResultArray[k].stId) + "/month", 
        success:function(response) {
            console.log(response);
        },
        error:function(request, error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
    
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
        //const sortlen = HHsort.length;
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
        //const sortlen = HHsort.length;
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
    ResetMarkerArray(HGmarkerArr);
    ResetMarkerArray(LPGmarkerArr);
    ResetMarkerArray(markerArr);
    var positionBounds = new Tmapv3.LatLngBounds();
    var i =0;

    for(var k=0;k<ResultArray.length;k++){      
        if(i > 9) break;
        
        var noorLat = Number(ResultArray[k].noorLat);
        var noorLon = Number(ResultArray[k].noorLon);

        ResultHtml += SelectStIdLogo(ResultArray[k].stId, ResultArray[k].name, ResultArray[k].radius);
        var pointCng = new Tmapv3.Point(noorLon, noorLat);
        var projectionCng = new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(pointCng);
        
        var lat = projectionCng._lat;
        var lon = projectionCng._lng;

        var markerPosition = new Tmapv3.LatLng(lat, lon);

        marker = new Tmapv3.Marker({
            position : map.getCenter(),
            iconHTML: "<div class='Map_Marker'><img src ='/public/img/mapInfo.png'><span id='Name'>현위치</span></div>",
            iconSize : Tmapv3.Size(10, 20),
            map:map
            });
        markerArr.push(marker);

        marker = new Tmapv3.Marker({
            position : markerPosition,
            iconHTML: `<div class='Map_Marker'><img src ='/public/img/mapInfo.png'><span id="Name">${ResultArray[k].name}</span></div>`,
            iconSize : Tmapv3.Size(10, 20),
            map:map
        });
        markerArr.push(marker);
        i++;

        if(searchOption==1){
            if(ResultArray[k].highHhSale != 0){
                ResultHtml += `<div class='main__ResultList__Contents'>
                        휘발유: <span>${ResultArray[k].hhPrice == 0 ? '-' : ResultArray[k].hhPrice}</span>
                        <i class="fa-solid fa-circle-check"></i>
                        </div></div>`;
            }
            else{
                ResultHtml += `<div class='main__ResultList__Contents'>
                        휘발유: <span>${ResultArray[k].hhPrice == 0 ? '-' : ResultArray[k].hhPrice}</span>
                        </div></div>`;
            } 
        }
        else if(searchOption==2){
            ResultHtml += `<div class='main__ResultList__Contents'>
                        경유: <span> ${ResultArray[k].ggPrice == 0 ? '-' : ResultArray[k].ggPrice} </span>&nbsp;&nbsp;
                        </div></div>`;
        }
        else if(searchOption==3){
            ResultHtml += `<div class='main__ResultList__Contents'>
                        LPG: <span> ${ResultArray[k].llPrice == 0 ? '-' : ResultArray[k].llPrice} </span>&nbsp;&nbsp;
                        </div></div>`;
        }
        positionBounds.extend(markerPosition);
    }
    // AddEventToMarker(markerArr);
    resultArea.innerHTML = ResultHtml;
    addEventToResult(ResultArray);
    map.fitBounds(positionBounds);	
}

function FillSTDetail(ResultArrayElem){
    const ST_name = document.getElementById("GSTdetail__Name");
    ST_name.innerHTML = ResultArrayElem.name;

    const ST_Price = document.getElementsByClassName("main__GSTdetail__Contents__Oilprice");
    var PriceinnerHtml = "";
    PriceinnerHtml += "<i class='fa-solid fa-gas-pump'></i>";
    if(ResultArrayElem.hhPrice != 0){
        PriceinnerHtml += "휘발유 : " + ResultArrayElem.hhPrice + "&nbsp&nbsp";
    }
    if(ResultArrayElem.ggPrice != 0) {
        PriceinnerHtml += "경유 : " + ResultArrayElem.ggPrice; + "&nbsp&nbsp";
    }
    if(ResultArrayElem.llPrice != 0) {
        PriceinnerHtml += "LPG : " + ResultArrayElem.llPrice;
    }
    ST_Price[0].innerHTML = PriceinnerHtml;

    const ST_addr = document.getElementById("GSTdetail_addr");
    ST_addr.innerHTML = `${ResultArrayElem.upperAddrName} ${ResultArrayElem.middleAddrName} ${ResultArrayElem.lowerAddrName}
                        ${ResultArrayElem.detailAddrName} ${ResultArrayElem.firstNo}
                        ( ${ResultArrayElem.roadName} ${ResultArrayElem.buildingNo1} )`

    const ST_phone = document.getElementById("GSTdetail_phone");
    var telNolen = ResultArrayElem.telNo.length;
    if(ResultArrayElem.telNo.includes('02')) {
        var psTelNo = ResultArrayElem.telNo.slice(0, 2) + '-' + ResultArrayElem.telNo.slice(2, telNolen-4) + '-' + ResultArrayElem.telNo.slice(telNolen-4, telNolen);
        console.log(psTelNo);
    }
    else {
        var psTelNo = ResultArrayElem.telNo.slice(0, 3) + '-' + ResultArrayElem.telNo.slice(3, telNolen-4) + '-' + ResultArrayElem.telNo.slice(telNolen-4, telNolen);
    }

    ST_phone.innerHTML = psTelNo;

    const ST_url = document.getElementById("GSTdetail_url");
    ST_url.innerHTML = `www.${ResultArrayElem.stId}.com`;

    const ST_PriceTable = document.getElementsByClassName("main__GSTdetail__Contents__OilPriceTable__Contents");
    ST_PriceTable[0].innerHTML = "";
    if(ResultArrayElem.hhPrice != 0){
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
                                    휘발유
                                    </div>
                                    <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                                        <span id="Contents_HH">${ResultArrayElem.hhPrice}</span>
                                    </div>`
        ST_PriceTable[0].append(ST_OilPrice);
    }
    
    if(ResultArrayElem.ggPrice != 0) {
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
                                    경유
                                    </div>
                                    <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                                        <span id="Contents_HH">${ResultArrayElem.ggPrice}</span>
                                    </div>`
        ST_PriceTable[0].append(ST_OilPrice);
    }

    if(ResultArrayElem.highHhPrice != 0) {
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
                                    고급 휘발유
                                    </div>
                                    <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                                        <span id="Contents_HH">${ResultArrayElem.highHhPrice}</span>
                                    </div>`
        ST_PriceTable[0].append(ST_OilPrice);
    }

    if(ResultArrayElem.llPrice != 0) {
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
                                    LPG
                                    </div>
                                    <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                                        <span id="Contents_HH">${ResultArrayElem.llPrice}</span>
                                    </div>`
        ST_PriceTable[0].append(ST_OilPrice);
    }
}


function transStId(stId){
    switch(stId){
        case 'S-Oil': return 'S-OIL';
        case '오일뱅크' : return '현대오일뱅크';
        case '알뜰' : return '알뜰주유소';
        case 'ex-OIL' : return '알뜰(ex)';
        case 'NH-OIL' : return 'NH-OIL';
        case 'SK' : return 'SK에너지';
        case 'GS' : return 'GS칼텍스';
        case '자가상표' : return '자가상표';
        case 'E1' : return 'E1';
    }
}

const labels = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
  ];

  const data = {
    labels: labels,
    datasets: [{
      label: 'My First dataset',
      backgroundColor: 'rgb(255, 99, 132)',
      borderColor: 'rgb(255, 99, 132)',
      data: [0, 10, 5, 2, 20, 30, 45],
    }]
  };

  const config = {
    type: 'line',
    data: data,
    options: {}
  }

//   const myChart = new Chart(
//     document.getElementById('myChart'),
//     config
//   );