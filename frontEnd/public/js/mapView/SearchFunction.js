function AdjustLocationToCurLocation(lon, lat){
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

            var lastLegal = arrResult.legalDong.charAt(arrResult.legalDong.length - 1);

            var newRoadAddr = arrResult.city_do + ' ' + arrResult.gu_gun + ' ';

            if (arrResult.eup_myun == '' && (lastLegal == "읍" || lastLegal == "면")) {
                newRoadAddr += arrResult.legalDong;
            } 
            else {
                newRoadAddr += arrResult.eup_myun;
            }
            newRoadAddr += ' ' + arrResult.roadName + ' ' + arrResult.buildingIndex;

            var result = "<div>" + newRoadAddr + "</div>";
            const curAddr = document.getElementsByClassName("main__CurrentLocationAddress");
            curAddr[0].innerHTML = result;
        },
        error : function(request, error) {
            console.log("code:" + request.status + ", message:" + request.responseText + ", error:" + error);
        }
    });
}

export {AdjustLocationToCurLocation};