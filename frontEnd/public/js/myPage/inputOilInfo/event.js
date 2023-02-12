import { addEvent, changeArrayCSS, changeCSS } from "../../common/function";
import { animateOilImage, parseOilPriceIntoKorean } from "./helperFunction";

NodeList.prototype.forEach = Array.prototype.forEach;

const eventToOilSelectArea = ($container) => {
    let oilSelectFlag = false;

    const $oilSelect = $container.querySelector(".oilInfoArea__oilSelect");
    const $oilSelectText = $container.querySelector(".oilInfoArea__oilSelect > span");
    const $oilSelectImg = $container.querySelector(".oilInfoArea__oilSelect > img");
    const $oilValues = $container.querySelectorAll(".oilInfoArea__oilValue");

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

export { eventToOilSelectArea, eventToOilPriceInput }