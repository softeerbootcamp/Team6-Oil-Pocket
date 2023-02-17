import { addEvent, changeCSS, _$, _$_ALL } from "../common/function"
import { distanceOption } from "./helperFunction";

NodeList.prototype.forEach = Array.prototype.forEach;

const btnPosMapper = [0, 20, 35, 50, 65, 80, 100];

const changeBtnColor = ($btns, index) => {
    for(let i=0;i<=index;i++) {
        changeCSS($btns[i], "backgroundColor", "#008000")
    }
    for(let i=index+1;i<7;i++) {
        changeCSS($btns[i], "backgroundColor", "#ddd")
    }
}

const eventToSeekBarBtns = ($container) => {
    const $btns = _$_ALL(".adjustBar__infoBox > span", $container);
    const $backGround = _$(".adjustBar__background", $container);

    $btns.forEach(($btn, index) => {
        addEvent($btn, [
            () => changeCSS($backGround, "width", `${btnPosMapper[index]}%`),
            () => changeBtnColor($btns, index),
            () => distanceOption = index + 1,
        ])
    });
}

export { eventToSeekBarBtns }