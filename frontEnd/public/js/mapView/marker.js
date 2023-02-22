import {map} from "./HelperFunction";

let marker;
let markerArr = [];
let curzIndex = 101;

function SetMarkerofResult(Resultelem){
    var noorLat = Number(Resultelem.noorLat);
    var noorLon = Number(Resultelem.noorLon);

    var pointCng = new Tmapv3.Point(noorLon, noorLat);
    var projectionCng = new Tmapv3.Projection.convertEPSG3857ToWGS84GEO(pointCng);
    
    var lat = projectionCng._lat;
    var lon = projectionCng._lng;

    var markerPosition = new Tmapv3.LatLng(lat, lon);

    return markerPosition;
}

function SetMarkerofMapCenter(){
    marker = new Tmapv3.Marker({
        position : map.getCenter(),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_m.png",
        map:map
        });
    markerArr.push(marker);
}

function SetHGmarker(markerPosition, Resultelem){
    marker = new Tmapv3.Marker({
        position : markerPosition,
        iconHTML: `<div class='Map_Marker_HG' id='${Resultelem.name}'><img class='img_HG'><span id="H">${Resultelem.hhPrice}</span>
                    <span id='G'>${Resultelem.ggPrice}</span></div>`,
        iconSize : Tmapv3.Size(10, 20),
        map:map
    });
    markerArr.push(marker);
}

function SetHmarker(markerPosition, Resultelem){
    marker = new Tmapv3.Marker({
        position : markerPosition,
        iconHTML: `<div class='Map_Marker_hprice' id='${Resultelem.name}'><img class='img_HnG'><span id="Price">${Resultelem.hhPrice}</span></div>`,
        iconSize : Tmapv3.Size(10, 20),
        map:map
    }); 
    markerArr.push(marker);
}

function SetGmarker(markerPosition, Resultelem){
    marker = new Tmapv3.Marker({
        position : markerPosition,
        iconHTML: `<div class='Map_Marker_gprice' id='${Resultelem.name}'><img class='img_HnG'><span id="Price">${Resultelem.ggPrice}</span></div>`,
        iconSize : Tmapv3.Size(10, 20),
        map:map
    });
    markerArr.push(marker);
}

function SetLPGmarker(markerPosition, Resultelem){
    marker = new Tmapv3.Marker({
        position : markerPosition,
        iconHTML: `<div class='Map_Marker_LPG' id='${Resultelem.name}'><img class='img_LPG'><span id="LL">${Resultelem.llPrice}</span></div>`,
        iconSize : Tmapv3.Size(10, 20),
        map:map
    });
    markerArr.push(marker);
}

function ResetMarkerArray(markerArr) {
    if(markerArr.length > 0){
        for(var i in markerArr){
            markerArr[i].setMap(null);
        }
        markerArr = [];
    }
}

function bringMarkerToFront(e) {
    const MouseonMarker = e.target.closest('.vsm-marker');
    MouseonMarker.style.zIndex = `${curzIndex}`;
    curzIndex++;
}
export {SetLPGmarker, SetMarkerofMapCenter, SetMarkerofResult, 
    SetHGmarker, SetHmarker, SetGmarker, ResetMarkerArray, marker, 
    markerArr, bringMarkerToFront};