NodeList.prototype.forEach = Array.prototype.forEach;

const addEvent = ($target, callBackArray, eventType="click") => {
    callBackArray.forEach((callBack) => $target.addEventListener(eventType, callBack));
}

const changeCSS = ($target, key, value) => $target.style[key] = value;

const changeArrayCSS = ($targetArray, key, value) => 
    $targetArray.forEach(($target) => changeCSS($target, key, value));

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

export { 
    addEvent, changeCSS, changeArrayCSS,
    replaceChildWithFadeEffect, pipe, makeLighter, makeTransparent,
    makeNodeArrayLighterSubsequently
}