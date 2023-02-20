import { RELEASE_HOST_URL } from "./variable";

NodeList.prototype.forEach = Array.prototype.forEach;

const _$ = (cssSelector, startNode=document) => startNode.querySelector(cssSelector);

const _$_ALL = (cssSelector, startNode=document) => startNode.querySelectorAll(cssSelector);

const addEvent = ($target, callBackArray, eventType="click") => {
    callBackArray.forEach((callBack) => $target.addEventListener(eventType, callBack));
}

const changeCSS = ($target, key, value) => $target.style[key] = value;

const changeArrayCSS = ($targetArray, key, value) => 
    $targetArray.forEach(($target) => changeCSS($target, key, value));

const toggleClass = ($target, className) => $target.classList.toggle(className);

const toggleArrayClass = ($targetArray, className) => 
    $targetArray.forEach(($target) => toggleClass($target, className));

const pipe = (...functionList) => (firstParam) => 
    functionList.reduce((curValue, curFunc) => { 
        return curFunc(curValue);
    }, firstParam);

const makeLighter = ($target) => $target.style.opacity = 1;

const makeNodeArrayLighterSubsequently = (nodeArray, time) => 
    nodeArray.forEach((node, index) => 
        setTimeout(() => makeLighter(node), time * (index + 1)));

const giveErrorStyle = ($target, styleKey, originalStyle, changeStyle, time) => {
    changeCSS($target, styleKey, changeStyle);
    setTimeout(() => {
        changeCSS($target, styleKey, originalStyle);
        $target.focus();
    }, time);
}

const parseNumberToMoneyString = (number) => {
    let parsedString = "";
    let numberToString = String(number);

    for(let index=numberToString.length-1, j=0;index>=0;index--, j += 1) {
        if(j && j % 3 == 0) {
            parsedString += ",";
        }
        parsedString += numberToString[index];
    }

    parsedString = parsedString.split("").reverse().join("");

    if(parsedString[0] === ",") {
        parsedString = parsedString.substring(1, );
    }
    else if(parsedString[0] ==="-" && parsedString[1] === ",") {
        parsedString = parsedString[0] + parsedString.substring(2, );
    }

    return `${parsedString}`;
}

const isReleaseMode = () => location.hostname === RELEASE_HOST_URL;

export {
    _$, _$_ALL,
    addEvent, changeCSS, changeArrayCSS,
    toggleArrayClass,
    pipe, makeLighter,
    makeNodeArrayLighterSubsequently, giveErrorStyle, parseNumberToMoneyString,
    isReleaseMode
}