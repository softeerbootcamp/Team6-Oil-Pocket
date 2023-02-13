import { _$, _$_ALL, addEvent, changeArrayCSS, changeCSS } from "../../common/function";
import { parseOilPriceIntoKorean } from "./helperFunction";

NodeList.prototype.forEach = Array.prototype.forEach;
let debounceTimer = "";

const eventToOilSelectArea = ($container) => {
    let oilSelectFlag = false;

    const $oilSelect = _$(".oilInfoArea__oilSelect", $container);
    const $oilSelectText = _$(".oilInfoArea__oilSelect > span", $container);
    const $oilSelectImg = _$(".oilInfoArea__oilSelect > img", $container);
    const $oilValues = _$(".oilInfoArea__oilValue", $container);

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
    const $oilSearchValues = _$_ALL(".oilInfoArea__oilSearchValue", $container);

    addEvent($oilSearchInput, [
        () => {
            if(debounceTimer) {
                clearTimeout(debounceTimer);
            }

            if($oilSearchInput.value === "") {
                changeArrayCSS($oilSearchValues, "top", "0");
                changeArrayCSS($oilSearchValues, "borderBottom", "none");
            }
            else {
                debounceTimer = setTimeout(() => {
                    // 검색 관련 통신 함수
                    
                    $oilSearchValues.forEach(($oilSearchValue, index) => {
                        changeCSS($oilSearchValue, "top", `${(index + 1) * 150}%`);
                        changeCSS($oilSearchValue, "borderBottom", "0.2vh solid black");
                    })
                }, 300);
            }
        }
    ], "input")

    addEvent($oilSearchInput, [
        () => changeArrayCSS($oilSearchValues, "top", "0"),
        () => changeArrayCSS($oilSearchValues, "borderBottom", "none")
    ], "focusout");
}

export { eventToOilSelectArea, eventToOilPriceInput, eventToOilSearchInput }