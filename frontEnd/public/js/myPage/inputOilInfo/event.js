import { _$, _$_ALL, addEvent, changeArrayCSS, changeCSS, makeLighter } from "../../common/function";
import { fetchGasStationSearch, fetchOilRegister } from "./fetch";
import { parseOilPriceIntoKorean } from "./helperFunction";

NodeList.prototype.forEach = Array.prototype.forEach;
let debounceTimer = "";

const eventToOilSelectArea = ($container) => {
    let oilSelectFlag = false;

    const $oilSelect = _$(".oilInfoArea__oilSelect", $container);
    const $oilSelectText = _$(".oilInfoArea__oilSelect > span", $container);
    const $oilSelectImg = _$(".oilInfoArea__oilSelect > img", $container);
    const $oilValues = _$_ALL(".oilInfoArea__oilValue", $container);

    addEvent($oilSelect, [
        () => {
            if(oilSelectFlag) {
                changeCSS($oilSelectImg, "transform", "rotate(0deg)");
                changeArrayCSS($oilValues, "top", 0);
                changeArrayCSS($oilValues, "outline", "none");
            }
            else {
                changeCSS($oilSelectImg, "transform", "rotate(180deg)")
                $oilValues.forEach(($oilValue, index) => {
                    changeCSS($oilValue, "top", `${(index + 1) * 100}%`);
                    changeCSS($oilValue, "outline", "0.2vh solid #14BD72");
                })
            }
        },
        () => oilSelectFlag = !oilSelectFlag
    ])

    $oilValues.forEach(($oilValue) => addEvent($oilValue, [
        () => $oilSelectText.innerHTML = $oilValue.innerHTML,
        () => changeCSS($oilSelectText, "color", "#000"),
        () => changeCSS($oilSelectImg, "transform", "rotate(0deg"),
        () => changeArrayCSS($oilValues, "top", 0),
        () => changeArrayCSS($oilValues, "outline", "none")
    ]));
}

const eventToOilPriceInput = ($container) => {
    const $oilPrcieInput = $container.querySelector(".oilInfoArea__oilPriceInput");
    const $oilPirceInfo = $container.querySelector(".oilInfoArea__oilPrice > span");
    const $effectImage = $container.querySelector(".oilInfoArea__effectImage");

    addEvent($oilPrcieInput, [
        () => $oilPirceInfo.innerHTML = $oilPrcieInput.value,
        () => $oilPirceInfo.innerHTML = parseOilPriceIntoKorean($oilPrcieInput, $effectImage), 
    ], "keyup")
}

const eventToOilSearchInput = ($container) => {
    const $oilSearchInput = _$(".oilInfoArea__searchInput", $container);
    const $oilSearchResultBox = _$(".oilInfoArea__oilSearchResultBox", $container);

    addEvent($oilSearchInput, [
        () => {
            if(debounceTimer) {
                clearTimeout(debounceTimer);
            }

            if($oilSearchInput.value === "") {
                changeCSS($oilSearchResultBox, "opacity", "0");
                $oilSearchResultBox.scrollTop = 0;
            }
            else {
                debounceTimer = setTimeout(() => {
                    // 검색 관련 통신 함수
                    fetchGasStationSearch($oilSearchResultBox, $oilSearchInput.value);

                    // 검색 결과 불러와서 넣어주는 함수
                    makeLighter($oilSearchResultBox);
                    changeCSS($oilSearchResultBox, "top", "130%");
                    $oilSearchResultBox.scrollBottom = $oilSearchResultBox.scrollHeight;
                }, 300);
            }
        }
    ], "input");
}

const eventToSearchValue = ($searchValue) => {
    const $searchInput = _$(".oilInfoArea__searchInput");
    const $searchResultBox = _$(".oilInfoArea__oilSearchResultBox");
    const $gasNameSection = _$("h1", $searchValue);

    addEvent($searchValue, [
        () => $searchInput.value = $gasNameSection.innerHTML,
        () => $searchInput.dataset.stationNo = $gasNameSection.closest(".oilInfoArea__oilSearchValue").dataset.stationNo,
        () => $searchResultBox.innerHTML = ``,
        () => $searchInput.disabled = true
    ]);
}

const eventToRegisterBtn = ($container) => {
    const $registerBtn = _$(".oilInfoArea__registerBtn", $container);
    addEvent($registerBtn, [() => fetchOilRegister($container)]); 
}

export { eventToOilSelectArea, eventToOilPriceInput, eventToOilSearchInput, eventToSearchValue, eventToRegisterBtn }