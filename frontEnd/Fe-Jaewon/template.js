let SideBarDisplay = true;
let isOption1 = true;
let isOption2 = false;
let myPosition_lat;
let myPosition_lng;
let searchOption = 1;

function sortHhPrice() {
    console.log("jaewon");
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
        HideSideBarButton.innerHTML = "<img src='svg_image/🦆 icon _chevron right_.svg'>";
    }
    else {
        HideSideBar[0].style.marginLeft = "0vw";
        SideBarDisplay = true;
        HideSideBarButton.innerHTML = "<img src='svg_image/🦆 icon _chevron left_.svg'>";
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

var map, marker;
var markerArr = [], labelArr = [];
let count=0;



async function initTmap(position) {

	// 1. 지도 띄우기
	map = new Tmapv2.Map("map_div", {
		center : new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude),
		width : "100vw",
		height : "82vh",
		zoom : 17,
		zoomControl : true,
		scrollwheel : true
	});
    
    reverseGeo(position.coords.longitude,position.coords.latitude);
	// 2. POI 통합 검색 API 요청
	$("#main__btn_select").click(function(){
		$.ajax({
			method:"GET", // 요청 방식
			url:"https://apis.openapi.sk.com/tmap/pois/search/around?version=1&format=json&callback=result", // url 주소
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
                reverseGeo(map.getCenter()._lng,map.getCenter()._lat);
				if(response==null){
					const jaewon = "검색 결과가 존재하지 않습니다.";
					$("#searchResult").html(jaewon);
				}
				else {
					var resultpoisData = response.searchPoiInfo.pois.poi;
					// console.log(resultpoisData);
					// 2. 기존 마커, 팝업 제거
					if(markerArr.length > 0){
						for(var i in markerArr){
							markerArr[i].setMap(null);
						}
						markerArr = [];
					}

					if(labelArr.length > 0){
						for(var i in labelArr){
							labelArr[i].setMap(null);
						}
						labelArr = [];
					}
					
					var innerHtml = ""; // Search Reulsts 결과값 노출 위한 변수
					var positionBounds = new Tmapv2.LatLngBounds(); //맵에 결과물 확인 하기 위한 LatLngBounds객체 생성
					
					if(searchOption==1){
						let HHsort = resultpoisData.sort(function(a, b) {
							return a.hhPrice - b.hhPrice;
						})
                        ShowResult(HHsort);
					}
					else if(searchOption==2){
						let GGsort = resultpoisData.sort(function(a, b) {
							return a.ggPrice - b.ggPrice;
						})
                        ShowResult(GGsort);
					}
					else if(searchOption==3){
						let LLsort = resultpoisData.sort(function(a, b) {
							return a.llPrice - b.llPrice;
						})
                        ShowResult(LLsort);
					}

					// console.log(resultpoisData.hhPrice);
                    function ShowResult(ResultArray){
                        for(var k in ResultArray){
						
                            // POI 마커 정보 저장
                            var noorLat = Number(ResultArray[k].noorLat);
                            var noorLon = Number(ResultArray[k].noorLon);
                            var name = ResultArray[k].name;
                            
                            // POI 정보의 ID
                            var id = ResultArray[k].id;
                            console.log(ResultArray[k].stId);

                            if(ResultArray[k].stId =='GS'){
                                //console.log("이거 왜 안드가");
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/GS.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            else if(ResultArray[k].stId =='SK'){
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/SK.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            else if(ResultArray[k].stId =='S-Oil'){
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/S-OIL.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            else if(ResultArray[k].stId =='오일뱅크'){
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/hyundai.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            else if(ResultArray[k].stId =='NH-OIL'){
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/nh.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            else if(ResultArray[k].stId =='알뜰'){
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/알뜰.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            else if(ResultArray[k].stId =='ex-OIL'){
                                innerHtml += "<li><div><img class='GasStationLogo' src='/svg_image/GasStation_Image/ex.png'>"
                                + "<span id='searchResultTitle'>"+name+"</span>"
                            }
                            // 좌표 객체 생성
                            var pointCng = new Tmapv2.Point(noorLon, noorLat);
                            
                            // EPSG3857좌표계를 WGS84GEO좌표계로 변환
                            var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(pointCng);
                            
                            var lat = projectionCng._lat;
                            var lon = projectionCng._lng;
                            
                            // 좌표 설정
                            var markerPosition = new Tmapv2.LatLng(lat, lon);
                            
                            // Marker 설정
                            marker = new Tmapv2.Marker({
                                position : markerPosition,
                                //icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_a.png",
                                icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_" + k + ".png",
                                iconSize : new Tmapv2.Size(24, 38),
                                title : name,
                                map:map
                            });
                            // 결과창에 나타날 검색 결과 html
                            if(ResultArray[k].hhPrice==0 || ResultArray[k].ggPrice==0){
                                innerHtml += "<br><span class='ResultContents'>유가 정보가 없습니다.</span>"
                                + "</div></li>";
                            }
                            else {
                            innerHtml += "<br><span class='ResultContents'> 휘발유: " + ResultArray[k].hhPrice + "</span>"
                            + "<span class='ResultContents'> 경유 : " + ResultArray[k].ggPrice + "</span>"
                            +"<button type='button' name='sendBtn' onClick='poiDetail("+id+");'>상세보기</button></div></li>";
                            }
                            // 마커들을 담을 배열에 마커 저장
                            markerArr.push(marker);
                            positionBounds.extend(markerPosition);	// LatLngBounds의 객체 확장
                        }
                    }
					$("#searchResult").html(innerHtml);	//searchResult 결과값 노출
					map.panToBounds(positionBounds);	// 확장된 bounds의 중심으로 이동시키기
					map.zoomOut();
				}
				// console.log(count);
			},
			error:function(request,status,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
}

function reverseGeo(lon, lat) {
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
            // 3. json에서 주소 파싱
            var arrResult = response.addressInfo;

            //법정동 마지막 문자 
            var lastLegal = arrResult.legalDong
                    .charAt(arrResult.legalDong.length - 1);

            // 새주소
            newRoadAddr = arrResult.city_do + ' '
                    + arrResult.gu_gun + ' ';

            if (arrResult.eup_myun == ''
                    && (lastLegal == "읍" || lastLegal == "면")) {//읍면
                newRoadAddr += arrResult.legalDong;
            } else {
                newRoadAddr += arrResult.eup_myun;
            }
            newRoadAddr += ' ' + arrResult.roadName + ' '
                    + arrResult.buildingIndex;

            // 새주소 법정동& 건물명 체크
            if (arrResult.legalDong != ''
                    && (lastLegal != "읍" && lastLegal != "면")) {//법정동과 읍면이 같은 경우

                if (arrResult.buildingName != '') {//빌딩명 존재하는 경우
                    newRoadAddr += (' (' + arrResult.legalDong
                            + ', ' + arrResult.buildingName + ') ');
                } else {
                    newRoadAddr += (' (' + arrResult.legalDong + ')');
                }
            } else if (arrResult.buildingName != '') {//빌딩명만 존재하는 경우
                newRoadAddr += (' (' + arrResult.buildingName + ') ');
            }

            // 구주소
            jibunAddr = arrResult.city_do + ' '
                    + arrResult.gu_gun + ' '
                    + arrResult.legalDong + ' ' + arrResult.ri
                    + ' ' + arrResult.bunji;
            //구주소 빌딩명 존재
            if (arrResult.buildingName != '') {//빌딩명만 존재하는 경우
                jibunAddr += (' ' + arrResult.buildingName);
            }

            result = "<div>" + newRoadAddr + "</div>";
            $(".main__CurrentLocationAddress").html(result);
            
        },
            error : function(request, status, error) {
                console.log("code:" + request.status + "\n"
                        + "message:" + request.responseText + "\n"
                        + "error:" + error);
            }
        });
    }
// 4. POI 상세 정보 API
function poiDetail(poiId){
	$.ajax({
		method:"GET",
		url:"	https://apis.openapi.sk.com/tmap/pois/"+poiId+"?version=1&resCoordType=EPSG3857&format=json&callback=result&appKey="+"l7xx7250af6176574c63a12302edf09d020c",
		async:false,
		success:function(response){
			var detailInfo = response.poiDetailInfo;
			var name = detailInfo.name;
			var address = detailInfo.address;
			
			var noorLat = Number(detailInfo.frontLat);
			var noorLon = Number(detailInfo.frontLon);
			
			var pointCng = new Tmapv2.Point(noorLon, noorLat);
			var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(pointCng);
			
			var lat = projectionCng._lat;
			var lon = projectionCng._lng;
			
			var labelPosition = new Tmapv2.LatLng(lat, lon);
			
			var content = "<div style=' border-radius:10px 10px 10px 10px;background-color:#2f4f4f; position: relative;"
					+ "line-height: 15px; width:50px; padding: 5 5px 2px 4; right:65px;'>"
					+ "<div style='font-size: 11px; font-weight: bold ; line-height: 15px; color : white'>"
					+ "여기에다가 가격 넣는거 어떰??"
					+ "</div>" + "</div>";
			
			var labelInfo = new Tmapv2.Label({
				position : labelPosition,
				content : content,
				map:map
			});//popup 생성
			
			labelArr.push(labelInfo);
			
		},
		error:function(request,status,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

