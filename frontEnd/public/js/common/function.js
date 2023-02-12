NodeList.prototype.forEach = Array.prototype.forEach;

const addEvent = ($target, callBackArray, eventType="click") => {
    callBackArray.forEach((callBack) => $target.addEventListener(eventType, callBack));
}

const changeCSS = ($target, key, value) => $target.style[key] = value;

const changeArrayCSS = ($targetArray, key, value) => 
    $targetArray.forEach(($target) => changeCSS($target, key, value));

const toggleClass = ($target, className) => $target.classList.toggle(className);

const toggleArrayClass = ($targetArray, className) => 
    $targetArray.forEach(($target) => toggleClass($target, className));

const replaceChildWithFadeEffect = ($parent, $child) => {
    $parent.innerHTML = "";
    $parent.appendChild($child);
    $child.style.opacity = 1;
}

const pipe = (...functionList) => (firstParam) => 
    functionList.reduce((curValue, curFunc) => { 
        return curFunc(curValue);
    }, firstParam);

const makeLighter = ($target) => $target.style.opacity = 1;

const makeTransparent = ($target) => $target.style.opacity = 0;

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

    return `${parsedString} 원`;
}

export { 
    addEvent, changeCSS, changeArrayCSS,
    toggleArrayClass,
    replaceChildWithFadeEffect, pipe, makeLighter, makeTransparent,
    makeNodeArrayLighterSubsequently, giveErrorStyle, parseNumberToMoneyString
}