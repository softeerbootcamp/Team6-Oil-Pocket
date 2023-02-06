var map;
var markerInfo;
var polyline;
//출발지,도착지 마커
var marker_s, marker_e, marker_p;
//경로그림정보
var drawInfoArr = [];
var drawInfoArr2 = [];

var chktraffic = [];
var resultdrawArr = [];
var resultMarkerArr = [];
var markerArr = [];
var pointList = [];
var lineString = "";
function initTmap() {
    console.log("jaewon");
    // 1. 지도 띄우기
    map = new Tmapv2.Map("map_div", {
        center : new Tmapv2.LatLng(37.56520450, 126.98702028),
        width : "60%",
        height : "400px",
        zoom : 17,
        position:"center",
        zoomControl : true,
        scrollwheel : true
    });

    // 2. 시작, 도착, 사용자 현재위치 심볼찍기
    // 시작
    marker_s = new Tmapv2.Marker(
            {
                position : new Tmapv2.LatLng(37.56643408004171,
                        126.98504447937059),
                icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png",
                iconSize : new Tmapv2.Size(24, 38),
                map : map
            });

    //도착
    marker_e = new Tmapv2.Marker(
            {
                position : new Tmapv2.LatLng(37.263416837831436,
                        127.02860151018457),
                icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png",
                iconSize : new Tmapv2.Size(24, 38),
                map : map
            });
            
    //사용자 현재위치
    marker_u = new Tmapv2.Marker(
        {
            position : new Tmapv2.LatLng(37.41490752771668,
                    127.08430456797187),
            icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_g_m_u.png",
            iconSize : new Tmapv2.Size(24, 38),
            map : map
        });
    
    //경로 그리기
    addRoute();
    
    
    $("#btn_select")
            .click(
                function() {
                    var searchKeyword = $('#searchKeyword').val();
                    $.ajax({
                        method:"POST",
                        url:"https://apis.openapi.sk.com/tmap/poi/findPoiRoute",
                        async:false,
                        data:{
                            "appKey" : "l7xx7250af6176574c63a12302edf09d020c",
                            "count" : "50",
                            "endX" : 127.02860151018457,
                            "endY" : 37.263416837831436,
                            "lineString" : lineString,
                            "page" : "1",
                            "radius" : "0.0",
                            "resCoordType" : "WGS84GEO",
                            "searchKeyword" : "주유소",
                            "searchType" : "keyword",
                            "sort" : "score",
                            "direction" : "all",
                            "searchCategory" : "C02",
                            "startX" : 126.98504447937059,
                            "startY" : 37.56643408004171,
                            "userX" : 127.08430456797187,
                            "userY" : 37.41490752771668
                        },
                        success:function(response){
                            var resultpoisData = response.searchPoiInfo.pois.poi;
                            
                            // 기존 마커, 팝업 제거
                            if(markerArr.length > 0){
                                for(var i in markerArr){
                                    markerArr[i].setMap(null);
                                }
                            }
                            var innerHtml ="";	// Search Reulsts 결과값 노출 위한 변수
                            
                            for(var k in resultpoisData){
                                
                                var centerLat = Number(resultpoisData[k].centerLat);
                                var centerLon = Number(resultpoisData[k].centerLon);
                                var name = resultpoisData[k].name;
                                
                                var markerPosition = new Tmapv2.LatLng(centerLat, centerLon);
                                
                                marker = new Tmapv2.Marker({
                                    position : markerPosition,
                                    icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_a.png",
                                    iconSize : new Tmapv2.Size(24, 38),
                                    title : name,
                                    map:map
                                });
                                
                                markerArr.push(marker);
                            }
                        },
                        error:function(request,status,error){
                            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });
                    
                });
}

//경로 그리기
function addRoute(){
    //JSON TYPE EDIT [S]
    $
            .ajax({
                type : "POST",
                url : "https://apis.openapi.sk.com/tmap/routes?version=1&format=json&callback=result",
                async : false,
                data : {
                    "appKey" : "l7xx7250af6176574c63a12302edf09d020c",
                    "startX" : "126.98504447937059",
                    "startY" : "37.56643408004171",
                    "endX" : "127.02860151018457",
                    "endY" : "37.263416837831436"
                },
                success : function(response) {

                    var resultData = response.features;
                    var PTbounds = new Tmapv2.LatLngBounds();
                    
                    var drawInfoArr = [];

                    var tDistance = "총 거리 : "
                            + (resultData[0].properties.totalDistance / 1000)
                                    .toFixed(1) + "km,";
                    var tTime = " 총 시간 : "
                            + (resultData[0].properties.totalTime / 60)
                                    .toFixed(0) + "분,";
                    var tFare = " 총 요금 : "
                            + resultData[0].properties.totalFare
                            + "원,";
                    var taxiFare = " 예상 택시 요금 : "
                            + resultData[0].properties.taxiFare
                            + "원";
                    
                    for ( var i in resultData) { //for문 [S]
                        var geometry = resultData[i].geometry;
                        var properties = resultData[i].properties;
                        // if(geometry.type == "Points")
                        // lineString += geometry.coordinates[0] + "," + geometry.coordinates[1] + "_";
                        if (geometry.type == "LineString") {
                            for ( var j in geometry.coordinates) {
                                // if(j == geometry.coordinates.length){
                                //     console.log("if");
                                //     lineString += geometry.coordinates[j][0] + "," + geometry.coordinates[j][1];
                                // }
                                // else {
                                    
                                // }
                                lineString += geometry.coordinates[j][0] + "," + geometry.coordinates[j][1] + "_";
                                
                                
                                console.log(lineString);
                                // 포인트객체의 정보로 좌표값 변환 객체로 저장
                                var convertChange = new Tmapv2.LatLng(
                                        geometry.coordinates[j][1],
                                        geometry.coordinates[j][0]);
                                var startPt = new Tmapv2.LatLng(geometry.coordinates[j][1],geometry.coordinates[j][0]);
                                PTbounds.extend(startPt);
                                // 배열에 담기
                                drawInfoArr.push(convertChange);
                                
                            }
                        }
                        else {
                            var startPt = new Tmapv2.LatLng(geometry.coordinates[1],geometry.coordinates[0]);
                            PTbounds.extend(startPt);
                        }
                    }//for문 [E]
                    
                    map.fitBounds(PTbounds);
                    
                    polyline = new Tmapv2.Polyline({
                        path: drawInfoArr,
                        strokeColor: "#DD0000",
                        strokeWeight: 6,
                        map: map
                    });
                    
                },
                error : function(request, status, error) {
                    console.log("code:"
                            + request.status + "\n"
                            + "message:"
                            + request.responseText
                            + "\n" + "error:" + error);
                }
            });
}